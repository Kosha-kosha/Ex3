package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class addDeclarationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private TextField shipperNumberFild;

    @FXML
    private TextField seatsFild;

    @FXML
    private TextField paymentMethodCodeFild;

    @FXML
    private TextField declarantNumberFild;

    @FXML
    private TextField consigneeNumberFild;

    @FXML
    private TextField positionsFild;

    @FXML
    private TextField shipperCountryCodeFild;

    @FXML
    private TextField numberTSFild;

    @FXML
    private TextField typeDeclarationFild;

    @FXML
    private TextField consigneeCountryCodeFild;

    @FXML
    private TextField grossWeightFild;

    @FXML
    private TextField netWeightFild;

    @FXML
    private TextField goodsCodeFild;

    @FXML
    void initialize() {
        Client client = new Client();
    addButton.setOnAction(event -> {
        String action = "addDec";
        try {
            Main.writer.write(action);
            Main.writer.newLine();
            Main.writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Client.addDec(shipperNumberFild.getText(), consigneeNumberFild.getText(), typeDeclarationFild.getText(),
                    seatsFild.getText(), positionsFild.getText(), paymentMethodCodeFild.getText(), shipperCountryCodeFild.getText(),
                    consigneeCountryCodeFild.getText(), declarantNumberFild.getText(), numberTSFild.getText(), grossWeightFild.getText(),
                    netWeightFild.getText(), goodsCodeFild.getText());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //addButton.getScene().getWindow().hide();
       // client.openNewScene("/sample/menuUser.fxml");
        try {
            WindowChanger.openWindow(getClass().getResource("/sample/menuUser.fxml" ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    });

    }
    @FXML
    public void openMenuUser() {
        try {
            WindowChanger.openWindow(getClass().getResource("/sample/menuUser.fxml" ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
