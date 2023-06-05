package com.yzhou.consumer;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.ConsumerEventListener;

public class StatusConsumerEventListener implements ConsumerEventListener {
    @Override
    public void becameActive(Consumer<?> consumer, int partitionId) {
        System.out.println(String.format("consumer name=%s,subscription=%s,topic=%s, partitionId=%s active",
                consumer.getConsumerName(),
                consumer.getSubscription(), consumer.getTopic(), partitionId));
    }

    @Override
    public void becameInactive(Consumer<?> consumer, int partitionId) {
        System.out.println(String.format("consumer name=%s,subscription=%s,topic=%s, partitionId=%s inactive",
                consumer.getConsumerName(),
                consumer.getSubscription(), consumer.getTopic(), partitionId));
    }
}
