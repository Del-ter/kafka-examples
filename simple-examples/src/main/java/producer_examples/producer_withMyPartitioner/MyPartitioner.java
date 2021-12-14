package producer_examples.producer_withMyPartitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import java.util.Map;

public class MyPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        //type-1
//        int availNum = cluster.availablePartitionsForTopic(topic).size();
//        return key.toString().hashCode() % availNum;

        return 1;

    }

    @Override
    public void close() {

    }

    @Override
    public void onNewBatch(String topic, Cluster cluster, int prevPartition) {

    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
