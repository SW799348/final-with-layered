package lk.ijse.finalwithlayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalwithlayered.bo.BOFactory;
import lk.ijse.finalwithlayered.bo.boInter.AddSupplierBO;
import lk.ijse.finalwithlayered.dto.SupplierDto;

import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddSupplierFormController {

    @FXML
    private AnchorPane anchorPaneForm;

    @FXML
    private JFXButton btnClose;

    @FXML
    private Label lblSupId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;

    SupplierFormController supplierFormController;

    AddSupplierBO addSupplierBO= (AddSupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADD_SUPPLIER);

    public void initialize(){

        generateNextSupId();
    }

    private void generateNextSupId() {
        String supId = null;
        try {
            supId = addSupplierBO.generateNextSupId();

            lblSupId.setText(supId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
         txtName.clear();
         txtAddress.clear();
         txtTel.clear();
    }

    @FXML
    void btnCloseOnAction(ActionEvent event) {
        Stage stage = (Stage) lblSupId.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {

        boolean validateSupplier = validateSupplier();

        if(validateSupplier){

            String id = lblSupId.getText();
            String name = txtName.getText();
            String contact_info = txtTel.getText();
            String address = txtAddress.getText();


            SupplierDto supplierDto = new SupplierDto(id,name,contact_info,address);

            boolean savedSupplier = false;
            try {
                savedSupplier = addSupplierBO .saveSupplier(supplierDto);

                if(savedSupplier){
                    new Alert(Alert.AlertType.CONFIRMATION,"supplier saved!").show();
                    SupplierFormController.supplierFormController.loadAllSuppliers();
                    clearFields();
                }else{
                    new Alert(Alert.AlertType.ERROR,"supplier cannot saved");
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

    }

    private void clearFields() {
        txtName.setText("");
        txtTel.setText("");
        txtAddress.setText("");
    }

    private boolean validateSupplier() {
        String id = lblSupId.getText();
        boolean isValid = Pattern.matches("[S][0-9]{3,}", id);

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

}
