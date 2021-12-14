package consumer_examples;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

public class Consumer_CustomOffset {
    public static void main(String[] args) {

        //消费之配置信息
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "test3:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "20211130-test-2");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //新建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        //设置订阅的主题
        consumer.subscribe(Arrays.asList("test-1", "test-2"), new ConsumerRebalanceListener() {
            @Override //rebalance前调用
            public void onPartitionsRevoked(Collection<TopicPartition> collection) {
//                ...
            }

            @Override //rebalance后调用
            public void onPartitionsAssigned(Collection<TopicPartition> collection) {
//                ...
            }
        });

        //消费过程
        while (true){
            consumer.poll(100);
        }

        //todo:把数据解析和提交offset捆绑成一个事务

    }
}
