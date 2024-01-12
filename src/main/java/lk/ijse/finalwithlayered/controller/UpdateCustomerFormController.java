package lk.ijse.finalwithlayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalwithlayered.bo.BOFactory;
import lk.ijse.finalwithlayered.bo.boInter.CustomerBO;
import lk.ijse.finalwithlayered.bo.boInter.UpdateCustomerBO;
import lk.ijse.finalwithlayered.dto.CustomerDto;
import lk.ijse.finalwithlayered.entity.Customer;


import java.sql.SQLException;

public class UpdateCustomerFormController {

    @FXML
    private AnchorPane anchorPaneForm;

    @FXML
    private JFXButton btnClose;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustId;


    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    CustomerBO customerBO= (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    UpdateCustomerBO updateCustomerBO= (UpdateCustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.UPDATE_CUSTOMER);


    public void initialize() {
            loadCustomerDetails(CustomerFormController.customerFormController.updateCustomerId);

        }

        private void loadCustomerDetails(String updateCustomerId) {

            Customer customerDto = null;
            try {
                customerDto = customerBO.getCustomer(updateCustomerId);

                txtCustId.setText(customerDto.getCustId());
                txtCustId.setEditable(false);
                txtName.setText(customerDto.getName());
                txtAddress.setText(customerDto.getAddress());
                txtTel.setText(String.valueOf(customerDto.getTel()));

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        }


    @FXML
    void btnCancelOnAction(ActionEvent event) {
        txtCustId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtTel.setText("");
    }


        @FXML
        void btnCloseOnAction(ActionEvent event) {
            Stage stage = (Stage) txtCustId.getScene().getWindow();
            stage.close();
        }

        @FXML
        void btnUpdateOnAction(ActionEvent event) {
            String custId = txtCustId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            int tel = Integer.parseInt(txtTel.getText());


            CustomerDto customerDto = new CustomerDto(custId,name,address,tel);

            try {
                boolean updatedCustomer = updateCustomerBO.updateCustomer(new CustomerDto(custId,name,address,tel));

                if (updatedCustomer) {
                    System.out.println("customer updated");
                    new Alert(Alert.AlertType.CONFIRMATION, "updated successfully!").show();
                    CustomerFormController.customerFormController.loadAllCustomers();
                    Stage stage = (Stage) txtCustId.getScene().getWindow();
                    stage.close();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

