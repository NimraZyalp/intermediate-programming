package Store;

import processing.core.PApplet;

import java.util.Objects;

public class FlightButtonSet {
    private SuperStore parent;
    private Destination destination;
    private Button fly1x, fly5x, fly10x, fly100x, fly1000x;

    public FlightButtonSet(SuperStore parent, Destination destination, float x, float y) {
        this.parent = parent;
        this.destination = destination;
        this.fly1x   = new Button(x, y,   110, 40, "Fly 1× to "   + destination.getName(), parent);
        this.fly5x   = new Button(x+120, y, 110, 40, "Fly 5× to "   + destination.getName(), parent);
        this.fly10x  = new Button(x+240, y, 110, 40, "Fly 10× to "  + destination.getName(), parent);
        this.fly100x = new Button(x+360, y, 110, 40, "Fly 100× to " + destination.getName(), parent);
        this.fly1000x= new Button(x+480, y, 120, 40, "Fly 1000× to "+ destination.getName(), parent);
    }

    public void display() {
        fly1x.display();
        fly5x.display();
        fly10x.display();
        fly100x.display();
        fly1000x.display();
    }

    public void handleClick() {
        if (fly1x.isMouseOver())   fly(1);
        if (fly5x.isMouseOver())   fly(5);
        if (fly10x.isMouseOver())  fly(10);
        if (fly100x.isMouseOver()) fly(100);
        if (fly1000x.isMouseOver())fly(1000);
    }

    private void fly(int times) {
        if (parent.selectedAircraft == null) {
            System.out.println("Select an aircraft first!");
            return;
        }
        long cost = destination.calculateFlightCost(times);
        double emissions = destination.getDistance()
                           * parent.selectedAircraft.getEmissionsPerMile()
                           * times;
        if (parent.player.deductBalance(cost)) {
            parent.player.addEmissions(emissions);
            System.out.println("Flew " + times + "× to " + destination.getName() + " for $" + cost);
        } else {
            System.out.println("Not enough money!");
        }
    }
}
