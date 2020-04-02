package com.neves_eduardo.cash_machine;

import com.neves_eduardo.cash_machine.atm.CashMachine;
import com.neves_eduardo.cash_machine.exception.MachineEmptyException;
import com.neves_eduardo.cash_machine.exception.NoNotesForTransactionException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CashMachine cashMachine = new CashMachine();
        System.out.println("Welcome to The Bank \nYour balance: ~INFINITE~");
        String response = "";
        while (!response.equals("n")) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please insert the desired amount to withdraw:");
                int value = scanner.nextInt();
                System.out.println("NOTES USED: \n" + cashMachine.withdraw(value));
                System.out.println("Make another withdrawal? (n to exit)");
                response = scanner.next();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input, please insert only numbers");
            } catch (NoNotesForTransactionException | MachineEmptyException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
