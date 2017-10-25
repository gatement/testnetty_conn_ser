package lgh.testnetty.conn.ser.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@Component
@Sharable
public class ServerChannelHandler extends SimpleChannelInboundHandler<byte[]> {
	private Logger logger = LoggerFactory.getLogger(ServerChannelHandler.class);

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {
		byte cmd = msg[0];
		switch (cmd) {
		case 0x01:
			logger.info("got: ping");
			// send back 'pong'
			ctx.writeAndFlush(new byte[] { 0x02 });
			break;
		default:
			logger.info("got unknow cmd: {}", cmd);
		}
	}
}