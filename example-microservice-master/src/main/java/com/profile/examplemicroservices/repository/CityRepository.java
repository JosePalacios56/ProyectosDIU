package com.profile.examplemicroservices.repository;

import com.profile.examplemicroservices.model.CityVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends MongoRepository< CityVO, String > {
}
