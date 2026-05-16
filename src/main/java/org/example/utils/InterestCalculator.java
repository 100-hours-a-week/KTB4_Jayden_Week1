package org.example.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class InterestCalculator {

    // 예금 복리 이자 계산
    public static BigDecimal calculateSavingInterest(BigDecimal principal, BigDecimal annulRatePercent, int months) {
        // 1200은 12개월, 퍼센트를 소수로
        BigDecimal monthlyRate = annulRatePercent.divide(BigDecimal.valueOf(1200), MathContext.DECIMAL64);

        return principal.multiply(BigDecimal.ONE.add(monthlyRate).pow(months, MathContext.DECIMAL64))
                .subtract(principal).setScale(0, RoundingMode.HALF_UP);
    }

    // 만기일시상환 "매월" 이자 계산
    public static BigDecimal calculateLoanInterest(BigDecimal principal, BigDecimal annulRatePercent) {
        // 1200은 12개월, 퍼센트를 소수로
        return principal.multiply(annulRatePercent).divide(BigDecimal.valueOf(1200), 0, RoundingMode.HALF_UP);
    }
}
