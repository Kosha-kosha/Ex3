package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MethodController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitFild;

    @FXML
    private Button toFileButton;

    @FXML
    private TableView<Declaration> idTable;

    @FXML
    private TableColumn<Declaration, String> idColum;

    @FXML
    void initialize() {
        try {
            Main.writer.write("method");
            Main.writer.newLine();
            Main.writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String idDeclaration = null;
        idColum.setCellValueFactory(new PropertyValueFactory<>("idDeclaration"));



        String num = null;
        while (true)
        {
            try {
                num = Main.reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(num.equals("last")){

                Declaration declaration = new Declaration();
                declaration.setIdDeclaration(Client.getIdDeclaration());
                idTable.getItems().add(declaration);
                System.out.println(declaration.getIdDeclaration());
                break;
            }
            Declaration declaration = new Declaration();
            declaration.setIdDeclaration(Client.getIdDeclaration());
            idTable.getItems().add(declaration);

            System.out.println(declaration.getIdDeclaration());

        }
        exitFild.setOnAction(event -> {
            try {
                Main.writer.write("w/oFile");
                Main.writer.newLine();
                Main.writer.flush();
                WindowChanger.openWindow(getClass().getResource("/sample/menuUser.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        toFileButton.setOnAction(event -> {
            try {
                Main.writer.write("toFile");
                Main.writer.newLine();
                Main.writer.flush();
                WindowChanger.openWindow(getClass().getResource("/sample/menuUser.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
