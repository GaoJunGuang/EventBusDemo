package com.gjg.eventbusdemo;

/**
 * Created by JunGuang_Gao
 * on 2017/2/22  21:53.
 * 类描述：
 */

public class MessageEvent {
    public String name;
    public String password;

    public MessageEvent(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
