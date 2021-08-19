package com.casestudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.model.Booking;
import com.casestudy.service.BookingService;

@CrossOrigin("*")
@RestController
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("/getalltickets")
	public List<Booking> getAllTickets() {
		return bookingService.getAllTickets();
	}
	
	@PostMapping("/reserve")
	public String reserveTicket(@RequestBody Booking book) {
		bookingService.reserveTicket(book);
		return "ticket booked successfully";
	}
	
	@DeleteMapping("/cancel/{pnr}")
	public String cancelTicket(@PathVariable long pnr) {
		bookingService.cancelTicket(pnr);
		return "ticket cancelled";
	}
	
	@GetMapping("/checkPnr/{pnr}")
	public Booking checkPnr(@PathVariable long pnr) {
		return bookingService.checkPnr(pnr);
	}
}
