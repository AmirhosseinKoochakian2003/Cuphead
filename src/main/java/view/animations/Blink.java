package view.animations;

import javafx.animation.Transition;
import javafx.util.Duration;
import view.components.CupHead;

public class Blink extends Transition {

    private CupHead cupHead;

    public Blink(CupHead cupHead) {
        this.cupHead = cupHead;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1000));
    }

    @Override
    protected void interpolate(double v) {
        if (0 <= v && v <= 0.25) cupHead.setOpacity(0.2);

        else if (0.25 < v && v <= 0.5) cupHead.setOpacity(1);

        else if (0.5 < v && v <= 0.75) cupHead.setOpacity(0.2);

        else if (0.75 < v && v <= 1) {
            cupHead.setOpacity(1);
            cupHead.setOnBlink(false);
        }
    }
}
