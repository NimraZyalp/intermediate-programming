package Store;

import processing.core.PApplet;
import java.util.ArrayList;

public class SuperStore extends PApplet {
    Store store;
    Player player;
    Button[] aircraftButtons;
    ArrayList<FlightButtonSet> flightButtonSets;
    public Aircraft selectedAircraft;
    public Button endGameButton;
    boolean gameEnded = false;

    public void settings() {
        size(900, 600);
    }

    public void setup() {
        store = new Store();
        player = new Player(500_000_000_000L);
        store.initializeItems();
        initializeAircraftButtons();
        initializeFlightButtons();
        endGameButton = new Button(50, 550, 200, 40, "End Game", this);
    }

    void initializeAircraftButtons() {
        aircraftButtons = new Button[store.aircraftList.size()];
        int y = 120;
        for (int i = 0; i < store.aircraftList.size(); i++) {
            Aircraft ac = store.aircraftList.get(i);
            aircraftButtons[i] = new Button(50, y, 700, 40,
                ac.getName() + " - $" + ac.getPrice(), this);
            y += 50;
        }
    }

    void initializeFlightButtons() {
        flightButtonSets = new ArrayList<>();
        int y = 300;
        for (Destination dest : store.destinations) {
            flightButtonSets.add(new FlightButtonSet(this, dest, 50, y));
            y += 50;
        }
    }

    public void draw() {
        drawSkyBackground();
        if (gameEnded) {
            displayEndScreen();
        } else {
            displayGameInfo();
            drawButtons();
        }
    }

    void drawSkyBackground() {
        for (int i = 0; i < height; i++) {
            float f = map(i, 0, height, 0, 1);
            int c = lerpColor(color(135,206,250), color(0,102,204), f);
            stroke(c);
            line(0, i, width, i);
        }
    }

    void displayGameInfo() {
        fill(0);
        textSize(24);
        textAlign(LEFT, TOP);
        text("Private Jet Travel!", 50, 30);
        textSize(18);
        text("Balance: $" + player.getBalance(), 50, 70);
        text("Emissions: " + player.getTotalEmissions() + " tons", 50, 90);
        if (player.hasDestroyedTheWorld()) {
            textSize(30);
            fill(255,0,0);
            text("Game Over: You Destroyed the World!", 50, 500);
        }
    }

    void drawButtons() {
        for (Button b : aircraftButtons) b.display();
        for (FlightButtonSet fbs : flightButtonSets) fbs.display();
        endGameButton.display();
    }

    void displayEndScreen() {
        background(50);
        fill(255);
        textSize(32);
        textAlign(CENTER, CENTER);
        text("Game Over!", width/2, 100);
        textSize(24);
        text("Total Emissions: " + player.getTotalEmissions(), width/2, 200);
        String msg = getImpactMessage(player.getTotalEmissions());
        textSize(20);
        displayWrappedText(msg, width/2, 350, width*0.8f);
    }

    void displayWrappedText(String text, float x, float y, float maxWidth) {
        String[] words = text.split(" ");
        String line = "";
        float lh = 30;
        for (String w : words) {
            String test = line + w + " ";
            if (textWidth(test) > maxWidth) {
                text(line, x, y);
                line = w + " ";
                y += lh;
            } else {
                line = test;
            }
        }
        text(line, x, y);
    }

    String getImpactMessage(double e) {
        if (e < 10_000) return "Small amount—world survives, but warming begins.";
        else if (e < 100_000_000) return "Accelerated climate change—ice caps melting!";
        else if (e < 1_000_000_000) return "Severe disasters—global warming beyond safe levels!";
        else return "Congratulations! You’ve destroyed the world.";
    }

    public void mousePressed() {
        if (gameEnded) return;
        if (endGameButton.isMouseOver()) {
            gameEnded = true;
            return;
        }
        for (int i = 0; i < aircraftButtons.length; i++) {
            if (aircraftButtons[i].isMouseOver()) {
                Aircraft ac = store.aircraftList.get(i);
                if (player.deductBalance(ac.getPrice())) {
                    selectedAircraft = ac;
                    System.out.println("Purchased: " + ac.getName());
                } else {
                    System.out.println("Not enough money!");
                }
            }
        }
        for (FlightButtonSet fbs : flightButtonSets) {
            fbs.handleClick();
        }
    }

    public static void main(String[] args) {
        PApplet.main("Store.SuperStore");
    }
}
