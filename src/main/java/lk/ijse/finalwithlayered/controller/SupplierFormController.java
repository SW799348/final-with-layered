package lk.ijse.finalwithlayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lk.ijse.finalwithlayered.bo.BOFactory;
import lk.ijse.finalwithlayered.bo.boInter.SupplierBO;
import lk.ijse.finalwithlayered.dto.SupplierDto;
import lk.ijse.finalwithlayered.dto.tm.SupplierTm;
import lk.ijse.finalwithlayered.entity.Supplier;
import lk.ijse.finalwithlayered.navigation.Navigation;
import lk.ijse.finalwithlayered.navigation.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierFormController {

    public String updateSupId;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSupId;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblName;

    @FXML
    private Label lblSupId;

    @FXML
    private Label lbltel;

    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TextField txtSearch;

   public static SupplierFormController supplierFormController;

   SupplierBO supplierBO= (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);


    public void initialize(){
        loadAllSuppliers();
        supplierFormController = this;
        setCellValueFactory();
        addButtonsToTable();


    }

    private void addButtonsToTable() {
        TableColumn<SupplierTm, JFXButton> editCol = (TableColumn<SupplierTm, JFXButton>)tblSupplier .getColumns().get(4);

        editCol.setCellValueFactory(param -> {
            JFXButton btnEdit = new JFXButton("       ");
            btnEdit.setCursor(Cursor.HAND);
            Font font = Font.font("Courier New", FontWeight.BOLD, 5);
            btnEdit.setFont(font);

            btnEdit.setMaxSize(25, 25);

            ImageView editImage = new ImageView(new Image("/icons/edit.png"));
            editImage.setFitWidth(25); // Set the width of the image
            editImage.setFitHeight(25); // Set the height of the image
            btnEdit.setGraphic(editImage);


            btnEdit.setOnAction(event -> {
                updateSupId = param.getValue().getSupplierId();
                try {

                    Navigation.navigatePopUpWindow(Routes.UPDATE_SUPPLIER);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            });
            return new ReadOnlyObjectWrapper<>(btnEdit);
        });

        TableColumn<SupplierTm, JFXButton> deleteCol = (TableColumn<SupplierTm, JFXButton>) tblSupplier.getColumns().get(5);

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
                String id = param.getValue().getSupplierId();
                boolean isDeleted = false;
                try {
                    isDeleted = supplierBO.deleteSupplier(id);

                    if (isDeleted) {
                        System.out.println("supplier deleted Successfully!!");
                        tblSupplier.getItems().remove(param.getValue());
                        loadAllSuppliers();
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
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("contact_info"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    public void loadAllSuppliers() {

        ArrayList<SupplierDto> dtoList = null;

        try {
            dtoList = supplierBO.getAllSuppliers();

            ObservableList<SupplierTm> obList = FXCollections.observableArrayList();


            for (SupplierDto dto : dtoList) {
                obList.add(
                        new SupplierTm(
                                dto.getSupplierId(),
                                dto.getName(),
                                dto.getContact_info(),
                                dto.getAddress()

                        )
                );
            }
            tblSupplier.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {
        try {
            Navigation.navigatePopUpWindow(Routes.ADD_SUPPLIER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Supplier supplierDto = null;
        try {
            supplierDto = supplierBO.searchSupplier(txtSearch.getText());
            lblSupId.setText(supplierDto.getSupplierId());
            lblName.setText(supplierDto.getName());
            lbltel.setText(String.valueOf(supplierDto.getContact_info()));
            lblAddress.setText(supplierDto.getAddress());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        btnSearchOnAction(event);
    }

}
