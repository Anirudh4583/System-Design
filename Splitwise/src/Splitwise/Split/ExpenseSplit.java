package Splitwise.Split;

import java.util.Map;

import Splitwise.Model.Expense;

/**
 * Interface for expense splitting strategies
 */
public interface ExpenseSplit {
    Map<String, Double> calculateSplit(Expense expense);
}