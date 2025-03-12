package Splitwise.Model;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import Splitwise.Split.SplitType;

/**
 * Expense entity class
 */
public class Expense {
    private final String id;
    private final String description;
    private final double amount;
    private final User paidBy;
    private final List<User> participants;
    private final SplitType splitType;
    private final List<Double> splitValues;
    private final Date createdAt;

    private Expense(ExpenseBuilder builder) {
        this.id = UUID.randomUUID().toString();
        this.description = builder.description;
        this.amount = builder.amount;
        this.paidBy = builder.paidBy;
        this.participants = builder.participants;
        this.splitType = builder.splitType;
        this.splitValues = builder.splitValues;
        this.createdAt = new Date();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public List<Double> getSplitValues() {
        return splitValues;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return String.format(
                "Expense{id='%s', description='%s', amount=%.2f, paidBy=%s, participants=%s, splitType=%s}",
                id, description, amount, paidBy.getName(),
                participants.stream().map(User::getName).collect(Collectors.joining(", ")),
                splitType);
    }

    /**
     * Builder for Expense objects
     */
    public static class ExpenseBuilder {
        private String description;
        private double amount;
        private User paidBy;
        private List<User> participants;
        private SplitType splitType;
        private List<Double> splitValues;

        public ExpenseBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public ExpenseBuilder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public ExpenseBuilder setPaidBy(User paidBy) {
            this.paidBy = paidBy;
            return this;
        }

        public ExpenseBuilder setParticipants(List<User> participants) {
            this.participants = participants;
            return this;
        }

        public ExpenseBuilder setSplitType(SplitType splitType) {
            this.splitType = splitType;
            return this;
        }

        public ExpenseBuilder setSplitValues(List<Double> splitValues) {
            this.splitValues = splitValues;
            return this;
        }

        public Expense build() {
            // Validation
            if (description == null || description.isEmpty()) {
                throw new IllegalArgumentException("Description cannot be empty");
            }
            if (amount <= 0) {
                throw new IllegalArgumentException("Amount must be positive");
            }
            if (paidBy == null) {
                throw new IllegalArgumentException("Payer must be specified");
            }
            if (participants == null || participants.isEmpty()) {
                throw new IllegalArgumentException("Participants cannot be empty");
            }
            if (splitType == null) {
                throw new IllegalArgumentException("Split type must be specified");
            }

            return new Expense(this);
        }
    }
}
