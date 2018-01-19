/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb2;

import static bb2.MainProgram.connect;
import static bb2.MainProgram.mainScene;
import static bb2.MainProgram.mainStage;
import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class AddStudentController implements Initializable {

    @FXML private Button backButton;
    @FXML private Button saveButton;
    @FXML private Button clearButton;
    @FXML private RadioButton defaultButton;
    @FXML private ChoiceBox prefixChoiceBox;
    @FXML private ChoiceBox levelChoiceBox;
    @FXML private TextField nameTextField;
    @FXML private TextField bDayTextField;
    @FXML private TextField bMonthTextField;
    @FXML private TextField bYearTextField;
    //@FXML private DatePicker dateOfBirthDatePicker;
    //@FXML private DatePicker graduationDateDatePicker;
    @FXML private TextField surnameTextField;
    @FXML private TextField IDTextField;
    @FXML private TextField nationalityTextField;
    @FXML private TextField raceTextField;
    @FXML private TextField phoneTextField;
    @FXML private TextField teacherTextField;
    @FXML private Label label;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prefixChoiceBox.getItems().add("เด็กชาย");
        prefixChoiceBox.getItems().add("เด็กหญิง");
        
        levelChoiceBox.getItems().add("อนุบาล 1");
        levelChoiceBox.getItems().add("อนุบาล 2");
        levelChoiceBox.getItems().add("อนุบาล 3");
        levelChoiceBox.getItems().add("ป.1");
        levelChoiceBox.getItems().add("ป.2");
        levelChoiceBox.getItems().add("ป.3");
        levelChoiceBox.getItems().add("ป.4");
        levelChoiceBox.getItems().add("ป.5");
        levelChoiceBox.getItems().add("ป.6");
        
        //nationalityTextField.setText("ไทย");
        //raceTextField.setText("ไทย");
    }    
    
    @FXML
    private void handleBackButton(ActionEvent event) {
        mainStage.setScene(mainScene);
    }    
    
    @FXML
    private void handleRadioButton(ActionEvent event) {
        //System.out.println("Hello Zenoel");
        if(defaultButton.isSelected())
        {
            nationalityTextField.setText("ไทย");
            raceTextField.setText("ไทย");
        }
        else
        {
            nationalityTextField.setText("");
            raceTextField.setText("");
        }
    }
    
    @FXML
    private void handleBDayButton(Event event) {
        if(bDayTextField.getText().length()>2)
            bDayTextField.replaceText(0, 3, bDayTextField.getText().substring(0, 2));
    }
    
    @FXML
    private void handleBMonthButton(Event event) {
        if(bMonthTextField.getText().length()>2)
            bMonthTextField.replaceText(0, 3, bMonthTextField.getText().substring(0, 2));
    }
    
    @FXML
    private void handleBYearButton(Event event) {
        if(bYearTextField.getText().length()>4)
            bYearTextField.replaceText(0, 5, bYearTextField.getText().substring(0, 4));
    }
    
    @FXML
    private void handleClearButton(ActionEvent event) {
        prefixChoiceBox.getSelectionModel().clearSelection();
        nameTextField.setText("");
        surnameTextField.setText("");
        IDTextField.setText("");
        bDayTextField.setText("");;
        bMonthTextField.setText("");;
        bYearTextField.setText("");;
        nationalityTextField.setText("");
        raceTextField.setText("");
        phoneTextField.setText("");
        levelChoiceBox.getSelectionModel().clearSelection();
        teacherTextField.setText("");
        //graduationDateDatePicker.getEditor().clear();
        defaultButton.setSelected(false);
    }

    @FXML
    private void handleSaveButton(ActionEvent event) throws Exception {
        try
        {
            PreparedStatement ps = connect.prepareStatement
            ("INSERT INTO studentdetail (StudentID, Prefix, Name, Surname, Nationality, Race,DateOfBirth,PhoneNumber,Level,Teacher) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            ps.setInt(1, Integer.parseInt(IDTextField.getText()));
            System.out.println(String.valueOf(prefixChoiceBox.getSelectionModel().getSelectedItem()));
            ps.setString(2, String.valueOf(prefixChoiceBox.getSelectionModel().getSelectedItem()));
            ps.setString(3, nameTextField.getText());
            ps.setString(4, surnameTextField.getText());
            ps.setString(5, nationalityTextField.getText());
            ps.setString(6, raceTextField.getText());
            int year = Integer.parseInt(bYearTextField.getText());
            int month = Integer.parseInt(bMonthTextField.getText());
            int day = Integer.parseInt(bDayTextField.getText());
            LocalDate localDate = LocalDate.of(year,month,day);
            System.out.println(localDate);
            System.out.println(localDate.toString());
            java.sql.Date sDate = java.sql.Date.valueOf( localDate );
            System.out.println(sDate);
            ps.setDate(7, sDate);
            ps.setString(8,phoneTextField.getText());
            System.out.println(String.valueOf(levelChoiceBox.getSelectionModel().getSelectedItem()));
            ps.setInt(9,lcbTextToValue(String.valueOf(levelChoiceBox.getSelectionModel().getSelectedItem())));
            System.out.println(lcbTextToValue(String.valueOf(levelChoiceBox.getSelectionModel().getSelectedItem())));
            ps.setString(10,teacherTextField.getText());
            ps.executeUpdate();
            ps.close();
            
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    private int lcbTextToValue(String level)
    {
        switch(level)
        {
            case "อนุบาล 1" : return 1;
            case "อนุบาล 2" : return 2;
            case "อนุบาล 3" : return 3;
            case "ป.1" : return 4;
            case "ป.2" : return 5;
            case "ป.3" : return 6;
            case "ป.4" : return 7;
            case "ป.5" : return 8;
            case "ป.6" : return 9;
        }
        return -1;
    }
}
