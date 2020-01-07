package com.groupfour.chatapp.chatapp.repositories;

import com.groupfour.chatapp.chatapp.dataprojections.PollDTO;
import com.groupfour.chatapp.chatapp.models.Poll;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends CrudRepository<Poll, Long> {

    @Query(value = "SELECT v.* " +
            "FROM Option o, Vote v " +
            "WHERE o.POLL_ID = ?1 " +
            "AND v.OPTION_ID = o.OPTION_ID", nativeQuery = true)
Poll findPollByChatAndPoll(Long id, Long pollId);

Iterable<PollDTO> findPollsByChatChatId(Long id);
PollDTO findByPollId(Long id);


}
