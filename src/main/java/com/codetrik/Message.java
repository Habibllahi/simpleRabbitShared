package com.codetrik;

import com.codetrik.common.AMPQMessage;
import com.rabbitmq.client.Channel;

public interface Message<T extends AMPQMessage> {
    void publishMessage(Channel channel, T message) throws Exception;
    void consumeMessage(Channel channel) throws Exception;
}
