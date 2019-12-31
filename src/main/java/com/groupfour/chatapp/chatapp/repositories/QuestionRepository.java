package com.groupfour.chatapp.chatapp.repositories;


import com.groupfour.chatapp.chatapp.models.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface QuestionRepository extends CrudRepository<Question, Long> {




}
