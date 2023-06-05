package com.yzhou.reader;

import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Reader;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.api.schema.GenericRecord;
import org.apache.pulsar.client.api.schema.RecordSchemaBuilder;
import org.apache.pulsar.client.api.schema.SchemaBuilder;
import org.apache.pulsar.client.impl.schema.generic.GenericJsonSchema;
import org.apache.pulsar.common.schema.SchemaInfo;
import org.apache.pulsar.common.schema.SchemaType;

public class ReaderGenericSchemaTest {
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

        //建 RecordSchema
//        RecordSchemaBuilder recordSchemaBuilder = SchemaBuilder.record("schemaName");
//        recordSchemaBuilder.field("intField").type(SchemaType.INT32);
//        recordSchemaBuilder.field("StringField").type(SchemaType.STRING);
//        SchemaInfo schemaInfo = recordSchemaBuilder.build(SchemaType.JSON);
//        Producer<GenericRecord> producer  =  client.newProducer(
//                GenericJsonSchema.of(schemaInfo))
//                .topic("my-topic").create();// 鸭建 GenericRecord
//        GenericRecord record =  GenericJsonSchema.of(schemaInfo).newRecordBuilder()
//                .set("intpield",10).set("stringpield","sting test").build();
//        producer.newMessage().value(record).send();



    }
}
