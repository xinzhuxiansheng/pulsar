
package com.yzhou.consumer;

import java.util.concurrent.TimeUnit;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.api.SubscriptionInitialPosition;

public class ConsumerAckTimeoutTest {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.175.129:6650")
                .build();

        Consumer<String> consumer = client.newConsumer(Schema.STRING)
                .topic("my-topic")
                .subscriptionInitialPosition(SubscriptionInitialPosition.Earliest)
                .subscriptionName("subscription_test")
                .ackTimeout(5, TimeUnit.SECONDS)
                .enableRetry(true)
                .subscribe();

        for (int i = 0; i < 100; i++) {
            Message<String> msg = consumer.receive();
            System.out.println("Message received: " + msg.getValue());
            consumer.reconsumeLater(msg, 10, TimeUnit.SECONDS);
            System.out.println("send retry " + msg.getValue());
        }
    }
}
