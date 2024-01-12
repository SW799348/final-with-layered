package lk.ijse.finalwithlayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.finalwithlayered.bo.BOFactory;
import lk.ijse.finalwithlayered.bo.boInter.AddCustomerBO;
import lk.ijse.finalwithlayered.bo.boInter.CustomerBO;
import lk.ijse.finalwithlayered.dto.CustomerDto;
import lk.ijse.finalwithlayered.dto.tm.CustomerTm;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class AddCustomerFormController {

    @FXML
    private JFXButton btnClose;

    @FXML
    private Label lblCustId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtTel;


    AddCustomerBO addCustomerBO= (AddCustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_CUSTOMER);


    public void initialize(){
        generateNextCustId();
    }
    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

        boolean validatedCustomer = validateCustomer();

        if(validatedCustomer){
            String id = lblCustId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            int tel = Integer.parseInt(txtTel.getText());

                try {
                   boolean savedCustomer = addCustomerBO.saveCustomer(new CustomerDto(id,name,address,tel));

                    if(savedCustomer){
                        new Alert(Alert.AlertType.CONFIRMATION,"customer saved!").show();
                        CustomerFormController.customerFormController.loadAllCustomers();
                        clearFields();
                    }


            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"customer can not saved").show();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();

                }
        }


    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) lblCustId.getScene().getWindow();
        stage.close();
    }




    private void clearFields() {
        lblCustId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
    }


    private boolean validateCustomer() {
        String id = lblCustId.getText();
        boolean isValid = Pattern.matches("[C][0-9]{3,}", id);

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

        String tel = (txtTel.getText());
        boolean isValidTel = Pattern.matches("[0-9]{10}", tel);

        if (!isValidTel){
            new Alert(Alert.AlertType.ERROR, "Invalid Tel").show();
            return false;
        }

        return true;

    }
    private String generateNextCustId() {

        String custId = null;
        try {
            custId = addCustomerBO.generateNextCustId();
            lblCustId.setText(custId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;

    }




}
