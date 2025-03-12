package Splitwise.Split;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Splitwise.Model.Expense;
import Splitwise.Model.User;

/**
 * Implementation for exact split
 */
class ExactExpenseSplit implements ExpenseSplit {
    @Override
    public Map<String, Double> calculateSplit(Expense expense) {
        Map<String, Double> shares = new HashMap<>();
        List<Double> exactAmounts = expense.getSplitValues();

        if (exactAmounts == null || exactAmounts.size() != expense.getParticipants().size()) {
            throw new IllegalArgumentException("Number of exact amounts must match number of participants");
        }

        // Validate exact amounts sum to total
        double sum = exactAmounts.stream().mapToDouble(Double::doubleValue).sum();
        if (Math.abs(sum - expense.getAmount()) > 0.01) {
            throw new IllegalArgumentException("Exact amounts must sum to the total expense amount");
        }

        for (int i = 0; i < expense.getParticipants().size(); i++) {
            User participant = expense.getParticipants().get(i);
            double amount = exactAmounts.get(i);
            shares.put(participant.getUserId(), amount);
        }

        return shares;
    }
}