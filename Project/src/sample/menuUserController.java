package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class menuUserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitFild;

    @FXML
    private Button addDecFild;

    @FXML
    private Button viewDBFild;

    @FXML
    private Button editDecFild;

    @FXML
    private Button deleteDecFild;

    @FXML
    private Button methodFild;


    @FXML
    void initialize() {
        Client client = new Client();
    addDecFild.setOnAction(event -> {

        try {
            WindowChanger.openWindow(getClass().getResource("/sample/addDeclaration.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }



    });
        deleteDecFild.setOnAction(event -> {
           //String action = "deleteDec";
           //try {
           //    Main.writer.write(action);
           //    Main.writer.newLine();
           //    Main.writer.flush();
           //} catch (IOException e) {
           //    e.printStackTrace();
           // }
           try {
               WindowChanger.openWindow(getClass().getResource("/sample/deleteDeclaration.fxml"));
           } catch (IOException e) {
               e.printStackTrace();
           }


        });
        viewDBFild.setOnAction(event -> {
            try {
                WindowChanger.openWindow(getClass().getResource("/sample/TableView.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
           // String action = "viewDB";
           //try {
           //    Main.writer.write(action);
           //    Main.writer.newLine();
           //    Main.writer.flush();
           //} catch (IOException e) {
           //    e.printStackTrace();
           //}
//
        });
        editDecFild.setOnAction(event -> {
            try {
                WindowChanger.openWindow(getClass().getResource("/sample/editDeclaration.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
           //String action = "editDec";
           //try {
           //    Main.writer.write(action);
           //    Main.writer.newLine();
           //    Main.writer.flush();
           //} catch (IOException e) {
           //    e.printStackTrace();
           //}

        });
        exitFild.setOnAction(event -> {
            String action = "exit";
            try {
                Main.writer.write(action);
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
        methodFild.setOnAction(event -> {

            try {
                WindowChanger.openWindow(getClass().getResource("/sample/Method.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
    @FXML
    public void addDec() throws IOException {

        WindowChanger.openWindow(getClass().getResource("/sample/addDeclaration.fxml" ));
    }
    @FXML
    public void tableView() throws IOException {

            WindowChanger.openWindow(getClass().getResource("/sample/TableView.fxml"));

    }
    @FXML
    public void deleteDec() throws IOException {
        WindowChanger.openWindow(getClass().getResource("/sample/deleteDeclaration.fxml"));
    }
}
