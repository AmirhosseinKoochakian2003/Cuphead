package view.animations;

import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class ExplosionAnimation extends Transition {
    Pane pane;
    Rectangle rectangle;

    public ExplosionAnimation(double x, double y, Pane pane) {
        rectangle = new Rectangle(x, y, 50, 40);
        this.pane = pane;
        this.setCycleCount(1);
        this.setCycleDuration(Duration.millis(2000));
        pane.getChildren().add(rectangle);
    }

    @Override
    protected void interpolate(double v) {
        if (0 <= v && v <= 0.5) {
            ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("/view/pictures/boom1.png").toString()));
            rectangle.setFill(imagePattern);
        }
        else {
            ImagePattern imagePattern = new ImagePattern(new Image(getClass().getResource("/view/pictures/boom2.png").toString()));
            rectangle.setFill(imagePattern);
        }

        this.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                pane.getChildren().remove(rectangle);
            }
        });
    }
}
