package org.example.MQ.MQdemo;

import lombok.Data;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author liushixing
 * @date 2023/6/7 15:57
 */
public class DelayQueueDemo {

    private static DelayQueue delayQueue = new DelayQueue();

    public static void main(String[] args) throws InterruptedException {
        producer();
        consumer();
    }

    public static void producer(){
        delayQueue.put(new Msg(1000,"消息1"));
        delayQueue.put(new Msg(5000,"消息2"));
    }

    public static void consumer() throws InterruptedException {
        System.out.println("开始执行时间：" + DateFormat.getDateTimeInstance().format(new Date()));
        while(!delayQueue.isEmpty()){
            System.out.println(delayQueue.take().toString());
        }
        System.out.println("结束执行时间：" + DateFormat.getDateTimeInstance().format(new Date()));
    }

    @Data
    static class Msg implements Delayed {

        long delaytime = System.currentTimeMillis();

        private String message;

        public Msg(long delaytime,String message){
            this.delaytime = (this.delaytime + delaytime);
            this.message = message;
        }

        /**
         * 获取剩余时间
         *
         * @param unit 时间单位
         * @return
         */
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(delaytime - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
        }

        /**
         * 队列中的元素排序
         *
         * @param o this对象本身要比较的对象
         * @return
         */
        @Override
        public int compareTo(Delayed o) {
            if(this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)){
                return 1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            }else{
                return 0;
            }
        }

        @Override
        public String toString() {
            return message;
        }
    }


}

