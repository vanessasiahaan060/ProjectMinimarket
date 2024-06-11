/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package minimarket_proyek;

import java.io.IOException;
import java.lang.StackWalker.Option;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.Statement;
import java.util.Date;
import java.util.Observable;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

/**
 *
 * @author vanessa siahaan
 */
public class adminDashboardController implements Initializable {

    @FXML
    private Label dashboard_Activecashier;

    @FXML
    private AnchorPane active_dashboard;

    @FXML
    private AnchorPane addProduct_form;

    @FXML
    private Button addproduct_btn;

    @FXML
    private Button cashier_btn;

    @FXML
    private TextField cashier_cashierID;

    @FXML
    private TextField cashier_firstname;

    @FXML
    private AnchorPane cashier_form;

    @FXML
    private Button cashier_clear;

    @FXML
    private ComboBox<String> cashier_gender;

    @FXML
    private TextField cashier_lastname;

    @FXML
    private TextField cashier_password;

    @FXML
    private AreaChart<?, ?> chart_dashboard;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AnchorPane from_Data_product;

    @FXML
    private AnchorPane income_dqashboard;

    @FXML
    private ImageView logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button product_add;

    @FXML
    private Button product_add11;

    @FXML
    private TextField product_brandname;

    @FXML
    private Button product_deletbtn;

    @FXML
    private Button product_deletbtn11;

    @FXML
    private TextField product_name;

    @FXML
    private TextField product_price;

    @FXML
    private TextField product_productID;

    @FXML
    private ComboBox<?> product_status;

    @FXML
    private Button product_update;

    @FXML
    private Button product_update11;

    @FXML
    private AnchorPane today_dahsboard;

    @FXML
    private Label username;

    @FXML
    private TableView<productData> addProduct_tableview;

    @FXML
    private TableColumn<productData, String> product_col_productID;

    @FXML
    private TableColumn<productData, String> product_col_brandName;

    @FXML
    private TableColumn<productData, String> product_col_price;

    @FXML
    private TableColumn<productData, String> product_col_producName;

    @FXML
    private TableColumn<productData, String> product_col_status;

    @FXML
    private TableView<cashierData> cashier_tableview;

    @FXML
    private TableColumn<cashierData, String> cashier_col_cashierID;

    @FXML
    private TableColumn<cashierData, String> cashier_col_date;

    @FXML
    private TableColumn<cashierData, String> cashier_col_firstname;

    @FXML
    private TableColumn<cashierData, String> cashier_col_gender;

    @FXML
    private TableColumn<cashierData, String> cashier_col_lastname;

    @FXML
    private TableColumn<cashierData, String> cashier_col_password;

    @FXML
    private Button product_clear;

    //DATABASE TOOLS
    private Connection connect;
    private ResultSet result;
    private PreparedStatement prepare;
    private Statement statement;

    public void addProductAdd() throws SQLException {
        String insertProduct = "INSERT INTO product"
                + "(product_id, brand, product_name, status, price)"
                + "VALUES(?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;

            if (product_productID.getText().isEmpty()
                    || product_brandname.getText().isEmpty()
                    || product_name.getText().isEmpty()
                    || product_status.getSelectionModel().getSelectedItem() == null
                    || product_price.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                String check = "SELECT product_id FROM product WHERE product_id = '" + product_productID.getText() + "'";

                statement = (Statement) connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);
                    alert.setHeaderText("Product ID: " + product_productID.getText() + "Was already exits");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertProduct);
                    prepare.setString(1, product_productID.getText());
                    prepare.setString(2, product_brandname.getText());
                    prepare.setString(3, product_name.getText());
                    prepare.setString(4, (String) product_status.getSelectionModel().getSelectedItem());
                    prepare.setString(5, product_price.getText());

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Massage");
                    alert.setHeaderText(null);
                    alert.setHeaderText("Successfully Added!");
                    alert.showAndWait();

