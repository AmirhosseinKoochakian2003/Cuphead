package view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ResultMenu {
    public void start(Stage stage, double bossHealth, String time, boolean state) throws IOException {
        URL url = new URL(Main.class.getResource("resultMenu.fxml").toString());
        Pane resultPane = FXMLLoader.load(url);

        Button restart = new Button("restart");
        Button mainMenu = new Button("Register menu");
        restart.setLayoutX(700);
        restart.setAlignment(Pos.CENTER);
        restart.setPrefWidth(200);
        restart.setPrefHeight(25);
        mainMenu.setPrefWidth(200);
        mainMenu.setPrefHeight(25);
        mainMenu.setAlignment(Pos.CENTER);
        StackPane stackPane = new StackPane();
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        stackPane.getChildren().add(vBox);
        stackPane.setAlignment(vBox, Pos.CENTER);
        stackPane.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);

        double percent = 100 - bossHealth;
        Text text = new Text("your progress percentage : " + percent + "%");
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        text.setX(150);
        text.setY(120);
        Text point = new Text("Points earned from this game : " + (100 - bossHealth));
        Text timeSpent = new Text("Time elapsed at this game : " + time);
        timeSpent.setY(400);
        timeSpent.setX(270);
        point.setY(440);
        point.setX(270);
        timeSpent.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        point.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));

        Rectangle rectangle = new Rectangle(180, 150, 360, 160);
        ImagePattern imagePattern;
        if (state) {
             imagePattern = new ImagePattern(new Image(getClass().getResource("pictures/cup.png").toString()));
        }
        else {
             imagePattern = new ImagePattern(new Image(getClass().getResource("pictures/bossq.png").toString()));
        }
        rectangle.setFill(imagePattern);
        Scene loseMenu = new Scene(resultPane);
        stage.setScene(loseMenu);
        resultPane.getChildren().add(text);
        vBox.getChildren().add(restart);
        vBox.getChildren().add(mainMenu);
        resultPane.getChildren().add(rectangle);
        resultPane.getChildren().add(stackPane);
        resultPane.getChildren().add(point);
        resultPane.getChildren().add(timeSpent);
        restart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Data.restart();
                try {
                    Game newGame = new Game();
                    newGame.start(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        mainMenu.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    Data.restart();
                    new Main().start(stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        stage.show();
    }
}
