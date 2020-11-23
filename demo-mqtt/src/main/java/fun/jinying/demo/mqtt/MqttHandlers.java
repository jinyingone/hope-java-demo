package fun.jinying.demo.mqtt;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.mqtt.MqttConnAckMessage;
import io.netty.handler.codec.mqtt.MqttConnAckVariableHeader;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageIdAndPropertiesVariableHeader;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.handler.codec.mqtt.MqttSubAckMessage;
import io.netty.handler.codec.mqtt.MqttSubAckPayload;
import io.netty.handler.codec.mqtt.MqttSubscribeMessage;
import io.netty.handler.codec.mqtt.MqttTopicSubscription;

import java.util.List;

/**
 * @author jy
 * @date 2020/11/20
 */
public class MqttHandlers extends SimpleChannelInboundHandler<MqttMessage> {
    public static final MqttHandlers INSTANCE = new MqttHandlers();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MqttMessage msg) throws Exception {
        switch (msg.fixedHeader().messageType()) {
            case CONNECT:
                connect(ctx, (MqttConnectMessage) msg);
                break;
            case SUBSCRIBE:
                subscribe(ctx, (MqttSubscribeMessage) msg);
                break;
            case PINGREQ:
                pingReq(ctx, msg);
                break;
            default:
        }
    }

    private void pingReq(ChannelHandlerContext ctx, MqttMessage msg) {
        MqttFixedHeader fixedHeader = new MqttFixedHeader(MqttMessageType.PINGRESP, false, MqttQoS.AT_LEAST_ONCE, false, 0);
        MqttMessage ackMessage = new MqttMessage(fixedHeader);
        ctx.writeAndFlush(ackMessage);
    }

    private void subscribe(ChannelHandlerContext ctx, MqttSubscribeMessage msg) {
        List<MqttTopicSubscription> topics = msg.payload().topicSubscriptions();
        topics.forEach(System.out::println);

        MqttFixedHeader header = new MqttFixedHeader(MqttMessageType.SUBACK, false, MqttQoS.AT_LEAST_ONCE, false, 0);
        MqttMessageIdAndPropertiesVariableHeader variableHeader = new MqttMessageIdAndPropertiesVariableHeader(msg.variableHeader().messageId(), null);
        MqttSubAckPayload payload = new MqttSubAckPayload();
        MqttSubAckMessage ackMessage = new MqttSubAckMessage(header, variableHeader, payload);
        ctx.writeAndFlush(ackMessage);
    }

    private void connect(ChannelHandlerContext ctx, MqttConnectMessage msg) {
        String s = msg.payload().clientIdentifier();
        String s1 = msg.payload().userName();
        byte[] bytes = msg.payload().passwordInBytes();

        MqttFixedHeader connAckFixedHeaderRes = new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0);
        MqttConnAckVariableHeader connAckVariableHeader = new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_ACCEPTED, false);
        MqttConnAckMessage ackMessage = new MqttConnAckMessage(connAckFixedHeaderRes, connAckVariableHeader);
        ctx.channel().writeAndFlush(ackMessage);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }
}