                    //Untuk mengdit tabelview 
                    addProductShowData();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void addProductUpade() {
        try {
            Alert alert; // check empty

            if (product_productID.getText().isEmpty()
                    || product_brandname.getText().isEmpty()
                    || product_name.getText().isEmpty()
                    || product_status.getSelectionModel().getSelectedItem() == null
                    || product_price.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                String updateProduct = "UPDATE product SET brand = '"
                        + product_brandname.getText() + "',product_name = '"
                        + product_name.getText() + "', status = '"
                        + product_status.getSelectionModel().getSelectedItem() + "', price = '"
                        + product_price.getText() + "' WHERE product_id = '"
                        + product_productID.getText() + "'";

                connect = database.connectDb();
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Massage");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure want to UPDATE Product ID: " + product_productID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateProduct);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Update!");
                    alert.showAndWait();

                    // Untuk mengedit tabelview
                    addProductShowData();
                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addProductDelete() {
        String deleteProduct = "DELETE FROM product WHERE product_id = '"
                + product_productID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (product_productID.getText().isEmpty()
                    || product_brandname.getText().isEmpty()
                    || product_name.getText().isEmpty()
                    || product_status.getSelectionModel().getSelectedItem() == null
                    || product_price.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Massage");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Product ID: " + product_productID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get() == ButtonType.OK) {
                    prepare = connect.prepareStatement(deleteProduct);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // Untuk mengedit tabelview
                    addProductShowData();
                    productReset();

                } else {
                    return;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: " + e.getMessage());

        }
    }

    public void productReset() {
        product_productID.setText("");
        product_brandname.setText("");
        product_name.setText("");
        product_status.getSelectionModel().clearSelection();
        product_price.setText("");

    }

    private String[] statusList = {"Available", "Not Available"};

    public void addProductStatusList() {
        List<String> listS = new ArrayList<>();

        for (String data : statusList) {
            listS.add(data);
        }

        ObservableList statusData = FXCollections.observableArrayList(listS);
        product_status.setItems(statusData);
    }

    public ObservableList<productData> addProductListData() {
        ObservableList<productData> prodList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM product";

        connect = database.connectDb();

        try {
            productData prod;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                prod = new productData(
                        result.getString("product_id"),
                        result.getString("brand"),
                        result.getString("product_name"),
                        result.getString("status"),
                        result.getDouble("price")
                );
                prodList.add(prod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prodList;
    }

    private ObservableList<productData> addProductList;

    public void addProductShowData() { //melihat tabel view
        addProductList = addProductListData();

        product_col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        product_col_brandName.setCellValueFactory(new PropertyValueFactory<>("brand"));
        product_col_producName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        product_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        product_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        addProduct_tableview.setItems(addProductList);
    }

    public void addProductSelect() {
        productData prod = addProduct_tableview.getSelectionModel().getSelectedItem();
        int num = addProduct_tableview.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        product_productID.setText(prod.getProductId());
        product_brandname.setText(prod.getBrand());
        product_name.setText(prod.getProductName());
        product_price.setText(String.valueOf(prod.getPrice()));

    }

    public void cashierSave() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String insertCashier = "INSERT INTO cashier (cashier_id, password, firstName, lastName, gender, date)"
                + "VALUES(?,?,?,?,?,?)";

        connect = database.connectDb();

        try {
            Alert alert;

            if (cashier_cashierID.getText().isEmpty()
                    || cashier_password.getText().isEmpty()
                    || cashier_firstname.getText().isEmpty()
                    || cashier_lastname.getText().isEmpty()
                    || cashier_gender.getSelectionModel().getSelectedItem() == null) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                String checkExist = "SELECT cashier_id FROM cashier WHERE cashier_id = '"
                        + cashier_cashierID.getText() + "'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkExist);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Massage");
                    alert.setHeaderText(null);

                    alert.setContentText("Cashier ID: " + cashier_cashierID.getText() + " was already exists!");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(insertCashier);
                    prepare.setString(1, cashier_cashierID.getText());
                    prepare.setString(2, cashier_password.getText());
                    prepare.setString(3, cashier_firstname.getText());
                    prepare.setString(4, cashier_lastname.getText());
                    prepare.setString(5, (String) cashier_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(6, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Saved!");
                    alert.showAndWait();

                    cashiershowListData();
                    cashierReset();

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void carhierDelete() {
        String deleteCashier = "DELETE FROM cashier WHERE cashier_id = '" + cashier_cashierID.getText() + "'";

        connect = database.connectDb();

        try {
            Alert alert;

            if (cashier_cashierID.getText().isEmpty()
                    || cashier_password.getText().isEmpty()
                    || cashier_firstname.getText().isEmpty()
                    || cashier_lastname.getText().isEmpty()
                    || cashier_gender.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Massage");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Cashier ID: " + cashier_cashierID.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();
                if (option.isPresent() && option.get() == ButtonType.OK) {
                    statement = connect.prepareStatement(deleteCashier);
                    statement.executeUpdate(deleteCashier);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();

                    // Untuk mengedit tabelview
                    cashiershowListData();

                } else {
                    return;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception: " + e.getMessage());

        }
    }

    public void cashierReset() {
        cashier_cashierID.setText("");
        cashier_password.setText("");
        cashier_firstname.setText("");
        cashier_lastname.setText("");
        cashier_gender.getSelectionModel().clearSelection();

    }

    private String[] genderList = {"Male", "Famale", "Other"};

    public void cashierGender() {
        List<String> genderL = new ArrayList<>();

        for (String data : genderList) {
            genderL.add(data);
        }

        ObservableList genderData = FXCollections.observableArrayList(genderL);
        cashier_gender.setItems(genderData);
    }

    public void cashierUpdate() {
        String updateCashier = "UPDATE cashier SET password = '"
                + cashier_password.getText() + "', firstname= '"
                + cashier_firstname.getText() + "',lastname= '"
                + cashier_lastname.getText() + "',gender='"
                + cashier_gender.getSelectionModel().getSelectedItem() + "' WHERE cashier_id= '"
                + cashier_cashierID.getText() + "'";

        connect = database.connectDb();

        try {

            Alert alert;

            if (cashier_cashierID.getText().isEmpty()
                    || cashier_password.getText().isEmpty()
                    || cashier_firstname.getText().isEmpty()
                    || cashier_lastname.getText().isEmpty()
                    || cashier_gender.getSelectionModel().getSelectedItem() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Massage");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Massage");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE cashier ID:" + cashier_cashierID.getText() + "2");

                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    statement = connect.createStatement();
                    statement.executeUpdate(updateCashier);

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Confirmation Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Update successful");
                    alert.showAndWait();

                } else {
                    return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<cashierData> cashierListData() throws SQLException {

        ObservableList<cashierData> emData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM cashier";

        connect = database.connectDb();

        try {

            cashierData cashierD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {

                cashierD = new cashierData(result.getString("cashier_id"),
                        result.getString("password"),
                        result.getString("firstName"),
                        result.getString("lastName"),
                        result.getString("gender"),
                        result.getDate("date"));

                emData.add(cashierD);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return emData;

    }

    private ObservableList<cashierData> cashierList;

    public void cashiershowListData() {
        try {
            cashierList = cashierListData();

            cashier_col_cashierID.setCellValueFactory(new PropertyValueFactory<>("cashierId"));
            cashier_col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
            cashier_col_firstname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            cashier_col_lastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            cashier_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            cashier_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

            cashier_tableview.setItems(cashierList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cashierSelect() {
        cashierData cashierD = cashier_tableview.getSelectionModel().getSelectedItem();
        int num = cashier_tableview.getSelectionModel().getFocusedIndex();

        if (cashierD == null) {
            // Handle jika cashierD null (tidak ada item terpilih)
            System.out.println("Tidak ada item terpilih.");
            return;
        }

        cashier_cashierID.setText(cashierD.getCashierId());
        cashier_password.setText(cashierD.getPassword());
        cashier_firstname.setText(cashierD.getFirstName());
        cashier_lastname.setText(cashierD.getLastName());
    }

    public void logout() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");

        java.util.Optional<ButtonType> option = alert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK) {
            logout.getScene().getWindow().hide();

            try {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void displayUsername() {
        username.setText(getData.username);
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            addProduct_form.setVisible(false);
            cashier_form.setVisible(false);
        } else if (event.getSource() == addproduct_btn) {
            dashboard_form.setVisible(false);
            addProduct_form.setVisible(true);
            cashier_form.setVisible(false);

            //untuk melihat data jika mengklik add product button
            addProductShowData();
            addProductStatusList();

        } else if (event.getSource() == cashier_btn) {
            dashboard_form.setVisible(false);
            addProduct_form.setVisible(false);
            cashier_form.setVisible(true);

            cashiershowListData();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayUsername();

        addProductShowData();
        addProductStatusList();

        cashiershowListData();
        cashierGender();

        addProduct_tableview.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                addProductSelect();
            }
        });
    }

}
