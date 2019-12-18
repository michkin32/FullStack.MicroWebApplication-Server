package com.groupfour.chatapp.chatapp.repositories;

import com.groupfour.chatapp.chatapp.models.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionRepository extends CrudRepository<Option, Long> {


}
