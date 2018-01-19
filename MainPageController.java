/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb2;

import static bb2.MainProgram.addScene;
import static bb2.MainProgram.mainStage;
import static bb2.MainProgram.searchScene;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Administrator
 */
public class MainPageController implements Initializable {
    /**
     * Initializes the controller class.
     */
    
    @FXML Button addStudentButton;
    @FXML Button searchStudentButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)  {
    }

    @FXML
    private void handleAddStudentButtonAction(ActionEvent event) {
        mainStage.setScene(addScene);
    }    
    
    @FXML
    private void handleSearchStudentButtonAction(ActionEvent event) {
        mainStage.setScene(searchScene);
    }    
    
}
