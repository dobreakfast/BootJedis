package com.example.demo.util;

public class ResultUtil {
    //成功 带数据
    public static  Msg success(Object o){
        Msg msg = new Msg();
        msg.setCode(200);
        msg.setMsg("成功");
        msg.setData(o);
        return msg;
    }
    //成功 不带数据
    public static  Msg success(){
        return success(null);
    }
    //失败 异常！！！
    public  static Msg error(int code,String resultmsg){
        Msg m = new Msg();
        m.setCode(code);
        m.setMsg(resultmsg);
        return m;
    }
}
