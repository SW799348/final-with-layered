package lk.ijse.finalwithlayered.navigation;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Navigation {
    public static AnchorPane anchorPane;


    public static void navigate(Routes route, AnchorPane anchorPane) throws IOException {
        Navigation.anchorPane = anchorPane;
        Navigation.anchorPane.getChildren().clear();
        Stage window = (Stage) Navigation.anchorPane.getScene().getWindow();


        switch (route) {

            case LOGIN:
                initUI("loginForm.fxml");
                window.setTitle("loginPage");
                break;

            case HOME:
                initUI("dashboardForm.fxml");
                window.setTitle("Dashboard");
                break;

            case CUSTOMER:
                initUI("customerForm.fxml");
                window.setTitle("Customer");
                break;

            case PLACE_ORDER:
                initUI("PlaceOrder.fxml");
                window.setTitle("PlaceOrder");
                break;

            case EMPLOYEE:
                initUI("employeeForm.fxml");
                window.setTitle("Employee");
                break;

            case ATTENDANCE:
                initUI("attendanceForm.fxml");
                window.setTitle("Attendance");
                break;

            case RAW_MATERIAL:
                initUI("rawMaterialForm.fxml");
                window.setTitle("Raw Material");
                break;

            case SUPPLIER:
                initUI("supplierForm.fxml");
                window.setTitle("Supplier");
                break;

            case SALARY:
                initUI("salaryForm.fxml");
                window.setTitle("Salary");
                break;


            default:
                new Alert(Alert.AlertType.ERROR, "Not suitable UI found!").show();
        }
    }

    private static void initUI(String location) throws IOException {
        Navigation.anchorPane.getChildren().add(FXMLLoader.load(Navigation.class.getResource("/view/" + location)));
    }


    public static void navigatePopUpWindow(Routes route) throws IOException {
        switch (route) {
            case ADD_CUSTOMER:
                initPopUp("addCustomerForm");
                break;

            case UPDATE_CUSTOMER:
                initPopUp("updateCustomerForm");
                break;

            case ADD_EMPLOYEE:
                initPopUp("addEmployeeForm");
                break;

            case UPDATE_EMPLOYEE:
                initPopUp("updateEmployeeForm");
                break;

            case ADD_SUPPLIER:
                initPopUp("addSupplierForm");
                break;

            case UPDATE_SUPPLIER:
                initPopUp("updateSupplierForm");
                break;
        }

    }

    private static void initPopUp(String url) throws IOException {

        Parent root = FXMLLoader.load(Navigation.class.getResource("/view/" + url + ".fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        //stage.setAlwaysOnTop(true);
        stage.setTitle("");
        stage.show();


    }
}
