package Splitwise.Split;

import java.util.HashMap;
import java.util.Map;

import Splitwise.Model.Expense;
import Splitwise.Model.User;

/**
 * Implementation for equal split
 */
class EqualExpenseSplit implements ExpenseSplit {
    @Override
    public Map<String, Double> calculateSplit(Expense expense) {
        Map<String, Double> shares = new HashMap<>();
        int numParticipants = expense.getParticipants().size();
        double share = expense.getAmount() / numParticipants;

        // Round to 2 decimal places
        share = Math.round(share * 100.0) / 100.0;

        for (User participant : expense.getParticipants()) {
            shares.put(participant.getUserId(), share);
        }

        return shares;
    }
}