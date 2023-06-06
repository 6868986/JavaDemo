package org.example.Java.thread.create;

import java.util.concurrent.Callable;

/**
 * @author liushixing
 * @date 2023/6/6 17:04
 */
public class CallableThread implements Callable {
    @Override
    public Object call() throws Exception {
        System.out.println("实现Callable接口创建线程");
        return "线程创建的3种方式";
    }
}
