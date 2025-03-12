package Splitwise.System;

import java.util.*;
import java.util.stream.Collectors;

import Splitwise.Model.Expense;
import Splitwise.Model.User;
import Splitwise.Split.ExpenseSplit;
import Splitwise.Split.ExpenseSplitFactory;
import Splitwise.Split.SplitType;

/**
 * Main class that implements the Splitwise System
 */
public class SplitwiseSystem {
    private static SplitwiseSystem instance;
    private final Map<String, User> users;
    private final List<Expense> expenses;
    private final ExpenseSplitFactory splitFactory;

    private SplitwiseSystem() {
        this.users = new HashMap<>();
        this.expenses = new ArrayList<>();
        this.splitFactory = new ExpenseSplitFactory();
    }

    public static synchronized SplitwiseSystem getInstance() {
        if (instance == null) {
            instance = new SplitwiseSystem();
        }
        return instance;
    }

    /**
     * Add a new user to the system
     * 
     * @param userId Unique identifier for the user
     * @param name   Name of the user
     * @param email  Email of the user
     * @return The created User object
     */
    public User addUser(String userId, String name, String email) {
        if (users.containsKey(userId)) {
            throw new IllegalArgumentException("User ID already exists: " + userId);
        }

        User newUser = new User(userId, name, email);
        users.put(userId, newUser);
        return newUser;
    }

    /**
     * Split an expense among users
     * 
     * @param description        Description of the expense
     * @param amount             Total amount of the expense
     * @param paidByUserId       ID of the user who paid
     * @param splitType          Type of split (EQUAL, PERCENTAGE, EXACT)
     * @param participantUserIds IDs of users participating in the expense
     * @param splitValues        Values for the split (percentages or exact amounts)
     * @return The created Expense object
     */
    public Expense splitExpense(String description, double amount, String paidByUserId,
            SplitType splitType, List<String> participantUserIds, List<Double> splitValues) {
        // Validate the payer exists
        User paidBy = getUserById(paidByUserId);

        // Validate all participants exist
        List<User> participants = participantUserIds.stream()
                .map(this::getUserById)
                .collect(Collectors.toList());

        // Create split strategy based on type
        ExpenseSplit splitStrategy = splitFactory.createSplit(splitType);

        // Build the expense
        Expense expense = new Expense.ExpenseBuilder()
                .setDescription(description)
                .setAmount(amount)
                .setPaidBy(paidBy)
                .setParticipants(participants)
                .setSplitType(splitType)
                .setSplitValues(splitValues)
                .build();

        // Calculate split and update balances
        Map<String, Double> shares = splitStrategy.calculateSplit(expense);
        updateBalances(paidBy, shares);

        // Add to expenses list
        expenses.add(expense);
        return expense;
    }

    /**
     * Show all expenses in the system
     * 
     * @return List of all expenses
     */
    public List<Expense> showExpenses() {
        return new ArrayList<>(expenses);
    }

    /**
     * Show expenses for a specific user
     * 
     * @param userId ID of the user
     * @return List of expenses involving the user
     */
    public List<Expense> showExpensesForUser(String userId) {
        User user = getUserById(userId);

        return expenses.stream()
                .filter(expense -> expense.getPaidBy().equals(user) ||
                        expense.getParticipants().contains(user))
                .collect(Collectors.toList());
    }

    /**
     * Show balances for all users
     * 
     * @return Map of user balances
     */
    public Map<String, Map<String, Double>> showBalances() {
        Map<String, Map<String, Double>> allBalances = new HashMap<>();

        users.values().forEach(user -> {
            allBalances.put(user.getUserId(), new HashMap<>(user.getBalances()));
        });

        return allBalances;
    }

    /**
     * Show balance for a specific user
     * 
     * @param userId ID of the user
     * @return Map of balances with other users
     */
    public Map<String, Double> showBalanceForUser(String userId) {
        User user = getUserById(userId);
        return new HashMap<>(user.getBalances());
    }

    private User getUserById(String userId) {
        User user = users.get(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + userId);
        }
        return user;
    }

    private void updateBalances(User paidBy, Map<String, Double> shares) {
        String payerId = paidBy.getUserId();

        shares.forEach((userId, amount) -> {
            if (!userId.equals(payerId) && amount > 0) {
                // Update payer's balance with others
                paidBy.updateBalance(userId, amount);

                // Update participant's balance with payer
                User participant = users.get(userId);
                participant.updateBalance(payerId, -amount);
            }
        });
    }
}
