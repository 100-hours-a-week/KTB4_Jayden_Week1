package org.example.adapter;

import org.example.controller.AccountController;
import org.example.controller.Controller;
import org.example.controller.LoanController;
import org.example.controller.SavingController;

public class ListAdapter {


    public Controller getController(int number) {
        if (number == 1) {
            System.out.println("입력 확인 " + number);
            return new AccountController();
        }

        if (number == 2) {
            System.out.println("입력 확인 " + number);
            return new LoanController();
        }

        if (number == 3) {
            System.out.println("입력 확인 " + number);
            return new SavingController();
        }

        if (number == 4) {
            System.out.println("입력 확인 " + number);
            shutDown();
        }

        System.out.println("잘못된 입력입니다: " + number);
        return new Controller();
    }

    private void shutDown() {
        Controller controller = new Controller();
        controller.shutDown();
    }
}
