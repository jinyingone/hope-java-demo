package fun.jinying.demo.mqtt;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttConnectPayload;
import io.netty.handler.codec.mqtt.MqttConnectVariableHeader;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageIdVariableHeader;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.handler.codec.mqtt.MqttSubscribeMessage;
import io.netty.handler.codec.mqtt.MqttSubscribePayload;
import io.netty.handler.codec.mqtt.MqttTopicSubscription;
import io.netty.handler.codec.mqtt.MqttVersion;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * @author jy
 * @date 2020/11/23
 */
public class ApplicationTest {
    @Test
    public void test() throws InterruptedException, IOException {
        MqttClient client = MqttClient.start("127.0.0.1", 1883);
        client.connect("1", "zhangsan", "123456");
        client.subscribe("demo");
        TimeUnit.SECONDS.sleep(30);
    }


    public static class MqttClient {
        private Channel channel;

        private MqttClient(Channel channel) {
            this.channel = channel;
        }

        public static MqttClient start(String ip, int port) throws InterruptedException {
            Bootstrap b = new Bootstrap();
            EventLoopGroup group = new NioEventLoopGroup();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    .addLast(MqttEncoder.INSTANCE)
                                    .addLast(new MqttDecoder())
                                    .addLast(new NettyClientHandler())
                            ;
                        }
                    });
            b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000);

            Channel channel = b.connect(ip, port).sync().channel();
            return new MqttClient(channel);
        }

        public void connect(String clientIdentifier, String userName, String password) {
            MqttConnectVariableHeader mqttConnectVariableHeader = new MqttConnectVariableHeader(MqttVersion.MQTT_3_1_1.protocolName(), 1 << 2, true, true, false, 0, false, true, 5);
            MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.CONNECT, true, MqttQoS.AT_LEAST_ONCE, false, 0);
            MqttConnectPayload mqttConnectPayLoad = new MqttConnectPayload(clientIdentifier, null, null, userName, password.getBytes());
            MqttConnectMessage mqttMessage = new MqttConnectMessage(mqttFixedHeader, mqttConnectVariableHeader, mqttConnectPayLoad);
            this.channel.writeAndFlush(mqttMessage);
        }

        public void subscribe(String topic) {
            MqttMessageIdVariableHeader variableHeader = MqttMessageIdVariableHeader.from(1);
            MqttFixedHeader mqttFixedHeader = new MqttFixedHeader(MqttMessageType.SUBSCRIBE, true, MqttQoS.AT_LEAST_ONCE, false, 0);
            MqttSubscribePayload payload = new MqttSubscribePayload(Collections.singletonList(new MqttTopicSubscription(topic, MqttQoS.AT_LEAST_ONCE)));
            MqttSubscribeMessage message = new MqttSubscribeMessage(mqttFixedHeader, variableHeader, payload);
            this.channel.writeAndFlush(message);
        }

        public void pingReq() {
            MqttMessage mqttMessage = new MqttMessage(new MqttFixedHeader(MqttMessageType.PINGREQ, false, MqttQoS.AT_LEAST_ONCE, false, 0));
            this.channel.writeAndFlush(mqttMessage);
        }

        public void disConnect() {
        }
    }

    private static class NettyClientHandler extends SimpleChannelInboundHandler<MqttMessage> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, MqttMessage msg) {
            Object payload = msg.payload();
            if (payload instanceof ByteBuf) {
                String payloadString = ((ByteBuf) payload).toString(StandardCharsets.UTF_8);
                System.out.println("收到消息：" + payloadString);
            } else {
                System.out.println(msg);
            }
        }
    }
}