package view;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.components.CupHead;

import java.io.IOException;
import java.net.URL;

public class PauseMenu {
    public void start(Stage pauseStage, CupHead cupHead, Scene gameScene, double bossHealth, String time) throws IOException {
        URL url = new URL(Main.class.getResource("pauseMenu.fxml").toString());
        Pane pausePane = FXMLLoader.load(url);
        Button resume = new Button("resume");
        Button saveGame = new Button("save game");
        Button quitGame = new Button("quit game");
        Button mute = new Button("mute music");
        Button restart = new Button("restart game");
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(resume);
        vBox.getChildren().add(mute);
        vBox.getChildren().add(saveGame);
        vBox.getChildren().add(quitGame);
        vBox.getChildren().add(restart);

        resume.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Data.giveFocus();
                Data.play();
                pauseStage.setScene(gameScene);
            }
        });

        restart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                restartEvent (bossHealth, pauseStage);
            }
        });

        quitGame.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                quitGameEvent (bossHealth, pauseStage);
            }
        });

        mute.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Game.mediaPlayer.stop();
            }
        });

        addChildToPane (pausePane, vBox, cupHead, time, bossHealth);
        Scene pauseScene = new Scene(pausePane);
        pauseStage.setScene(pauseScene);
        pauseStage.show();
    }

    public void restartEvent(double bossHealth, Stage pauseStage) {
        int newPoint = (int)(100 - bossHealth) + Main.player.getPoint();
        Main.player.setPoint(newPoint);
        Game.mediaPlayer.stop();
        try {
            Main.usersController.writeInFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Data.restart();
            new Game().start(pauseStage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void quitGameEvent (double bossHealth, Stage pauseStage) {
        int newPoint = (int)(100 - bossHealth) + Main.player.getPoint();
        Game.mediaPlayer.stop();
        Main.player.setPoint(newPoint);
        try {
            Main.usersController.writeInFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Data.restart();
        try {
            new Main().start(pauseStage);
        } catch (IOException e) {

        }
    }

    public void addChildToPane(Pane pausePane, VBox vBox, CupHead cupHead, String time, double bossHealth) {
        Text text = new Text(300, 50, "game's keys : arrows for left/right/up/down \n" +
                "space for shooting \n" + "enter for pause menu \n" + "B for rocket attack");
        Text text2 = new Text(300, 125, "your remain health : " + cupHead.getHealth());
        Text text3 = new Text(300, 150, "your point : " + (100 - bossHealth));
        Text text4 = new Text(300, 175, "time : " + time);
        if (cupHead.getHealth() <= 1.5) {
            text2.setUnderline(true);
            text2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 10));
        }
        pausePane.getChildren().add(vBox);
        pausePane.getChildren().add(text);
        pausePane.getChildren().add(text2);
        pausePane.getChildren().add(text3);
        pausePane.getChildren().add(text4);
    }
}
