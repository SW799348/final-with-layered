package lk.ijse.finalwithlayered;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {



        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/supplierForm.fxml"))));
       // stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();

        var image = new Image("assests/cool_cloth-removebg-preview.png");
        stage.getIcons().add(image);

        stage.show();

    }
}
