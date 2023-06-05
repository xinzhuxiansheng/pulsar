package com.yzhou.producer;

import java.nio.ByteBuffer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.api.interceptor.ProducerInterceptor;
import org.apache.pulsar.client.impl.MessageImpl;
import org.apache.pulsar.client.impl.schema.StringSchema;

public class PulsarInterceptUpper implements ProducerInterceptor {
    @Override
    public void close() {

    }

    @Override
    public boolean eligible(Message message) {
        if (message instanceof MessageImpl) {
            Schema schema = ((MessageImpl) message).getSchemaInternal();
            return schema instanceof StringSchema;
        } else {
            return false;
        }
    }

    @Override
    public Message beforeSend(Producer producer, Message message) {
        MessageImpl messageImpl = ((MessageImpl) message);
        byte[] raw_data = new String(message.getData()).toUpperCase().getBytes();
        MessageImpl upperMessage = MessageImpl.create(
                ((MessageImpl<?>) message).getMessageBuilder()
                , ByteBuffer.wrap(raw_data),
                StringSchema.utf8(), "");
        return upperMessage;
    }

    @Override
    public void onSendAcknowledgement(Producer producer, Message message, MessageId msgId, Throwable exception) {

    }
}
