package org.example.repository;

import org.example.data.product.Saving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SavingRepository {

    private static final Map<Long, Saving> store = new HashMap<>();
    private static long sequence = 0L;

    public Saving save(Saving saving) {
        saving.setSavingId(++sequence);
        store.put(saving.getSavingId(), saving);
        return saving;
    }

    public Saving findById(Long savingId) {
        return store.get(savingId);
    }

    public List<Saving> findAll() {
        return new ArrayList<>(store.values());
    }
}
