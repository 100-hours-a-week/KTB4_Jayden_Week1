package org.example.controller;

import org.example.data.account.Account;
import org.example.repository.AccountRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class AccountController extends Controller {
    private final AccountRepository accountRepository = new AccountRepository();
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        System.out.println("==========================");
        System.out.println("[1] 내 계좌/입출금을 선택하셨습니다.");
        System.out.println("도움받고자 하는 메뉴를 선택해주세요.");
        System.out.println("");
        System.out.println("");
        System.out.println("[1] 계좌개설");
        System.out.println("[2] 입금");
        System.out.println("[3] 출금");
        System.out.println("[4] 이체");
        System.out.println("");
        System.out.println("");

        int number = inputInt();
        System.out.println(number + "번을 선택하셨습니다.");

        getAction(number);
    }

    private int inputInt() {
        System.out.print("번호 선택: ");
        return sc.nextInt();
    }

    private void getAction(int number) {
        if (number == 1) {
            join();
        } else if (number == 2) {
            addMoney();
        } else if (number == 3) {
            withdraw();
        } else if (number == 4) {
            transfer();
        } else {
            System.out.println("잘못된 입력입니다.");
            System.out.println("==========================");
            run();
        }
    }

    void join() {
        System.out.println("==========================");
        System.out.println("[1] 계좌개설을 선택하셨습니다.");

        System.out.print("계좌명을 입력해주세요: ");
        String accountName = sc.next();

        System.out.print("사용자명을 입력해주세요: ");
        String userName = sc.next();

        System.out.print("초기 입금할 금액을 입력해주세요: ");
        Long money = sc.nextLong();

        LocalDateTime createdAt = LocalDateTime.now();

        Account savedAccount = saveAccount(accountName, userName, money, createdAt);

        System.out.println("개설이 완료되었습니다.");
        System.out.println("계좌명: " + savedAccount.getAccountName());
        System.out.println("사용자명: " + savedAccount.getUserName());
        System.out.println("잔액: " + savedAccount.getAmount());
        System.out.println("개설일: " + savedAccount.getCreatedAt().truncatedTo(ChronoUnit.SECONDS));
        System.out.println("");

        super.returnHome();
    }

    private Account saveAccount(String accountName, String userName, Long money, LocalDateTime createdAt) {
        Account account = new Account(accountName, userName, money, createdAt);
        return accountRepository.save(account);
    }

    void addMoney() {
        System.out.println("==========================");
        System.out.println("[2] 입금을 선택하셨습니다.");
        System.out.println("");
        System.out.println("");

        List<Account> accounts = accountRepository.findAll();

        System.out.println("계좌를 선택해주세요: ");
        accounts.stream()
                .forEach(account -> System.out.println("[" + account.getAccountId() + "] " + account.getAccountName()));

        long accountId = inputInt();
        System.out.println(accountId + "번을 선택하셨습니다.");

        System.out.print("입금할 금액을 입력해주세요: ");
        Long money = sc.nextLong();

        accountRepository.addMoney(accountId, money);
        Account findAccount = accountRepository.findById(accountId);

        System.out.println("입금이 완료되었습니다.");
        System.out.println("");
        System.out.println("계좌 잔액: " + findAccount.getAmount());

        super.returnHome();

    }

    void withdraw() {
        System.out.println("==========================");

    }

    void transfer() {
        System.out.println("==========================");

    }
}
