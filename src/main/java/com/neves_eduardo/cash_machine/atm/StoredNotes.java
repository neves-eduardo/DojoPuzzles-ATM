package com.neves_eduardo.cash_machine.atm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class StoredNotes {

    private static final List<Note> storedNotes = new ArrayList<>();

    public static List<Note> getStoredNotes() {
        return storedNotes;
    }

    public static BigDecimal getTotalAmountOfCash() {
        return storedNotes.stream()
                .map(s -> s.getValue().multiply(BigDecimal.valueOf(s.getNumberOfNotesStored())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);
    }

}
