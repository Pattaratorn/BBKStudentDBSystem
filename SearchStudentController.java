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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class SearchStudentController implements Initializable {

    
    @FXML private TableView<Student> resultTableView;
    @FXML private TableColumn<Student,Integer> column1;
    @FXML private TableColumn<Student,Integer> column2;
    @FXML private TableColumn<Student,String> column3;
    @FXML private TableColumn<Student,String> column4;
    @FXML private TableColumn<Student,String> column5;
    @FXML private RadioButton IDRadioButton;
    @FXML private RadioButton fullnameRadioButton;
    @FXML private TextField IDTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField surnameTextField;
    @FXML private Button searchButton;
    @FXML private Button backButton;
    @FXML private Label searchCountLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        column1.setCellValueFactory(new PropertyValueFactory<>("Order"));
        column2.setCellValueFactory(new PropertyValueFactory<>("ID"));
        column3.setCellValueFactory(new PropertyValueFactory<>("Name"));
        column4.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        column5.setCellValueFactory(new PropertyValueFactory<>("LevelString"));
        IDTextField.setDisable(true);
        nameTextField.setDisable(true);
        surnameTextField.setDisable(true);
        searchButton.setDisable(true);
    }
    
    @FXML
    private void handleBackButton(ActionEvent event) {
        mainStage.setScene(mainScene);
    }    
    
    @FXML
    private void handleIDRadioButton(ActionEvent event) {
        IDRadioButton.setSelected(true);
        fullnameRadioButton.setSelected(false);
        IDTextField.setDisable(false);
        nameTextField.setDisable(true);
        surnameTextField.setDisable(true);
        searchButton.setDisable(false);
        nameTextField.clear();
        surnameTextField.clear();
    }
    
    @FXML
    private void handlefullnameRadioButton(ActionEvent event) {
        IDRadioButton.setSelected(false);
        fullnameRadioButton.setSelected(true);
        IDTextField.setDisable(true);
        nameTextField.setDisable(false);
        surnameTextField.setDisable(false);
        searchButton.setDisable(false);
        IDTextField.clear();
    }
    
    @FXML
    private void handleSearchButton(ActionEvent event) {
        
        try
        {
            resultTableView.getItems().clear();
            String query;
            PreparedStatement ps = null;
            ResultSet result = null;
            ObservableList<Student> studentList = FXCollections.observableArrayList();
            int order = 1;
            if(IDRadioButton.isSelected())
            {
                if(IDTextField.getText().length()==0)
                {
                    JFrame frame = new JFrame("ค้นหาผิดพลาด");
                    JOptionPane.showMessageDialog(frame,"โปรดใส่รหัสนักเรียนที่ต้องการจะค้นหาก่อน","ค้นหาไม่ได้",JOptionPane.INFORMATION_MESSAGE);
                    searchCountLabel.setText("เจอ " + 0 + " ผลลัพธ์");
                    return;
                }
                query = "SELECT StudentID, Name, Surname, Level FROM StudentDetail WHERE StudentID = ?";
                ps = connect.prepareStatement(query);
                ps.setInt(1, Integer.parseInt(IDTextField.getText()));
                result = ps.executeQuery();
                while (result.next())
                {
                    Student student = new Student(order,result.getInt("StudentID"),result.getString("Name"),result.getString("Surname"),result.getInt("Level"));
                    studentList.add(student);
                    System.out.println("รหัส : " + student.getID());
                    System.out.println("ชื่อ : " + student.getName());
                    System.out.println("นามสกุล : " + student.getSurname());
                    System.out.println("ชั้น : " + student.getLevelString());
                    order++;
                }
            }
            else if(fullnameRadioButton.isSelected())
            {
                if(nameTextField.getText().length()==0 && surnameTextField.getText().length()==0)
                {
                    JFrame frame = new JFrame("ค้นหาผิดพลาด");
                    JOptionPane.showMessageDialog(frame,"โปรดใส่ชื่อหรือนามสกุลนักเรียนที่ต้องการจะค้นหาก่อน","ค้นหาไม่ได้",JOptionPane.INFORMATION_MESSAGE);
                    searchCountLabel.setText("เจอ " + 0 + " ผลลัพธ์");
                    return;
                }
                if(nameTextField.getText().length()>0 && surnameTextField.getText().length()==0) // ชื่ออย่างเดียว
                {
                    query = "SELECT StudentID, Name, Surname, Level FROM StudentDetail WHERE Name = ?";
                    ps = connect.prepareStatement(query);
                    ps.setString(1, nameTextField.getText());
                    result = ps.executeQuery();
                }
                else if(nameTextField.getText().length()==0 && surnameTextField.getText().length()>0) // นามสกุลอย่างเดียว
                {
                    query = "SELECT StudentID, Name, Surname, Level FROM StudentDetail WHERE Surname = ?";
                    ps = connect.prepareStatement(query);
                    ps.setString(1, surnameTextField.getText());
                    result = ps.executeQuery();
                }
                else if(nameTextField.getText().length()>0 && surnameTextField.getText().length()>0) //  ทั้งคู่
                {
                    query = "SELECT StudentID, Name, Surname, Level FROM StudentDetail WHERE Name = ? AND Surname = ?";
                    ps = connect.prepareStatement(query);
                    ps.setString(1, nameTextField.getText());
                    ps.setString(2, surnameTextField.getText());
                    result = ps.executeQuery();
                }
                while (result.next())
                {
                    Student student = new Student(order,result.getInt("StudentID"),result.getString("Name"),result.getString("Surname"),result.getInt("Level"));
                    studentList.add(student);
                    System.out.println("รหัส : " + student.getID());
                    System.out.println("ชื่อ : " + student.getName());
                    System.out.println("นามสกุล : " + student.getSurname());
                    System.out.println("ชั้น : " + student.getLevelString());
                    order++;
                }
            }
            searchCountLabel.setText("เจอ " + studentList.size() + " ผลลัพธ์");
            resultTableView.setItems(studentList);
            ps.close();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
