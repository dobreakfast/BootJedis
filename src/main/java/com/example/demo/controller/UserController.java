package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.util.Msg;
import com.example.demo.util.ResultUtil;
import com.example.demo.util.UserList;
import com.sun.deploy.net.HttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
@RequestMapping("/user")
@RestController
public class UserController {
    /**
     * 登录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Msg login(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println("登录接口已被调用");
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        System.out.println(name+"  c,c  "+pwd);
        Jedis j = new Jedis("localhost");
        List<String> s = j.lrange("User",0,j.llen("User")-1);
        List<User> u = new ArrayList<>();
        for (String ss : s){
            String[] sl = ss.split(",");
            u.add(new User(Integer.parseInt(sl[0]),sl[1],sl[2]));
            System.out.println(sl[1]+" , "+sl[2]);
        }
        for (User uu : u) {
            if (uu.getName().equals(name)&&uu.getPwd().equals(pwd)){
                return ResultUtil.success();
            }else if (uu.getName().equals(name) || uu.getPwd().equals(pwd)) {
                if (uu.getName().equals(name)) {
                    return ResultUtil.error(-203, "密码错误");
                }
            }
        }
        return ResultUtil.error(-202, "账号未注册");
    }

    /**
     * 关闭Jedis
     */
    @RequestMapping(value = "/goOut",method = RequestMethod.GET)
    public void down() {
        UserList.closeJedis();
    }
}
