package com.casestudy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.model.Booking;
import com.casestudy.model.UserInfo;
import com.casestudy.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	public UserRepository userRepository;
	
	
	public String registerUser(UserInfo userInfo) {
		userRepository.save(userInfo);
		return "user registered successfully";
	}


	public UserInfo showProfile(String username) {
		Optional<UserInfo> user = userRepository.findById(username);
		return user.get();
	}


	public UserInfo getuser(String username) {
		Optional<UserInfo> user = userRepository.findById(username);
		return user.get();
	}


	public List<Booking> showMyBookings(String username) {
		Optional<UserInfo> userOpt = userRepository.findById(username);
		UserInfo user = userOpt.get();
		List<Booking> tickets = user.getBookings();
		return tickets;
	}


	public void updateUser(UserInfo user) {
		userRepository.save(user);	
		
	}


	public String removeUser(String username) {
		userRepository.deleteById(username);
		return "user deleted successfully";
	}

}
