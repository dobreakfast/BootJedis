package com.example.demo.util;

import com.example.demo.pojo.MESUser;
import com.example.demo.pojo.User;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

public class UserList {

    private static Jedis j;
    /**
     * 查全部
     * @return
     */
    public static List<MESUser> t(){
        j = new Jedis("localhost");
        List<String> s = j.lrange("MESUser",0,j.llen("MESUser")-1);
        List<MESUser> bookList = new ArrayList<>();
        String[] strings;
        for (String s1 :s){
            strings = s1.split(",");
            bookList.add(new MESUser(strings[0],strings[1],strings[2]));
        }
        j.close();
        return bookList;
    }
    /**
     * 根据id查一个  可能该用户不存在！！！
     * @param id
     * @return
     */
    public static User byIdGetUser(int id){
        j = new Jedis("localhost");
        User u;
        List<String> s = j.lrange("User",0,j.llen("User")-1);
        String[] strings;
        for (int i = 0; i < s.size(); i++) {
            strings = s.get(i).split(",");
            if (Integer.parseInt(strings[0])==id){
                u = new User(Integer.parseInt(strings[0]),strings[1],strings[2]);
                return u;
            }
        }
        j.close();
        return new User();
    }
    /**
     * 删除
     * @param id
     * @return
     */
    public static long delUser(int id){
        j = new Jedis("localhost");
        List<String> s = j.lrange("User",0,j.llen("User")-1);
        String[] strings;
        long num=0;
        for (int i = 0; i < s.size(); i++) {
            strings = s.get(i).split(",");
            if (Integer.parseInt(strings[0])==id){
                num = j.lrem("User",1,s.get(i));
            }
        }
        j.close();
        return num;
    }
    /**
     * 新增
     * @param u
     * @return
     */
    public static long addUser(User u){
        j = new Jedis("localhost");
        long num =0;
        if (UserList.byIdGetUser(u.getId()).equals("用户不存在")){
            num = j.rpush("User",u.getId()+","+u.getName()+","+u.getPwd());
        }else {
            System.out.println("用户已存在");
        }

        j.close();
        return num;
    }
    /**
     * 查询最后一位用户的id，新增用户的id=最后一位用户的id+1
     * @return
     */
    public static long getLastId(){
        j = new Jedis("localhost");
        List<String> s = j.lrange("User",0,j.llen("User")-1);
        String[] ss =s.get(Math.toIntExact((j.llen("User") - 1))).split(",");
        long id= Long.parseLong(ss[0]);
        j.close();
        return id;
    }

    /**
     * 异步查询，根据url的不同传递的结果不同
     * @param numType
     * @return
     */
    public static List<MESUser> byNumTypeGetAll(String numType){
        j = new Jedis("localhost");
        List<String> s ;
        long size = j.llen("MESUser")-1;
        switch (numType){
            case "one":
                if (size>10){
                    s = j.lrange("MESUser",0,10);
                }else {
                    s = j.lrange("MESUser",0,size);
                }
                break;
            case "two":
                if (size>20){
                    s = j.lrange("MESUser",10,20);
                }else {
                    s = j.lrange("MESUser",10,size);
                }
                break;
            case "three":
                if (size>30){
                    s = j.lrange("MESUser",20,30);
                }else {
                    s = j.lrange("MESUser",20,size);
                }
                break;
            case "all":
                s = j.lrange("MESUser",0,size);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + numType+"，传递的参数未在switch内");
        }
        List<MESUser> bookList = new ArrayList<>();
        String[] strings;
        for (String s1 :s){
            strings = s1.split(",");
            bookList.add(new MESUser(strings[0],strings[1],strings[2]));
        }
        j.close();
        return bookList;
    }

    public static void main(String[] args) {
        //删除
        //UserList.delUser(2);

        //查全部
//        List<MESUser> u = UserList.t();
//        for (MESUser uu :u){
//            System.out.println(uu.getId()+", "+uu.getName());
//        }
        //异步查询
        List<MESUser> us = UserList.byNumTypeGetAll("all");
        for (MESUser u : us){
            System.out.println(u.getId()+" , "+u.getName());
        }

//        j = new Jedis("localhost");
//        List<String> s = j.lrange("User",0,j.llen("User")-1);
//        List<User> bookList = new ArrayList<>();
//        String[] strings = null;
//        for (String s1 :s){
//            strings = s1.split(",");
//            bookList.add(new User(Integer.parseInt(strings[0]),strings[1],strings[2]));
//        }
//        for (User c :bookList){
//            System.out.println(c.getName()+c.getPwd());
//        }
//        j.close();

//        //根据id查用户
//        User u= UserList.byIdGetUser(1);
//        if (u.getName()==null){
//            System.out.println("该用户不存在");
//        }else {
//            System.out.println(u.getId()+","+u.getName()+","+u.getPwd());
//        }
    }
}
