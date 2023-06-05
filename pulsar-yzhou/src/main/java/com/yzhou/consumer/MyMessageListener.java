package com.yzhou.consumer;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageListener;

public class MyMessageListener implements MessageListener<String> {
    @Override
    public void received(Consumer<String> consumer, Message<String> msg) {
        System.out.println(consumer.getConsumerName() + " MyMessageListener receive msg" + msg.getValue());
    }

    @Override
    public void reachedEndOfTopic(Consumer<String> consumer) {
        MessageListener.super.reachedEndOfTopic(consumer);
        System.out.println("reachedEndOfTopic");
    }
}
