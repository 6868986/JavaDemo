package org.example.MQ.MQdemo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author liushixing
 * @date 2023/6/7 15:52
 */
public class DemoQueue {

    private static Queue<String> queue = new LinkedList<>();

    public static void main(String[] args) {
        producer();
        consumer();
    }

    private static void producer(){
        queue.add("First Message");
        queue.add("Second Message");
        queue.add("Third Message");
    }

    private static void consumer(){
        while(!queue.isEmpty()){
            System.out.println(queue.poll());
        }
    }
}
