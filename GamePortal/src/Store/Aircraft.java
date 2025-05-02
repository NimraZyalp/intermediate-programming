package Store;

public class Aircraft extends Item {
    private double emissionsPerMile;

    public Aircraft(String name, long price, double emissionsPerMile) {
        super(name, price);
        this.emissionsPerMile = emissionsPerMile;
    }

    public double getEmissionsPerMile() {
        return emissionsPerMile;
    }
}
