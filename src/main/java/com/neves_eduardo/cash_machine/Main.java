package com.neves_eduardo.cash_machine;

import com.neves_eduardo.cash_machine.atm.CashMachine;
import com.neves_eduardo.cash_machine.atm.Note;
import com.neves_eduardo.cash_machine.atm.StoredNotes;
import com.neves_eduardo.cash_machine.exception.MachineEmptyException;
import com.neves_eduardo.cash_machine.exception.NoNotesForTransactionException;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        addNotesToATM();
        System.out.println(new BigDecimal("100.00").remainder(new BigDecimal("10.00")).compareTo(BigDecimal.ZERO));
        CashMachine cashMachine = new CashMachine(StoredNotes.getStoredNotes());
        System.out.println("Welcome to The Bank \nYour balance: ~INFINITE~");
        String response = "";
        while (!response.equals("n")) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please insert the desired amount to withdraw:");
                String value = scanner.next();
                System.out.println("NOTES USED: \n" + cashMachine.withdraw(new BigDecimal(value)));
                System.out.println("Make another withdrawal? (n to exit)");
                response = scanner.next();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input, please insert only numbers");
            } catch (NoNotesForTransactionException | MachineEmptyException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    public static void addNotesToATM() {
        StoredNotes.getStoredNotes().add(new Note(new BigDecimal("100.00"),100));
        StoredNotes.getStoredNotes().add(new Note(new BigDecimal("50.00"),100));
        StoredNotes.getStoredNotes().add(new Note(new BigDecimal("20.00"),100));
        StoredNotes.getStoredNotes().add(new Note(new BigDecimal("10.00"),100));
    }

}
