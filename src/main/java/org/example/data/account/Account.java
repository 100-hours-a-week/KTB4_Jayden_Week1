package org.example.data.account;

import java.time.LocalDateTime;

public class Account {
    private Long accountId;
    private String accountName; // 계좌명
    private String userName; // 소유자
    private Long amount; // 계좌 잔액
    private LocalDateTime createdAt; // 계좌 개설날짜

    // 생성시 반드시 초기값을 넣도록 막아두기
    private Account() {
    }

    public Account(String accountName, String userName, Long amount, LocalDateTime createdAt) {
        this.accountName = accountName;
        this.userName = userName;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
