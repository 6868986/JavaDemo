package org.example.RPC.server.response;

import java.io.Serializable;

/**
 * @author liushixing
 * @date 2023/5/19 15:44
 */
//@Data
public class Response implements Serializable {
    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    private String res;

    public Response(String res){
        this.res = res;
    }
}
