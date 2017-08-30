package com.gdzc.api.entity;

/**
 * Created by 王少岩 on 2017/2/5.
 */

public class HttpResult<T> {
    private String code;
    private String info;
    private T data;
    private String total;
    private String page_count;

    public boolean isSuccess(){
        return code.equals("1");
    }

    public String getCode() {
        return code;
    }

    public String getInfo(){
        return info;
    }

    public T getData() {
        return data;
    }

    public String getTotal() {
        return total;
    }

    public String getPage_count() {
        return page_count;
    }
}
