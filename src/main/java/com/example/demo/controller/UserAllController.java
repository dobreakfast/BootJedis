package com.example.demo.controller;

import com.example.demo.pojo.MESUser;
import com.example.demo.pojo.User;
import com.example.demo.util.UserList;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/UserAll")
public class UserAllController {
    /**
     * User查全部
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/getAll",method = RequestMethod.POST)
    public List<MESUser> getAll(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println("接口已被调用");
        return UserList.t();
    }
    /**
     * 获取id 删除
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/delUser",method = RequestMethod.POST)
    public int delUser(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        System.out.println("按钮获取id");
        int id = Integer.parseInt(request.getParameter("id"));
        if (UserList.delUser(id)==1){
            return 1;
        }
        return 0;
    }
    /**
     * 新增，先判断该账号是否存在
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public int addUser(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        User u = new User(Math.toIntExact(UserList.getLastId()),name,pwd);
        long num = UserList.addUser(u);
        if (num>0){
            System.out.println("新增成功");
            return 1;
        }else {
            System.out.println("新增失败");
        }
        return 0;
    }
}
