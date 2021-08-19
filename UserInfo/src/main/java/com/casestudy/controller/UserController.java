package com.casestudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.model.Booking;
import com.casestudy.model.UserInfo;
import com.casestudy.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@PostMapping("/register")
	public String registerUser(@RequestBody UserInfo userInfo) {
		return userService.registerUser(userInfo);
	}
	
	@GetMapping("/showprofile/{username}")
	public UserInfo showProfile(@PathVariable("username") String username) {
		return userService.showProfile(username);
	}
	
	@GetMapping("/getuser/{username}")
	public UserInfo getUser(@PathVariable("username") String username) {
		return userService.getuser(username);
	}
	
	@GetMapping("/myBookings/{username}")
	public List<Booking> showMyBookings(@PathVariable String username){
		return userService.showMyBookings(username);
		
	}
	
	@PostMapping("/updateUser")
	public void updateUser(@RequestBody UserInfo user) {
		userService.updateUser(user);
	}
	
	@DeleteMapping("/removeUser/{username}")
	public String removeUser(@PathVariable String username) {
		return userService.removeUser(username);
	}
}
