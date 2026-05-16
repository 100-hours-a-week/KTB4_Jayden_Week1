package org.example.controller;

import org.example.data.product.Saving;
import org.example.repository.SavingRepository;
import org.example.utils.InputManager;
import org.example.utils.InterestCalculator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class SavingController extends Controller{
    private final InputManager inputManager = new InputManager();
    private final SavingRepository savingRepository = new SavingRepository();
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        System.out.println("==========================");
        System.out.println("[3] 예적금을 선택하셨습니다.");
        System.out.println("도움받고자 하는 메뉴를 선택해주세요.");
        System.out.println();
        System.out.println();
        System.out.println("[1] 계좌개설");
        System.out.println("[2] 조회");
        System.out.println();
        System.out.println();

        int number = inputManager.inputInt();
        System.out.println(number + "번을 선택하셨습니다.");

        getService(number);
    }

    private void getService(int number) {
        if (number == 1) {
            join();
        } else if (number == 2) {
            getSavingSpec();
        } else {
            System.out.println("잘못된 입력입니다.");
            run();
        }
    }

    private void join() {
        System.out.println("==========================");
        System.out.println("[1] 계좌개설을 선택하셨습니다.");

        System.out.print("계좌명을 입력해주세요: ");
        String productName = sc.next();

        System.out.print("사용자명을 입력해주세요: ");
        String userName = sc.next();

        BigDecimal principal = inputManager.inputMoney("초기 입금할 금액을 입력해주세요: ");

        System.out.print("원하시는 만기 개월을 입력해주세요: ");
        int duration = inputManager.inputInt();

        LocalDateTime createdAt = LocalDateTime.now();

        Saving savedSavingAccount = saveSavingAccount(productName, userName, createdAt, duration, principal);
        System.out.println("계좌명: " + savedSavingAccount.getProductName());
        System.out.println("사용자명: " + savedSavingAccount.getUserName());
        System.out.println("입금액: " + savedSavingAccount.getPrincipal());

        System.out.println("가입일: " + savedSavingAccount.getCreatedAt().truncatedTo(ChronoUnit.DAYS));
        System.out.println("만기일: " + savedSavingAccount.getCreatedAt().plusMonths(savedSavingAccount.getDuration()).truncatedTo(ChronoUnit.DAYS));

        System.out.println("연이율: " + Saving.ANNUAL_RATE + " %");

        BigDecimal interest = InterestCalculator.calculateSavingInterest(savedSavingAccount.getPrincipal(), Saving.ANNUAL_RATE, duration);
        System.out.println("이자 금액: " + savedSavingAccount.getPrincipal().add(interest));
        System.out.println("만료시 금액: " + savedSavingAccount.getPrincipal().add(interest));

        super.returnHomeList();
    }

    private void getSavingSpec() {
        System.out.println("==========================");
        System.out.println("[2] 조회를 선택하셨습니다.");
        System.out.println();
        System.out.println();

        List<Saving> savings = savingRepository.findAll();
        readAccountList(savings);

        System.out.println("계좌를 선택하세요.");
        Long savingId = inputManager.inputLong(
                savings.stream()
                        .map(Saving::getSavingId)
                        .toList()
        );

        Saving findSaving = savingRepository.findById(savingId);

        System.out.println();
        System.out.println("계좌명: " + findSaving.getProductName());
        System.out.println("사용자명: " + findSaving.getUserName());
        System.out.println("입금액: " + findSaving.getPrincipal());

        System.out.println("가입일: " + findSaving.getCreatedAt().truncatedTo(ChronoUnit.DAYS));
        System.out.println("만기일: " + findSaving.getCreatedAt().plusMonths(findSaving.getDuration()).truncatedTo(ChronoUnit.DAYS));

        System.out.println("연이율: " + Saving.ANNUAL_RATE + " %");

        BigDecimal interest = InterestCalculator.calculateSavingInterest(findSaving.getPrincipal(), Saving.ANNUAL_RATE, findSaving.getDuration());
        System.out.println("이자 금액: " + findSaving.getPrincipal().add(interest));
        System.out.println("만료시 금액: " + findSaving.getPrincipal().add(interest));

        super.returnHomeList();
    }

    private Saving saveSavingAccount(String userName, String productName, LocalDateTime createdAt, int duration, BigDecimal principal) {
        Saving saving = new Saving(userName, productName, createdAt, duration, principal);
        return savingRepository.save(saving);
    }

    private void readAccountList(List<Saving> savings) {
        savings.forEach(saving -> System.out.println("[" + saving.getSavingId() + "] " + saving.getProductName() + ", 연이율: " + Saving.ANNUAL_RATE));
        System.out.println();
    }

}
