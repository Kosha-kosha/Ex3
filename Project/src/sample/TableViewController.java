package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.Struct;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sun.nio.cs.ext.MacArabic;
import sun.security.krb5.internal.crypto.Des;

import javax.swing.*;

public class TableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitFild;

    @FXML
    private Button importButton;

    @FXML
    private Button exportButton;

    @FXML
    private TableView<Declaration> table;

    @FXML
    private TableColumn<Declaration, String> idDecFild;

    @FXML
    private TableColumn<Declaration, String> shipperNumberFild;

    @FXML
    private TableColumn<Declaration, String> consigneeNumberFild;

    @FXML
    private TableColumn<Declaration, String> typeDeclarationFild;

    @FXML
    private TableColumn<Declaration, String> seatsFild;

    @FXML
    private TableColumn<Declaration, String> positionsFild;

    @FXML
    private TableColumn<Declaration, String> paymentMethodCodeFild;

    @FXML
    private TableColumn<Declaration, String> shipperCountryCodeFild;

    @FXML
    private TableColumn<Declaration, String> consigneeCountryCodeFild;

    @FXML
    private TableColumn<Declaration, String> declarantNumberFild;

    @FXML
    private TableColumn<Declaration, String> numberTSFild;

    @FXML
    private TableColumn<Declaration, String> grossWeightFild;

    @FXML
    private TableColumn<Declaration, String> netWeightFild;

    @FXML
    private TableColumn<Declaration, String> goodsCodeFild;

    @FXML
    private TableColumn<Declaration, String> statusFild;

    String action = null;
    Declaration declaration;
    String num;
    @FXML
    void initialize() {
        action = "viewDB";
        try {
            Main.writer.write(action);
            Main.writer.newLine();
            Main.writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        declaration = new Declaration();
        idDecFild.setCellValueFactory(new PropertyValueFactory<>("idDeclaration"));
        shipperNumberFild.setCellValueFactory(new PropertyValueFactory<>("shipperNumber"));
        consigneeNumberFild.setCellValueFactory(new PropertyValueFactory<>("consigneeNumber"));
        typeDeclarationFild.setCellValueFactory(new PropertyValueFactory<>("typeDeclaration"));
        seatsFild.setCellValueFactory(new PropertyValueFactory<>("seats"));
        positionsFild.setCellValueFactory(new PropertyValueFactory<>("positions"));
        paymentMethodCodeFild.setCellValueFactory(new PropertyValueFactory<>("paymentMethodCode"));
        shipperCountryCodeFild.setCellValueFactory(new PropertyValueFactory<>("shipperCountryCode"));
        consigneeCountryCodeFild.setCellValueFactory(new PropertyValueFactory<>("consigneeCountryCode"));
        declarantNumberFild.setCellValueFactory(new PropertyValueFactory<>("declarantNumber"));
        numberTSFild.setCellValueFactory(new PropertyValueFactory<>("numberTS"));
        grossWeightFild.setCellValueFactory(new PropertyValueFactory<>("grossWeight"));
        netWeightFild.setCellValueFactory(new PropertyValueFactory<>("netWeight"));
        statusFild.setCellValueFactory(new PropertyValueFactory<>("status"));
        goodsCodeFild.setCellValueFactory(new PropertyValueFactory<>("goodsCode"));

        declaration.setIdDeclaration("null");
        num = null;
        while (true)
        {
            try {
                num = Main.reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(num.equals("last")){

                declaration = Client.getDeclaration();
                table.getItems().add(declaration);
                break;
            }
            declaration = Client.getDeclaration();
            System.out.println(declaration);


            table.getItems().add(declaration);

        }
        exportButton.setOnAction(event -> {
            action = "viewExport";
            try {
                Main.writer.write(action);
                Main.writer.newLine();
                Main.writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            declaration = new Declaration();
            idDecFild.setCellValueFactory(new PropertyValueFactory<>("idDeclaration"));
            shipperNumberFild.setCellValueFactory(new PropertyValueFactory<>("shipperNumber"));
            consigneeNumberFild.setCellValueFactory(new PropertyValueFactory<>("consigneeNumber"));
            typeDeclarationFild.setCellValueFactory(new PropertyValueFactory<>("typeDeclaration"));
            seatsFild.setCellValueFactory(new PropertyValueFactory<>("seats"));
            positionsFild.setCellValueFactory(new PropertyValueFactory<>("positions"));
            paymentMethodCodeFild.setCellValueFactory(new PropertyValueFactory<>("paymentMethodCode"));
            shipperCountryCodeFild.setCellValueFactory(new PropertyValueFactory<>("shipperCountryCode"));
            consigneeCountryCodeFild.setCellValueFactory(new PropertyValueFactory<>("consigneeCountryCode"));
            declarantNumberFild.setCellValueFactory(new PropertyValueFactory<>("declarantNumber"));
            numberTSFild.setCellValueFactory(new PropertyValueFactory<>("numberTS"));
            grossWeightFild.setCellValueFactory(new PropertyValueFactory<>("grossWeight"));
            netWeightFild.setCellValueFactory(new PropertyValueFactory<>("netWeight"));
            statusFild.setCellValueFactory(new PropertyValueFactory<>("status"));
            goodsCodeFild.setCellValueFactory(new PropertyValueFactory<>("goodsCode"));

            declaration.setIdDeclaration("null");
            num = null;
            while (true)
            {
                try {
                    num = Main.reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(num.equals("last")){

                    declaration = Client.getDeclaration();
                    table.getItems().add(declaration);
                    break;
                }
                declaration = Client.getDeclaration();
                System.out.println(declaration);


                table.getItems().add(declaration);

            }
        });
        importButton.setOnAction(event -> {
            action = "viewImport";

            try {
                Main.writer.write(action);
                Main.writer.newLine();
                Main.writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            declaration = new Declaration();
            idDecFild.setCellValueFactory(new PropertyValueFactory<>("idDeclaration"));
            shipperNumberFild.setCellValueFactory(new PropertyValueFactory<>("shipperNumber"));
            consigneeNumberFild.setCellValueFactory(new PropertyValueFactory<>("consigneeNumber"));
            typeDeclarationFild.setCellValueFactory(new PropertyValueFactory<>("typeDeclaration"));
            seatsFild.setCellValueFactory(new PropertyValueFactory<>("seats"));
            positionsFild.setCellValueFactory(new PropertyValueFactory<>("positions"));
            paymentMethodCodeFild.setCellValueFactory(new PropertyValueFactory<>("paymentMethodCode"));
            shipperCountryCodeFild.setCellValueFactory(new PropertyValueFactory<>("shipperCountryCode"));
            consigneeCountryCodeFild.setCellValueFactory(new PropertyValueFactory<>("consigneeCountryCode"));
            declarantNumberFild.setCellValueFactory(new PropertyValueFactory<>("declarantNumber"));
            numberTSFild.setCellValueFactory(new PropertyValueFactory<>("numberTS"));
            grossWeightFild.setCellValueFactory(new PropertyValueFactory<>("grossWeight"));
            netWeightFild.setCellValueFactory(new PropertyValueFactory<>("netWeight"));
            statusFild.setCellValueFactory(new PropertyValueFactory<>("status"));
            goodsCodeFild.setCellValueFactory(new PropertyValueFactory<>("goodsCode"));

            declaration.setIdDeclaration("null");
            num = null;
            while (true)
            {
                try {
                    num = Main.reader.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(num.equals("last")){

                    declaration = Client.getDeclaration();
                    table.getItems().add(declaration);
                    break;
                }
                declaration = Client.getDeclaration();
                System.out.println(declaration);


                table.getItems().add(declaration);

            }
        });
    }

    @FXML
    public void openMenuUser() {
        try {
            WindowChanger.openWindow(getClass().getResource("/sample/menuUser.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
