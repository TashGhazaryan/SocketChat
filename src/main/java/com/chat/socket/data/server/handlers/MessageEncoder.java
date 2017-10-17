package com.chat.socket.data.server.handlers;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by artash on 9/28/17.
 */
public class MessageEncoder extends MessageToByteEncoder<InputStream> {
	@Override
	protected void encode(ChannelHandlerContext ctx, InputStream msg, ByteBuf out) throws Exception {
		String message = getStringFromInputStream(msg);
		ctx.channel().writeAndFlush(new TextWebSocketFrame(message));
	}

	private static String getStringFromInputStream(InputStream is) {

		try {
			return IOUtils.toString(is, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
