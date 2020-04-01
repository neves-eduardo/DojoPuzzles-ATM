package com.neves_eduardo.cash_machine;

import com.neves_eduardo.cash_machine.exception.NoNotesForTransactionException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CashMachine cashMachine = new CashMachine();
        System.out.println("Welcome to The Bank \nYour balance: infinite");
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please insert the desired amount to withdraw:");
            int z = scanner.nextInt();
            System.out.println(cashMachine.withdraw(z));
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, please insert only numbers");
        } catch (NoNotesForTransactionException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
