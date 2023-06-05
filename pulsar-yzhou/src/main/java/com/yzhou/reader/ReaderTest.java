package com.yzhou.reader;

import com.yzhou.producer.DemoData;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.ProducerBuilder;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Reader;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.impl.schema.AvroSchema;
import org.apache.pulsar.client.impl.schema.JSONSchema;
import org.apache.pulsar.client.impl.schema.ProtobufSchema;
import org.apache.pulsar.common.schema.KeyValue;
import org.apache.pulsar.common.schema.KeyValueEncodingType;

public class ReaderTest {
    public static void main(String[] args) throws PulsarClientException {
        PulsarClient client = PulsarClient.builder()
                .serviceUrl("pulsar://192.168.175.129:6650")
                .build();

        Reader<String> reader = client.newReader(Schema.STRING)
                .topic("my-topic")
                .create();

        while (true) {
            Message<String> message = reader.readNext();
            System.out.println(message.getValue());
        }


//        Producer<String> producer = client.newProducer(Schema.STRING)
//                .topic("my-topic")
//                .create();
//        producer.newMessage().value("String test");
//
//        Consumer<Float> consumer = client.newConsumer(Schema.FLOAT)
//                .topic("my-topic")
//                .subscriptionName("subscription name")
//                .subscribe();
//        Message<Float> msg = consumer.receive();


//        Schema<KeyValue<Integer, String>> kvSchema1 = Schema.KeyValue(Schema.INT32,Schema.STRING,
//        KeyValueEncodingType.INLINE);
//        Schema<KeyValue<Integer, String>> kvSchema2 = Schema.KeyValue(Schema.INT32,Schema.STRING,
//        KeyValueEncodingType.SEPARATED);
//        ProducerBuilder<KeyValue<Integer, String>> producer = client.newProducer(kvSchema1);

//        Producer<DemoData> producer = client.newProducer(JSONSchema.of(DemoData.class))
//                .topic("my-topic").create();
//
//        Producer<DemoData> producer2 = client.newProducer(AvroSchema.of(DemoData.class))
//                .topic("my-topic").create();
//
//        // ProtobufSchema 使用的对象 ProtocolData 要能自 GeneratedMesaagov3
//        Producer<ProtocolData> producer3 = client.newProducer(ProtobufSchema.of(ProtocolData.class))
//        producer<ProtocolData> producer4mclient, newProducer (Protobufnativeschema
//                of(ProtocolData.class))
//                .topic("my-topic").create();


    }
}
