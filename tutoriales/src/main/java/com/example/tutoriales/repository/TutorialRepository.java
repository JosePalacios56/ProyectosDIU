package com.example.tutoriales.repository;

import com.example.tutoriales.model.TutorialVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends MongoRepository<TutorialVO,String> {
}
