package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class editDeclarationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button exitButton;

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
    private TableColumn<Declaration, String> statusFild;

    @FXML
    private TableColumn<Declaration, String> goodsCodeFild;


    @FXML
    private Button editButton;

    @FXML
    private TextField idDeclarationFild;

    @FXML
    void openMenuUser(ActionEvent event) {

    }

    @FXML
    void initialize() {
        String action = "editDec";
        try {
            Main.writer.write(action);
            Main.writer.newLine();
            Main.writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Declaration declaration = new Declaration();
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
        String num = null;
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

            //String idDeclaration = "11";
            //while (idDeclaration!=null) {
            //    try {
            //        idDeclaration = Main.reader.readLine();
            //        String shipperNumber = Main.reader.readLine(), consigneeNumber = Main.reader.readLine(), typeDeclaration = Main.reader.readLine();
            //        String seats = Main.reader.readLine(), positions = Main.reader.readLine(), paymentMethodCode = Main.reader.readLine();
            //        String shipperCountryCode = Main.reader.readLine(), consigneeCountryCode = Main.reader.readLine(), declarantNumber = Main.reader.readLine();
            //        String numberTS = Main.reader.readLine(), grossWeight = Main.reader.readLine(), netWeight = Main.reader.readLine(), status = Main.reader.readLine();
//
            //    } catch (IOException e) {
            //        e.printStackTrace();
            //    }
            table.getItems().add(declaration);
            //declaration.setIdDeclaration("null");
        }
        editButton.setOnAction(event -> {
            String id = idDeclarationFild.getText();
            try {
                Main.writer.write(id);
                Main.writer.newLine();
                Main.writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Изменено...");
            try {
                WindowChanger.openWindow(getClass().getResource("/sample/addDeclaration.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        exitButton.setOnAction(event -> {
            try {
                WindowChanger.openWindow(getClass().getResource("/sample/menuUser.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Main.writer.write("exit");
                Main.writer.newLine();
                Main.writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
