package sample;

/**
 * Sample Skeleton for 'sample.fxml' Controller Class
 */

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class Controller extends Client {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="loginFild"
    private TextField loginFild; // Value injected by FXMLLoader

    @FXML // fx:id="passFild"
    private PasswordField passFild; // Value injected by FXMLLoader

    @FXML // fx:id="enterButton"
    private Button enterButton; // Value injected by FXMLLoader

    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete

    void initialize() {
    loginFild.setOnAction(event -> {
        System.out.println("Вы ввели логин");


    });

        Client client = new Client();
    enterButton.setOnAction(event -> {
        if(loginFild.getText().equals("admin") && passFild.getText().equals("admin")){
            try {
                client.dataToServer(loginFild.getText(), passFild.getText(), "admin");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {

            try {
                client.dataToServer(loginFild.getText(), passFild.getText(), "get");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Данные ушли на сервер...");
        try {
            String action = client.openApp();
            if(action.equals("true")){

                //openNewScene("/sample/menuUser.fxml");
                WindowChanger.openWindow(getClass().getResource("/sample/menuUser.fxml"));
            }
            if(action.equals("admin")){
                WindowChanger.openWindow(getClass().getResource("/sample/menuAdmin.fxml"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    });

    addButton.setOnAction(event -> {
        try {
            openNewScene("/sample/addUser.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    });

    }
     public void openNewScene(String window) throws IOException {

        //addButton.getScene().getWindow().hide();
        //FXMLLoader loader = new FXMLLoader();
        //loader.setLocation(getClass().getResource(window));
        //try {
        //    loader.load();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        //Parent root = loader.getRoot();
        //Stage stage = new Stage();
        //stage.setScene(new Scene(root));
        //stage.show();
         WindowChanger.openWindow(getClass().getResource("/sample/addUser.fxml"));
     }

    public void openMenuUser(ActionEvent actionEvent) throws IOException {
        WindowChanger.openWindow(getClass().getResource("/sample/menuUser.fxml"));
    }
}

