import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * KSE was created by amir on 03.12.15.
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/mainframe.fxml"));
        primaryStage.setTitle("Conceptions of the modern natural science");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
