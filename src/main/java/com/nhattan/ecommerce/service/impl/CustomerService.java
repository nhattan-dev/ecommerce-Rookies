package com.nhattan.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhattan.ecommerce.dto.ProductRatingDTO;
import com.nhattan.ecommerce.entity.CustomerEntity;
import com.nhattan.ecommerce.entity.OrderDetailEntity;
import com.nhattan.ecommerce.entity.OrderEntity;
import com.nhattan.ecommerce.entity.ProductColorEntity;
import com.nhattan.ecommerce.entity.ProductEntity;
import com.nhattan.ecommerce.entity.ProductRatingEntity;
import com.nhattan.ecommerce.entity.ProductSizeEntity;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.IAddressRepository;
import com.nhattan.ecommerce.repository.ICustomerRepository;
import com.nhattan.ecommerce.repository.IOrderDetailRepository;
import com.nhattan.ecommerce.repository.IOrderRepository;
import com.nhattan.ecommerce.repository.IProductColorRepository;
import com.nhattan.ecommerce.repository.IProductPriceRepository;
import com.nhattan.ecommerce.repository.IProductRatingRepository;
import com.nhattan.ecommerce.repository.IProductRepository;
import com.nhattan.ecommerce.repository.IProductSizeRepository;
import com.nhattan.ecommerce.request.CreateOrderRequest;
import com.nhattan.ecommerce.response.ReadAddressResponse;
import com.nhattan.ecommerce.response.ReadOrderResponse;
import com.nhattan.ecommerce.service.ICustomerService;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private IAddressRepository addressRepository;

	@Autowired
	private IOrderRepository orderRepository;

	@Autowired
	private IOrderDetailRepository orderDetailRepository;

	@Autowired
	private IProductRepository productRepository;

	@Autowired
	private IProductSizeRepository productSizeRepository;

	@Autowired
	private IProductColorRepository productColorRepository;

	@Autowired
	private IProductPriceRepository productPriceRepository;

	@Autowired
	private IProductRatingRepository productRatingRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ReadAddressResponse> showAddressByCustomerID(int customerID) {
		return addressRepository.findByCustomerID(customerID).stream()
				.map(x -> modelMapper.map(x, ReadAddressResponse.class)).collect(Collectors.toList());
	}

	@Override
	public List<ReadOrderResponse> showOrderByCustomerID(int customerID) {
		return orderRepository.findOrdersByCustomerID(customerID).stream()
				.map(x -> modelMapper.map(x, ReadOrderResponse.class)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public String saveOrder(CreateOrderRequest orderRequest) {
		CustomerEntity customer = customerRepository.findOne(orderRequest.getCustomerID());
		if (customer == null)
			throw new NotFoundException("customer-not-found");

		if (orderRequest.getOrderDetails().size() == 0)
			throw new ConflictException("bad-order");

		OrderEntity newOrder = new OrderEntity();
		newOrder.setCustomer(customer);
		int newOrderID = orderRepository.save(newOrder).getOrderID();

		orderRequest.getOrderDetails().forEach(x -> {
			int NotDeleteValue = 0;
			ProductEntity product = productRepository.findProductValidByProductID(NotDeleteValue, x.getProductID());
			if (product == null)
				throw new NotFoundException("product-not-found");

			if (product.getQuantity() < x.getQuantity())
				throw new ConflictException("bad-quantity");

			ProductColorEntity color = productColorRepository.findOne(x.getProductColorID());
			if (color == null)
				throw new NotFoundException("color-not-found");

			ProductSizeEntity size = productSizeRepository.findOne(x.getProductSizeID());
			if (size == null)
				throw new NotFoundException("size-not-found");

			int latestPrice = productPriceRepository.getLatestPriceByProductID(x.getProductID());

			OrderDetailEntity newOrderDetail = new OrderDetailEntity();
			newOrderDetail.setOrder(new OrderEntity(newOrderID));
			newOrderDetail.setProduct(product);
			newOrderDetail.setColor(color.getColor());
			newOrderDetail.setSize(size.getSize());
			newOrderDetail.setPrice(latestPrice);
			newOrderDetail.setQuantity(x.getQuantity());
			orderDetailRepository.save(newOrderDetail);

			// update quantity of product
			int newQuantity = product.getQuantity() - x.getQuantity();
			productRepository.updateQuantityByProductID(newQuantity, x.getProductID());
		});
		return "success";
	}

	@Transactional
	@Override
	public String deleteOrder(int orderID) {
		OrderEntity order = orderRepository.findOne(orderID);
		if (order == null)
			throw new NotFoundException("order-not-found");

		order.getOrderDetails().forEach(x -> {
			ProductEntity product = x.getProduct();
			int newQuantity = product.getQuantity() + x.getQuantity();
			productRepository.updateQuantityByProductID(newQuantity, product.getProductID());
		});

		orderRepository.delete(orderID);
		return "success";
	}

	@Transactional
	@Override
	public String ratingProduct(ProductRatingDTO productRating) {
		int MaxPoint = 10, MinPoint = 1;
		if (productRating.getPoint() < MinPoint || productRating.getPoint() > MaxPoint)
			throw new ConflictException("point-must-be-between-1-and-10");

		ProductEntity product = productRepository.findOne(productRating.getProductID());
		if (product == null)
			throw new NotFoundException("product-not-found");

		CustomerEntity customer = customerRepository.findOne(productRating.getCustomerID());
		if (customer == null)
			throw new NotFoundException("customer-not-found");

		if (orderDetailRepository.findOneByProductIDAndCustomerID(productRating.getProductID(),
				productRating.getCustomerID()) == null)
			throw new ConflictException("customer-has-never-buy");

		ProductRatingEntity oldRating = productRatingRepository
				.findOneByCustomerIDAndProductID(productRating.getCustomerID(), productRating.getProductID());
		if (oldRating != null) {
			oldRating.setPoint(productRating.getPoint());
			productRatingRepository.save(oldRating);
		} else {
			ProductRatingEntity newRating = new ProductRatingEntity();
			newRating.setCustomer(customer);
			newRating.setPoint(productRating.getPoint());
			newRating.setProduct(product);
			productRatingRepository.save(newRating);
		}
		// update point
		productRepository.updateProductPointByProductID(productRating.getProductID());
		return "success";
	}
}
