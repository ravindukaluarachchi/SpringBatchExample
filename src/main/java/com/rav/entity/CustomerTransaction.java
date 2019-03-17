package com.rav.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomerTransaction {
    @Id
    private String transactionId;
    private String customerId;
    private String orderNo;
    private String item;
    private Integer quantity;

    public CustomerTransaction() {
    }

    public CustomerTransaction(String customerId, String orderNo, String item, Integer quantity) {
        this.customerId = customerId;
        this.orderNo = orderNo;
        this.item = item;
        this.quantity = quantity;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
                "" + customerId +
                "," + orderNo +
                "," + item +
                "," + quantity +
                '}';
    }
}
