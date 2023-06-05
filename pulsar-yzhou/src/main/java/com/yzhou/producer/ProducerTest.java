package com.yzhou.producer;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class ProducerTest {

    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.175.129:6650")
                .build();

        // 构建生产者
        Producer<byte[]> producer = client.newProducer()
                .topic("persist_topic_1")
                .create();

        // 同步发送消息
        producer.send("发送同步消息".getBytes());
        // 异步发送消息
//        producer.sendAsync("发送异步消息".getBytes());
//        producer.sendAsync("发送同步消息".getBytes())
//                .thenAccept(msgId -> {
//                    System.out.println("发送成功");
//                }).exceptionally(throwable -> {
//                    System.out.println("消息发送失败");
//                    return null;
//                });

        // 关闭生产者与客户端
        producer.close();
        client.close();
    }
}
