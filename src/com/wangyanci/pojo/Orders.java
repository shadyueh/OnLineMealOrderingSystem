package com.wangyanci.pojo;

import java.util.Date;

public class Orders {
    private String id;

    private Double money;

    private String receiverinfo;

    private Integer paystate;

    private Date ordertime;

    private Integer userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getReceiverinfo() {
        return receiverinfo;
    }

    public void setReceiverinfo(String receiverinfo) {
        this.receiverinfo = receiverinfo == null ? null : receiverinfo.trim();
    }

    public Integer getPaystate() {
        return paystate;
    }

    public void setPaystate(Integer paystate) {
        this.paystate = paystate;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}