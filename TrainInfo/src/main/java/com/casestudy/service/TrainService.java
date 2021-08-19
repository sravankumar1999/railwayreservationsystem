package com.casestudy.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.casestudy.model.Train;
import com.casestudy.repository.TrainRepository;


@Service
@Transactional
public class TrainService {
	
	@Autowired
	public TrainRepository trainRepository;

	public List<Train> getAllTrains() {
		return trainRepository.findAll();
	}

	public void addTrains(Train train) {
		trainRepository.save(train);
		
	}

	public List<Train> getTrainByName(String name) {
		return trainRepository.findAllByName(name);
		
	}

	public Optional<Train> getTrainById(String id) {
		return trainRepository.findById(id);
	}

	public Train checkTrains(String source, String destination) {
		return trainRepository.findAllBySourceAndDestination(source,destination);
	}

//	public String removeTrain(String trainId) {
//		trainRepository.deleteById(trainId);
//		return "train removed successfully";
//	}
	public void removeTrain(String id){
		trainRepository.deleteTrainByTrainId(id);
    }

	public void updateTrain(Train train) {
		trainRepository.save(train);
		
	}
	
	
}
