package org.example;

import org.example.controller.Controller;
import org.example.adapter.ListAdapter;
import org.example.utils.InputManager;

import java.util.Scanner;

public class Home {
    private final ListAdapter listAdapter = new ListAdapter();
    private final InputManager inputManager = new InputManager();



    public void home() {
        System.out.println("====================================");
        System.out.println("안녕하세요. 반갑습니다.");
        System.out.println("Jayden 은행입니다.");
        homeList();
    }

    public void homeList() {
        System.out.println("====================================");
        System.out.println();
        System.out.println("무엇을 도와드릴까요?");
        System.out.println();
        System.out.println();

        System.out.println("[1] 내 계좌/입출금");
        System.out.println("[2] 대출");
        System.out.println("[3] 예적금");
        System.out.println("[4] 종료");
        System.out.println();
        System.out.println();

        int number = inputManager.inputInt();

        System.out.println(number + " 번을 선택하셨습니다.");
        Controller controller = listAdapter.getController(number);
        controller.run();
    }
}
