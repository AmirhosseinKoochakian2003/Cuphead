package view;

import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import view.animations.BossFlyAnimation;
import view.animations.BossShootAnimation;
import view.animations.BulletAnimation;
import view.animations.MiniBossFlyAnimation;

public class Data {
    static private Pane pane;
  static private BulletAnimation bulletAnimation;
  static private BossFlyAnimation bossFly;
  static private BossShootAnimation bossShoot;
  static private MiniBossFlyAnimation miniBossFly1;
  static private MiniBossFlyAnimation miniBossFly2;
  static private ShootAnimation shootAnimation;
  static private Timeline bossMoveTimeline;
  static private Timeline miniBossTimeline;
  static private Timeline eggTimeline;

    public static Timeline getBossMoveTimeline() {
        return bossMoveTimeline;
    }
    public static void setBossMoveTimeline(Timeline bossMoveTimeline) {
        Data.bossMoveTimeline = bossMoveTimeline;
    }

    public static Timeline getMiniBossTimeline() {
        return miniBossTimeline;
    }
    public static void setMiniBossTimeline(Timeline miniBossTimeline) {
        Data.miniBossTimeline = miniBossTimeline;
    }

    public static Timeline getEggTimeline() {
        return eggTimeline;
    }
    public static void setEggTimeline(Timeline eggTimeline) {
        Data.eggTimeline = eggTimeline;
    }

    public static BulletAnimation getBulletAnimation() {
      return bulletAnimation;
   }
    public static void setBulletAnimation(BulletAnimation bulletAnimation) {
      Data.bulletAnimation = bulletAnimation;
   }

    public static BossFlyAnimation getBossFly() {
      return bossFly;
   }
    public static void setBossFly(BossFlyAnimation bossFly) {
      Data.bossFly = bossFly;
   }

    public static BossShootAnimation getBossShoot() {
      return bossShoot;
   }
    public static void setBossShoot(BossShootAnimation bossShoot) {
      Data.bossShoot = bossShoot;
   }

    public static MiniBossFlyAnimation getMiniBossFly1() {
      return miniBossFly1;
   }
    public static void setMiniBossFly1(MiniBossFlyAnimation miniBossFly1) {
      Data.miniBossFly1 = miniBossFly1;
   }

    public static ShootAnimation getShootAnimation() {
      return shootAnimation;
   }
    public static void setShootAnimation(ShootAnimation shootAnimation) {
      Data.shootAnimation = shootAnimation;
   }

    public static MiniBossFlyAnimation getMiniBossFly2() {
      return miniBossFly2;
   }
    public static void setMiniBossFly2(MiniBossFlyAnimation miniBossFly2) {
      Data.miniBossFly2 = miniBossFly2;
   }

    public static void stop() {
       if (bulletAnimation != null) bulletAnimation.stop();
       if (bossFly != null) bossFly.stop();
       if (bossShoot != null) bossShoot.stop();
       if (miniBossFly1 != null) miniBossFly1.stop();
       if (miniBossFly2 != null) miniBossFly2.stop();
       if (shootAnimation != null) shootAnimation.stop();
       if (bossMoveTimeline != null) bossMoveTimeline.stop();
       if (miniBossTimeline != null) miniBossTimeline.stop();
       if (eggTimeline != null) eggTimeline.stop();
    }
    public static void removeFocus() {
        for (int i = 0; i < pane.getChildren().size(); i++) {
            pane.getChildren().get(i).setFocusTraversable(false);
        }
    }
    public static void restart() {
         bulletAnimation = null;
         bossFly = null;
         bossShoot = null;
         miniBossFly1 = null;
         miniBossFly2 = null;
         shootAnimation = null;
         eggTimeline = null;
         bossMoveTimeline = null;
         miniBossTimeline = null;
         pane = null;
    }

    public static void play () {
        if (bulletAnimation != null) bulletAnimation.play();
        if (bossFly != null) bossFly.play();
        if (bossShoot != null) bossShoot.play();
        if (miniBossFly1 != null) miniBossFly1.play();
        if (miniBossFly2 != null) miniBossFly2.play();
        if (shootAnimation != null) shootAnimation.play();
        if (bossMoveTimeline != null) bossMoveTimeline.play();
        if (miniBossTimeline != null) miniBossTimeline.play();
        if (eggTimeline != null) eggTimeline.play();
    }
    public static void giveFocus () {
        pane.getChildren().get(0).requestFocus();
    }

    public static Pane getPane() {
        return pane;
    }

    public static void setPane(Pane pane) {
        Data.pane = pane;
    }
}