package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.User;

import java.io.*;
import java.util.ArrayList;

public class ChangeName {
    @FXML
    private TextField name;
    public void apply(MouseEvent mouseEvent) throws IOException {
        String name = this.name.getText();
        ArrayList<User> users = Main.usersController.getAllUsers();
        boolean isNameOk = true;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(name)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("register");
                alert.setHeaderText("result :");
                alert.setContentText("your new name is repetitive");
                alert.showAndWait();
                isNameOk = false;
            }
        }
        if (isNameOk) {
            Main.player.setName(name);
            Main.usersController.writeInFile();
            Main.changeMenu("profileMenu");
        }
    }
}
