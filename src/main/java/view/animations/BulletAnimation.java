package view.animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import view.Data;

public class BulletAnimation extends Transition {
    Pane gamePane;
    Rectangle rectangle;

    public BulletAnimation(double x, double y, Pane gamePane) {
        this.gamePane = gamePane;
        rectangle = new Rectangle(x + 10, y, 50, 40);
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(1000));
        gamePane.getChildren().add(rectangle);
    }

    @Override
    protected void interpolate(double v) {
        int number = 1;
        if (0 <= v && v <= 0.25) number = 1;
        else if (0.25 < v && v <= 0.5) number = 2;
        else if (0.5 < v && v <= 0.75) number = 3;
        else if (0.75 < v && v <= 1) number = 4;

        ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("/view/pictures/b" + number +".png").toString()));
        rectangle.setFill(imagePattern);

        if (number == 4) {
            Data.setBulletAnimation(null);
            this.stop();
            gamePane.getChildren().remove(rectangle);
        }
    }
}
