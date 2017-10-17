package com.chat.socket.data.service;

import com.chat.socket.data.entity.Message;
import com.chat.socket.data.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by artash on 9/28/17.
 */


@Service
public class MessageService {
	@Autowired
	private MessageRepository messageRepository;


	public void saveMessage(Message message) {
		messageRepository.save(message);
	}
}
