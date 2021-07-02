package com.nhattan.ecommerce.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhattan.ecommerce.entity.CustomerEntity;
import com.nhattan.ecommerce.entity.RoleEntity;
import com.nhattan.ecommerce.entity.UserEntity;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.ICustomerRepository;
import com.nhattan.ecommerce.repository.IRoleRepository;
import com.nhattan.ecommerce.repository.IUserRepository;
import com.nhattan.ecommerce.request.CreateUserRequest;
import com.nhattan.ecommerce.request.UpdateUserRequest;
import com.nhattan.ecommerce.response.CreateUserResponse;
import com.nhattan.ecommerce.response.ReadUserResponse;
import com.nhattan.ecommerce.response.UpdateUserResponse;
import com.nhattan.ecommerce.security.jwt.JwtUtils;
import com.nhattan.ecommerce.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private ICustomerRepository customerRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JwtUtils jwtService;

	@Override
	public UserEntity findByEmail(String email) {
		UserEntity user = userRepository.findOneByEmail(email);
		if (user == null)
			throw new NotFoundException("email-not-found");
		return user;
	}

	@Override
	public String login(String username, String password) {
//		int NotDeletedValue = 0;
//		System.out.println(encoder.encode(password));
//		if (userRepository.findOneByEmailAndPasswordAndDeleted(username, encoder.encode(password),
//				NotDeletedValue) == null)
//			throw new NotFoundException("email-or-password-maybe-wrong");

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		// if go there, the user/password is correct
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return jwtService.generateJwtToken(authentication);
//		return null;
	}

	@Transactional
	@Override
	public CreateUserResponse register(CreateUserRequest userRequest) {
		if (userRepository.findOneByEmail(userRequest.getEmail()) != null)
			throw new ConflictException("email-already-used");

		CustomerEntity newCustomer = new CustomerEntity();
		newCustomer = customerRepository.save(newCustomer);

		String user_role = "ROLE_USER";
		RoleEntity role = roleRepository.findByRoleLike(user_role);
		if (role == null)
			throw new ConflictException("can-create-user-role-not-found");

		UserEntity newUser = modelMapper.map(userRequest, UserEntity.class);
		newUser.setCustomer(newCustomer);
		newUser.setRole(role);
		newUser.setPassword(encoder.encode(userRequest.getPassword()));

		newUser = userRepository.save(newUser);
		CreateUserResponse newUserResponse = modelMapper.map(newUser, CreateUserResponse.class);
		return newUserResponse;
	}

	@Override
	public List<ReadUserResponse> showAll() {
		return userRepository.findAll().stream().map(x -> modelMapper.map(x, ReadUserResponse.class))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public UpdateUserResponse update(UpdateUserRequest userRequest) {
		if (userRepository.exists(userRequest.getUserID()))
			throw new NotFoundException("user-not-found");
		if (userRepository.findOneByEmailAndUserIDNot(userRequest.getEmail(), userRequest.getUserID()) != null)
			throw new ConflictException("email-already-used");

		String dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").format(userRequest.getDateOfBirth());

		userRepository.updateByUserID(userRequest.getEmail(), userRequest.getPhoneNumber(), userRequest.getFirstName(),
				userRequest.getLastName(), userRequest.getGender(), dateOfBirth, userRequest.getUserID());

		return modelMapper.map(userRepository.findOne(userRequest.getUserID()), UpdateUserResponse.class);
	}

	@Transactional
	@Override
	public void delete(int userID) {
		if (!userRepository.exists(userID))
			throw new NotFoundException("user-not-found");
		userRepository.deletedByUserID(userID);
	}

	@Transactional
	@Override
	public void changePassword(String email, String password) {
		if (userRepository.findOneByEmail(email) == null)
			throw new NotFoundException("email-not-found");
		userRepository.updatePasswordByEmail(email, password);
	}
}
