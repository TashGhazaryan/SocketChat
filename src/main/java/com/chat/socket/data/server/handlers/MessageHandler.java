package com.chat.socket.data.server.handlers;

import com.chat.socket.data.entity.Message;
import com.google.gson.Gson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by artash on 9/28/17.
 */
public class MessageHandler extends SimpleChannelInboundHandler<Message>{

	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
		Gson gson = new Gson();
		String json = gson.toJson(msg);
		for (Channel ch: channels) {
			if (ch != ctx.channel()) {
				ch.writeAndFlush(new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8.name())));
			}
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("new user is connected");
		channels.add(ctx.channel());
		System.out.println(channels);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
