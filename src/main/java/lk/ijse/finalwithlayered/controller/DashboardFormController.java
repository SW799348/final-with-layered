
package lk.ijse.finalwithlayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalwithlayered.navigation.Navigation;
import lk.ijse.finalwithlayered.navigation.Routes;

import java.io.IOException;
import java.time.LocalDateTime;

public class DashboardFormController {

    @FXML
    private JFXButton btnAttendance;

    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private JFXButton btnRawMaterial;

    @FXML
    private JFXButton btnSalary;

    @FXML
    private JFXButton btnSupplier;

    @FXML
    private ImageView homeId;

    @FXML
    private ImageView imgNotify;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane mainAnchorPane;

    @FXML
    private AnchorPane subAnchorPane;



    public void initialize() {
        events();
        actions();
        lblDate.setText(String.valueOf(LocalDateTime.now()));
    }



    private void actions() {
        btnHome.setOnAction(actionEvent -> {
            try {
                Navigation.navigate(Routes.HOME,mainAnchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btnCustomer.setOnAction(actionEvent -> {
            try {
                Navigation.navigate(Routes.CUSTOMER,subAnchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btnPlaceOrder.setOnAction(actionEvent -> {
            try {
              Navigation.navigate(Routes.PLACE_ORDER,subAnchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btnRawMaterial.setOnAction(actionEvent -> {
            try {
                Navigation.navigate(Routes.RAW_MATERIAL,subAnchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btnEmployee.setOnAction(actionEvent -> {
            try {
                Navigation.navigate(Routes.EMPLOYEE,subAnchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btnAttendance.setOnAction(actionEvent -> {
            try {
                Navigation.navigate(Routes.ATTENDANCE,subAnchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btnSalary.setOnAction(actionEvent -> {
            try {
                Navigation.navigate(Routes.SALARY,subAnchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        btnSupplier.setOnAction(actionEvent -> {
            try {
                Navigation.navigate(Routes.SUPPLIER,subAnchorPane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

    private void events() {
        // btn1.setStyle("-fx-background-radius: 10 0 0 10;-fx-border-radius:10 0 0 10");

        btnHome.setOnMouseClicked(mouseEvent -> {
            btnHome.setStyle("-fx-background-color: rgb(255,255,255);-fx-background-radius: 10 0 0 10;-fx-border-radius:10 0 0 10");
            btnCustomer.setStyle("");
            btnPlaceOrder.setStyle("");
            btnEmployee.setStyle("");
            btnAttendance.setStyle("");
            btnSalary.setStyle("");
            btnSupplier.setStyle("");
            btnRawMaterial.setStyle("");

        });

        btnCustomer.setOnMouseClicked(mouseEvent -> {
            btnCustomer.setStyle("-fx-background-color: rgb(255,255,255);-fx-background-radius: 10 0 0 10;-fx-border-radius:10 0 0 10");
            btnHome.setStyle("");
            btnPlaceOrder.setStyle("");
            btnEmployee.setStyle("");
            btnAttendance.setStyle("");
            btnSalary.setStyle("");
            btnSupplier.setStyle("");
            btnRawMaterial.setStyle("");



        });
        btnPlaceOrder.setOnMouseClicked(mouseEvent -> {
            btnPlaceOrder.setStyle("-fx-background-color: rgb(255,255,255);-fx-background-radius: 10 0 0 10;-fx-border-radius:10 0 0 10");
            btnHome.setStyle("");
            btnCustomer.setStyle("");
            btnEmployee.setStyle("");
            btnAttendance.setStyle("");
            btnSalary.setStyle("");
            btnSupplier.setStyle("");
            btnRawMaterial.setStyle("");



        });
        btnRawMaterial.setOnMouseClicked(mouseEvent -> {
            btnRawMaterial.setStyle("-fx-background-color: rgb(255,255,255);-fx-background-radius: 10 0 0 10;-fx-border-radius:10 0 0 10");
            btnHome.setStyle("");
            btnPlaceOrder.setStyle("");
            btnEmployee.setStyle("");
            btnAttendance.setStyle("");
            btnSalary.setStyle("");
            btnSupplier.setStyle("");
            btnCustomer.setStyle("");



        });

        btnEmployee.setOnMouseClicked(mouseEvent -> {
            btnEmployee.setStyle("-fx-background-color: rgb(255,255,255);-fx-background-radius: 10 0 0 10;-fx-border-radius:10 0 0 10");
            btnHome.setStyle("");
            btnPlaceOrder.setStyle("");
            btnCustomer.setStyle("");
            btnAttendance.setStyle("");
            btnSalary.setStyle("");
            btnSupplier.setStyle("");
            btnRawMaterial.setStyle("");



        });
        btnAttendance.setOnMouseClicked(mouseEvent -> {
            btnAttendance.setStyle("-fx-background-color: rgb(255,255,255);-fx-background-radius: 10 0 0 10;-fx-border-radius:10 0 0 10");
            btnHome.setStyle("");
            btnPlaceOrder.setStyle("");
            btnEmployee.setStyle("");
            btnCustomer.setStyle("");
            btnSalary.setStyle("");
            btnSupplier.setStyle("");
            btnRawMaterial.setStyle("");



        });
        btnSalary.setOnMouseClicked(mouseEvent -> {
            btnSalary.setStyle("-fx-background-color: rgb(255,255,255);-fx-background-radius: 10 0 0 10;-fx-border-radius:10 0 0 10");
            btnHome.setStyle("");
            btnPlaceOrder.setStyle("");
            btnEmployee.setStyle("");
            btnAttendance.setStyle("");
            btnCustomer.setStyle("");
            btnSupplier.setStyle("");
            btnRawMaterial.setStyle("");



        });
        btnSupplier.setOnMouseClicked(mouseEvent -> {
            btnSupplier.setStyle("-fx-background-color: rgb(255,255,255);-fx-background-radius: 10 0 0 10;-fx-border-radius:10 0 0 10");
            btnHome.setStyle("");
            btnPlaceOrder.setStyle("");
            btnEmployee.setStyle("");
            btnAttendance.setStyle("");
            btnSalary.setStyle("");
            btnCustomer.setStyle("");
            btnRawMaterial.setStyle("");

        });

        btnLogout.setOnMouseClicked(mouseEvent -> {
            Parent rootNode = null;
            try {
                rootNode = FXMLLoader.load(this.getClass().getResource("/view/LoginForm.fxml"));
                Scene scene = new Scene(rootNode);

                mainAnchorPane.getChildren().clear();
                Stage primaryStage = (Stage) mainAnchorPane.getScene().getWindow();

                primaryStage.setScene(scene);
                primaryStage.centerOnScreen();
                primaryStage.setTitle("DashBoard");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }



        });
    }



}
