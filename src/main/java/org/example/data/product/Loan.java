package org.example.data.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Loan extends Product {
    private Long loanId;
    private BigDecimal principal;

    public static final BigDecimal ANNUAL_RATE = BigDecimal.valueOf(3.5);

    public Loan(String productName, String userName, LocalDateTime createdAt, int duration, BigDecimal principal) {
        super(productName, userName, createdAt, duration);
        this.principal = principal;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }
}
