package com.nhattan.ecommerce.service.impl;

import com.nhattan.ecommerce.dto.*;
import com.nhattan.ecommerce.entity.*;
import com.nhattan.ecommerce.enums.ACCOUNT_STATUS;
import com.nhattan.ecommerce.enums.ORDER_STATUS;
import com.nhattan.ecommerce.enums.STATUS;
import com.nhattan.ecommerce.exception.BadRequestException;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.*;
import com.nhattan.ecommerce.service.ICustomerService;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<AddressDTO> showAddress(String token) {
        int customerID = getCustomerFromToken(token).getCustomerID();
        return addressRepository.findByCustomerID(customerID).stream().map(AddressDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO showOneAddress(int addressID, String token) {
        int customerID = getCustomerFromToken(token).getCustomerID();
        AddressEntity entity = addressRepository.findOneByAddressIDAndCustomerID(addressID, customerID).orElseThrow(() -> new NotFoundException("address-not-found"));
        return AddressDTO.toDTO(entity);
    }

    @Override
    public AddressDTO createAddress(AddressDTO dto, String token) {
        CustomerEntity customer = getCustomerFromToken(token);
        AddressEntity entity = AddressDTO.toEntity(dto);
        int defaultAddressID = 0;
        entity.setAddressID(defaultAddressID);
        entity.setCustomer(customer);
        return AddressDTO.toDTO(addressRepository.save(entity));
    }

    @Transactional
    @Override
    public AddressDTO updateAddress(AddressDTO dto, String token) {
        CustomerEntity customer = getCustomerFromToken(token);
        addressRepository.findOneByAddressIDAndCustomerID(dto.getAddressID(), customer.getCustomerID())
                .orElseThrow(() -> new NotFoundException("address-not-found"));
        addressRepository.updateAddressByAddressID(dto.getFullname(), dto.getAddress(), dto.getPhoneNumber()
                , dto.getAddressID());
        return AddressDTO.toDTO(addressRepository.findById(dto.getAddressID()).get());
    }

    @Transactional
    @Override
    public void deleteAddress(int addressID, String token) {
        CustomerEntity customer = getCustomerFromToken(token);
        addressRepository.findOneByAddressIDAndCustomerID(addressID, customer.getCustomerID()).orElseThrow(() -> new NotFoundException("address-not-found"));
        addressRepository.deleteById(addressID);
    }

    @Override
    public List<OrderDTO> showOrder(String token) {
        int customerID = getCustomerFromToken(token).getCustomerID();
        return orderRepository.findOrdersByCustomerID(customerID).stream().map(OrderDTO::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO showOneOrder(int orderID, String token) {
        int customerID = getCustomerFromToken(token).getCustomerID();
        OrderEntity order = orderRepository.findOneByOrderIDAndCustomerID(orderID, customerID)
                .orElseThrow(() -> new NotFoundException("order-not-found"));
        return OrderDTO.toDTO(order);
    }

    @Transactional
    @Override
    public OrderDTO saveOrder(OrderDTO dto, String token) {
        int defaultID = 0;
        CustomerEntity customer = getCustomerFromToken(token);
        if (dto.getOrderDetails().size() == 0)
            throw new BadRequestException("order-has-no-product");
        if (!addressRepository.existsByAddressIDAndCustomer_CustomerID(dto.getAddressID(), customer.getCustomerID()))
            throw new NotFoundException("address-not-found");
        OrderEntity newOrder = OrderDTO.toEntity(dto);
        newOrder.setCustomer(customer);
        newOrder.setOrderCode(generatorOrderCode());
        newOrder.setOrderID(defaultID);
        OrderEntity order = orderRepository.save(newOrder);
        dto.getOrderDetails().forEach(x -> saveOrderDetail(x, order, defaultID));
        return OrderDTO.toDTO(order);
    }

    private void saveOrderDetail(OrderDetailDTO x, OrderEntity order, int defaultID){
        ProductEntity product = productRepository.findByStatusAndProductID(STATUS.AVAILABLE.name(), x.getProductID())
                .orElseThrow(() -> new NotFoundException("product-not-found"));
        if (product.getQuantity() < x.getQuantity())
            throw new ConflictException("quantity-is-larger-than-available-product");
        // update quantity of product
        int newQuantity = product.getQuantity() - x.getQuantity();
        productRepository.updateQuantityByProductID(newQuantity, x.getProductID());
        if (!productColorRepository.existsByColorAndProduct_ProductID(x.getColor(), x.getProductID()))
            throw new NotFoundException("color-not-found");
        if (!productSizeRepository.existsBySizeAndProduct_ProductID(x.getSize(), x.getProductID()))
            throw new NotFoundException("size-not-found");
        int latestPrice = productPriceRepository.getLatestPriceByProductID(x.getProductID());
        OrderDetailEntity newOrderDetail = OrderDetailDTO.toEntity(x);
        newOrderDetail.setOrder(order);
        newOrderDetail.setPrice(latestPrice);
        newOrderDetail.setOrderDetailID(defaultID);
        orderDetailRepository.save(newOrderDetail);
    }

    @Transactional
    @Override
    public String deleteOrder(int orderID, String token) {
        int customerID = getCustomerFromToken(token).getCustomerID();
        OrderEntity order = orderRepository.findOneByOrderIDAndCustomerID(orderID, customerID)
                .orElseThrow(() -> new NotFoundException("order-not-found"));
        if (order.getTransactStatus().equals(ORDER_STATUS.UNCONFIRMED.name()))
            throw new ConflictException("order-confirmed");

        order.getOrderDetails().forEach(x -> {
            ProductEntity product = x.getProduct();
            int newQuantity = product.getQuantity() + x.getQuantity();
            productRepository.updateQuantityByProductID(newQuantity, product.getProductID());
        });

        orderRepository.deleteById(orderID);
        return "success";
    }

    @Transactional
    @Override
    public String ratingProduct(ProductRatingDTO dto, String token) {
        ProductEntity product = productRepository.findOneAvailable(dto.getProductID())
                .orElseThrow(() -> new NotFoundException("product-not-found"));
        CustomerEntity customer = getCustomerFromToken(token);
        if (orderDetailRepository.existsByProduct_ProductIDAndOrder_Customer_CustomerID(dto.getProductID()
                , dto.getCustomerID()))
            throw new BadRequestException("customer-has-never-buy");
        ProductRatingEntity oldRating = productRatingRepository
                .findOneByCustomerIDAndProductID(dto.getCustomerID(), dto.getProductID());
        if (oldRating != null) {
            oldRating.setPoint(dto.getPoint());
            productRatingRepository.save(oldRating);
        } else {
            ProductRatingEntity newRating = new ProductRatingEntity();
            newRating.setCustomer(customer);
            newRating.setPoint(dto.getPoint());
            newRating.setProduct(product);
            productRatingRepository.save(newRating);
        }
        // update point
        productRepository.updateProductPointByProductID(dto.getProductID());
        return "success";
    }

    private CustomerEntity getCustomerFromToken(String token) {
        String email = getEmailFromToken(token);
        return getCustomerFromEmail(email);
    }

    private CustomerEntity getCustomerFromEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("user-not-found"));
        if (user.getStatus().equalsIgnoreCase(ACCOUNT_STATUS.UNVERIFIED.name()))
            throw new BadRequestException("unverified-account");
        if (user.getStatus().equalsIgnoreCase(ACCOUNT_STATUS.NOT_AVAILABLE.name()))
            throw new BadRequestException("account-has-been-deleted");
        if (user.getCustomer() == null)
            throw new NotFoundException("customer-not-found");
        return user.getCustomer();
    }

    private String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    private AddressEntity getAddress(int addressID, int customerID) {
        return addressRepository.findOneByAddressIDAndCustomerID(addressID, customerID)
                .orElseThrow(() -> new NotFoundException("address-not-found"));
    }

    private String generatorOrderCode() {
        int max = orderRepository.getMaxOrderCode();
        return prefix + (String.format("%09d", max + 1));
    }
}
