package com.ruin.domain;

import java.io.Serializable;

/**
 * @author ruin
 * @date 2020/1/23-22:31
 */
public class Msg implements Serializable{
    Integer statusCode;
    Object content;
    String extra;

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "statusCode=" + statusCode +
                ", content=" + content +
                ", extra='" + extra + '\'' +
                '}';
    }
}
