package view.components;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Bullet extends Rectangle {
    static ArrayList<Bullet> bullets = new ArrayList<>();
    public Bullet(double v, double v1) {
        super(v, v1,50, 10);
        ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("/view/pictures/bullet.png").toString()));
        super.setFill(imagePattern);
        bullets.add(this);
    }

    public static void removeBullet (Bullet bullet) {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).equals(bullet)) {
                bullets.remove(i);
                break;
            }
        }
    }
}
