package com.nhattan.ecommerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhattan.ecommerce.dto.AddressDTO;
import com.nhattan.ecommerce.dto.OrderDTO;
import com.nhattan.ecommerce.dto.ProductRatingDTO;
import com.nhattan.ecommerce.entity.AddressEntity;
import com.nhattan.ecommerce.entity.CustomerEntity;
import com.nhattan.ecommerce.entity.OrderDetailEntity;
import com.nhattan.ecommerce.entity.OrderEntity;
import com.nhattan.ecommerce.entity.ProductColorEntity;
import com.nhattan.ecommerce.entity.ProductEntity;
import com.nhattan.ecommerce.entity.ProductRatingEntity;
import com.nhattan.ecommerce.entity.ProductSizeEntity;
import com.nhattan.ecommerce.entity.UserEntity;
import com.nhattan.ecommerce.exception.BadRequestException;
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
import com.nhattan.ecommerce.repository.IUserRepository;
import com.nhattan.ecommerce.service.ICustomerService;

import io.jsonwebtoken.Jwts;

@Service
public class CustomerService implements ICustomerService {
	private final static String prefix = "OC";

	@Value("${ecommerce.app.jwtSecret}")
	private String jwtSecret;

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
	private IUserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<AddressDTO> showAddressByCustomer(String token) {
		int customerID = getCustomerFromToken(token).getCustomerID();
		return addressRepository.findByCustomerID(customerID).stream().map(x -> modelMapper.map(x, AddressDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> showOrderByCustomer(String token) {
		int customerID = getCustomerFromToken(token).getCustomerID();
		return orderRepository.findOrdersByCustomerID(customerID).stream().map(x -> modelMapper.map(x, OrderDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public OrderDTO showOneOrder(int orderID, String token) {
		int customerID = getCustomerFromToken(token).getCustomerID();
		OrderEntity order = orderRepository.findOneByOrderIDAndCustomerID(orderID, customerID)
				.orElseThrow(() -> new NotFoundException("order-not-found"));
		return modelMapper.map(order, OrderDTO.class);
	}

	@Transactional
	@Override
	public String saveOrder(OrderDTO orderRequest, String token) {
		CustomerEntity customer = getCustomerFromToken(token);

		if (orderRequest.getOrderDetails().size() == 0)
			throw new ConflictException("bad-order");

		OrderEntity newOrder = new OrderEntity();
		newOrder.setCustomer(customer);
		newOrder.setAddress(getAddress(orderRequest.getAddressID(), customer.getCustomerID()));
		newOrder.setOrderCode(generatorOrderCode());
		int newOrderID = orderRepository.save(newOrder).getOrderID();

		orderRequest.getOrderDetails().forEach(x -> {
			int NotDeleteValue = 0;
			ProductEntity product = productRepository.findProductValidByProductID(NotDeleteValue, x.getProductID())
					.orElseThrow(() -> new NotFoundException("product-not-found"));

			if (product.getQuantity() < x.getQuantity())
				throw new ConflictException("bad-quantity");

			ProductColorEntity color = productColorRepository.findOneByColorAndProductID(x.getColor(), x.getProductID())
					.orElseThrow(() -> new NotFoundException("color-not-found"));

			ProductSizeEntity size = productSizeRepository.findOneBySizeAndProductID(x.getSize(), x.getProductID())
					.orElseThrow(() -> new NotFoundException("color-not-found"));

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
	public String invalidateOrder(int orderID, String token) {
		int customerID = getCustomerFromToken(token).getCustomerID();
		OrderEntity order = orderRepository.findOneByOrderIDAndCustomerID(orderID, customerID)
				.orElseThrow(() -> new NotFoundException("order-not-found"));
		if (order.getTransactStatus() == 0)
			throw new ConflictException("order-confirmed");

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
	public String ratingProduct(ProductRatingDTO productRating, String token) {
		int MaxPoint = 10, MinPoint = 1;
		if (productRating.getPoint() < MinPoint || productRating.getPoint() > MaxPoint)
			throw new ConflictException("point-must-be-between-1-and-10");

		ProductEntity product = productRepository.findOne(productRating.getProductID());
		if (product == null)
			throw new NotFoundException("product-not-found");

		CustomerEntity customer = getCustomerFromToken(token);

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

	private CustomerEntity getCustomerFromToken(String token) {
		String email = getEmailFromToken(token);
		return getCustomerFromEmail(email);
	}

	private CustomerEntity getCustomerFromEmail(String email) {
		UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("user-not-found"));
		int unconfirmedValue = 1;
		if (user.getValid() == unconfirmedValue)
			throw new BadRequestException("unverified-account");
		int deletedValue = 1;
		if (user.getDeleted() == deletedValue)
			throw new BadRequestException("account-has-been-deleted");
		if (user.getCustomer() == null)
			throw new NotFoundException("customer-not-found");
		return user.getCustomer();
	}

	private String getEmailFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	private AddressEntity getAddress(int addressID, int customerID) {
		AddressEntity address = addressRepository.findOneByAddressIDAndCustomerID(addressID, customerID)
				.orElseThrow(() -> new NotFoundException("address-not-found"));
		return address;
	}

	private String generatorOrderCode() {
		int max = orderRepository.getMaxOrderCode();
		return prefix + (String.format("%09d", max + 1));
	}

//	private void checkAccountVerification(int customerID) {
//		int notValidValue = 1;
//		if (customerRepository.findByAvailableUser(customerID, notValidValue).isPresent())
//			throw new BadRequestException("unverified-account");
//	}
//	
//	private void checkAccountValidity(String email) {
//		int notValidValue = 0;
//		if (userRepository.findByEmailAndDeletedNot(email, notValidValue).isPresent())
//			throw new BadRequestException("unverified-account");
//	}
//	
//	private void getCustomerIDFromEmail(String email) {
//		int notValidValue = 0;
//		if (userRepository.findByEmailAndDeletedNot(email, notValidValue).isPresent())
//			throw new BadRequestException("unverified-account");
//	}
}
