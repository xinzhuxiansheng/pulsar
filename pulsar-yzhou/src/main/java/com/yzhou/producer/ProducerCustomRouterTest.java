package com.yzhou.producer;

import org.apache.pulsar.client.api.HashingScheme;
import org.apache.pulsar.client.api.MessageRoutingMode;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class ProducerCustomRouterTest {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.175.129:6650")
                .build();

        // 构建生产者
        Producer<byte[]> producer = client.newProducer()
                .topic("my-topic")
                .messageRouter(new MessageLengthRouter())
                .create();

    }
}
