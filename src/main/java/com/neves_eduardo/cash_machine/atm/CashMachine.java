package com.neves_eduardo.cash_machine.atm;

import com.neves_eduardo.cash_machine.exception.MachineEmptyException;
import com.neves_eduardo.cash_machine.exception.NoNotesForTransactionException;


import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CashMachine {

    private List<Note> listOfNotes;
    private BigDecimal totalAmountOfCash;
    private static final String CURRENCY = "R$";

    public CashMachine(List<Note> listOfNotes) {
        this.listOfNotes = listOfNotes;
        this.totalAmountOfCash = StoredNotes.getTotalAmountOfCash();

    }

    public Map<String,Integer> withdraw(BigDecimal quantityToWithdraw) {
        if(totalAmountOfCash.compareTo(BigDecimal.ZERO) == 0) {throw new MachineEmptyException("Machine Empty! Please use another one.");}
        Map<String, Integer> notesQuantity = new HashMap<>();
        BigDecimal smallestAvailableNoteValue = getSmallestNoteAvailable();
        BigDecimal remainingToWithdraw = quantityToWithdraw;
        validateWithdrawalAmount(quantityToWithdraw,smallestAvailableNoteValue);

        for ( Note note: listOfNotes) {
            int usedOfNotes = countUsedNotes(remainingToWithdraw,note);
            remainingToWithdraw = remainingToWithdraw.subtract(note.getValue().multiply(BigDecimal.valueOf(usedOfNotes)));
            if(usedOfNotes != 0) notesQuantity.put(CURRENCY + note.getValue().toString(),usedOfNotes);
            if(remainingToWithdraw.compareTo(BigDecimal.ZERO) == 0) break;
        }
        totalAmountOfCash = totalAmountOfCash.subtract(quantityToWithdraw);
        return notesQuantity;
    }

    private int countUsedNotes(BigDecimal value, Note note) {
        int numberOfNotesWithdrawn = value.intValue()/note.getValue().intValue();
        if(numberOfNotesWithdrawn > note.getNumberOfNotesStored()) {numberOfNotesWithdrawn = note.getNumberOfNotesStored();}
        note.setNumberOfNotesStored(note.getNumberOfNotesStored() - numberOfNotesWithdrawn);
        return numberOfNotesWithdrawn;
    }

    private void validateWithdrawalAmount(BigDecimal quantityToWithdraw, BigDecimal smallestAvailableNoteValue) {
        if(quantityToWithdraw.compareTo(BigDecimal.ZERO) < 0){throw new IllegalArgumentException("Invalid Input: Please insert only positive numbers");}

        if(quantityToWithdraw.compareTo(totalAmountOfCash) > 0) {
            throw new NoNotesForTransactionException(
                    "Not enough notes available for this withdrawal, maximum withdrawal amount: " + totalAmountOfCash);
        }
        if(!(quantityToWithdraw.remainder(smallestAvailableNoteValue).compareTo(BigDecimal.ZERO) == 0)) {
            throw new NoNotesForTransactionException(
                    "No notes for this transaction, please insert a value that is multiple by " + smallestAvailableNoteValue);
        }
    }

    private BigDecimal getSmallestNoteAvailable() {
        return this.listOfNotes.stream()
                .filter(s -> s.getNumberOfNotesStored() != 0)
                .sorted(Comparator.comparing(Note::getValue))
                .collect(Collectors.toList())
                .get(0)
                .getValue();
    }

}
