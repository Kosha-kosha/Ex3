package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UserTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitFild;

    @FXML
    private TableView<User> userTable;

    @FXML
    private TableColumn<User, String> idColum;

    @FXML
    private TableColumn<User, String> loginColum;

    @FXML
    private TableColumn<User, String> passColum;

    @FXML
    void initialize() {
        String action = "tableViewUser";
        try {
            Main.writer.write(action);
            Main.writer.newLine();
            Main.writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        idColum.setCellValueFactory(new PropertyValueFactory<>("idUsers"));
        loginColum.setCellValueFactory(new PropertyValueFactory<>("login"));
        passColum.setCellValueFactory(new PropertyValueFactory<>("password"));

        User user = new User();
        String num = null;
        while (true)
        {
            try {
                num = Main.reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(num.equals("last")){

                user = Client.getUser();
                userTable.getItems().add(user);
                break;
            }
            user = Client.getUser();
            System.out.println(user);


            userTable.getItems().add(user);

        }
        exitFild.setOnAction(event -> {
            try {
                WindowChanger.openWindow(getClass().getResource("/sample/menuAdmin.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
