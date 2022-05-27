package view;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class MainMenu {


    public void newGame(MouseEvent mouseEvent) throws IOException {
        Main.mediaPlayer.stop();
        new Game().start(Main.stage);
    }
    public void continueGame (MouseEvent mouseEvent) {

    }
    public void profile(MouseEvent mouseEvent) throws IOException {
        if (!Main.player.getName().equals("guest")) Main.changeMenu("profileMenu");

        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("profile menu");
            alert.setHeaderText("result :");
            alert.setContentText("you are not a logged in user");
            alert.showAndWait();
        }
    }
    public void scoreBoard(MouseEvent mouseEvent) throws IOException {
        Main.changeMenu("scoreboard");
    }
    public void exit(MouseEvent mouseEvent) throws IOException {
        Main.changeMenu("registerMenu");
    }
    public void setting(MouseEvent mouseEvent) throws IOException {
        Main.changeMenu("setting");
    }
}
