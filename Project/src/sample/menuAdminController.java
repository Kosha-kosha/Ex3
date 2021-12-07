package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class menuAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitFild;

    @FXML
    private Button addUserFild;

    @FXML
    private Button viewUserButton;

    @FXML
    void addDec(ActionEvent event) {

    }

    @FXML
    void initialize() {
        addUserFild.setOnAction(event -> {

            try {
                WindowChanger.openWindow(getClass().getResource("/sample/AdminAddUser.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        viewUserButton.setOnAction(event -> {
            System.out.println("viewUserButton");
            try {
                WindowChanger.openWindow(getClass().getResource("/sample/UserTable.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        exitFild.setOnAction(event -> {
            try {
                Main.writer.write("exit");
                Main.writer.newLine();
                Main.writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                WindowChanger.openWindow(getClass().getResource("/sample/sample.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }

    @FXML
    public void userTabteView() {
        try {
            WindowChanger.openWindow(getClass().getResource("/sample/UserTable.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
