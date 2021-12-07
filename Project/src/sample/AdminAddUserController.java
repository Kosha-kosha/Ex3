package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminAddUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField addLoginFild;

    @FXML
    private PasswordField addPassFild;

    @FXML
    void initialize() {
        addButton.setOnAction(event -> {
            Client client = new Client();
            try {
                client.dataToServer(addLoginFild.getText(), addPassFild.getText(), "addUserAdmin");
                System.out.println(addLoginFild.getText());
                System.out.println(addPassFild.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Данные ушли на сервер...");

            String action = "bnm";
            try {
                action = Main.reader.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }

            if(action.equals("true")){
                //openNewScene("/sample/menuUser.fxml");
                System.out.println("Такой пользовотель уже есть");
            }
            else{
                //addButton.getScene().getWindow().hide();
                //client.openNewScene("/sample/menuUser.fxml");
                try {
                    WindowChanger.openWindow(getClass().getResource("/sample/menuAdmin.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            System.out.printf(action);


        });

    }
}
