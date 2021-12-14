package producer_examples;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class Producer_CallBack {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "test3:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 50; i < 60; i++) {
            producer.send(new ProducerRecord<String,String>("test-1", "secTest"+i),
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
