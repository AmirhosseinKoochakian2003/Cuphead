package view.animations;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import view.*;
import view.components.CupHead;
import view.components.Egg;

public class BossShootAnimation extends Transition {
    private Pane gamePane;
    private CupHead cupHead;
    private Egg egg;
    private Boss boss;

    public BossShootAnimation(CupHead cupHead, Egg egg, Pane gamePane, Boss boss) {
        this.boss = boss;
        this.gamePane = gamePane;
        this.cupHead = cupHead;
        this.egg = egg;
        this.setCycleDuration(Duration.millis(5000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        int number = 1;
        number = GameController.getFrameBossShooting (12 * v);

        if (!boss.isOnDevilMode()) {
            ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("/view/pictures/s" + number + ".png").toString()));
            boss.setFill(imagePattern);
        }
        else {
            ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("/view/pictures/es" + number + ".png").toString()));
            boss.setFill(imagePattern);
        }

        double dx = egg.getX() - 5;
        if (cupHead.getBoundsInParent().intersects(egg.getLayoutBounds()) && !cupHead.isOnBlink()) {
            cupHeadCollisionWithEgg();
        }

        if (egg.getX() <= 0) {
            Data.setBossShoot(null);
            gamePane.getChildren().remove(egg);
            this.stop();
        }
        egg.setX(dx);
    }

    public void cupHeadCollisionWithEgg () {
        cupHead.setHealth(cupHead.getHealth() - boss.getDamage());
        gamePane.getChildren().remove(egg);
        System.out.println("health of cup head : " + cupHead.getHealth());
        Data.setBossShoot(null);
        cupHead.setOnBlink(true);
        new Blink(cupHead).play();
        this.stop();
    }
}
