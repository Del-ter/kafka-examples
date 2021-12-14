package producer_examples;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class Producer_MyProducer {

    public static void main(String[] args) {

        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.1.119:9092");
        prop.put(ProducerConfig.ACKS_CONFIG,"all"); //all=-1
        //失败重试次数
        prop.put("retries",3);
        //每次发送的批次的大小/时间
        prop.put("batch.size","1048576");
        prop.put("linger.ms","5");

        prop.put("compression.type","snappy");
        //32M:RecordAccumulator缓冲区大小
        prop.put("buffer.memory","33554432");
        //序列化类
        prop.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        for (int i = 0; i < 10; i++) {
            producer.send(new ProducerRecord<String,String>("test-1","20211130-mess-"+i));
        }
        //io在关闭之前会缓存中的数据全部发送出去
        producer.close();
    }
}
