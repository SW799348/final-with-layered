package lk.ijse.finalwithlayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalwithlayered.bo.BOFactory;
import lk.ijse.finalwithlayered.bo.boInter.EmployeeBO;
import lk.ijse.finalwithlayered.bo.boInter.UpdateEmployeeBO;
import lk.ijse.finalwithlayered.dto.EmployeeDto;
import lk.ijse.finalwithlayered.entity.Employee;


import java.sql.SQLException;

public class UpdateEmployeeFormController {

    @FXML
    private AnchorPane anchorPaneForm;

    @FXML
    private JFXButton btnClose;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmpId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    @FXML
    private TextField txtUserId;

    EmployeeBO employeeBO= (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);
    UpdateEmployeeBO updateEmployeeBO= (UpdateEmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.UPDATE_EMPLOYEE);

    public void initialize() {
        loadEmployeeDetails(EmployeeFormController.employeeFormController.updateEmpId);

    }

    private void loadEmployeeDetails(String updateEmpId) {

        Employee employeeDto = null;
        try {
            employeeDto = employeeBO.getEmployee(updateEmpId);

            txtEmpId.setText(employeeDto.getEmpId());
            txtEmpId.setEditable(false);
            txtName.setText(employeeDto.getName());
            txtAddress.setText(employeeDto.getAddress());
            txtTel.setText(String.valueOf(employeeDto.getTel()));
            txtUserId.setText(employeeDto.getUserId());
            //txtUserId.setEditable(false);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void btnCancelOnAction(ActionEvent event) {
        txtEmpId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
        txtUserId.setText("");
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) txtEmpId.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String empId = txtEmpId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        int tel = Integer.parseInt(txtTel.getText());
        String userId = txtUserId.getText();

        EmployeeDto employeeDto = new EmployeeDto(empId,name,address,tel,userId);

        try {
            boolean updatedEmployee = updateEmployeeBO.updateEmployee(employeeDto);
            if (updatedEmployee) {
                System.out.println("employee updated");
                new Alert(Alert.AlertType.CONFIRMATION, "updated successfully!").show();
                EmployeeFormController.employeeFormController.loadAllEmployees();
                Stage stage = (Stage) txtEmpId.getScene().getWindow();
                stage.close();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}


