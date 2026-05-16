package org.example.repository;

import org.example.data.product.Loan;
import org.example.data.product.Saving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoanRepository {

    private static final Map<Long, Loan> store = new HashMap<>();
    private static long sequence = 0L;

    public Loan save(Loan loan) {
        loan.setLoanId(++sequence);
        store.put(loan.getLoanId(), loan);
        return loan;
    }

    public Loan findById(Long loanId) {
        return store.get(loanId);
    }

    public List<Loan> findAll() {
        return new ArrayList<>(store.values());
    }
}
