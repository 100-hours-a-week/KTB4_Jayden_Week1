package org.example.utils;

import java.util.List;
import java.util.Scanner;

public class InputManager {
    private final Scanner sc = new Scanner(System.in);

    public int inputInt() {

        System.out.print("번호 선택: ");
        int result = sc.nextInt();

        if (result > 0) {
            return result;
        }

        System.out.println("잘못된 입력입니다.");
        return inputInt();
    }

    public Long inputLong(List<Long> ids) {
        System.out.print("번호 선택: ");
        Long result = sc.nextLong();

        if (ids.contains(result)) {
            return result;
        }

        System.out.println("잘못된 입력입니다.");
        return inputLong(ids);
    }

    public long inputMoney(String message) {
        System.out.print(message);
        long result = sc.nextLong();

        if (result >= 0) {
            return result;
        }

        System.out.println("잘못된 입력입니다.");
        return inputMoney(message);
    }

    public long inputMoney(Long amount, String message) {
        System.out.print(message);
        long result = sc.nextLong();

        if (result >= 0 && result <= amount) {
            return result;
        }

        System.out.println("잔액이 부족합니다.");
        return inputMoney(message);
    }

}
