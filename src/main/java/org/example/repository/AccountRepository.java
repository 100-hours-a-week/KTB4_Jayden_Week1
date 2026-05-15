package org.example.repository;

import org.example.data.account.Account;

import java.util.*;

public class AccountRepository {

    private static final Map<Long, Account> store = new HashMap<>();
    private static long sequence = 0L;

    public Account save(Account account) {
        account.setAccountId(++sequence);
        store.put(account.getAccountId(), account);
        return account;
    }

    public Account findById(Long accountId) {
        return store.get(accountId);
    }

    public List<Account> findAll() {
        return new ArrayList<>(store.values());
    }

    public void addMoney(Long accountId, Long money) {
        Account account = findById(accountId);
        Long amount = account.getAmount();
        account.setAmount(amount + money);
        store.put(accountId, account);
    }

    public void withdraw(Long accountId, Long money) {
        Account account = findById(accountId);
        Long amount = account.getAmount();
        account.setAmount(amount - money);
        store.put(accountId, account);
    }
}
