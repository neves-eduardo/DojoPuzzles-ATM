package com.neves_eduardo.cash_machine;

import com.google.common.collect.Iterables;
import com.neves_eduardo.cash_machine.exception.NoNotesForTransactionException;
import org.apache.commons.lang3.EnumUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CashMachine {
    private List<AvailableNotes> listOfNotes;
    private int totalAmountOfCash;
    public CashMachine() {
        this.listOfNotes = EnumUtils.getEnumList(AvailableNotes.class);
        listOfNotes.sort(Comparator.comparingInt(AvailableNotes::getValue).reversed());
        totalAmountOfCash = listOfNotes.stream().mapToInt(n -> n.getValue() * n.getNumberOfNotesStored()).sum();
    }

    public Map<String,Integer> withdraw(int quantity) {
        Map<String, Integer> notesQuantity = new HashMap<>();
        int smallestAvailableNoteValue = listOfNotes.stream().filter(s -> s.getNumberOfNotesStored() != 0).sorted(Comparator.reverseOrder()).collect(Collectors.toList()).get(0).getValue();
        int remaining = quantity;

        if(quantity > totalAmountOfCash) {
            throw new NoNotesForTransactionException("Not enough notes available for this withdrawal, please insert a smaller value");
        }
        if(quantity % smallestAvailableNoteValue != 0) {
            throw new NoNotesForTransactionException("No notes for this transaction, please insert a value that is multiple by " + smallestAvailableNoteValue);
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
        int numberOfNotesWithdrawn = value/note.getValue();
        if(numberOfNotesWithdrawn > note.getNumberOfNotesStored()) {numberOfNotesWithdrawn = note.getNumberOfNotesStored();}
        note.setNumberOfNotesStored(note.getNumberOfNotesStored() - numberOfNotesWithdrawn);
        System.out.println(note.getNumberOfNotesStored());
        return numberOfNotesWithdrawn;
    }


}
