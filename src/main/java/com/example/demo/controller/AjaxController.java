package com.example.demo.controller;

import com.example.demo.pojo.MESUser;
import com.example.demo.util.UserList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/Ajax")
@RestController
public class AjaxController {
    /**
     * 异步查询
     * @param numType
     * @param response
     * @return
     */
    @RequestMapping(value = "/getUser/{numType}",method = RequestMethod.POST)
    public List<MESUser> getOne(@PathVariable(value = "numType",
            required = false)String numType, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        return UserList.byNumTypeGetAll(numType);
    }
}
