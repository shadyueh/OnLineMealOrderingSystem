package com.wangyanci.pojo;

import java.util.Date;

public class OrderTab extends OrderTabKey {
    private Date begintime;

    private Date endtime;

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}