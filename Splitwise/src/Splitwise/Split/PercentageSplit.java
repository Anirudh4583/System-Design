package Splitwise.Split;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Splitwise.Model.Expense;
import Splitwise.Model.User;

/**
 * Implementation for percentage split
 */
class PercentageExpenseSplit implements ExpenseSplit {
    @Override
    public Map<String, Double> calculateSplit(Expense expense) {
        Map<String, Double> shares = new HashMap<>();
        List<Double> percentages = expense.getSplitValues();

        if (percentages == null || percentages.size() != expense.getParticipants().size()) {
            throw new IllegalArgumentException("Number of percentages must match number of participants");
        }

        // Validate percentages sum to 100
        double sum = percentages.stream().mapToDouble(Double::doubleValue).sum();
        if (Math.abs(sum - 100.0) > 0.01) {
            throw new IllegalArgumentException("Percentages must sum to 100");
        }

        for (int i = 0; i < expense.getParticipants().size(); i++) {
            User participant = expense.getParticipants().get(i);
            double percentage = percentages.get(i);
            double share = expense.getAmount() * percentage / 100.0;

            // Round to 2 decimal places
            share = Math.round(share * 100.0) / 100.0;

            shares.put(participant.getUserId(), share);
        }

        return shares;
    }
}