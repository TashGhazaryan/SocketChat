package com.chat.socket.data.server;


import com.chat.socket.data.server.connection.UserChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


public class ChatSocketServer extends ServerBootstrap {

	private final static int RCV_BUFFER_SIZE = 1024 * 1024 * 10;
	private final static int SND_BUFFER_SIZE = 1024 * 1024 * 10;

	private int port;

	public ChatSocketServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(40);
		try {
			this.group(bossGroup, workerGroup)
					.option(ChannelOption.TCP_NODELAY, true)
					.childOption(ChannelOption.TCP_NODELAY, true)
					.childOption(ChannelOption.SO_KEEPALIVE, true)
					.childOption(ChannelOption.SO_REUSEADDR, true)

					.option(ChannelOption.SO_SNDBUF, SND_BUFFER_SIZE)
					.childOption(ChannelOption.SO_SNDBUF, SND_BUFFER_SIZE)

					.option(ChannelOption.SO_RCVBUF, RCV_BUFFER_SIZE)
					.childOption(ChannelOption.SO_RCVBUF, RCV_BUFFER_SIZE)
					.childOption(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(RCV_BUFFER_SIZE))

					.option(ChannelOption.SO_BACKLOG, 128)
//				.option(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 1024 * 1024 * 25)
//				.option(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 1024 * 1024 * 50)
					.channel(NioServerSocketChannel.class)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new UserChannelInitializer());
			this.bind(this.port)
					.sync()
					.channel()
					.closeFuture()
					.sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port = args.length > 0 ? Integer.parseInt(args[0]) : 8080;
		new ChatSocketServer(port).run();
	}
}
