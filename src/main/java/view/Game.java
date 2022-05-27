package view;

import controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.animations.BossFlyAnimation;
import view.components.CupHead;


import java.io.IOException;
import java.net.URL;

public class Game {
    private Scene scene;
    private long time;
    private Timeline miniBossTimeline;
    private Timeline eggTimeline;
    private Timeline bossMoveTimeline;
    public static MediaPlayer mediaPlayer;


    public void start(Stage stage) throws IOException {
        Media media = new Media(getClass().getResource("music/53 One Hell Of A Time.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        URL url = new URL(Main.class.getResource("game.fxml").toString());
        Pane gamePane = FXMLLoader.load(url);
        Data.setPane(gamePane);

        Boss boss = new Boss(100, Main.player.getEnemyDamage());

        HBox hBox = new HBox();
        Text healthOfBoss = new Text(360, 50, "health of boss : "+boss.getHealth() + "%");
        ProgressBar progressBar = new ProgressBar(1);
        HBoxMaker(hBox, healthOfBoss, progressBar);

        CupHead cupHead = moveCup(gamePane, boss, healthOfBoss, progressBar, stage);
        time = System.currentTimeMillis();

        BossFlyAnimation bossFly = new BossFlyAnimation(boss, gamePane, cupHead, time, stage, healthOfBoss, progressBar);
        Data.setBossFly(bossFly);
        bossFly.play();

        gamePane.getChildren().add(cupHead);
        gamePane.getChildren().add(boss);
        gamePane.getChildren().add(hBox);

        Background backGround = new Background(setBackGround());
        gamePane.setBackground(backGround);

        scene = new Scene(gamePane);
        stage.setScene(scene);
        gamePane.getChildren().get(0).requestFocus();

        setBossMoveTimeLine(bossFly);
        setMiniBossTimeLine(bossFly);
        setEggTimeLine(bossFly);
        stage.show();
    }
    public CupHead moveCup (Pane pane, Boss boss, Text healthOfBoss, ProgressBar progressBar, Stage stage) {
        CupHead cupHead = CupHead.getInstance();
        cupHead.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                String keyName = keyEvent.getCode().getName();
                if (keyName.equals("Right")) GameController.moveRight(cupHead);

                else if (keyName.equals("Left")) GameController.moveLeft(cupHead);

                else if (keyName.equals("Up")) GameController.moveUp(cupHead);

                else if (keyName.equals("Down")) GameController.moveDown(cupHead);

                else if (keyName.equals("Space") && !cupHead.isOnRocketMode())
                    GameController.shoot(cupHead, pane, boss, healthOfBoss, progressBar);

                else if (keyName.equals("B") && !cupHead.isOnRocketMode()) {
                    cupHead.setOnRocketMode(true);
                    cupHead.rocketMode();
                }
                else if (keyName.equals("Enter")) {
                    pauseMenuCalling (stage, boss, cupHead, scene, boss.getHealth());
                }
            }
        });
        return cupHead;
    }

    public void HBoxMaker (HBox hBox, Text healthOfBoss, ProgressBar progressBar) {
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(healthOfBoss);
        hBox.setStyle("-fx-border-color: red");
        hBox.setSpacing(50);
        hBox.getChildren().add(progressBar);
        Text helpText = new Text("** Press Enter to pause the game");
        Text name = new Text("** Player :" + Main.player.getName());
        hBox.getChildren().add(helpText);
        hBox.getChildren().add(name);
    }

    public BackgroundImage setBackGround () {
        Image image = new Image(getClass().getResource( "pictures/bc3.png").toString());
        BackgroundImage backgroundImage = new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        return backgroundImage;
    }
    public void setBossMoveTimeLine (BossFlyAnimation bossFly) {
        bossMoveTimeline = new Timeline(new KeyFrame(Duration.millis(5000), actionEvent ->
        {
            try {
                bossFly.bossMove();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
        bossMoveTimeline.setCycleCount(-1);
        bossMoveTimeline.play();
        Data.setBossMoveTimeline(bossMoveTimeline);
    }

    public void setMiniBossTimeLine (BossFlyAnimation bossFly) {
        miniBossTimeline = new Timeline(new KeyFrame(Duration.millis(10000), actionEvent ->
                bossFly.miniBossAttack()));
        miniBossTimeline.setCycleCount(-1);
        miniBossTimeline.play();
        Data.setMiniBossTimeline(miniBossTimeline);
    }

    public void setEggTimeLine (BossFlyAnimation bossFly) {
        eggTimeline = new Timeline(new KeyFrame(Duration.millis(7000), actionEvent ->
                bossFly.eggAttack()));
        eggTimeline.setCycleCount(-1);
        eggTimeline.play();
        Data.setEggTimeline(eggTimeline);
    }

    public void pauseMenuCalling (Stage stage, Boss boss, CupHead cupHead, Scene scene, double health) {
        Data.stop();
        Data.removeFocus();
        long timer = System.currentTimeMillis();
        long delta = timer - time;
        String timeFormat = GameController.convertToMinSecond (delta);
        try {
            new PauseMenu().start(stage, cupHead, this.scene, boss.getHealth(), timeFormat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize () {

    }
}
