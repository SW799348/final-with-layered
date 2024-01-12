package lk.ijse.finalwithlayered.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.finalwithlayered.bo.BOFactory;
import lk.ijse.finalwithlayered.bo.boInter.AddEmployeeBO;
import lk.ijse.finalwithlayered.bo.boInter.EmployeeBO;
import lk.ijse.finalwithlayered.bo.boInter.UserBO;
import lk.ijse.finalwithlayered.dto.EmployeeDto;


import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddEmployeeFormController {

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private Label lblEmpId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUserId;

    UserBO userBO= (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);
    AddEmployeeBO addEmployeeBO= (AddEmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_EMPLOYEE);

    public void initialize(){
        generateNextEmployeeId();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) lblEmpId.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        String userId = null;
        try {
            userId = userBO.getUserId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        boolean validatedEmployee = validateEmployee();

         if(validatedEmployee){

             String empId = lblEmpId.getText();
             String name = txtName.getText();
             String address = txtAddress.getText();
             int tel = Integer.parseInt(txtContact.getText());
             try {
                 txtUserId.setText(userBO.getUserId());
                 EmployeeDto employeeDto = new EmployeeDto(empId,name,address,tel,userId);

                 try {
                     boolean savedEmployee = addEmployeeBO.saveEmployee(employeeDto);
                     if(savedEmployee){
                         new Alert(Alert.AlertType.CONFIRMATION,"employee saved!").show();
                         EmployeeFormController.employeeFormController.loadAllEmployees();
                         clearFields();
                         Stage stage = (Stage) lblEmpId.getScene().getWindow();
                         stage.close();
                     }
                 } catch (SQLException e) {
                     throw new RuntimeException(e);
                 }
             } catch (SQLException e) {
                 throw new RuntimeException(e);
             } catch (ClassNotFoundException e) {
                 throw new RuntimeException(e);
             }


         }



    }
    private void clearFields() {
        lblEmpId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        txtUserId.setText("");
    }

    private boolean validateEmployee() {
        String id = lblEmpId.getText();
        boolean isValid = Pattern.matches("[E][0-9]{3,}", id);

        if (!isValid){
            new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
            return false;
        }

        String name = txtName.getText();
        boolean isValidName = Pattern.matches("([a-zA-Z\\s]+)", name);

        if (!isValidName){
            new Alert(Alert.AlertType.ERROR, "Invalid Name").show();
            return false;
        }

        String address = txtAddress.getText();
        boolean isValidAddress = Pattern.matches("([a-zA-Z0-9\\s]+)", address);

        if (!isValidAddress){
            new Alert(Alert.AlertType.ERROR, "Invalid Address").show();
            return false;
        }

        String tel = txtContact.getText();
        boolean isValidTel = Pattern.matches("[0-9]{10}", tel);

        if (!isValidTel){
            new Alert(Alert.AlertType.ERROR, "Invalid Tel").show();
            return false;
        }
        String userId = txtUserId.getText();
        boolean isValidUserId = Pattern.matches("([0-9]{4,})", userId);

        if (!isValidUserId){
            new Alert(Alert.AlertType.ERROR, "Invalid userId").show();
            return false;
        }


        return true;


    }
    private void generateNextEmployeeId() {
        String empId = null;
        try {
            empId = addEmployeeBO.generateNextEmployeeId();
            lblEmpId.setText(empId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


}
