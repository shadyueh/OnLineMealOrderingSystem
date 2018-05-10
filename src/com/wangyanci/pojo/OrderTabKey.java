package com.wangyanci.pojo;

public class OrderTabKey {
    private String orderId;

    private String tabId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId == null ? null : tabId.trim();
    }
}