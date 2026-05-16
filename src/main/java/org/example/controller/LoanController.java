package org.example.controller;

import org.example.data.product.Loan;
import org.example.data.product.Saving;
import org.example.repository.LoanRepository;
import org.example.utils.InputManager;
import org.example.utils.InterestCalculator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LoanController extends Controller{
    private final LoanRepository loanRepository = new LoanRepository();
    private final Scanner sc = new Scanner(System.in);

    private final Map<Integer, Runnable> methodMap = Map.of(
            1, this::join,
            2, this::getLoanSpec
    );

    @Override
    public void run() {
        System.out.println("==========================");
        System.out.println("[2] 대출을 선택하셨습니다.");
        System.out.println("도움받고자 하는 메뉴를 선택해주세요.");
        System.out.println();
        System.out.println();
        System.out.println("[1] 대출받기");
        System.out.println("[2] 조회");
        System.out.println();
        System.out.println();

        int number = InputManager.inputInt();
        System.out.println(number + "번을 선택하셨습니다.");

        getService(number);
    }

    private void getService(int number) {
        methodMap.getOrDefault(number, () -> {
            System.out.println("잘못된 입력입니다.");
            run();
        }).run();
    }

    private void join() {
        System.out.println("==========================");
        System.out.println("[1] 계좌개설을 선택하셨습니다.");

        System.out.print("계좌명을 입력해주세요: ");
        String productName = sc.next();

        System.out.print("사용자명을 입력해주세요: ");
        String userName = sc.next();

        BigDecimal principal = InputManager.inputMoney("대출받을 금액을 입력해주세요: ");

        System.out.print("원하시는 만기 개월을 입력해주세요: ");
        int duration = InputManager.inputInt();

        LocalDateTime createdAt = LocalDateTime.now();

        Loan savedLoanAccount = saveLoanProduct(userName, productName, createdAt, duration, principal);

        System.out.println();
        System.out.println("계좌명: " + savedLoanAccount.getProductName());
        System.out.println("사용자명: " + savedLoanAccount.getUserName());
        System.out.println("대출액: " + savedLoanAccount.getPrincipal());

        System.out.println("가입일: " + savedLoanAccount.getCreatedAt().truncatedTo(ChronoUnit.DAYS));
        System.out.println("만기일: " + savedLoanAccount.getCreatedAt().plusMonths(savedLoanAccount.getDuration()).truncatedTo(ChronoUnit.DAYS));

        BigDecimal interest = InterestCalculator.calculateLoanInterest(savedLoanAccount.getPrincipal(), Saving.ANNUAL_RATE);
        System.out.println("월이자 금액: " + interest);

        super.returnHomeList();
    }

    private void getLoanSpec() {
        System.out.println("==========================");
        System.out.println("[2] 조회를 선택하셨습니다.");
        System.out.println();
        System.out.println();

        List<Loan> loans = loanRepository.findAll();
        readProductList(loans);

        System.out.println("대출 목록을 선택하세요.");
        Long loanId = InputManager.inputLong(
                loans.stream()
                        .map(Loan::getLoanId)
                        .toList()
        );

        Loan findLoanId = loanRepository.findById(loanId);

        System.out.println();
        System.out.println("계좌명: " + findLoanId.getProductName());
        System.out.println("사용자명: " + findLoanId.getUserName());
        System.out.println("대출액: " + findLoanId.getPrincipal());

        System.out.println("가입일: " + findLoanId.getCreatedAt().truncatedTo(ChronoUnit.DAYS));
        System.out.println("만기일: " + findLoanId.getCreatedAt().plusMonths(findLoanId.getDuration()).truncatedTo(ChronoUnit.DAYS));

        System.out.println("연이자: " + Saving.ANNUAL_RATE + " %");

        BigDecimal interest = InterestCalculator.calculateLoanInterest(findLoanId.getPrincipal(), Saving.ANNUAL_RATE);
        System.out.println("월이자 금액: " + interest);

        super.returnHomeList();
    }

    private Loan saveLoanProduct(String userName, String productName, LocalDateTime createdAt, int duration, BigDecimal principal) {
        return loanRepository.save(new Loan(userName, productName, createdAt, duration, principal));
    }

    private void readProductList(List<Loan> loans) {
        loans.forEach(loan -> System.out.println("[" + loan.getLoanId() + "] " + loan.getProductName() + ", 연이자: " + Loan.ANNUAL_RATE));
        System.out.println();
    }

}
