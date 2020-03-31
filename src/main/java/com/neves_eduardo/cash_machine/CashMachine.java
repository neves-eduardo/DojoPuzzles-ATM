package com.neves_eduardo.cash_machine;

import com.google.common.collect.Iterables;
import org.apache.commons.lang3.EnumUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.EnumUtils.getEnumList;

public class CashMachine {
    private List<AvailableMonetaryUnits> listOfNotes;
    private Map<String, Integer> notesQuantity = new HashMap<>();
    public CashMachine() {
        this.listOfNotes = EnumUtils.getEnumList(AvailableMonetaryUnits.class);
        listOfNotes.sort(Comparator.comparingInt(AvailableMonetaryUnits::getValue).reversed());
    }

    public int withdraw(int quantity) {
        int remaining = quantity;
        if(quantity % Iterables.getLast(listOfNotes).getValue() != 0) {
            System.out.println("Please insert a value that is multiple by" + Iterables.getLast(listOfNotes).getValue());
        }
        for ( AvailableMonetaryUnits note: listOfNotes) {
            remaining -= countNotes(remaining,note)*note.getValue();
            if(remaining == 0) break;
        }
        System.out.println(notesQuantity);
        return remaining;
    }

    private int countNotes(int value, AvailableMonetaryUnits note) {
        int numberOfNotes = value/note.getValue();
        notesQuantity.put(note.name(),numberOfNotes);
        return numberOfNotes;
    }


}
