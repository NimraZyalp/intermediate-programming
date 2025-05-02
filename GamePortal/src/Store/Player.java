package Store;

public class Player {
    private long balance;
    private double totalEmissions;

    public Player(long balance) {
        this.balance = balance;
        this.totalEmissions = 0;
    }

    public boolean purchaseItem(Item item) {
        if (balance >= item.getPrice()) {
            balance -= item.getPrice();
            return true;
        }
        return false;
    }

    public boolean deductBalance(long amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void addEmissions(double emissions) {
        totalEmissions += emissions;
    }

    public long getBalance() {
        return balance;
    }

    public double getTotalEmissions() {
        return totalEmissions;
    }
    
    public boolean hasDestroyedTheWorld() {
        return totalEmissions >= 1_000_000_000;
    }
}
