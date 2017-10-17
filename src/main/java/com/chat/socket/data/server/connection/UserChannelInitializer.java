package com.chat.socket.data.server.connection;

import com.chat.socket.data.server.handlers.MessageDecoder;
import com.chat.socket.data.server.handlers.MessageEncoder;
import com.chat.socket.data.server.handlers.MessageHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.concurrent.GenericFutureListener;


/**
 * Created by artash on 9/28/17.
 */
public class UserChannelInitializer  extends ChannelInitializer<SocketChannel>{





	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();

		pipeline.addLast(new HttpServerCodec());
		pipeline.addLast(new HttpObjectAggregator(65536));
		pipeline.addLast(new WebSocketServerProtocolHandler("/ws", null, true));
		pipeline.addLast(new MessageDecoder());
		pipeline.addLast(new MessageHandler());
		pipeline.addLast(new MessageEncoder());

		ch.closeFuture().addListener(new GenericFutureListener<ChannelFuture>() {
			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				System.out.println("Channel closed");
			}
		});



	}
}
