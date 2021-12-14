package producer_examples.producer_withMyPartitioner;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class Producer_Partition {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "test3:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        //指定自定义分区器
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "producer_examples.producer_withMyPartitioner.MyPartitioner");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<>("test-1", "20211130-mess-"+i),
                    new Callback() {
                        @Override
                        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                            if (e == null){
                                System.out.println((recordMetadata.partition() + "-" + recordMetadata.offset()));
                            } else {
                                e.printStackTrace();
                            }
                        }
                    });
        }

        producer.close();
    }
}
