package org.example.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class InputManager {
    private final static Scanner sc = new Scanner(System.in);

    public static int inputInt() {

        System.out.print("번호 선택: ");
        int result = sc.nextInt();

        if (result > 0) {
            return result;
        }

        System.out.println("잘못된 입력입니다.");
        return inputInt();
    }

    public static Long inputLong(List<Long> ids) {
        System.out.print("번호 선택: ");
        Long result = sc.nextLong();

        if (ids.contains(result)) {
            return result;
        }

        System.out.println("잘못된 입력입니다.");
        return inputLong(ids);
    }

    public static BigDecimal inputMoney(String message) {
        System.out.print(message);
        BigDecimal result = sc.nextBigDecimal();

        if (result.compareTo(BigDecimal.ZERO) >= 0) {
            return result;
        }

        System.out.println("잘못된 입력입니다.");
        return inputMoney(message);
    }

    public static BigDecimal inputMoney(BigDecimal amount, String message) {
        System.out.print(message);
        BigDecimal result = sc.nextBigDecimal();

        if (result.compareTo(BigDecimal.ZERO) >= 0 && result.compareTo(amount) <= 0) {
            return result;
        }

        System.out.println("잔액이 부족합니다.");
        return inputMoney(amount, message);
    }
}
