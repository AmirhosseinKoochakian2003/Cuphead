package view;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.components.MiniBoss;

import java.util.ArrayList;

public class Boss extends Rectangle {
    private double health;
    private double damage;
    ArrayList<MiniBoss> miniBosses = new ArrayList<>();
    private boolean isOnDevilMode = false;

    public Boss(double health, double damage) {
        super(480, 200, 200, 150);
        this.health = health;
        this.damage = damage;
        ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("pictures/1.png").toString()));
        super.setFill(imagePattern);
    }


    public boolean isOnDevilMode() {
        return isOnDevilMode;
    }
    public void setOnDevilMode(boolean onDevilMode) {
        isOnDevilMode = onDevilMode;
    }
    public double getHealth() {
        return health;
    }
    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
    public void setHealth(double health) {this.health = health;}
    public void addMiniBoss (MiniBoss miniBoss) {
        miniBosses.add(miniBoss);
    }

    public void removeAllMiniBosses () {
        miniBosses.clear();
    }
    public ArrayList<MiniBoss> getMiniBosses () {
        return miniBosses;
    }


    public void removeMiniBoss (MiniBoss miniBoss) {
        for (int i = 0; i < miniBosses.size(); i++) {
            if (miniBosses.get(i).equals(miniBoss)) {
                miniBosses.remove(i);
                break;
            }
        }
    }
}
