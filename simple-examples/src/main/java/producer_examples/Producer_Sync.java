package producer_examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Producer_Sync {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "test3:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 50; i < 60; i++) {
            //send方法由另一个线程运行，所以常规状态（异步发送）：main() 和 send() 异步执行的
            Future<RecordMetadata> sendBack = producer.send(new ProducerRecord<String, String>("test-1", "secTest" + i));

            //当调用get()时，会阻塞main()进程，从而实现同步
            try {
                RecordMetadata recordMetadata = sendBack.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            //同步发送效率较低，所以生产场景不常用。
            //todo:可能使用场景 -> 同步向一个分区发送数据，从而实现topic数据有序发送。

        }

        producer.close();
    }
}
