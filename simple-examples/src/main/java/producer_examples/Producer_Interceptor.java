package producer_examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.Properties;

public class Producer_Interceptor {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "test3:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        //添加拦截器
        ArrayList<String> interceptors = new ArrayList<>();
        interceptors.add("interceptor_examples.InterceptorTime");
        interceptors.add("interceptor_examples.InterceptorCounter");
        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String,String>("test-1", "20211202-mess-"+i),
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
