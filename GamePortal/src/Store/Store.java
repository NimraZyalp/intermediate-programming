package Store;

import java.util.ArrayList;

public class Store {
    public ArrayList<Aircraft> aircraftList;
    public ArrayList<Destination> destinations;

    public Store() {
        aircraftList = new ArrayList<>();
        destinations  = new ArrayList<>();
    }

    public void initializeItems() {
        aircraftList.add(new Aircraft("Boeing 767", 185_000_000, 0.025));
        aircraftList.add(new Aircraft("Boeing 787", 240_000_000, 0.018));

        destinations.add(new Destination("London", 3450, 20));
        destinations.add(new Destination("Paris", 3620, 21));
        destinations.add(new Destination("Tokyo", 6730, 25));
        destinations.add(new Destination("Dubai", 6830, 24));
        destinations.add(new Destination("Sydney", 9930, 30));
    }
}
