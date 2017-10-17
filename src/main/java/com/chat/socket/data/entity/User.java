package com.chat.socket.data.entity;

import java.nio.channels.Channel;

/**
 * Created by artash on 9/28/17.
 */



public class User {

	private String nickname;

	private Channel channel;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
}
