/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package minimarket_proyek;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Vanessa Siahaan
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane admin_form;

    @FXML
    private AnchorPane cashier_form;

    @FXML
    private Hyperlink admin_hyperlink;

    @FXML
    private Button admin_loginbtn;

    @FXML
    private PasswordField admin_password;

    @FXML
    private TextField admin_username;

    @FXML
    private TextField cashier_id;

    @FXML
    private Button cashier_loginbtn;

    @FXML
    private PasswordField cashier_password;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Hyperlink cashier_hyperlink;

    //DATABASE TOOLS
    private Connection connect;
    private ResultSet result;
    private PreparedStatement prepare;
    
    public void cashierLogin() throws SQLException{
        
        String cashierData = "SELECT * FROM cashier WHERE cashier_id = ? and password = ?";
        
        connect = database.connectDb();
        
        try{
            Alert alert;
            
            prepare = connect.prepareStatement(cashierData);
            
            if(cashier_id.getText().isEmpty() 
                    || cashier_password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else {
                
                prepare.setString(1, cashier_id.getText());
                prepare.setString(2, cashier_password.getText());
                
                result = prepare.executeQuery();
                
                if(result.next()){
                    
                    getData.cashierId = cashier_id.getText();
                   
                    
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!!");
                    alert.showAndWait();
                    
                    cashier_loginbtn.getScene().getWindow().hide();
                                        
                    Parent root = FXMLLoader.load(getClass().getResource("cashierDashboard.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                    
                }else{
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong ID/Password");
                    alert.showAndWait();
                }
                }

            
        }catch(Exception e)
                {e.printStackTrace();}
    }

    public void adminLogin() {
        String adminData = "SELECT * FROM admin WHERE username = ? and password = ?";

        connect = database.connectDb();

        Alert alert;

        try {
            if (admin_username.getText().isEmpty() || admin_password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                prepare = connect.prepareStatement(adminData);
                prepare.setString(1, admin_username.getText());
                prepare.setString(2, admin_password.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    
                    getData.username = admin_username.getText();
                     
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!!");
                    alert.showAndWait();

                    admin_loginbtn.getScene().getWindow().hide();

                    Parent root = FXMLLoader.load(getClass().getResource("adminDashboard.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setScene(scene);
                    stage.show();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == admin_hyperlink) {
            admin_form.setVisible(false);
            cashier_form.setVisible(true);
        } else if (event.getSource() == cashier_hyperlink) {
            admin_form.setVisible(true);
            cashier_form.setVisible(false);
        }
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
