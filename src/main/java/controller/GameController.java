package controller;

import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import view.*;
import view.components.Bullet;
import view.components.CupHead;

public class GameController {
    public static void moveRight (CupHead cupHead) {
        if (cupHead.getX() + 60 < 720)
            cupHead.setX(cupHead.getX() + 10);
    }
    public static void moveLeft (CupHead cupHead) {
        if (cupHead.getX()  > 0)
            cupHead.setX(cupHead.getX() - 10);
    }

    public static void moveUp (CupHead cupHead) {
        if (cupHead.getY()  > 20)
            cupHead.setY(cupHead.getY() - 10);
    }

    public static void moveDown (CupHead cupHead) {
        if (cupHead.getY() + 50 < 480)
            cupHead.setY(cupHead.getY() + 10);
    }

    public static void shoot (CupHead cupHead, Pane pane, Boss boss, Text healthOfBoss, ProgressBar progressBar) {
        Bullet bullet = new Bullet(cupHead.getX() + 5, cupHead.getY());
        pane.getChildren().add(bullet);
        ShootAnimation shootAnimation = new ShootAnimation(bullet, pane, boss, healthOfBoss, progressBar, cupHead);
        Data.setShootAnimation(shootAnimation);
        shootAnimation.play();
        System.out.println(boss.getMiniBosses().size());
    }

    public static double whichDirection (CupHead cupHead, Boss boss) {
        double delta = Math.random() * 80 - 40;
        return delta;
    }

    public static int getFrameBossShooting(double v) {
        if (0 <= v && v <= 1) return 1;
        else if (1 < v && v <= 2) return 2;
        else if (2 < v && v <= 3) return 3;
        else if (3 < v && v <= 4) return 4;
        else if (4 < v && v <= 5) return 5;
        else if (5 < v && v <= 6) return 6;
        else if (6 < v && v <= 7) return 7;
        else if (7 < v && v <= 8) return 8;
        else if (8 < v && v <= 9) return 9;
        else if (9 < v && v <= 10) return 10;
        else if (10 < v && v <= 11) return 11;
        else if (11 < v && v <= 12) return 12;
        return 1;
    }

    public static double[] getValidY(Boss boss) {
        double[] y = new double[2];
        double bossY = boss.getY();

        if (bossY - 60 >= 0) {
            y[0] = bossY - 50;
            if (y[0] - 70 >= 0) {
                y[1] = y[0] - 60;
            }
            else {
                y[1] = bossY + 50 + 150;
            }
        }
        else {
            y[0] = bossY + 50 + 150;
            if (y[0] + 90 <= 480) {
                y[1] = y[0] + 60;
            }
            else {
                y[1] = bossY - 60;
            }
        }
        return y;
    }

    public static String convertToMinSecond(long delta) {
        delta = delta / 1000;
        long minute = delta / 60;
        long second = delta % 60;
        return minute + ":" + second;
    }
}
