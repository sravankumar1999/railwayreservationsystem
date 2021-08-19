package com.casestudy.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.model.Train;

public interface TrainRepository extends MongoRepository<Train, String> {
	
	List<Train> findAllByName(String name);

	Train findAllBySourceAndDestination(String source, String destination);

	void deleteTrainByTrainId(String id);

	//void deleteByTrainId(long trainId);
	
	
}
