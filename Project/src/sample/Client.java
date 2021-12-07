package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.*;
import java.net.Socket;

public class Client {



public String openApp() throws IOException {


        String action = Main.reader.readLine();
        System.out.printf(action);
        return action;
}
void dataToServer(String login, String password, String action) throws IOException {

        System.out.println("Connected to server...");
        Main.writer.write(action);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(login);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(password);
        Main.writer.newLine();
        Main.writer.flush();



}
public static void addDec(String shipperNumber, String consigneeNumber, String typeDeclaration,
                          String seats, String positions, String paymentMethodCode, String shipperCountryCode,
                          String consigneeCountryCode, String declarantNumber, String numberTS, String grossWeight,
                          String netWeight, String goodsCode)throws IOException{
        Main.writer.write(shipperNumber);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(consigneeNumber);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(typeDeclaration);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(seats);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(positions);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(paymentMethodCode);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(shipperCountryCode);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(consigneeCountryCode);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(declarantNumber);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(numberTS);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(grossWeight);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(netWeight);
        Main.writer.newLine();
        Main.writer.flush();

        Main.writer.write(goodsCode);
        Main.writer.newLine();
        Main.writer.flush();
}

public  void openNewScene(String window) throws IOException {

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

public static Declaration getDeclaration(){
        Declaration declaration = new Declaration();
       // String num = null;
        try {

                        declaration.setIdDeclaration(Main.reader.readLine());
                        declaration.setShipperNumber(Main.reader.readLine());
                        declaration.setConsigneeNumber(Main.reader.readLine());
                        declaration.setTypeDeclaration(Main.reader.readLine());
                        declaration.setSeats(Main.reader.readLine());
                        declaration.setPositions(Main.reader.readLine());
                        declaration.setPaymentMethodCode(Main.reader.readLine());
                        declaration.setShipperCountryCode(Main.reader.readLine());
                        declaration.setConsigneeCountryCode(Main.reader.readLine());
                        declaration.setDeclarantNumber(Main.reader.readLine());
                        declaration.setNumberTS(Main.reader.readLine());
                        declaration.setGrossWeight(Main.reader.readLine());
                        declaration.setNetWeight(Main.reader.readLine());
                        declaration.setStatus(Main.reader.readLine());
                        declaration.setGoodsCode(Main.reader.readLine());
               // }
        } catch (IOException e) {
                e.printStackTrace();
        }
        return declaration;
}
public static String getIdDeclaration(){
        String idDeclaration = null;
        try {
                idDeclaration = Main.reader.readLine();
        } catch (IOException e) {
                e.printStackTrace();
        }
        return idDeclaration;
}
public static User getUser(){
        User user = new User();
        try {
                user.setId(Main.reader.readLine());
                user.setLogin(Main.reader.readLine());
                user.setPassword(Main.reader.readLine());
        } catch (IOException e) {
                e.printStackTrace();
        }


        return user;
}
}

