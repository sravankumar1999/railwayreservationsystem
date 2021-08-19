package com.casestudy.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.model.Train;
import com.casestudy.service.TrainService;

@CrossOrigin(origins="*")
@RestController
public class TrainController {
	
	@Autowired
	private TrainService trainService;
	
	@PostMapping("/addTrains")
	public String addTrains(@RequestBody Train train) {
		trainService.addTrains(train);
		return "train added succesfully";
	}
	
	@GetMapping("/trains")
	public List<Train> getAllTrains() {
		return trainService.getAllTrains();
	}
	
	@GetMapping("/trainSearchByName/{name}")
	public List<Train> getTrainByName(@PathVariable String name) {
		return trainService.getTrainByName(name);
	}
	
	@GetMapping("/trainSearchById/{id}")
	public Optional<Train> getTrainById(@PathVariable String id) {
		return trainService.getTrainById(id);
	}
	
	//cannot convert java.util.date from string using postman
	@GetMapping("/checkTrains/{source}/{destination}")
	public Train checkTrains(@PathVariable("source") String source, @PathVariable("destination") String destination) {
		return trainService.checkTrains(source,destination);
	}
	
//	@DeleteMapping("/removeTrains/{trainId}")
//	public String removeTrain(@PathVariable String trainId) {
//		return trainService.removeTrain(trainId);
//	}
	@DeleteMapping("/removeTrains/{id}")
	public ResponseEntity<?> deleteTrain(@PathVariable("id") String id) {
		trainService.removeTrain(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PutMapping("/updateTrain")
	public void updateTrain(@RequestBody Train train) {
		trainService.updateTrain(train);
	}
	
}
