package Splitwise.Split;

/**
 * Factory to create different types of split strategies
 */
public class ExpenseSplitFactory {
    public ExpenseSplit createSplit(SplitType type) {
        switch (type) {
            case EQUAL:
                return new EqualExpenseSplit();
            case PERCENTAGE:
                return new PercentageExpenseSplit();
            case EXACT:
                return new ExactExpenseSplit();
            default:
                throw new IllegalArgumentException("Unknown split type: " + type);
        }
    }
}