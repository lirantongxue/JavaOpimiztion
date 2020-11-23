package iodemo.nettyDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;

/**
 * 服务端入口:
 *
 * @auther liran
 * @create 2018-08-20-下午10:35
 */
public class EchoServer {

    private final int port;


    public EchoServer(int port) {
        this.port = port;
    }


    public static void main(String[] args) {

        EchoServer echoServer = new EchoServer(8080);
        try {
            echoServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void start() throws Exception {

        final EchoServerHandler serverHandler = new EchoServerHandler();
        //设置线程的数量 默认1
        EventLoopGroup group = new NioEventLoopGroup(1);
        EventLoopGroup chaildGroup = new NioEventLoopGroup();

        try {
            //构建 服务引擎 serverBootstrap
            ServerBootstrap b = new ServerBootstrap();

            // group(EventLoopGroup group) {
            b.group(group, chaildGroup).channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder
                                    (Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                            ch.pipeline().addLast(serverHandler);
                        }
                    });
            ChannelFuture channelFuture = b.bind(8080).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {

            group.shutdownGracefully();
            chaildGroup.shutdownGracefully();
        }


    }
}
