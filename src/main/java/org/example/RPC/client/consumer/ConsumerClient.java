package org.example.RPC.client.consumer;

import org.example.RPC.client.proxy.RemoteServiceImplProxy;
import org.example.RPC.server.service.HelloService;

/**
 * @author liushixing
 * @date 2023/5/19 15:15
 */
public class ConsumerClient {

    public static void main(String[] args) {

        //HelloService是远程服务暴露出来的sdk接口，通过导入jar包引入远程服务的sdk接口
        HelloService service = new RemoteServiceImplProxy();
        String res = service.hello("武汉大学");
        System.out.println(res);
    }
}
