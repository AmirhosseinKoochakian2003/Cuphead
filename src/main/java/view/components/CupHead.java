package view.components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Main;

public class CupHead extends Rectangle {
    private static CupHead instance;
    private double health;
    private double damage;
    private boolean isOnBlink = false;
    private boolean isOnRocketMode = false;

    public static CupHead getInstance() {
        if (instance == null) instance = new CupHead(Main.player.getHealthOfCupHead(), Main.player.getCupHeadDamage());
        return instance;
    }
    public CupHead(double health, double damage) {
        super(100, 240, 60, 50);
        ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("/view/pictures/red.png").toString()));
        super.setFill(imagePattern);
        this.health = health;
        this.damage = damage;
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
    public void setHealth(double health) {
        this.health = health;
    }
    public void normalMode () {
        ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("/view/pictures/red.png").toString()));
        super.setFill(imagePattern);
    }
    public void rocketMode () {
        ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("/view/pictures/bomb.png").toString()));
        super.setFill(imagePattern);
    }
    public boolean isOnRocketMode() {
        return isOnRocketMode;
    }
    public void setOnRocketMode(boolean onRocketMode) {
        isOnRocketMode = onRocketMode;
    }
    public boolean isOnBlink() {
        return isOnBlink;
    }
    public void setOnBlink(boolean onBlink) {
        isOnBlink = onBlink;
    }
}
