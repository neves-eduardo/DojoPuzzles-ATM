package com.neves_eduardo.cash_machine;

import com.google.common.collect.Iterables;
import com.neves_eduardo.cash_machine.exception.NoNotesForTransactionException;
import org.apache.commons.lang3.EnumUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CashMachine {
    private List<AvailableNotes> listOfNotes;
    public CashMachine() {
        this.listOfNotes = EnumUtils.getEnumList(AvailableNotes.class);
        listOfNotes.sort(Comparator.comparingInt(AvailableNotes::getValue).reversed());
    }

    public Map<String,Integer> withdraw(int quantity) {
        Map<String, Integer> notesQuantity = new HashMap<>();
        int remaining = quantity;

        if(quantity % Iterables.getLast(listOfNotes).getValue() != 0) {
            throw new NoNotesForTransactionException("No notes for this transaction, please insert a value that is multiple by " + Iterables.getLast(listOfNotes).getValue());
        }

        for ( AvailableNotes note: listOfNotes) {
            int numberOfNotes = countNotes(remaining,note);
            remaining -= numberOfNotes*note.getValue();
            notesQuantity.put(note.name(),numberOfNotes);
            if(remaining == 0) break;
        }
        return notesQuantity;
    }

    private int countNotes(int value, AvailableNotes note) {
        return value/note.getValue();
    }


}
