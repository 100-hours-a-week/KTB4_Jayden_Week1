package org.example.adapter;

import org.example.controller.AccountController;
import org.example.controller.Controller;
import org.example.controller.LoanController;
import org.example.controller.SavingController;

import java.util.Map;

public class ListAdapter {
    private final Map<Integer, Controller> controllerMap = Map.of(
            1, new AccountController(),
            2, new LoanController(),
            3, new SavingController(),
            4, new Controller()
    );

    public Controller getController(int number) {
        return controllerMap.getOrDefault(number, new Controller());
    }
}
