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
import lk.ijse.finalwithlayered.bo.boInter.CustomerBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dto.CustomerDto;
import lk.ijse.finalwithlayered.dto.tm.CustomerTm;
import lk.ijse.finalwithlayered.entity.Customer;
import lk.ijse.finalwithlayered.navigation.Navigation;
import lk.ijse.finalwithlayered.navigation.Routes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerFormController {

    public String updateCustomerId;

    @FXML
    private TableColumn<?, ?> colCustId;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblCustId;

    @FXML
    private Label lblName;

    @FXML
    private Label lbltel;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtSearch;

    public static CustomerFormController customerFormController;

    CustomerBO customerBO= (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize() {
        customerFormController = this;
        loadAllCustomers();
        setCellValueFactory();
        addButtonsToTable();


    }

    public void loadAllCustomers() {

        tblCustomer.getItems().clear();
        /*Get all customers*/
        try {
            ArrayList<CustomerDto> allCustomers = customerBO.getAllCustomers();

            for (CustomerDto dto : allCustomers){
                tblCustomer.getItems().add(
                        new CustomerTm(
                                dto.getCustId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getTel()
                        )
                );
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "aaaaaaaa").show();
        }

    }

    private void addButtonsToTable() {
        TableColumn<CustomerTm, JFXButton> editCol = (TableColumn<CustomerTm, JFXButton>) tblCustomer.getColumns().get(4);

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
                updateCustomerId = param.getValue().getCustId();
                try {

                    Navigation.navigatePopUpWindow(Routes.UPDATE_CUSTOMER);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            });
            return new ReadOnlyObjectWrapper<>(btnEdit);
        });

        TableColumn<CustomerTm, JFXButton> deleteCol = (TableColumn<CustomerTm, JFXButton>) tblCustomer.getColumns().get(5);

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
                String id = param.getValue().getCustId();

                boolean isDeleted = false;
                try {
                    isDeleted = customerBO.deleteCustomer(id);

                    if (isDeleted) {
                        tblCustomer.getItems().remove(param.getValue());
                        System.out.println("customer deleted Successfully!!");

                    } else {
                        System.out.println("something went wrong !!");
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR,"aaaa").show();
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            });
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

    }

    private void setCellValueFactory() {
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("btnEdit"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

    }


    @FXML
    void btnSearchOnAction(ActionEvent event) {

        Customer customer = null;
        try {
            customer = customerBO.getCustomer(txtSearch.getText());

            lblCustId.setText(customer.getCustId());
            lblName.setText(customer.getName());
            lblAddress.setText(customer.getAddress());
            lbltel.setText(String.valueOf(customer.getTel()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }



    @FXML
    void txtSearchBarOnAction(ActionEvent event) {
        btnSearchOnAction(event);
    }

    public void btnAddCustomerOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigatePopUpWindow(Routes.ADD_CUSTOMER);
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
    }
}
