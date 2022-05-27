package view;

import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

public class Profile {
    public void changeName(MouseEvent mouseEvent) throws IOException {
        Main.changeMenu("changeName");
    }
    public void changePassword(MouseEvent mouseEvent) throws IOException {
        Main.changeMenu("changePassword");
    }
    public void delete(MouseEvent mouseEvent) throws IOException {
        ArrayList<User> users = Main.usersController.getAllUsers();
        for (int i = 0; i < users.size(); i++) {
            if (Main.player.equals(users.get(i))) {
                users.remove(i);
                break;
            }
        }
        Main.usersController.writeInFile();
        Main.player = null;
        Main.changeMenu("registerMenu");
    }
    public void logout(MouseEvent mouseEvent) throws IOException {
        Main.changeMenu("registerMenu");
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Main.changeMenu("mainMenu");
    }
}
