package com.codetrik;

import com.codetrik.common.AMPQMessage;
import com.rabbitmq.client.Channel;

import java.io.IOException;

public interface Message<T extends AMPQMessage> {
    void publishMessage(Channel channel, T message) throws IOException;
    T consumeMessage(Channel channel) throws IOException;
}
