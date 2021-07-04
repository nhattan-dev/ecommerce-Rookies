package com.nhattan.ecommerce.security.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhattan.ecommerce.entity.UserEntity;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private int userID;
	private String email;
	@JsonIgnore
	private String password;
	private String phoneNumber;
	private String firstName;
	private String lastName;
	private int gender;
	private int valid;
	private int deleted;
	private Date dateOfBirth;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(int userID, String email, String password, String phoneNumber, String firstName,
			String lastName, int gender, int valid, int deleted, Date dateOfBirth,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.userID = userID;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.valid = valid;
		this.deleted = deleted;
		this.dateOfBirth = dateOfBirth;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(UserEntity user) {
		List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole().getRole()));

		return new UserDetailsImpl(user.getUserID(), user.getEmail(), user.getPassword(), user.getPhoneNumber(),
				user.getFirstName(), user.getLastName(), user.getGender(), user.getValid(), user.getDeleted(),
				user.getDateOfBirth(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(userID, user.getUserID());
	}
}