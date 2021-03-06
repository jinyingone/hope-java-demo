package fun.jinying.domain.shard.model;

/**
 * @description: 事件服务
 * @author: sjy
 * @create: 2020-03-01 17:39
 **/
public interface EventProducer<T extends DomainEvent> {
    /**
     * 发送事件
     *
     * @param event
     */
    void sendEvent(T event);
}
