package interceptor_examples;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class InterceptorTime implements ProducerInterceptor {
    @Override
    public void configure(Map<String, ?> map) {

    }

    //拦截器尽量不改变原数据的分区
    @Override
    public ProducerRecord onSend(ProducerRecord producerRecord) {
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(
                producerRecord.topic(), producerRecord.partition(),
                producerRecord.key().toString(), System.currentTimeMillis()+"--"+producerRecord.value()
        );
        return record;

    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {

    }

    @Override
    public void close() {

    }

}
