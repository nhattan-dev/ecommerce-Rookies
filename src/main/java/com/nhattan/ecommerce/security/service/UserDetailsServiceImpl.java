package com.nhattan.ecommerce.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nhattan.ecommerce.entity.UserEntity;
import com.nhattan.ecommerce.repository.IUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final IUserRepository userRepository;

	public UserDetailsServiceImpl(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		UserEntity user = userRepository.findOneByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + email));

		return UserDetailsImpl.build(user);
	}
}
