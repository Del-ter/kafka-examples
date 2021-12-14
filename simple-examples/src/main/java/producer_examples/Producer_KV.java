package producer_examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.Properties;

public class Producer_KV {
    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "test3:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String,String>("test-1",strings.get(i%3),"thirdTest"+i),
                    (recordMetadata, exception) -> {
                        if (exception == null){
                            //由返回的offset,可以看出 区内有序
                            System.out.println((recordMetadata.partition() + "--" + recordMetadata.offset()));
                        } else {
                            exception.printStackTrace();
                        }
                    });
        }

        producer.close();
    }
}
