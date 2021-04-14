package sample;

/**
 * Sample Skeleton for 'sample.fxml' Controller Class
 */

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
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
       // String login = loginFild.getText();
        //System.out.println(login);

       //try {
       //
       //} catch (IOException e) {
       //    e.printStackTrace();
       //}
       //try {
       //    BufferedReader reader =
       //            new BufferedReader(
       //                    new InputStreamReader(
       //                            Main.socket.getInputStream()));
       //} catch (IOException e) {
       //    e.printStackTrace();
       //}

    });

        Client client = new Client();
    enterButton.setOnAction(event -> {

        try {
            client.dataToServer(loginFild.getText(), passFild.getText(), "get");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Данные ушли на сервер...");


        try {
            if(client.openApp().equals("true")){

                openNewScene("/sample/addUser.fxml");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    });

    addButton.setOnAction(event -> {
        openNewScene("/sample/addUser.fxml");
    });

    }
     public void openNewScene(String window){

         addButton.getScene().getWindow().hide();
         FXMLLoader loader = new FXMLLoader();
         loader.setLocation(getClass().getResource(window));
         try {
             loader.load();
         } catch (IOException e) {
             e.printStackTrace();
         }
         Parent root = loader.getRoot();
         Stage stage = new Stage();
         stage.setScene(new Scene(root));
         stage.showAndWait();
     }

}

