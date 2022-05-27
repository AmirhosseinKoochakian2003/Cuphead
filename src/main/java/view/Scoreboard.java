package view;

import controller.GameController;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Text;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

public class Scoreboard {
    @FXML
    GridPane gridPane;
    @FXML
    Button button;
    public void showList(MouseEvent mouseEvent) {
        gridPane.setAlignment(Pos.TOP_CENTER);
        ArrayList<User> users = Main.usersController.sortUsers();
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getName() + "  " + users.get(i).getTime());
        }
        for (int i = 0; i < users.size(); i++) {
            if (i < 10) {
                Label text = getLabel (i, users);
                gridPane.addRow(i, text);
            }
            else break;
        }
        this.button.setVisible(false);
    }

    public void backClicked(MouseEvent mouseEvent) throws IOException {
        Main.changeMenu("mainMenu");
    }

    public Label getLabel (int i, ArrayList<User> users) {
        int index = i + 1;
        if (i == 0) {
            ImageView imageView = new ImageView(getClass().getResource("pictures/gold.png").toString());
            imageView.setFitHeight(30);
            imageView.setFitWidth(30);
            return new Label(index + ". " + users.get(i).getName()+ ", time: "
                    + GameController.convertToMinSecond(users.get(i).getTime()), imageView);
        }
        else if (i == 1) {
            ImageView imageView = new ImageView(getClass().getResource("pictures/silver.png").toString());
            imageView.setFitHeight(30);
            imageView.setFitWidth(30);
            return new Label(index + ". " + users.get(i).getName()+ ", time: "
                    + GameController.convertToMinSecond(users.get(i).getTime()), imageView);
        }
        else if (i == 2) {
            ImageView imageView = new ImageView(getClass().getResource("pictures/bronze.png").toString());
            imageView.setFitHeight(30);
            imageView.setFitWidth(30);
            return new Label(index + ". " + users.get(i).getName()+ ", time: "
                    + GameController.convertToMinSecond(users.get(i).getTime()), imageView);
        }
        else {
            return new Label(index + ". " + users.get(i).getName() + ", time: "
                    + GameController.convertToMinSecond(users.get(i).getTime()));
        }
    }
}
