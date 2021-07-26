package com.nhattan.ecommerce.service.impl;

import com.nhattan.ecommerce.dto.UserDTO;
import com.nhattan.ecommerce.dto.request.CreateUserRequestDTO;
import com.nhattan.ecommerce.dto.request.LoginRequest;
import com.nhattan.ecommerce.entity.CustomerEntity;
import com.nhattan.ecommerce.entity.RoleEntity;
import com.nhattan.ecommerce.entity.UserEntity;
import com.nhattan.ecommerce.enums.ACCOUNT_STATUS;
import com.nhattan.ecommerce.enums.GENDER;
import com.nhattan.ecommerce.enums.ROLE;
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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    @Value("${ecommerce.app.jwtSecret}")
    private String jwtSecret;

    @Value("${ecommerce.app.url}")
    private String url;

    private final ICustomerRepository customerRepository;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder encoder;

    private final IUserRepository userRepository;

    private final IRoleRepository roleRepository;

    private final JwtUtils jwtService;

    private final JavaMailSender emailSender;

    @Override
    public String login(LoginRequest login) {
        checkAccountAvailable(login.getEmail());
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtService.generateJwtToken(authentication);
    }

    @Transactional
    @Override
    public UserDTO register(CreateUserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new ConflictException("email-already-used");
        checkGenderValid(dto.getGender());
        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer = customerRepository.save(newCustomer);
        String user_role = ROLE.ROLE_USER.name();
        RoleEntity role = findRoleByRole(user_role);
        UserEntity newUser = CreateUserRequestDTO.toEntity(dto);
        newUser.setCustomer(newCustomer);
        newUser.setRole(role);
        newUser.setPassword(encoder.encode(dto.getPassword()));
        String OTP = OTPUtils.makeOTP();
        newUser.setOTP(OTP);
        newUser = userRepository.save(newUser);
        sendEmail(dto.getEmail(), dto.getPassword());
        return UserDTO.toDTO(newUser);
    }

    @Transactional
    @Override
    public String verify(String token) {
        String email = getEmailFromToken(token);
        userRepository.updateStatusByEmail(ACCOUNT_STATUS.VERIFIED.name(), email);
        return "successfully";
    }

    @Transactional
    @Override
    public UserDTO createUser(CreateUserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new ConflictException("email-already-used");
        checkGenderValid(dto.getGender());
        String user_role = ROLE.ROLE_ADMIN.name();
        RoleEntity role = findRoleByRole(user_role);
        UserEntity newUser = CreateUserRequestDTO.toEntity(dto);
        newUser.setRole(role);
        newUser.setPassword(encoder.encode(dto.getPassword()));
        newUser.setOTP(OTPUtils.makeOTP());
        return UserDTO.toDTO(userRepository.save(newUser));
    }

    @Override
    public List<UserDTO> showAll() {
        return userRepository.findByRole_Role(ROLE.ROLE_ADMIN.name()).stream().map(x -> UserDTO.toDTO(x))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO dto, String token) {
        String email = getEmailFromToken(token);
        checkAccountAvailable(email);
//        checkEmailAlreadyUsed(dto.getEmail(), dto.getUserID());
        userRepository.updateByEmail(dto.getPhoneNumber(), dto.getFirstName(),
                dto.getLastName(), dto.getGender(), dto.getDateOfBirth(), email);
        return UserDTO.toDTO(userRepository.findByEmail(email).get());
    }

    @Transactional
    @Override
    public void invalidateUser(String token) {
        String email = getEmailFromToken(token);
        userRepository.deletedByEmail(email);
    }

    @Transactional
    @Override
    public void invalidateUserByUserID(String token, int userID) {
        userRepository.deletedByUserID(userID);
    }

    @Transactional
    @Override
    public void changePassword(LoginRequest login, String token) {
        if (userRepository.findOneByEmail(getEmailFromToken(token)) == null)
            throw new NotFoundException("email-not-found");
        String email = getEmailFromToken(token);
        checkAccountAvailable(email);
        userRepository.updatePasswordByEmail(email, encoder.encode(login.getPassword()));
    }

    @Override
    public UserDTO showOneUser(String token) {
        String email = getEmailFromToken(token);
        checkAccountAvailable(email);
        UserEntity user = userRepository.findOneByEmail(email)
                .orElseThrow(() -> new NotFoundException("email-not-found"));
        System.out.println(user.getFirstName());
        return UserDTO.toDTO(user);
    }

    private void checkAccountAvailable(String email) {
        if (!userRepository.existsByEmailAndStatus(email, ACCOUNT_STATUS.VERIFIED.name()))
            throw new BadRequestException("unverified-account-or-not-available");
    }

    private void checkUserExists(int userID) {
        if (!userRepository.existsById(userID))
            throw new NotFoundException("user-not-found");
    }

    private void checkEmailAlreadyUsed(String email, int userID) {
        if (userRepository.findOneByEmailAndUserIDNot(email, userID) != null)
            throw new ConflictException("email-already-used");
    }

    private String getEmailFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    private RoleEntity findRoleByRole(String user_role) {
        return roleRepository.findOneByRole(user_role).orElseThrow(() -> new ConflictException("can-create-user-.-role-not-found"));
    }

    private void checkGenderValid(String gender) {
        if (!(gender.equalsIgnoreCase(GENDER.MALE.name()) || gender.equalsIgnoreCase(GENDER.FEMALE.name()) || gender.equalsIgnoreCase(GENDER.OTHER.name())))
            throw new ConflictException("gender-not-valid");
    }

    private void sendEmail(String receiverEmail, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(receiverEmail, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String content = url + "/account/verify?token=" + jwtService.generateVerifyJwtToken(authentication);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiverEmail);
        message.setSubject("Confirm Account");
        message.setText(content);
        this.emailSender.send(message);
    }
}
