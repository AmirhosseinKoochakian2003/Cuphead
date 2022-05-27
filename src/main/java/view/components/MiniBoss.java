package view.components;

import javafx.scene.shape.Rectangle;
import view.animations.MiniBossFlyAnimation;

public class MiniBoss extends Rectangle {
    private int combatCount = 2;
    private double damage;


    public MiniBoss(double v, double v1, double damage) {
        super(v, v1, 40, 30);
        this.damage = damage;
    }


    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
    private MiniBossFlyAnimation miniBossFly;

    public MiniBossFlyAnimation getMiniBossFly() {
        return miniBossFly;
    }
    public void setMiniBossFly(MiniBossFlyAnimation miniBossFly) {
        this.miniBossFly = miniBossFly;
    }
    public void reduceOneCombat () {
        combatCount--;
    }
    public int getCombatCount() {
        return combatCount;
    }
}
