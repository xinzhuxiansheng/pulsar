package com.yzhou.producer;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.common.schema.KeyValue;

public class ProducerAddKeyTest {

    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.175.129:6650")
                .build();

        // 构建生产者
        Producer<KeyValue<String, String>> producer = client.newProducer(Schema.KeyValue(Schema.STRING, Schema.STRING))
                .topic("persist_topic_1")
                .create();


    }
}
