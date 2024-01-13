package lk.ijse.finalwithlayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lk.ijse.finalwithlayered.bo.BOFactory;
import lk.ijse.finalwithlayered.bo.boInter.RawMaterialBO;
import lk.ijse.finalwithlayered.dto.MaterialDto;
import lk.ijse.finalwithlayered.dto.tm.MaterialTm;
import lk.ijse.finalwithlayered.entity.Material;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RawMaterialFormController {

    @FXML
    private Label lblMaterialId;
    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colMaterialId;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableColumn<?, ?> colqtyOnHand;


    @FXML
    private TextField txtDescription;


    @FXML
    private TextField txtQtyOnHand;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TableView<MaterialTm> tblMaterial;

    RawMaterialBO rawMaterialBO= (RawMaterialBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RAW_MATERIAL);

    public void initialize() {
        loadAllMaterials();
        setCellValueFactory();
        addButtonsToTable();
        generateNextMaterialId();

    }

    private String generateNextMaterialId() {
        String materialId = null;
        try {
            materialId = rawMaterialBO.generateNextMaterialId();

            lblMaterialId.setText(materialId);

            lblMaterialId.setText(materialId);

            return materialId;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }

    private void addButtonsToTable() {


        TableColumn<MaterialTm, JFXButton> deleteCol = (TableColumn<MaterialTm, JFXButton>) tblMaterial.getColumns().get(4);

        deleteCol.setCellValueFactory(param -> {
            JFXButton btnDelete = new JFXButton("       ");
            btnDelete.setCursor(Cursor.HAND);
            Font font = Font.font("Courier New", FontWeight.BOLD, 14);
            btnDelete.setFont(font);

            btnDelete.setMaxSize(25, 25);

            ImageView editImage = new ImageView(new Image("/icons/delete.png"));
            editImage.setFitWidth(25); // Set the width of the image
            editImage.setFitHeight(25); // Set the height of the image
            btnDelete.setGraphic(editImage);


            btnDelete.setOnAction(event -> {
                String id = param.getValue().getMaterialId();
                boolean isDeleted = false;
                try {
                    isDeleted = rawMaterialBO.deleteMaterial(id);
                    if (isDeleted) {
                        System.out.println("material deleted Successfully!!");
                        tblMaterial.getItems().remove(param.getValue());
                        loadAllMaterials();
                    } else {
                        System.out.println("something went wrong !!");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }



            });
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

    }

    private void setCellValueFactory() {
        colMaterialId.setCellValueFactory(new PropertyValueFactory<>("materialId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colqtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }

    private void loadAllMaterials() {

        ArrayList<MaterialDto> dtoList = null;

        try {
            dtoList = rawMaterialBO.getAllMaterials();
            ObservableList<MaterialTm> obList = FXCollections.observableArrayList();


            for (MaterialDto dto : dtoList) {
                obList.add(
                        new MaterialTm(
                                dto.getMaterialId(),
                                dto.getDescription(),
                                dto.getQtyOnHand(),
                                dto.getUnitPrice()

                        )
                );
            }
            tblMaterial.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }





    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        clearFields();

    }

    @FXML

    void btnSaveOnAction(ActionEvent event) {

        boolean validateMaterial = validateMaterial();

        if(validateMaterial){

            String materialId = lblMaterialId.getText();
            String description = txtDescription.getText();
            String qtyOnHand = txtQtyOnHand.getText();
            double unitPrice = Double.parseDouble((txtUnitPrice.getText()));

            MaterialDto dto = new MaterialDto(materialId,description,qtyOnHand,unitPrice);

            try {
                boolean savedMaterial =rawMaterialBO .saveMaterial(dto);

                if(savedMaterial){

                    System.out.println(generateNextMaterialId());
                    generateNextMaterialId();

                    new Alert(Alert.AlertType.CONFIRMATION,"material saved!").show();
                    loadAllMaterials();
                    clearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,"material can not saved").show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }




    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Material materialDto = null;
        try {
            materialDto = rawMaterialBO.getMaterial(txtSearch.getText());
            lblMaterialId.setText(materialDto.getMaterialId());
            txtDescription.setText(materialDto.getDescription());
            txtQtyOnHand.setText(materialDto.getQtyOnHand());
            txtUnitPrice.setText(String.valueOf(materialDto.getUnitPrice()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        boolean validateMaterial = validateMaterial();

        if(validateMaterial){
            String materialId = lblMaterialId.getText();
            String description = txtDescription.getText();
            String qtyOnhand = txtQtyOnHand.getText();
            double unitPrice = Double.parseDouble((txtUnitPrice.getText()));


            MaterialDto materialDto = new MaterialDto(materialId,description,qtyOnhand,unitPrice);

            boolean updateMaterial = false;
            try {
                updateMaterial = rawMaterialBO.updateMaterial(materialDto);
                if (updateMaterial) {
                    System.out.println("material updated");
                    new Alert(Alert.AlertType.CONFIRMATION, "updated successfully!").show();
                    loadAllMaterials();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

        }



    }


    @FXML
    void txtSearchBarOnAction(ActionEvent event) {
        btnSearchOnAction(event);
    }
    private boolean validateMaterial() {
        String id = lblMaterialId.getText();
        boolean isValid = Pattern.matches("[M][0-9]{3,}", id);

        if (!isValid){
            new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
            return false;
        }


        String qtyOnHand= txtQtyOnHand.getText();
        boolean isValidQty = Pattern.matches("([a-zA-Z0-9\\s]+)", qtyOnHand);

        if (!isValidQty){
            new Alert(Alert.AlertType.ERROR, "Invalid QtyType").show();
            return false;
        }

        String unitPrice = (txtUnitPrice.getText());
        boolean isValidUnitPrice = Pattern.matches("^\\d{0,8}[.]?\\d{1,2}$", unitPrice);

        if (!isValidUnitPrice){
            new Alert(Alert.AlertType.ERROR, "Invalid Price").show();
            return false;
        }

        return true;
    }
public void clearFields(){
    lblMaterialId.setText("");
    txtDescription.setText("");
    txtQtyOnHand.setText("");
    txtUnitPrice.setText("");

}
}
