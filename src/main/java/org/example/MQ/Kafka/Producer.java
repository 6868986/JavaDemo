/**
 * meituan.com Inc.
 * Copyright (c) 2010-2023 All Rights Reserved.
 */
package org.example.MQ.Kafka;

import com.meituan.mafka.client.MafkaClient;
import com.meituan.mafka.client.consumer.ConsumerConstants;
import com.meituan.mafka.client.producer.AsyncProducerResult;
import com.meituan.mafka.client.producer.FutureCallback;
import com.meituan.mafka.client.producer.IProducerProcessor;
import com.meituan.mafka.client.producer.ProducerResult;

import java.util.Properties;

/**
 * <p>
 *
 * </p>
 *
 * @author liushixing@meituan.com
 * @date 2023/4/26 15:59
 */
public class Producer {

    private static IProducerProcessor producer;

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();

        properties.setProperty(ConsumerConstants.MafkaBGNamespace,"bgname");
        properties.setProperty(ConsumerConstants.MafkaClientAppkey,"appkey");

        producer = MafkaClient.buildProduceFactory(properties,"topic_name");

        for(int i = 0;i < 10;i++){

            try{
                //同步发送
                ProducerResult producerResult = producer.sendMessage("这是第" + i + "条消息");

                //异步发送
                String message = "这是第" + i + "条消息";
                ProducerResult producerResult1 = producer.sendAsyncMessage(message,
                        new FutureCallback() {
                            @Override
                            public void onSuccess(AsyncProducerResult asyncProducerResult) {
                                System.out.println("发送成功" + message);
                            }

                            @Override
                            public void onFailure(Throwable throwable) {
                                System.out.println("发送失败" + message);
                            }
                        }
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
