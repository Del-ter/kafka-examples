package interceptor_examples;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class InterceptorCounter implements ProducerInterceptor {
    int success;
    int error;

    @Override
    public ProducerRecord onSend(ProducerRecord producerRecord) {
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (!recordMetadata.equals(null)){
            success++;
        } else{
            error++;
        }
    }

    //producer关闭时，调用
    @Override
    public void close() {
        System.out.println("success:" + success);
        System.out.println("error:" + error);
    }

    @Override
    public void configure(Map<String, ?> map) {

    }
}
