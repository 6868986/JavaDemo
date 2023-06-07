package org.example.RPC.RPCdemo.server.service;

import org.springframework.stereotype.Service;

/**
 * @author liushixing
 * @date 2023/5/11 11:19
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name + "!";
    }
}
