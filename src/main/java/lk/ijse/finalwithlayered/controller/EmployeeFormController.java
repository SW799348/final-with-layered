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
import lk.ijse.finalwithlayered.bo.boInter.EmployeeBO;
import lk.ijse.finalwithlayered.dbConnection.DbConnection;
import lk.ijse.finalwithlayered.dto.EmployeeDto;
import lk.ijse.finalwithlayered.dto.tm.EmployeeTm;
import lk.ijse.finalwithlayered.entity.Employee;
import lk.ijse.finalwithlayered.navigation.Navigation;
import lk.ijse.finalwithlayered.navigation.Routes;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colEmpId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableColumn<?, ?> colUpdate;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private Label lblAddress;

    @FXML
    private Label lblEmpid;

    @FXML
    private Label lblName;

    @FXML
    private Label lblUserId;

    @FXML
    private Label lbltel;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtSearch;


    public static EmployeeFormController employeeFormController;

    public String updateEmpId;

    EmployeeBO employeeBO= (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void initialize() {
        employeeFormController = this;
        loadAllEmployees();
        setCellValueFactory();
        addButtonsToTable();


    }

    public void loadAllEmployees() {
        ArrayList<EmployeeDto> dtoList = null;
        try {
            dtoList = employeeBO.getAllEmployees();

            ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();


            for (EmployeeDto dto : dtoList) {
                obList.add(
                        new EmployeeTm(
                                dto.getEmpId(),
                                dto.getName(),
                                dto.getAddress(),
                                dto.getTel(),
                                dto.getUserId()
                        )
                );
            }
            tblEmployee.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void addButtonsToTable() {
        TableColumn<EmployeeTm, JFXButton> editCol = (TableColumn<EmployeeTm, JFXButton>) tblEmployee.getColumns().get(5);

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
                updateEmpId = param.getValue().getEmpId();
                try {

                    Navigation.navigatePopUpWindow(Routes.UPDATE_EMPLOYEE);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


            });
            return new ReadOnlyObjectWrapper<>(btnEdit);
        });

        TableColumn<EmployeeTm, JFXButton> deleteCol = (TableColumn<EmployeeTm, JFXButton>) tblEmployee.getColumns().get(6);

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
                String id = param.getValue().getEmpId();
                boolean isDeleted = false;
                try {
                    isDeleted = employeeBO.deleteEmployee(id);

                    if (isDeleted) {
                        System.out.println("customer deleted Successfully!!");
                        tblEmployee.getItems().remove(param.getValue());
                        //loadAllEmployees();
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
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUpdate.setCellValueFactory(new PropertyValueFactory<>("btnEdit"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

    }

    @FXML
    void btnAddEmployeeOnAction(ActionEvent event) throws IOException {
        Navigation.navigatePopUpWindow(Routes.ADD_EMPLOYEE);

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Employee employee = null;
        try {
            employee = employeeBO.getEmployee(txtSearch.getText());

            lblUserId.setText(employee.getUserId());
            lblEmpid.setText(employee.getEmpId());
            lblName.setText(employee.getName());
            lblAddress.setText(employee.getAddress());
            lbltel.setText(String.valueOf(employee.getTel()));

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

    @FXML
    void btnGenerateReportOnAction(ActionEvent event) {
        try{
            InputStream resourceAsStream = getClass().getResourceAsStream("/report/employee.jrxml");
            JasperDesign load = JRXmlLoader.load(resourceAsStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(load);

            JasperPrint jasperPrint =
                    JasperFillManager.fillReport(
                            jasperReport, //compiled report
                            null,
                            DbConnection.getDbConnection().getConnection()
                    );

            JasperViewer.viewReport(jasperPrint, false);
        }catch (JRException | SQLException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}

