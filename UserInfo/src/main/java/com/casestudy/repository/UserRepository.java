package com.casestudy.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.casestudy.model.UserInfo;

public interface UserRepository extends MongoRepository<UserInfo, String> {

}
