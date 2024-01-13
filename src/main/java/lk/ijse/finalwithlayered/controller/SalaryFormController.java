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
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lk.ijse.finalwithlayered.bo.BOFactory;
import lk.ijse.finalwithlayered.bo.boInter.AttendanceBO;
import lk.ijse.finalwithlayered.bo.boInter.EmployeeBO;
import lk.ijse.finalwithlayered.bo.boInter.SalaryBO;
import lk.ijse.finalwithlayered.dto.EmployeeDto;
import lk.ijse.finalwithlayered.dto.SalaryDto;
import lk.ijse.finalwithlayered.dto.tm.SalaryTm;
import lk.ijse.finalwithlayered.entity.Employee;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SalaryFormController {



    @FXML
    private DatePicker datePickerEnd;

    @FXML
    private DatePicker datePickerStart;

    @FXML
    private ComboBox<String> cmbEmployeeId;

    @FXML
    private ComboBox<String> cmbMonth;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colMonth;

    @FXML
    private TableColumn<?, ?> colOtHours;

    @FXML
    private TableColumn<?, ?> colSalaryId;

    @FXML
    private TableColumn<?, ?> colTotalSalary;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblHoursToBeWorkedPerMonth;

    @FXML
    private Label lblOtHours;

    @FXML
    private Label lblSalaryId;

    @FXML
    private Label lblTotal;

    @FXML
    private TableView<SalaryTm> tblSalary;

    @FXML
    private TextField txtSearch;
    private final ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

    SalaryBO salaryBO= (SalaryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SALARY);
    AttendanceBO attendanceBO= (AttendanceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ATTENDANCE);

    EmployeeBO employeeBO= (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);

    public void initialize() {
        getAllSalaries();
        setCellValueFactroy();
        addButtonsToTable();
        setEmployeeComboBox();
        setMonthComboBox();
        generateNextSalaryId();
        setDate();

    }

    @FXML
    void btnEnterOnAction(ActionEvent event) {

        var salaryId = lblSalaryId.getText();
        var employeeIdValue = cmbEmployeeId.getValue();
        var startDate = datePickerStart.getValue();
        var endDate = datePickerEnd.getValue();
        var monthValue = cmbMonth.getValue();

        int totalWorkingHours = 0;
        try {
            totalWorkingHours = Integer.parseInt(String.valueOf(attendanceBO.getTotalWorkingHours(employeeIdValue,startDate, endDate )));

            int Number_of_hours_to_be_worked_permonth = 160;

            int ot = -(totalWorkingHours - Number_of_hours_to_be_worked_permonth);

            lblOtHours.setText(String.valueOf(ot));

            double hourPay = 200;

            double basicSalary = 40000;

            double totalSalary = 40000 + (ot * 200);

            lblTotal.setText(String.valueOf(totalSalary));

            var salaryDto = new SalaryDto(salaryId,employeeIdValue,monthValue,ot,totalSalary,totalWorkingHours);


            boolean isDone = salaryBO.saveSalary(salaryDto);

            //salaryBO.checkSalary();

            if (isDone) {
                new Alert(Alert.AlertType.CONFIRMATION,"salary done Successfully").show();
                System.out.println("salary done Successfully!!");
                getAllSalaries();
            } else {
                new Alert(Alert.AlertType.ERROR).show();
                System.out.println("something went wrong !!");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    void endDateOnAction(KeyEvent event) {
        var salaryId = lblSalaryId.getText();
        var employeeIdValue = cmbEmployeeId.getValue();
        var startDate = datePickerStart.getValue();
        var endDate = datePickerEnd.getValue();
        var monthValue = cmbMonth.getValue();

        int totalWorkingHours = 0;
        try {
            totalWorkingHours = Integer.parseInt(String.valueOf(attendanceBO.getTotalWorkingHours(employeeIdValue,startDate, endDate )));

            int Number_of_hours_to_be_worked_permonth = 160;

            int ot = -(totalWorkingHours - Number_of_hours_to_be_worked_permonth);

            lblOtHours.setText(String.valueOf(ot));

            double hourPay = 200;

            double basicSalary = 40000;

            double totalSalary = 40000 + (ot * 200);

            lblTotal.setText(String.valueOf(totalSalary));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void setMonthComboBox() {
        cmbMonth.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    }

    private void setDate() {
        String date = String.valueOf(LocalDate.now());
        lblDate.setText(date);
    }


    private void setEmployeeComboBox() {
        ObservableList<String> employeeList = FXCollections.observableArrayList();

        ArrayList<String> allEmployeeIds = null;
        try {
            allEmployeeIds = employeeBO.getAllEmpId();

            for (String ids : allEmployeeIds) {
                employeeList.add(ids);
            }
            cmbEmployeeId.setItems(employeeList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    private void addButtonsToTable() {

        TableColumn<SalaryTm, JFXButton> deleteCol = (TableColumn<SalaryTm, JFXButton>) tblSalary.getColumns().get(5);

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
                String id = param.getValue().getSalaryId();
                boolean isDeleted = false;
                try {
                    isDeleted = salaryBO.deleteSalary(id);

                    if (isDeleted) {
                        System.out.println("salary deleted Successfully!!");
                        getAllSalaries();
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

    private void setCellValueFactroy() {
        colSalaryId.setCellValueFactory(new PropertyValueFactory<>("SalaryId"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colMonth.setCellValueFactory(new PropertyValueFactory<>("month"));
        colOtHours.setCellValueFactory(new PropertyValueFactory<>("ot_hours"));
        colTotalSalary.setCellValueFactory(new PropertyValueFactory<>("total_salary"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }

    private void getAllSalaries() {

        ArrayList<SalaryDto> dtoList = null;

        try {
            dtoList = salaryBO.getAllSalaries();

            ObservableList<SalaryTm> obList = FXCollections.observableArrayList();


            for (SalaryDto dto : dtoList) {
                obList.add(
                        new SalaryTm(
                                dto.getSalaryId(),
                                dto.getEmpId(),
                                dto.getMonth(),
                                dto.getOt_hours(),
                                dto.getTotal_salary(),
                                dto.getAmount_of_work_done_per_month()

                        )
                );
            }
            tblSalary.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
      lblSalaryId.setText("");
      cmbEmployeeId.setValue("");
      cmbMonth.setValue("");
      datePickerStart.setValue(LocalDate.parse(""));
      datePickerEnd.setValue(LocalDate.parse(""));
      lblOtHours.setText("");
      lblTotal.setText("");

    }

    @FXML
    void cmbMonthOnAction(ActionEvent event) {

    }


    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {
        String employeeIdValue = cmbEmployeeId.getValue();

        try {
            Employee employeeDto = employeeBO.getEmployee(employeeIdValue);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    private String generateNextSalaryId() {

        String salaryId = null;
        try {
            salaryId = salaryBO.generateNextSalaryId();

            lblSalaryId.setText(salaryId);

            return null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
