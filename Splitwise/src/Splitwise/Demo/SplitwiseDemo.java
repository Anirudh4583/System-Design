package Splitwise.Demo;

import java.util.Arrays;
import java.util.Map;

import Splitwise.Split.SplitType;
import Splitwise.System.SplitwiseSystem;

/**
 * Main class to demonstrate the system
 */
public class SplitwiseDemo {
    public static void main(String[] args) {
        SplitwiseSystem splitwise = SplitwiseSystem.getInstance();

        // Add users
        splitwise.addUser("u1", "Alice", "alice@example.com");
        splitwise.addUser("u2", "Bob", "bob@example.com");
        splitwise.addUser("u3", "Charlie", "charlie@example.com");

        // Create equal split expense
        splitwise.splitExpense(
                "Dinner",
                300.0,
                "u1",
                SplitType.EQUAL,
                Arrays.asList("u1", "u2", "u3"),
                null);

        // Create percentage split expense
        splitwise.splitExpense(
                "Trip",
                1000.0,
                "u2",
                SplitType.PERCENTAGE,
                Arrays.asList("u1", "u2", "u3"),
                Arrays.asList(30.0, 40.0, 30.0));

        // Create exact split expense
        splitwise.splitExpense(
                "Groceries",
                150.0,
                "u3",
                SplitType.EXACT,
                Arrays.asList("u1", "u2", "u3"),
                Arrays.asList(50.0, 50.0, 50.0));

        // Show all expenses
        System.out.println("All Expenses:");
        splitwise.showExpenses().forEach(System.out::println);

        // Show expenses for specific user
        System.out.println("\nExpenses for Alice:");
        splitwise.showExpensesForUser("u1").forEach(System.out::println);

        // Show balances
        System.out.println("\nAll Balances:");
        Map<String, Map<String, Double>> allBalances = splitwise.showBalances();
        allBalances.forEach((userId, balances) -> {
            System.out.println("User: " + userId);
            balances.forEach((otherUser, amount) -> {
                if (amount > 0) {
                    System.out.println("  Owes you: " + otherUser + " -> $" + amount);
                } else if (amount < 0) {
                    System.out.println("  You owe: " + otherUser + " -> $" + (-amount));
                }
            });
        });
    }
}