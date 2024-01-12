package lk.ijse.finalwithlayered.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import lk.ijse.finalwithlayered.bo.BOFactory;
import lk.ijse.finalwithlayered.bo.boInter.AttendanceBO;
import lk.ijse.finalwithlayered.bo.boInter.EmployeeBO;
import lk.ijse.finalwithlayered.dto.AttendanceDto;
import lk.ijse.finalwithlayered.dto.EmployeeDto;
import lk.ijse.finalwithlayered.dto.tm.AttendanceTm;


import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class AttendanceFormController {

    @FXML
    private ComboBox<String> cmbEmployeeId;

    @FXML
    private TableColumn<?, ?> colAttendanceId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDelete;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colTimeIn;

    @FXML
    private TableColumn<?, ?> colTimeOut;

    @FXML
    private TableColumn<?, ?> colWorkingHours;

    @FXML
    private DatePicker datePickerId;

    @FXML
    private Label lblAttendanceId;

    @FXML
    private Label lblCurrentTime;

    @FXML
    private Label lblTimeIn;

    @FXML
    private Label lblWorkingHours;

    @FXML
    private Label lblTimeOut;

    @FXML
    private TableView<AttendanceTm> tblAttendance;

    @FXML
    private TextField txtSearch;

    AttendanceBO attendanceBO= (AttendanceBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ATTENDANCE);

    EmployeeBO employeeBO= (EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EMPLOYEE);


    public void initialize(){
        setEmpIdComboBox();
        generateRealTime();
        datePickerId.setValue(LocalDate.now());
        generateNextAttendanceId();
        loadAllAttendance();
        setCellValueFactory();
        addButtonsToTable();


    }


    private void setCellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        colAttendanceId.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTimeIn.setCellValueFactory(new PropertyValueFactory<>("time_in"));
        colTimeOut.setCellValueFactory(new PropertyValueFactory<>("time_out"));
        colWorkingHours.setCellValueFactory(new PropertyValueFactory<>("working_hours"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

    }

    private void loadAllAttendance() {

        ArrayList<AttendanceDto> dtoList = null;

        dtoList = attendanceBO.getAllAttendances();

        for (int i = 0; i < dtoList.size(); i++) {
             System.out.println( dtoList.get(i).getEmpId());
            System.out.println( dtoList.get(i).getAttendanceId());
            System.out.println( dtoList.get(i).getDate());
            System.out.println( dtoList.get(i).getTime_out());
            System.out.println( dtoList.get(i).getTime_in());
            System.out.println( dtoList.get(i).getWorking_hours());
        }
        ObservableList<AttendanceTm> obList = FXCollections.observableArrayList();


        for (AttendanceDto dto : dtoList) {
            obList.add(
                    new AttendanceTm(
                            dto.getEmpId(),
                            dto.getAttendanceId(),
                            dto.getDate(),
                            dto.getTime_in(),
                            dto.getTime_out(),
                            dto.getWorking_hours()

                    )
            );
        }
        tblAttendance.setItems(obList);




    }

    private void setEmpIdComboBox() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        ArrayList<String> allId = new EmployeeModel().getAllId();
        for (String id : allId) {
            obList.add(id);
        }
        cmbEmployeeId.setItems(obList);

    }



    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        cmbEmployeeId.setValue("");


    }
    @FXML
    void cmbEmployeeIdOnAction(ActionEvent event) {
        String id = cmbEmployeeId.getValue();
        EmployeeModel employeeModel = new EmployeeModel();

        EmployeeDto employee = employeeModel.getEmployee(id);


       // txtEmpId.setText(employee.getEmpId());
    }

    private void addButtonsToTable() {


        TableColumn<AttendanceTm, JFXButton> deleteCol = (TableColumn<AttendanceTm, JFXButton>) tblAttendance.getColumns().get(6);

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
                String id = param.getValue().getAttendanceId();
                AttendanceModel attendanceModel = new AttendanceModel();
                boolean isDeleted = attendanceModel.deleteAttendance(id);

                if (isDeleted) {
                    System.out.println("customer deleted Successfully!!");
                    loadAllAttendance();
                } else {
                    System.out.println("something went wrong !!");
                }


            });
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean validatedAttendance = validateAttendance();

        if(validatedAttendance){
            String empId = cmbEmployeeId.getValue();
            Date date = Date.valueOf(datePickerId.getValue());
            LocalTime timeIn = LocalTime.parse(lblCurrentTime.getText());
            String attendanceId = lblAttendanceId.getText();



            AttendanceDto dto = new AttendanceDto(
                    attendanceId,
                    empId,
                    date,
                    timeIn,
                    timeIn,
                    0
            );

            AttendanceModel attendanceModel = new AttendanceModel();


                boolean isSaved =attendanceModel .saveAttendance(dto);

                if (isSaved){
                    System.out.println("saved attendance");
                    new Alert(Alert.AlertType.CONFIRMATION,"attendance saved").show();
                    loadAllAttendance();
                }else {
                    System.out.println("not saved attendance");
                }


        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean isUpdated= new AttendanceModel().updateAttendance(cmbEmployeeId.getValue(), Date.valueOf(datePickerId.getValue()), LocalTime.parse(lblCurrentTime.getText()));

        if (isUpdated){
            System.out.println("out time updated");
            new AttendanceModel().calculateWorkingHours(cmbEmployeeId.getValue(),Date.valueOf(datePickerId.getValue()));
            loadAllAttendance();
        }else {
            System.out.println("out time not updated");

        }
    }
    private String generateNextAttendanceId() {

            String attendanceId = AttendanceModel.generateNextAttendanceId();
            lblAttendanceId.setText(attendanceId);

            return null;
    }
    private boolean validateAttendance() {
        String id = cmbEmployeeId.getValue();
        boolean isValid = Pattern.matches("[E][0-9]{3,}", id);

        if (!isValid){
            new Alert(Alert.AlertType.ERROR, "Invalid ID").show();
            return false;
        }

        return true;

    }
    private void generateRealTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            lblCurrentTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
}
}
