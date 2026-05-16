package org.example.data.account;

import org.example.data.FinancialContract;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Account extends FinancialContract {
    private Long accountId;
    private String accountName; // 계좌명
    private BigDecimal amount; // 계좌 잔액
    private LocalDateTime createdAt; // 계좌 개설날짜

    public Account(String userName, String accountName, BigDecimal amount, LocalDateTime createdAt) {
        super(userName);
        this.accountName = accountName;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
