package org.example.adapter;

import org.example.controller.AccountController;
import org.example.controller.Controller;
import org.example.controller.LoanController;
import org.example.controller.SavingController;

import java.util.HashMap;
import java.util.Map;

public class ListAdapter {
    private final static Map<Integer, Controller> controllerMap = Map.of(
            1, new AccountController(),
            2, new LoanController(),
            3, new SavingController(),
            4, new Controller()
    );

    public Controller getController(int number) {
        if (!controllerMap.containsKey(number)) {
            System.out.println("잘못된 입력입니다: " + number);
            return new Controller();
        }
        return controllerMap.get(number);
    }
}
