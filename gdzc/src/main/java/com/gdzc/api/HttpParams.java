package com.gdzc.api;


import java.util.HashMap;
import java.util.Map;


/**
 * Created by 王少岩 on 2016/9/14.
 */
public class HttpParams {

    /**
     * @param keyword
     * @param page
     * @return
     */
    public static Map<String, String> getIndexParam(String keyword, String page) {
        Map<String, String> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("page", page);
        map.put("pagesize", "10");
        return map;
    }

    /**
     * @param id
     * @return
     */
    public static Map<String, String> getInfoParam(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        return map;
    }

    /**
     * 预约报名
     *
     * @param id
     * @return
     */
    public static Map<String, String> getPageParam(String id, String page) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("page", page);
        map.put("pagesize", "10");
        return map;
    }

    /**登录
     * @param username
     * @param password
     * @return
     */
    public static Map<String, String> userLogin(String username, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        return map;
    }

}
