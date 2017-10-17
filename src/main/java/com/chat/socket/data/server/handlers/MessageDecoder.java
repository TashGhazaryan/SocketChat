package com.chat.socket.data.server.handlers;

import com.chat.socket.data.entity.Message;
import com.chat.socket.data.entity.User;
import com.chat.socket.data.service.MessageService;
import com.google.gson.Gson;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by artash on 9/28/17.
 */


public class MessageDecoder extends MessageToMessageDecoder <TextWebSocketFrame>{

//	@Autowired
//	private MessageService messageService;


	@Override
	protected void decode(ChannelHandlerContext ctx, TextWebSocketFrame msg, List<Object> out) throws Exception {
		Message message;
		String textMessage = msg.text();
		Gson gson = new Gson();
		message = gson.fromJson(textMessage,Message.class);
//		messageService.saveMessage(message);
		out.add(message);

	}
}

