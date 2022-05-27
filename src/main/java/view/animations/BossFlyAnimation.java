package view.animations;

import controller.GameController;
import javafx.animation.Transition;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.*;
import view.components.CupHead;
import view.components.Egg;
import view.components.MiniBoss;


import java.io.IOException;

public class BossFlyAnimation extends Transition {
    private Stage stage;
    private long time;
    private Boss boss;
    private Pane gamePane;
    private CupHead cupHead;
    private Text healthOfBoss;
    private ProgressBar progressBar;

    public BossFlyAnimation(Boss boss, Pane gamePane, CupHead cupHead, long time, Stage stage, Text healthOfBoss, ProgressBar progressBar) {
        this.healthOfBoss = healthOfBoss;
        this.progressBar = progressBar;
        this.stage = stage;
        this.time = time;
        this.cupHead = cupHead;
        this.gamePane = gamePane;
        this.boss = boss;
        this.setCycleCount(-1);
        this.setCycleDuration(Duration.millis(1000));
    }


    @Override
    protected void interpolate(double v) {
        int number = 1;
        if (0 <= v && v <= 0.2) number = 1;
        else if (0.2 < v && v <= 0.3) number = 2;
        else if (0.3 < v && v <= 0.5) number = 3;
        else if (0.5 < v && v <= 0.7) number = 4;
        else if (0.7 < v && v <= 0.9) number = 5;
        else if (0.9 < v && v <= 1.0) number = 6;

        if (!boss.isOnDevilMode()) {
            ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource(  "/view/pictures/"+ number + ".png").toString()));
            boss.setFill(imagePattern);
        } else {
            ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("/view/pictures/e" + number + ".png").toString()));
            boss.setFill(imagePattern);
        }
        if (boss.getHealth() <= 50) boss.setOnDevilMode(true);

        if (cupHead.getLayoutBounds().intersects(boss.getLayoutBounds()) && !cupHead.isOnBlink()) {
            cupHeadCollisionWithBoss();
        }

        if (cupHead.getHealth() <= 0) {
            cupHeadDied();
        }

        if (boss.getHealth() <= 0) {
            bossDied();
        }
    }

    public void bossMove() throws IOException {
        double dy = GameController.whichDirection(cupHead, boss);
        if (boss.getY() + dy + 150 <= 480 && boss.getY() + dy >= 20) {
            boss.setY(dy + boss.getY());
        } else {
            boss.setY(-1 * dy + boss.getY());
        }

        if (boss.isOnDevilMode()) {
            double dx = GameController.whichDirection(cupHead, boss);
            if (boss.getX() + dx + 200 <= 720 && boss.getX() + dx >= 0) {
                boss.setX(dx + boss.getX());
            }
        }
    }

    public void eggAttack() {
        Egg egg = new Egg(boss.getX() - 10, boss.getY() + 75, boss);
        gamePane.getChildren().add(egg);
        BossShootAnimation bossShoot = new BossShootAnimation(cupHead, egg, gamePane, boss);
        bossShoot.play();
        Data.setBossShoot(bossShoot);
    }

    public void miniBossAttack() {
        double[] y = GameController.getValidY(boss);
        MiniBoss miniBoss1 = new MiniBoss(boss.getX(), y[0], Main.player.getEnemyDamage());
        MiniBoss miniBoss2 = new MiniBoss(boss.getX(), y[1], Main.player.getEnemyDamage());

        gamePane.getChildren().add(miniBoss1);
        gamePane.getChildren().add(miniBoss2);

        boss.addMiniBoss(miniBoss1);
        boss.addMiniBoss(miniBoss2);

        MiniBossFlyAnimation miniBossFly1 = new MiniBossFlyAnimation(cupHead, miniBoss1, boss, gamePane);
        MiniBossFlyAnimation miniBossFly2 = new MiniBossFlyAnimation(cupHead, miniBoss2, boss, gamePane);

        miniBoss1.setMiniBossFly(miniBossFly1);
        miniBoss2.setMiniBossFly(miniBossFly2);

        Data.setMiniBossFly1(miniBossFly1);
        Data.setMiniBossFly2(miniBossFly2);

        miniBossFly1.play();
        miniBossFly2.play();
    }

    public void cupHeadCollisionWithBoss() {
        if (!cupHead.isOnRocketMode()) {
            cupHead.setHealth(cupHead.getHealth() - boss.getDamage());
            cupHead.setOnBlink(true);
            if (boss.getY() > 240) {
                cupHead.setX(50);
                cupHead.setY(50);
            } else {
                cupHead.setX(50);
                cupHead.setY(400);
            }
            new Blink(cupHead).play();
            System.out.println("cup head  :   " + cupHead.getHealth());
        } else {
            boss.setHealth(boss.getHealth() - cupHead.getDamage() * 4);
            new ExplosionAnimation(cupHead.getX(), cupHead.getY(), gamePane).play();
            Media media = new Media(getClass().getResource("/view/music/mixkit-explosion-in-battle-2809.wav").toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setStopTime(Duration.millis(3000));
            mediaPlayer.setVolume(1);
            healthOfBoss.setText("health of boss : " + boss.getHealth() + "%");
            progressBar.setProgress(progressBar.getProgress() - (cupHead.getDamage() / 100));
            cupHead.setOnRocketMode(false);
            cupHead.normalMode();
            if (boss.getY() > 240) {
                cupHead.setX(50);
                cupHead.setY(50);
            } else {
                cupHead.setX(50);
                cupHead.setY(400);
            }
        }
    }

    public void cupHeadDied() {
        int newPoint = (int) (100 - boss.getHealth()) + Main.player.getPoint();
        Main.player.setPoint(newPoint);
        long timer = System.currentTimeMillis();
        long delta = timer - time;
        Main.player.setTime(delta);
        Game.mediaPlayer.stop();
        String timeFormat = GameController.convertToMinSecond(delta);
        Data.stop();
        Data.removeFocus();
        try {
            Main.usersController.writeInFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            new ResultMenu().start(stage, boss.getHealth(), timeFormat, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void bossDied() {
        int newPoint = (int) (100 - boss.getHealth()) + Main.player.getPoint();
        Main.player.setPoint(newPoint);
        long timer = System.currentTimeMillis();
        long delta = timer - time;
        Main.player.setTime(delta);
        Game.mediaPlayer.stop();
        String timeFormat = GameController.convertToMinSecond(delta);
        Data.stop();
        Data.removeFocus();
        try {
            Main.usersController.writeInFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            new ResultMenu().start(stage, boss.getHealth(), timeFormat, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

