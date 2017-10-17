package com.chat.socket.data.repository;

import com.chat.socket.data.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by artash on 9/28/17.
 */

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
}
