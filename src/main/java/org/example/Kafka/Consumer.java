package org.example.Kafka;

import com.meituan.mafka.client.MafkaClient;
import com.meituan.mafka.client.consumer.ConsumeStatus;
import com.meituan.mafka.client.consumer.ConsumerConstants;
import com.meituan.mafka.client.consumer.IConsumerProcessor;
import com.meituan.mafka.client.consumer.IMessageListener;
import com.meituan.mafka.client.message.MafkaMessage;
import com.meituan.mafka.client.message.MessagetContext;

import java.util.Properties;

/**
 * @author liushixing
 * @date 2023/4/26 15:59
 */
public class Consumer {

    public static IConsumerProcessor consumer;

    public static void main(String[] args) throws Exception {

        Properties properties = new Properties();

        properties.setProperty(ConsumerConstants.MafkaBGNamespace,"bgname");
        properties.setProperty(ConsumerConstants.MafkaClientAppkey,"appkey");
        properties.setProperty(ConsumerConstants.SubscribeGroup,"consumer_group");

        consumer = MafkaClient.buildConsumerFactory(properties,"topic_name");
        //参数1：消息class   参数2：消费逻辑封装IMessageListener
        consumer.recvMessageWithParallel(String.class, new IMessageListener() {
            @Override
            public ConsumeStatus recvMessage(MafkaMessage mafkaMessage, MessagetContext messagetContext) {
                //消费逻辑
                return null;
            }
        });
    }
}
