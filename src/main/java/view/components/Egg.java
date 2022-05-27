package view.components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import view.Boss;

public class Egg extends Rectangle {
    private Boss boss;
    public Egg(double v, double v1, Boss boss) {
        super(v, v1, 40, 30);

        if (!boss.isOnDevilMode()) {
            ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("/view/pictures/egg.png").toString()));
            super.setFill(imagePattern);
        }
        else {
            ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("/view/pictures/eegg.png").toString()));
            super.setFill(imagePattern);
        }
        this.boss = boss;
    }
}
