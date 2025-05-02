package Store;

import processing.core.PApplet;

public class Button {
    float x, y, width, height;
    String label;
    PApplet parent;

    public Button(float x, float y, float width, float height, String label, PApplet parent) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.label = label;
        this.parent = parent;
    }

    public void display() {
        parent.fill(200);
        parent.stroke(0);
        parent.rect(x, y, width, height, 5);
        parent.fill(0);
        parent.textSize(16);
        parent.textAlign(PApplet.CENTER, PApplet.CENTER);
        parent.text(label, x + width / 2, y + height / 2);
    }

    public boolean isMouseOver() {
        return parent.mouseX >= x && parent.mouseX <= x + width
            && parent.mouseY >= y && parent.mouseY <= y + height;
    }
}
