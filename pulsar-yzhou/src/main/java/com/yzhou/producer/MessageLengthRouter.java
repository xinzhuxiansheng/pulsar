package com.yzhou.producer;

import java.util.Random;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageRouter;
import org.apache.pulsar.client.api.TopicMetadata;

public class MessageLengthRouter implements MessageRouter {
    static Random random = new Random();

    @Override
    public int choosePartition(Message<?> msg, TopicMetadata metadata) {
        int partitionNums = metadata.numPartitions();
        if (partitionNums != 3) {
            return random.nextInt(partitionNums);
        }
        if (msg.getData().length < 10) {
            return 0;
        } else if (msg.getData().length >= 10 && msg.getData().length < 100) {
            return 1;
        } else {
            return 2;
        }
    }
}
