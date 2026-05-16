package org.example.data.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Saving extends Product{
    private Long savingId;
    private BigDecimal principal;

    public static final BigDecimal ANNUAL_RATE = BigDecimal.valueOf(2.9);

    public Saving(String userName, String productName, LocalDateTime createdAt, int duration, BigDecimal principal) {
        super(userName, productName, createdAt, duration);
        this.principal = principal;
    }

    public Long getSavingId() {
        return savingId;
    }

    public void setSavingId(Long savingId) {
        this.savingId = savingId;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }
}
