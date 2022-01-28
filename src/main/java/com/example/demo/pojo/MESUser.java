package com.example.demo.pojo;

public class MESUser {
    private String id;
    private String name;
    private String data;

    public MESUser() {
    }

    public MESUser(String id, String name, String data) {
        this.id = id;
        this.name = name;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MESUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
