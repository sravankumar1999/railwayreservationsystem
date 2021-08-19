package com.casestudy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.casestudy.model.Booking;
import com.casestudy.model.Train;
import com.casestudy.model.UserInfo;
import com.casestudy.repository.BookingRepository;


@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	//private Train train;

	public List<Booking> getAllTickets() {
		return bookingRepository.findAll();
	}

	public Booking reserveTicket(Booking book) {
		book.setPnr(sequenceGeneratorService.generateSequence(Booking.SEQUENCE_NAME));
		bookingRepository.save(book);
		updateSeats(book);
		//long var = book.getTrainId();
		//int var2 =var.getGeneralFare();
		//book.setTotalFare(book.getSeats()*(train.getGeneralFare(var)));
		
	//	updateMyBookings(book);
		return book;
	}
	
	public void cancelTicket(long pnr) {
		cancelSeats(pnr);
		cancelMyBookings(pnr);
		bookingRepository.deleteById(pnr);
	}

	public void updateSeats(Booking book) {
		
		Train train = restTemplate.getForObject("http://localhost:9100/trainSearchById/"+ book.getTrainId(), Train.class);
		train.setSeatsLeft(train.getSeatsLeft() - book.getSeats());
		restTemplate.put("http://localhost:9100/updateTrain/", train);			     
	}
	
//	public void updateMyBookings(Booking book) {
//		UserInfo user = restTemplate.getForObject("http://localhost:9101/getuser/" + book.getUsername(), UserInfo.class);
	//	List<Booking> ticket = user.getBookings();
	//	ticket.add(book);
	//	user.setBookings(ticket);
	//	restTemplate.put("http://localhost:9101/updateUser", user);
//	}
	
	public void cancelMyBookings(long pnr) {
	bookingRepository.deleteById(pnr);
	}
	
	public void cancelSeats(long pnr) {

		Booking book = bookingRepository.findByPnr(pnr);
		
		Train train = restTemplate.getForObject("http://localhost:9100/trainSearchById/"+ book.getTrainId(), Train.class);
		train.setSeatsLeft(train.getSeatsLeft() + book.getSeats());
		restTemplate.put("http://localhost:9100/updateTrain/", train);
    
	}
	
	public Booking checkPnr(long pnr) {
		return bookingRepository.findByPnr(pnr);
	}
	
}
