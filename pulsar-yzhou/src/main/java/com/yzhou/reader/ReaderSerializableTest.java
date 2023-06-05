package com.yzhou.reader;

import com.yzhou.producer.DemoData;
import java.io.IOException;
import java.util.Random;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;

public class ReaderSerializableTest {

    public static void main(String[] args) throws Exception {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.175.129:6650")
                .build();

        // 构建生产者
        Producer<byte[]> producer = client.newProducer()
                .topic("my-topic")
                .create();

        DemoData sendDemoData = new DemoData(new Random().nextInt(),"test");
        producer.send(sendDemoData.serialize());


        Consumer<byte[]> consumer = client.newConsumer().topic("my-topic")
                .subscriptionName("subscriptionName-01")
                .subscribe();

        Message<byte[]> message = consumer.receive();
        DemoData receivedDemoData = DemoData.deserializer(message.getData());
        consumer.acknowledge(message);


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
