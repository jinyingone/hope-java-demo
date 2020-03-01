package fun.jinying.domain.service;

import fun.jinying.domain.shard.model.Event;

/**
 * @description: 事件服务
 * @author: sjy
 * @create: 2020-03-01 17:39
 **/
public interface EventProducer<T extends Event> {
    /**
     * 发送事件
     *
     * @param event
     */
    void sendEvent(T event);
}
