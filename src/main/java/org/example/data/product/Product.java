package org.example.data.product;

import org.example.data.User;

import java.time.LocalDateTime;

public class Product extends User {
    private String productName;
    private LocalDateTime createdAt;
    private int duration;

    public Product(String userName, String productName, LocalDateTime createdAt, int duration) {
        super(userName);
        this.productName = productName;
        this.createdAt = createdAt;
        this.duration = duration;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
