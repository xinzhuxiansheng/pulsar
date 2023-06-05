package com.yzhou.consumer;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.Messages;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.api.SubscriptionInitialPosition;

public class ConsumerBatchTest {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.175.129:6650")
                .build();

        Consumer<String> consumer = client.newConsumer(Schema.STRING)
                .topic("my-topic")
                .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
                .subscriptionName("subscription_test")
                .subscribe();

        Messages<String> msgs = consumer.batchReceive();
        for (Message<String> msg : msgs) {
            System.out.println("Message received: " + new String(msg.getData()));
            // 可以选择确认单条消息
//            consumer.acknowledge(msg);
        }
        // 或者确认整批消息
        consumer.acknowledge(msgs);
    }
}
