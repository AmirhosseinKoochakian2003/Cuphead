package view;

import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Setting {
    public void level1(MouseEvent mouseEvent) throws IOException {
        Main.player.setCupHeadDamage(1.5);
        Main.player.setEnemyDamage(0.5);
        Main.player.setHealthOfCupHead(10);
        Main.changeMenu("mainMenu");
    }
    public void level2(MouseEvent mouseEvent) throws IOException {
        Main.player.setCupHeadDamage(1);
        Main.player.setEnemyDamage(1);
        Main.player.setHealthOfCupHead(5);
        Main.changeMenu("mainMenu");
    }
    public void level3(MouseEvent mouseEvent) throws IOException {
        Main.player.setCupHeadDamage(0.5);
        Main.player.setEnemyDamage(1.5);
        Main.player.setHealthOfCupHead(2);
        Main.changeMenu("mainMenu");
    }
    public void mute(MouseEvent mouseEvent) throws IOException {
        Main.mediaPlayer.stop();
        Main.changeMenu("mainMenu");
    }
    public void unmute(MouseEvent mouseEvent) throws IOException {
        Main.mediaPlayer.play();
        Main.changeMenu("mainMenu");
    }
}
