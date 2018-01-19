/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb2;

import java.lang.ModuleLayer.Controller;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Administrator
 */
public class MainProgram extends Application {
    
    static Scene mainScene,addScene,searchScene;
    static Stage mainStage;
    static Connection connect;
    
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        mainScene = new Scene(root);
        root = FXMLLoader.load(getClass().getResource("addStudent.fxml"));
        addScene = new Scene(root);
        root = FXMLLoader.load(getClass().getResource("searchStudent.fxml"));
        searchScene = new Scene(root);
        mainStage.setTitle("BB2 Student Database");
        mainStage.setScene(mainScene);
        mainStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","15711242");
        launch(args);
    }
    
}
