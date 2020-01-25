package com.ruin.test;

import com.alibaba.fastjson.JSONObject;
import com.ruin.utils.HttpUtil;

import java.io.IOException;import java.io.IOException;

/**
 * @author ruin
 * @date 2020/1/23-17:12
 */
public class TestPost {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        JSONObject o = HttpUtil.post("/doLogin", "{\"account\":\"99\",\"password\":\"99\"}");
        System.out.println(o.get("content"));
    }


}
