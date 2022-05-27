package controller;

import java.io.*;
import java.util.ArrayList;
import model.*;
import view.Main;

public class UsersController {
    ArrayList<User> users = new ArrayList<User>();

    public void addUser(User user) {
        users.add(user);
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }

    public ArrayList<User> sortUsers() {
        ArrayList<User> users = this.users;
        for (int i = 1; i < users.size(); i++) {
            for (int j = 0; j < users.size() - 1; ++j) {
                if (users.get(j).getPoint() < users.get(j + 1).getPoint()) {
                    User user = users.get(j);
                    users.set(j, users.get(j + 1));
                    users.set(j + 1, user);
                } else if (users.get(j).getPoint() == users.get(j + 1).getPoint()) {
                    if (users.get(j).getTime() < users.get(j + 1).getTime()) {
                        User user = users.get(j);
                        users.set(j, users.get(j + 1));
                        users.set(j + 1, user);
                    }
                }
            }
        }
        return users;
    }

    public void writeInFile () throws IOException {
        File file = new File("src/main/java/view/data.txt");
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (User user : Main.usersController.getAllUsers()) {
            String input = user.getName() + " " + user.getPassword() + " " + user.getTime() + " " + user.getPoint() + " \n";
            bufferedWriter.write(input);
        }
        bufferedWriter.close();
    }
    public static void getUsersFromFile () throws IOException {
        File file = new File("src/main/java/view/data.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string;
        while((string = bufferedReader.readLine()) != null) {
            String[] strings = string.split(" ");
            if (strings.length < 4) {
                break;
            }
            User user = new User(strings[0],strings[1],Integer.parseInt(strings[2]),Integer.parseInt(strings[3]));
            Main.usersController.addUser(user);
        }
        bufferedReader.close();
    }
}


