package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import controller.*;
import model.User;

import java.io.*;
import java.net.URL;

public class Main extends Application {

    public static Stage stage;

    public static Pane pane;
    private static Scene scene;
    public static UsersController usersController = new UsersController();

    public static User player;

    public static MediaPlayer mediaPlayer;

    @Override
    public void start(Stage stage) throws IOException {
        Media media = new Media(getClass().getResource("music/05 Elder Kettle.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        Main.stage = stage;
        BorderPane registerPane = (BorderPane) loadFxml("registerMenu");
        scene = new Scene(registerPane);
        stage.setScene(scene);
        stage.show();
    }
    public static void changeMenu(String name) throws IOException {
        Parent root = loadFxml(name);
        scene.setRoot(root);
    }
    private static Pane loadFxml (String address) throws IOException {
        URL url = new URL(Main.class.getResource(address+".fxml").toString());
        pane = FXMLLoader.load(url);
        return pane;
    }



    public static void main(String[] args) throws IOException {
        UsersController.getUsersFromFile ();
        launch();
    }
}