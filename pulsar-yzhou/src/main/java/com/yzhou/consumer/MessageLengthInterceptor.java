package com.yzhou.consumer;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.ConsumerInterceptor;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.impl.MessageImpl;

public class MessageLengthInterceptor implements ConsumerInterceptor {
    private AtomicInteger tooLongMsgCount = new AtomicInteger(0);

    @Override
    public void close() {

    }

    @Override
    public Message beforeConsume(Consumer consumer, Message message) {
        MessageImpl<String> messageImpl = ((MessageImpl<String>) message);
        if (messageImpl.getValue().length() > 20) {
            try {
                consumer.acknowledge(message);
                tooLongMsgCount.incrementAndGet();
            } catch (PulsarClientException e) {
                System.out.println("beforeConsume ack failed");
            }
            return null;
        }
        return message;
    }

    @Override
    public void onAcknowledge(Consumer consumer, MessageId messageId, Throwable exception) {

    }

    @Override
    public void onAcknowledgeCumulative(Consumer consumer, MessageId messageId, Throwable exception) {

    }

    @Override
    public void onAckTimeoutSend(Consumer consumer, Set set) {

    }

    @Override
    public void onNegativeAcksSend(Consumer consumer, Set set) {

    }
}
