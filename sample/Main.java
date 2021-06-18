package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Main extends Application {

    public static Socket socket;
    public static BufferedWriter writer;
    public static BufferedReader reader;
    @Override
    public void start(Stage primaryStage) throws Exception{
       //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
       //primaryStage.setTitle("Hello World");
       //primaryStage.setScene(new Scene(root, 700, 400));
       //primaryStage.show();
        WindowChanger.openWindow(getClass().getResource("/sample/sample.fxml"));


    }

    public static void main(String[] args) throws IOException {

        try {
             socket = new Socket("127.0.0.1", 8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer = new BufferedWriter(
                new OutputStreamWriter(
                        Main.socket.getOutputStream()));
        reader =
                new BufferedReader(
                        new InputStreamReader(
                                Main.socket.getInputStream()));

        launch(args);
    }

}

