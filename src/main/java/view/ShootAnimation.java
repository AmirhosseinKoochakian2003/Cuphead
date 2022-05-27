package view;

import javafx.animation.Transition;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import view.animations.BulletAnimation;
import view.components.Bullet;
import view.components.CupHead;

public class ShootAnimation extends Transition {
    private Bullet bullet;
    private Pane pane;
    private Boss boss;
    private Text healthOfBoss;
    private ProgressBar progressBar;
    private CupHead cupHead;

    public ShootAnimation(Bullet bullet, Pane pane, Boss boss, Text healthOfBoss, ProgressBar progressBar, CupHead cupHead) {
        this.cupHead = cupHead;
        this.progressBar = progressBar;
        this.healthOfBoss = healthOfBoss;
        this.boss = boss;
        this.pane = pane;
        this.bullet = bullet;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(-1);
    }

    @Override
    protected void interpolate(double v) {
        double dx = bullet.getX() + 10;

        for (int i = 0; i < boss.miniBosses.size(); i++) {
            if (bullet.getBoundsInParent().intersects(boss.miniBosses.get(i).getLayoutBounds())) {
                boss.miniBosses.get(i).reduceOneCombat();
                pane.getChildren().remove(bullet);
                BulletAnimation bulletAnimation = new BulletAnimation(bullet.getX(), bullet.getY(), pane);
                bulletAnimation.play();
                this.stop();
                if (boss.miniBosses.get(i).getCombatCount() == 0) {
                    bulletCollisionWithMiniBoss(i, bulletAnimation);
                    break;
                }
            }
        }

        if (bullet.getBoundsInParent().intersects(boss.getLayoutBounds())) {
            bulletCollisionWithBoss();
        }

        if (dx >= 720) {
            Bullet.removeBullet(bullet);
            pane.getChildren().remove(bullet);
            Data.setShootAnimation(null);
            this.stop();
        }
        bullet.setX(dx);
    }

    public void bulletCollisionWithBoss () {
        boss.setHealth(boss.getHealth() - cupHead.getDamage());
        healthOfBoss.setText("health of boss : "+boss.getHealth() + "%");
        progressBar.setProgress(progressBar.getProgress() - (cupHead.getDamage() / 100));
        Bullet.removeBullet(bullet);
        pane.getChildren().remove(bullet);
        System.out.println(boss.getHealth());
        BulletAnimation bulletAnimation = new BulletAnimation(bullet.getX(), bullet.getY(), pane);
        bulletAnimation.play();
        Data.setBulletAnimation(bulletAnimation);
        Data.setShootAnimation(null);
        this.stop();
    }

    public void bulletCollisionWithMiniBoss (int i, BulletAnimation bulletAnimation) {
        boss.miniBosses.get(i).getMiniBossFly().stop();
        boss.miniBosses.get(i).setMiniBossFly(null);
        pane.getChildren().remove(boss.miniBosses.get(i));
        boss.removeMiniBoss(boss.miniBosses.get(i));
        if (i == 0) {
            Data.setMiniBossFly1(null);
        }
        else {
            Data.setMiniBossFly2(null);
        }
        Data.setBulletAnimation(bulletAnimation);
        Data.setShootAnimation(null);
    }
}
