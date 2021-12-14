package consumer_examples;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

public class Consumer_CommitSync {

    public static void main(String[] args) {

        //消费之配置信息
        Properties properties = new Properties();
        //连接信息
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "test3:9092");

        //todo:启动同步提交（手动提交），自动提交的配置不会生效
//        //开启自动提交（offset）
//        //todo:如果为false,进程会将offset保存在内存中，但是消费结束时，该offset不会提交至kafka
//        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,true);
//        //自动提交（offset）延迟 1s
//        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,1000);



        //k,v 的反序列化(double shift 查出StringDeserializer)
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //消费者组指定
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "20211130-test-2");

        //重置消费者的offset（默认：latest）
        //todo:该配置生效场景（1、消费者组变更后；2、上次保存的offset已经过期）
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //新建消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        //设置订阅的主题
        consumer.subscribe(Arrays.asList("test-1", "test-2"));

        while (true){
            //获取数据(poll(长轮询未请求到数据时需要延时))
            ConsumerRecords<String, String> consumerRecords = consumer.poll(100);

            //解析数据
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord.key()+"-"+consumerRecord.value());
            }

            //todo:offset同步提交（效率较低），不会丢数据，但是会有重复数据
            consumer.commitSync();//没有提交，则会阻塞main()进程。
        }

    }
}
