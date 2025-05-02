package Store;

public class Destination {
    private String name;
    private int distance;
    private double costPerMile;

    public Destination(String name, int distance, double costPerMile) {
        this.name = name;
        this.distance = distance;
        this.costPerMile = costPerMile;
    }

    public String getName() {
        return name;
    }

    public int getDistance() {
        return distance;
    }

    public double getCostPerMile() {
        return costPerMile;
    }

    public long calculateFlightCost(int trips) {
        return (long) (distance * costPerMile * trips);
    }
}
