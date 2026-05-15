package org.example.controller;

import org.example.data.account.Account;
import org.example.repository.AccountRepository;
import org.example.utils.InputManager;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class AccountController extends Controller {
    private final InputManager inputManager = new InputManager();
    private final AccountRepository accountRepository = new AccountRepository();
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void run() {
        System.out.println("==========================");
        System.out.println("[1] 내 계좌/입출금을 선택하셨습니다.");
        System.out.println("도움받고자 하는 메뉴를 선택해주세요.");
        System.out.println();
        System.out.println();
        System.out.println("[1] 계좌개설");
        System.out.println("[2] 입금");
        System.out.println("[3] 출금");
        System.out.println("[4] 이체");
        System.out.println();
        System.out.println();

        int number = inputManager.inputInt();
        System.out.println(number + "번을 선택하셨습니다.");

        getService(number);
    }

    public void join() {
        System.out.println("==========================");
        System.out.println("[1] 계좌개설을 선택하셨습니다.");

        System.out.print("계좌명을 입력해주세요: ");
        String accountName = sc.next();

        System.out.print("사용자명을 입력해주세요: ");
        String userName = sc.next();

        Long money = inputManager.inputMoney("초기 입금할 금액을 입력해주세요: ");

        LocalDateTime createdAt = LocalDateTime.now();

        Account savedAccount = saveAccount(accountName, userName, money, createdAt);

        System.out.println("개설이 완료되었습니다.");
        System.out.println("계좌명: " + savedAccount.getAccountName());
        System.out.println("사용자명: " + savedAccount.getUserName());
        System.out.println("잔액: " + savedAccount.getAmount());
        System.out.println("개설일: " + savedAccount.getCreatedAt().truncatedTo(ChronoUnit.SECONDS));
        System.out.println();

        super.returnHomeList();
    }

    public void addMoney() {
        System.out.println("==========================");
        System.out.println("[2] 입금을 선택하셨습니다.");
        System.out.println();
        System.out.println();

        List<Account> accounts = accountRepository.findAll();
        readAccountList(accounts);

        System.out.println("계좌를 선택하세요.");
        Long accountId = inputManager.inputLong(
                accounts.stream()
                        .map(Account::getAccountId)
                        .toList()
        );

        Long money = inputManager.inputMoney("입금할 금액을 입력해주세요: ");

        accountRepository.addMoney(accountId, money);
        Account findAccount = accountRepository.findById(accountId);

        System.out.println("입금이 완료되었습니다.");
        System.out.println();
        System.out.println("계좌 잔액: " + findAccount.getAmount());

        super.returnHomeList();
    }

    public void withdraw() {
        System.out.println("==========================");
        System.out.println("[3] 출금을 선택하셨습니다.");
        System.out.println();
        System.out.println();

        List<Account> accounts = accountRepository.findAll();
        readAccountList(accounts);

        System.out.println("계좌를 선택하세요.");
        Long accountId = inputManager.inputLong(
                accounts.stream()
                        .map(Account::getAccountId)
                        .toList()
        );
        Long amount = accountRepository.findById(accountId).getAmount();

        Long money = inputManager.inputMoney(amount, "출금할 금액을 입력해주세요: ");

        accountRepository.withdraw(accountId, money);
        Account findAccount = accountRepository.findById(accountId);

        System.out.println("출금이 완료되었습니다.");
        System.out.println();
        System.out.println("계좌 잔액: " + findAccount.getAmount());

        super.returnHomeList();
    }

    public void transfer() {
        System.out.println("==========================");
        System.out.println("[4] 이체을 선택하셨습니다.");
        System.out.println();
        System.out.println();

        List<Account> accounts = accountRepository.findAll();
        readAccountList(accounts);

        System.out.println("계좌를 선택하세요.");
        Long accountId = inputManager.inputLong(
                accounts.stream()
                        .map(Account::getAccountId)
                        .toList()
        );
        Long amount = accountRepository.findById(accountId).getAmount();

        System.out.print("계좌명을 입력해주세요: ");
        String toUser = sc.next();

        Long money = inputManager.inputMoney(amount, "이체할 금액을 입력해주세요: ");

        accountRepository.withdraw(accountId, money);
        Account findAccount = accountRepository.findById(accountId);

        System.out.println("이체가 완료되었습니다.");
        System.out.println();
        System.out.println("받은 사람: " + toUser);
        System.out.println("이체액: " + money);
        System.out.println("계좌 잔액: " + findAccount.getAmount());

        super.returnHomeList();
    }

    private void getService(int number) {
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
            run();
        }
    }

    private Account saveAccount(String accountName, String userName, Long money, LocalDateTime createdAt) {
        Account account = new Account(accountName, userName, money, createdAt);
        return accountRepository.save(account);
    }

    private void readAccountList(List<Account> accounts) {
        accounts.forEach(account -> System.out.println("[" + account.getAccountId() + "] " + account.getAccountName()));
        System.out.println();
    }
}
