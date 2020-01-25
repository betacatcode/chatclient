package com.ruin.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @author ruin
 * @date 2020/1/24-15:54
 */
public class HttpUtil {

    public static final String baseUrl="http://"+Config.target;

    public static JSONObject post(String request, String jsonParams) throws IOException {
        HttpClient httpClient= HttpClients.custom().build();
        String resultURL=baseUrl+request;
        HttpPost httpPost=new HttpPost(resultURL);

        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(jsonParams, ContentType.create("application/json", "utf-8")));
        HttpResponse response=httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();

        String str= EntityUtils.toString(entity,"UTF-8");
        JSONObject result= JSON.parseObject(str);
        return result;
    }
}
