package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class WindowChanger {
    private static Stage stage = new Stage();
    public static void openWindow(URL url) throws IOException {
        stage.setTitle("Таможня");

        FXMLLoader loader = new FXMLLoader();
            Parent root = (Parent) loader.load(url);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
