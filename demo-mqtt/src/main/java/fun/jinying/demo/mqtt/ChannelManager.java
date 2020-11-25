package fun.jinying.demo.mqtt;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author jy
 * @date 2020/11/24
 */
public class ChannelManager {
    /**
     * 映射存储
     */
    private static final ConcurrentHashMap<String, ChannelInfo> CLIENT_CHANNEL_MAPPING = new ConcurrentHashMap<>(128);
    private static final ConcurrentHashMap<String, String> CHANNEL_CLIENT_MAPPING = new ConcurrentHashMap<>(128);
    private static final ConcurrentHashMap<String, ConcurrentHashMap.KeySetView<Channel, Boolean>> TOPIC_CLIENT_MAPPING = new ConcurrentHashMap<>(128);


    public static void saveChannelMapping(String clientId, Channel channel) {
        ChannelInfo info = new ChannelInfo();
        info.setChannel(channel);
        CLIENT_CHANNEL_MAPPING.put(clientId, info);
        CHANNEL_CLIENT_MAPPING.put(channel.id().asLongText(), clientId);
    }

    public static ChannelInfo getChannelInfo(String clientId) {
        return CLIENT_CHANNEL_MAPPING.get(clientId);
    }

    public static String getClientId(Channel channel) {
        return CHANNEL_CLIENT_MAPPING.get(channel.id().asLongText());
    }

    public static void saveTopics(Channel channel, List<String> topics) {
        topics.forEach(topic -> {
            ConcurrentHashMap.KeySetView<Channel, Boolean> channels = TOPIC_CLIENT_MAPPING.get(topic);
            if (channels == null) {
                synchronized (TOPIC_CLIENT_MAPPING) {
                    if (!TOPIC_CLIENT_MAPPING.containsKey(topic)) {
                        channels = ConcurrentHashMap.newKeySet();
                    } else {
                        channels = TOPIC_CLIENT_MAPPING.get(topic);
                    }
                }
            }
            channels.add(channel);
            TOPIC_CLIENT_MAPPING.put(topic, channels);
        });
    }

    public static List<Channel> listChannels(String topic) {
        ConcurrentHashMap.KeySetView<Channel, Boolean> channels = TOPIC_CLIENT_MAPPING.get(topic);
        if (channels == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(channels);
    }


    public static class ChannelInfo {
        private Channel channel;
        private String clientId;
        private String userName;

        public Channel getChannel() {
            return channel;
        }

        public void setChannel(Channel channel) {
            this.channel = channel;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }


}
