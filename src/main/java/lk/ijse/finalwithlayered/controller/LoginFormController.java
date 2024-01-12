package lk.ijse.finalwithlayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {

    @FXML
    private AnchorPane loginAnchorPane;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
//        if(txtUsername.getText().equals("samadhi") && txtPassword.getText().equals("2002")){


            Parent loginAnchorPane = FXMLLoader.load(this.getClass().getResource("/view/dashBoardForm.fxml"));

            Scene scene = new Scene(loginAnchorPane);
            Stage stage = (Stage) this.loginAnchorPane.getScene().getWindow();

            stage.setTitle("Dashboard");
            stage.setScene(scene);
            stage.centerOnScreen();

//        }else{
//            new Alert(Alert.AlertType.WARNING,"Invalid username or password");
//        }

    }

    @FXML
    void txtForgetPasswordOnAction(MouseEvent event) {

    }

}
