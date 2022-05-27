package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.*;
import controller.*;

import java.io.*;
import java.util.ArrayList;

public class Register {
    @FXML
    private TextField name;
    @FXML
    private TextField password;
    @FXML
    private Label nameLabel;
    @FXML
    private Label passwordLabel;
    public void registerClick(MouseEvent mouseEvent) throws IOException {
        String name = this.name.getText();
        String password = this.password.getText();
        ArrayList<User> users = Main.usersController.getAllUsers();
        boolean isNameOk = true;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(name)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("register");
                alert.setHeaderText("result :");
                alert.setContentText("your name is repetitive");
                alert.showAndWait();
                isNameOk = false;
            }
        }
        if (isNameOk) {
            User newUser = new User(name, password, 0, 0);
            Main.usersController.addUser(newUser);
            Main.player = newUser;
            Main.changeMenu("mainMenu");
        }
    }
    public void loginClick(MouseEvent mouseEvent) throws IOException {
        String name = this.name.getText();
        String password = this.password.getText();
        ArrayList<User> users = Main.usersController.getAllUsers();
        boolean doesUserNameExist = false;
        boolean isPasswordOk = true;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getName().equals(name)) {
                if (!users.get(i).getPassword().equals(password)) {
                    alert.setTitle("login");
                    alert.setHeaderText("result :");
                    alert.setContentText("your password is incorrect");
                    alert.showAndWait();
                    isPasswordOk = false;
                }
                doesUserNameExist = true;
            }
        }
        if (!doesUserNameExist) {
            alert.setTitle("login");
            alert.setHeaderText("result :");
            alert.setContentText("there is no username with this name");
            alert.showAndWait();
            isPasswordOk = false;
        }
        if (isPasswordOk) {
            User loginUser = new User(name, password, 0, 0);
            for (User user : Main.usersController.getAllUsers()) {
                if (user.getName().equals(name)) {
                    loginUser = user;
                    break;
                }
            }
            Main.player = loginUser;
            Main.changeMenu("mainMenu");
        }
    }
    public void guestClick(MouseEvent mouseEvent) throws IOException {
        User user = new User("guest","",0, 0);
        Main.player = user;
        Main.changeMenu("mainMenu");
    }
    public void quitClick(MouseEvent mouseEvent) throws IOException {
        Main.usersController.writeInFile();
        System.exit(0);
    }
}