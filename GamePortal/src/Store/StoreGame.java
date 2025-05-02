package Store;

import Game.GameWriteable;
import Game.ErrorCheck;

import java.io.File;
import java.util.Scanner;

public class StoreGame implements GameWriteable {
    private String score;

    @Override
    public String getGameName() {
        return "Store Simulation";
    }

    @Override
    public void play() {
        Scanner sc = new Scanner(System.in);
        Store store = new Store();
        Player player = new Player(500_000_000_000L);
        store.initializeItems();

        System.out.println("=== Welcome to the Store Simulation ===");
        // Purchase phase
        System.out.println("Available Aircraft:");
        for (int i = 0; i < store.aircraftList.size(); i++) {
            Aircraft a = store.aircraftList.get(i);
            System.out.println("[" + (i+1) + "] " + a.getName() + " - $" + a.getPrice());
        }
        System.out.print("Select an aircraft by number: ");
        int choice = ErrorCheck.getInt(sc);
        while (choice < 1 || choice > store.aircraftList.size()) {
            System.out.print("Invalid choice. Try again: ");
            choice = ErrorCheck.getInt(sc);
        }
        Aircraft selected = store.aircraftList.get(choice - 1);
        if (player.purchaseItem(selected)) {
            System.out.println("Purchased: " + selected.getName());
        } else {
            System.out.println("Not enough money. Exiting game.");
            score = "0";
            return;
        }

        // Flight phase
        while (true) {
            System.out.println("\nBalance: $" + player.getBalance() + " | Emissions: " + player.getTotalEmissions());
            System.out.println("Destinations:");
            for (int i = 0; i < store.destinations.size(); i++) {
                Destination d = store.destinations.get(i);
                System.out.println("[" + (i+1) + "] " + d.getName() + " — Cost/mile $" + d.getCostPerMile());
            }
            System.out.println("[0] End Game");
            System.out.print("Choose destination: ");
            int destChoice = ErrorCheck.getInt(sc);
            if (destChoice == 0) break;
            if (destChoice < 1 || destChoice > store.destinations.size()) {
                System.out.println("Invalid. Try again.");
                continue;
            }
            Destination dest = store.destinations.get(destChoice - 1);
            System.out.print("How many trips? ");
            int trips = ErrorCheck.getInt(sc);
            long cost = dest.calculateFlightCost(trips);
            if (player.deductBalance(cost)) {
                double emis = dest.getDistance() * selected.getEmissionsPerMile() * trips;
                player.addEmissions(emis);
                System.out.println("Flew " + trips + "x to " + dest.getName() + " for $" + cost);
            } else {
                System.out.println("Not enough balance.");
            }
        }

        System.out.println("\n=== Game Over ===");
        System.out.println("Total Emissions: " + player.getTotalEmissions());
        score = String.valueOf(player.getTotalEmissions());
    }

    @Override
    public String getScore() {
        return score;
    }

    @Override
    public boolean isHighScore(String scoreStr, String currentHighScore) {
        double sc = Double.parseDouble(scoreStr);
        double hs = (currentHighScore != null) ? Double.parseDouble(currentHighScore) : 0;
        return sc > hs;
    }
}
