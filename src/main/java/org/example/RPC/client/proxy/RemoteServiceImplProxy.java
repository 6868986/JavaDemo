package org.example.RPC.client.proxy;

import org.example.RPC.server.request.Request;
import org.example.RPC.server.response.Response;
import org.example.RPC.server.service.HelloService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liushixing
 * @date 2023/5/19 15:19
 */

public class RemoteServiceImplProxy implements HelloService {

    public static final int PORT = 9090;
    //private static final Logger log = LoggerFactory.getLogger(RemoteServiceImplProxy.class);

    @Override
    public String hello(String name) {

        //从注册中心中拿到所有服务名为HelloService的服务实例的IP地址
        List<String> addrList = allServers("HelloService");
        //根据负载均衡策略选择其中之一
        String addr = chooseTargetServer(addrList);

        try {
            Socket socket = new Socket(addr, PORT);

            //请求序列化
            //Request同样也是通过jar包引入
            Request request = generateRequest(name, "hello");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            //发送请求
            objectOutputStream.writeObject(request);



            //将响应反序列化
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();

            //objectInputStream.close();
            //objectOutputStream.close();
            //socket.close();


            String res = null;
            if (object instanceof Response) {
                Response response = (Response) object;
                res = response.getRes();
            } else {
                throw new InternalError();
            }

            return res;
        } catch (Exception e){
            //log.error("fail",e);
            System.out.println(e);
            throw new RuntimeException();
        }
    }

    public List<String> allServers(String ServerName){
        List<String> res = new ArrayList<>();
        res.add("127.0.0.1");
        return res;
    }

    public String chooseTargetServer(List<String> servers){
        if(servers == null || servers.size() == 0){
            throw new IllegalArgumentException();
        }
        return servers.get(0);
    }

    public Request generateRequest(String name,String method){
        Request request = new Request();
        request.setName(name);
        request.setMethod(method);
        return request;
    }
}
