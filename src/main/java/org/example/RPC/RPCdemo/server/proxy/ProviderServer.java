package org.example.RPC.RPCdemo.server.proxy;



import org.example.RPC.RPCdemo.server.request.Request;
import org.example.RPC.RPCdemo.server.response.Response;
import org.example.RPC.RPCdemo.server.service.HelloService;
import org.example.RPC.RPCdemo.server.service.HelloServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author liushixing
 * @date 2023/5/19 15:49
 */
public class ProviderServer {

//    @Resource
//    HelloService helloService;
    HelloService helloService = new HelloServiceImpl();

    public static void main(String[] args) throws IOException {
        new ProviderServer().run();
    }

    public void run() throws IOException {

        ServerSocket listener = new ServerSocket(9090);
        try{
            //未引入线程池
            while(true){
                Socket socket = listener.accept();
                try{
                    //将请求反序列化
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    Object object = objectInputStream.readObject();

                    //调用HelloServiceImpl服务
                    String res = null;
                    if(object instanceof Request){
                        Request request = (Request) object;
                        if("hello".equals(request.getMethod())){
                            //通过反射调用服务方法（Spring工厂底层通过反射调用 method.invoke)
                            res = helloService.hello(request.getName());
                        } else {
                            throw new UnsupportedOperationException();
                        }
                    }

                    //返回结果
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(new Response(res));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    socket.close();
                }
            }
        } finally {
            listener.close();
        }

    }
}
