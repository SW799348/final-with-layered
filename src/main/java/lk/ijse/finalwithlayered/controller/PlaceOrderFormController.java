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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lk.ijse.finalwithlayered.bo.BOFactory;
import lk.ijse.finalwithlayered.bo.boInter.PlaceOrderBO;
import lk.ijse.finalwithlayered.dbConnection.DbConnection;
import lk.ijse.finalwithlayered.dto.CustomerDto;
import lk.ijse.finalwithlayered.dto.MaterialDto;
import lk.ijse.finalwithlayered.dto.PlaceOrderDto;
import lk.ijse.finalwithlayered.dto.tm.CartTm;
import lk.ijse.finalwithlayered.entity.Customer;
import lk.ijse.finalwithlayered.entity.Material;
import lk.ijse.finalwithlayered.navigation.Navigation;
import lk.ijse.finalwithlayered.navigation.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaceOrderFormController {


    @FXML
    private TextField txtOrderS;

    @FXML
    private ComboBox<String> cmbCustId;

    @FXML
    private ComboBox<String> cmbMaterialId;

    @FXML
    private ComboBox<String> cmbSize;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colMaterialId;

    @FXML
    private TableColumn<?, ?> colQtyOnShirts;

    @FXML
    private TableColumn<?, ?> colRequireMaterialQtyForEachSizes;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane subAnchorPane;

    @FXML
    private TableView<CartTm> tblOrderCart;


    @FXML
    private TextField txtQtyOfShirts;

    @FXML
    private TextField txtRequiredMaterialQty;

    private final ObservableList<CartTm> obList = FXCollections.observableArrayList();

    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_ORDER);


    public void initialize() {
        setCustomerComboBox();
        setMaterialComboBox();
        cmbCustId.getItems().addAll();
        generateNextOrderId();
        setDate();
        setSizeComboBox();
        setCellValueFactory();
        addButtonsToTable();


    }

    private void addButtonsToTable() {

        TableColumn<CartTm, JFXButton> deleteCol = (TableColumn<CartTm, JFXButton>) tblOrderCart.getColumns().get(7);

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

            btnDelete.setOnAction((e) -> {
                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                if (type.orElse(no) == yes) {
                    int index = tblOrderCart.getSelectionModel().getSelectedIndex();
                    obList.remove(index);
                    tblOrderCart.refresh();

                    calculateNetTotal();
                }
            });


            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

    }


    @FXML
    void txtQtyOfShirtsOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);

    }


    @FXML
    void txtRequiredMaterialQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }


    private void setSizeComboBox() {
        cmbSize.getItems().add("xs");
        cmbSize.getItems().add("Small");
        cmbSize.getItems().add("Medium");
        cmbSize.getItems().add("Large");
        cmbSize.getItems().add("XL");
    }

    private void setCellValueFactory() {
        colMaterialId.setCellValueFactory(new PropertyValueFactory<>("materialId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colRequireMaterialQtyForEachSizes.setCellValueFactory(new PropertyValueFactory<>("requiredMaterialQty"));
        colQtyOnShirts.setCellValueFactory(new PropertyValueFactory<>("qtyOfShirts"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }

    private void setDate() {
        String date = String.valueOf(LocalDate.now());
        lblDate.setText(date);
    }

    private void setMaterialComboBox() {
        ObservableList<String> materialList = FXCollections.observableArrayList();

        ArrayList<String> allMaterialIds = null;
        try {
            allMaterialIds = placeOrderBO.getAllMaterialIds();
            for (String ids : allMaterialIds) {
                materialList.add(ids);
            }
            cmbMaterialId.setItems(materialList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }




    }

    private String generateNextOrderId() {

        String orderId = null;
        try {
            orderId = placeOrderBO.generateNextOrderId();

            lblOrderId.setText(orderId);

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCustomerComboBox() {
        ObservableList<String> custList = FXCollections.observableArrayList();

        ArrayList<String> allCustomerIds = null;
        try {
            allCustomerIds = placeOrderBO.getAllCustomerIds();
            for (String ids : allCustomerIds) {
                custList.add(ids);
            }
            cmbCustId.setItems(custList);
        } catch (SQLException e) {


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {

        AddCustomerFormController addCustomerFormController = new AddCustomerFormController();
        try {
            Navigation.navigatePopUpWindow(Routes.ADD_CUSTOMER);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

        String materialIdValue = cmbMaterialId.getValue();
        String description = lblDescription.getText();
        String sizeValue = cmbSize.getValue();
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        int requiredMaterialQtyText = (int) Double.parseDouble(txtRequiredMaterialQty.getText());
        int qtyOfShirtsText = Integer.parseInt(txtQtyOfShirts.getText());

        double total = requiredMaterialQtyText * unitPrice * qtyOfShirtsText;

        //JFXButton btnDelete = new JFXButton();





        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if (materialIdValue.equals(colMaterialId.getCellData(i))) {
                qtyOfShirtsText += (int) colQtyOnShirts.getCellData(i);
                requiredMaterialQtyText +=(int) colRequireMaterialQtyForEachSizes.getCellData(i);
                total = qtyOfShirtsText * unitPrice * requiredMaterialQtyText;

                obList.get(i).setQtyOfShirts(qtyOfShirtsText);
                obList.get(i).setRequiredMaterialQty((int) requiredMaterialQtyText);
                obList.get(i).setTotal(total);

                tblOrderCart.refresh();
                calculateNetTotal();
                return;
            }
        }

        obList.add(new CartTm(
                materialIdValue,
                description,
                sizeValue,
                unitPrice,
                requiredMaterialQtyText,
                qtyOfShirtsText,
                total

        ));

        tblOrderCart.setItems(obList);
        calculateNetTotal();
        txtQtyOfShirts.clear();
        txtRequiredMaterialQty.clear();
        txtOrderS.clear();
    }

    private void calculateNetTotal() {
        double total = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }

        lblNetTotal.setText(String.valueOf(total));
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        colMaterialId.setText("");
        colDescription.setText("");
        colSize.setText("");
        colUnitPrice.setText("");
        colRequireMaterialQtyForEachSizes.setText("");
        colQtyOnShirts.setText("");
        colTotal.setText("");

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = lblOrderId.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());
        String cusId = cmbCustId.getValue();
        String status = txtOrderS.getText();


        List<CartTm> tmList = new ArrayList<>();

        for (CartTm cartTm : obList) {
            tmList.add(cartTm);
        }
        var placeOrderDto = new PlaceOrderDto(
                orderId,
                date,
                cusId,
                status,
                tmList

        );

        boolean isSuccess = false;
        try {
            isSuccess = placeOrderBO.placeOrder(placeOrderDto);
            if (isSuccess) {
                new Alert(Alert.AlertType.CONFIRMATION, "order completed!").show();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) {
        String custIdValue = cmbCustId.getValue();

        try {
            Customer customer = placeOrderBO.getCustomer(custIdValue);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void cmbMaterialIdOnAction(ActionEvent event) {
        String materialIdValue = cmbMaterialId.getValue();

        Material materialDto = null;
        try {
            materialDto = placeOrderBO.getMaterial(materialIdValue);
            lblDescription.setText(materialDto.getDescription());
            lblUnitPrice.setText(String.valueOf(materialDto.getUnitPrice()));
            lblQtyOnHand.setText(String.valueOf(materialDto.getQtyOnHand()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



    }

    @FXML
    void cmbSizeOnAction(ActionEvent event) {

        String sizeValue = cmbSize.getValue();

        txtRequiredMaterialQty.requestFocus();
        txtQtyOfShirts.requestFocus();

    }

    public void btnReportOnAction(ActionEvent actionEvent) {
        try{
            InputStream resourceAsStream = getClass().getResourceAsStream("/report/placeOrder.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(load);

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport, //compiled report
                            null,
                            DbConnection.getInstance().getConnection() //database connection
                    );

            JasperViewer.viewReport(jasperPrint, false);
        }catch (JRException e){
            e.printStackTrace();
        }
    }
}
