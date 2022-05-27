package view.animations;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.util.Duration;
import view.Boss;
import view.components.CupHead;
import view.components.MiniBoss;

public class MiniBossFlyAnimation extends Transition {
    private Pane gamePane;
    private CupHead cupHead;
    private MiniBoss miniBoss;
    private Boss boss;

    public MiniBossFlyAnimation(CupHead cupHead, MiniBoss miniBoss, Boss boss, Pane gamePane) {
        this.gamePane = gamePane;
        this.boss = boss;
        this.cupHead = cupHead;
        this.miniBoss = miniBoss;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        int number = 1;
        if (0 <= v && v <= 0.25) number = 1;
        else if (0.25 < v && v <= 0.5) number = 2;
        else if (0.5 < v && v <= 0.75) number = 3;
        else if (0.75 < v && v <= 1) number = 4;

        ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("/view/pictures/m"+number+".png").toString()));
        miniBoss.setFill(imagePattern);

        double dx = miniBoss.getX() - 0.5;
        if (miniBoss.getBoundsInParent().intersects(cupHead.getLayoutBounds()) && !cupHead.isOnBlink()) {
            cupHeadCollisionWithMiniBoss();
        }
        if (miniBoss.getX() <= 0) {
            gamePane.getChildren().remove(miniBoss);
            this.stop();
            boss.removeMiniBoss(miniBoss);
        }
        miniBoss.setX(dx);
    }

    public void cupHeadCollisionWithMiniBoss () {
        if (!cupHead.isOnRocketMode()) {
            cupHead.setHealth(cupHead.getHealth() - miniBoss.getDamage());
            gamePane.getChildren().remove(miniBoss);
            this.stop();
            boss.removeMiniBoss(miniBoss);
            cupHead.setOnBlink(true);
            new Blink(cupHead).play();
            System.out.println("cup head  :   " + cupHead.getHealth());
        } else {
            gamePane.getChildren().remove(miniBoss);
            boss.removeMiniBoss(miniBoss);
            miniBoss.getMiniBossFly().stop();
            new ExplosionAnimation(miniBoss.getX(), miniBoss.getY(), gamePane).play();
            miniBoss.setMiniBossFly(null);
            cupHead.setOnRocketMode(false);
            cupHead.normalMode();
            Media media = new Media(getClass().getResource("/view/music/mixkit-explosion-in-battle-2809.wav").toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setStopTime(Duration.millis(3000));
            mediaPlayer.setVolume(1);
        }
    }
}
