/**
 * Sample Skeleton for 'addUser.fxml' Controller Class
 */

package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class addUserController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="addLoginFild"
    private TextField addLoginFild; // Value injected by FXMLLoader

    @FXML // fx:id="addPassFild"
    private PasswordField addPassFild; // Value injected by FXMLLoader

    @FXML // fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    addButton.setOnAction(event -> {
        Client client = new Client();
        try {
            client.dataToServer(addLoginFild.getText(), addPassFild.getText(), "add");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Данные ушли на сервер...");

    });
    }
}
