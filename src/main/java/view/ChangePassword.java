package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class ChangePassword {
    @FXML
    private TextField password;

    public void apply(MouseEvent mouseEvent) throws IOException {
        String password = this.password.getText();
        Main.player.setPassword(password);
        Main.usersController.writeInFile();
        Main.changeMenu("profileMenu");
    }
}
