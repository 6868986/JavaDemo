package org.example.MQ.Kafka.Config;

import com.meituan.mafka.client.MafkaClient;
import com.meituan.mafka.client.bean.MafkaProducer;
import com.meituan.mafka.client.consumer.ConsumerConstants;
import com.meituan.mafka.client.consumer.IConsumerProcessor;
import org.example.MQ.Kafka.Listener.WorkListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Properties;

/**
 *
 * @author liushixing
 * @date 2023/5/10 10:56
 */
@Configuration
public class MafkaConfig {

    @Resource
    WorkListener workListener;

    @Bean(name = "workConsumer")
    public IConsumerProcessor workConsumer() throws Exception {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConstants.MafkaBGNamespace, "waimai");
        properties.setProperty(ConsumerConstants.MafkaClientAppkey, "com.sankuai.health.merchant.pricing");
        properties.setProperty(ConsumerConstants.SubscribeGroup, "workflow.to.health.pricing.consumer");
        IConsumerProcessor consumer = MafkaClient.buildConsumerFactory(properties, "com.sankuai.healthm.workflow.process.compete.event");
        consumer.recvMessageWithParallel(String.class, workListener);
        return consumer;
    }

    @Bean(name = "messageProducer")
    public MafkaProducer workProducer(){
        MafkaProducer producer = new MafkaProducer();
        producer.setNamespace("waimai");
        producer.setAppkey("com.sankuai.health.merchant.pricing");
        producer.setTopic("topic");
        return producer;
    }

}
