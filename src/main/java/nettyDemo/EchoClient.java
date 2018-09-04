package nettyDemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringEncoder;

/**
 * netty客户端:
 *
 * @auther liran
 * @create 2018-08-29-上午11:23
 */

public class EchoClient {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<NioSocketChannel>() {


                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {


                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder
                                    (Integer.MAX_VALUE, Delimiters.lineDelimiter()[0]));
                            ch.pipeline().addLast(new EchoClientHandler());
                            //追加编码器
                            ch.pipeline().addLast(new StringEncoder());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            String sendMgs = "adddw哈哈哈\r\n";
            channelFuture.channel().writeAndFlush(sendMgs);
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            group.shutdownGracefully();
        }


    }
}
