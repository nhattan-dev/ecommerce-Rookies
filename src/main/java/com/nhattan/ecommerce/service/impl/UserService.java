package com.nhattan.ecommerce.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhattan.ecommerce.dto.UserDTO;
import com.nhattan.ecommerce.dto.request.CreateUserRequest;
import com.nhattan.ecommerce.dto.request.LoginRequest;
import com.nhattan.ecommerce.entity.CustomerEntity;
import com.nhattan.ecommerce.entity.RoleEntity;
import com.nhattan.ecommerce.entity.UserEntity;
import com.nhattan.ecommerce.exception.BadRequestException;
import com.nhattan.ecommerce.exception.ConflictException;
import com.nhattan.ecommerce.exception.NotFoundException;
import com.nhattan.ecommerce.repository.ICustomerRepository;
import com.nhattan.ecommerce.repository.IRoleRepository;
import com.nhattan.ecommerce.repository.IUserRepository;
import com.nhattan.ecommerce.security.jwt.JwtUtils;
import com.nhattan.ecommerce.service.IUserService;
import com.nhattan.ecommerce.util.OTPUtils;

import io.jsonwebtoken.Jwts;

@Service
public class UserService implements IUserService {

	@Value("${ecommerce.app.jwtSecret}")
	private String jwtSecret;

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
	public String login(LoginRequest login) {
		checkAccountVerification(login.getEmail());
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwtService.generateJwtToken(authentication);
	}

	@Transactional
	@Override
	public UserDTO register(CreateUserRequest userRequest) {
		if (userRepository.existsByEmail(userRequest.getEmail()))
			throw new ConflictException("email-already-used");

		CustomerEntity newCustomer = new CustomerEntity();
		newCustomer = customerRepository.save(newCustomer);

		String user_role = "ROLE_USER";
		RoleEntity role = roleRepository.findOneByRole(user_role);
		if (role == null)
			throw new ConflictException("can-create-user-role-not-found");

		UserEntity newUser = CreateUserRequest.toEntity(userRequest);
		newUser.setCustomer(newCustomer);
		newUser.setRole(role);
		newUser.setPassword(encoder.encode(userRequest.getPassword()));
		newUser.setOTP(OTPUtils.makeOTP());

		newUser = userRepository.save(newUser);
		return modelMapper.map(newUser, UserDTO.class);
	}

	@Override
	public List<UserDTO> showAll() {
		System.out.println("show all user");
		return userRepository.findAll().stream().map(x -> modelMapper.map(x, UserDTO.class))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public UserDTO updateUser(UserDTO userRequest, String token) {
		checkUserExists(userRequest.getUserID());
		checkAccountVerification(userRequest.getEmail());
		checkEmailAlreadyUsed(userRequest.getEmail(), userRequest.getUserID());

		String dateOfBirth = new SimpleDateFormat("yyyy-MM-dd").format(userRequest.getDateOfBirth());

		userRepository.updateByUserID(userRequest.getEmail(), userRequest.getPhoneNumber(), userRequest.getFirstName(),
				userRequest.getLastName(), userRequest.getGender(), dateOfBirth, userRequest.getUserID());

		return modelMapper.map(userRepository.findOne(userRequest.getUserID()), UserDTO.class);
	}

	@Transactional
	@Override
	public void invalidateUser(String token) {
		String email = getEmailFromToken(token);
		userRepository.deletedByEmail(email);
	}

	@Transactional
	@Override
	public void changePassword(LoginRequest login, String token) {
		if (userRepository.findOneByEmail(getEmailFromToken(token)) == null)
			throw new NotFoundException("email-not-found");
		String email = getEmailFromToken(token);
		checkAccountVerification(email);
		userRepository.updatePasswordByEmail(email, encoder.encode(login.getPassword()));
	}

	@Override
	public UserDTO showOneUser(String token) {
		String email = getEmailFromToken(token);
		UserEntity user = userRepository.findOneByEmail(email)
				.orElseThrow(() -> new NotFoundException("email-not-found"));
		return modelMapper.map(user, UserDTO.class);
	}

	private void checkAccountVerification(String email) {
		int notValidValue = 1;
		if (userRepository.existsByEmailAndValid(email, notValidValue))
			throw new BadRequestException("unverified-account");
	}

	private void checkUserExists(int userID) {
		if (!userRepository.exists(userID))
			throw new NotFoundException("user-not-found");
	}

	private void checkEmailAlreadyUsed(String email, int userID) {
		if (userRepository.findOneByEmailAndUserIDNot(email, userID) != null)
			throw new ConflictException("email-already-used");
	}

	private String getEmailFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}
}
