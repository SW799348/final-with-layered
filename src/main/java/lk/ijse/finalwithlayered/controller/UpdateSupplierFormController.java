package lk.ijse.finalwithlayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalwithlayered.bo.BOFactory;
import lk.ijse.finalwithlayered.bo.boInter.SupplierBO;
import lk.ijse.finalwithlayered.bo.boInter.UpdateSupplierBO;
import lk.ijse.finalwithlayered.dto.SupplierDto;
import lk.ijse.finalwithlayered.entity.Supplier;

import java.sql.SQLException;

public class UpdateSupplierFormController {

    @FXML
    private AnchorPane anchorPaneForm;

    @FXML
    private JFXButton btnClose;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSuppId;

    @FXML
    private TextField txtTel;

    SupplierBO supplierBO= (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    UpdateSupplierBO updateSupplierBO= (UpdateSupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.UPDATE_SUPPLIER);

    public void initialize(){

        loadAllSupplierDetails(SupplierFormController.supplierFormController.updateSupId);

    }

    private void loadAllSupplierDetails(String updateSupId) {

        Supplier supplierDto = null;
        try {
            supplierDto = supplierBO.searchSupplier(updateSupId);

            txtSuppId.setText(supplierDto.getSupplierId());
            txtSuppId.setEditable(false);
            txtName.setText(supplierDto.getName());
            txtTel.setText(String.valueOf(supplierDto.getContact_info()));
            txtAddress.setText(supplierDto.getAddress());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        txtSuppId.setText("");
        txtName.setText("");
        txtTel.setText("");
        txtAddress.setText("");
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) txtSuppId.getScene().getWindow();
        stage.close();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {

        String supId = txtSuppId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        var contact_info = txtTel.getText();


        SupplierDto supplierDto = new SupplierDto(supId, name, contact_info, address);
        boolean updatedSupplier = false;
        try {
            updatedSupplier = updateSupplierBO.updateSupplier(supplierDto);
            if (updatedSupplier) {
                System.out.println("supplier updated");
                new Alert(Alert.AlertType.CONFIRMATION, "updated successfully!").show();
                SupplierFormController.supplierFormController.loadAllSuppliers();

                Stage stage = (Stage) txtSuppId.getScene().getWindow();
                stage.close();
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }
}

