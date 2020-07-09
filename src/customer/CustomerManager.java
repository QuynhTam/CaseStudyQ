package customer;

import QLmuonSach.QLMuonSach;
import bookmanage.Books;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import filemanager.FileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerManager implements Initializable {
    @FXML
    TableView<Customer> tableView;

    @FXML
    TableColumn<Customer, String> codeCustomerColumn;
    @FXML
    TableColumn<Customer, String> nameCustomerColumn;
    @FXML
    TableColumn<Customer, LocalDate> birthColumn;
    @FXML
    TableColumn<Customer, String> addressColumn;
    @FXML
    TableColumn<Customer, String> phoneNumberColumn;
    @FXML
    TextField txtMakh;
    @FXML
    Button btnEdit;
    @FXML
    Button btnSave;
    @FXML
    TextField txtCodeCustomer;
    @FXML
    TextField txtNameCustomer;
    @FXML
    DatePicker birthday;
    @FXML
    TextField txtAddress;
    @FXML
    TextField txtPhoneNumber;
    @FXML
    TextField txt_search;
    List<Customer> customerList = new ArrayList<>();
    @FXML
    ComboBox<String> comboBox;
    String[] comboboxList = {"Mã Khách Hàng", "Tên Khách Hàng", "Số Điện Thoại"};

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            readFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        codeCustomerColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("idCustomer"));
        nameCustomerColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("nameCustomer"));
        birthColumn.setCellValueFactory(new PropertyValueFactory<Customer, LocalDate>("birthday"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("addressCustomer"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
        tableView.getItems().addAll(customerList);
        comboBox.getItems().addAll(comboboxList);
//        btnEdit.setDisable(true);
    }

    public void loadCustomer() {
        tableView.getItems().clear();
        for (Customer customer : customerList) {
            tableView.getItems().add(customer);
        }
    }

    //Add customer
    public void addCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setIdCustomer(txtCodeCustomer.getText());
        customer.setNameCustomer(txtNameCustomer.getText());
        customer.setBirthday(birthday.getValue());
        customer.setAddressCustomer(txtAddress.getText());
        customer.setPhoneNumber(txtPhoneNumber.getText());
        for (Customer cus : customerList) {
            if (cus.getIdCustomer().equals(txtCodeCustomer.getText()) || txtCodeCustomer.getText().equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("System Information");
                alert.setContentText("Code customer was exist or is blank ! Try again !!!");
                alert.show();
                return;
            }
        }
        customerList.add(customer);
        loadCustomer();
        writeFile();
    }

    //delete customer
    public void delete() {
        Customer customer = tableView.getSelectionModel().getSelectedItem();
        customerList.remove(customer);
        loadCustomer();
    }

    //Search customer
    public void codeCustomer(String codeCustomer) {
        String code = codeCustomer.toLowerCase();
        tableView.getItems().clear();
        for (Customer customer : customerList) {
            if (customer.getIdCustomer().toLowerCase().equals(code)) {
                tableView.getItems().add(customer);
            }
        }
    }

    public void nameCustomer(String nameCustomer) {
        String name = nameCustomer.toLowerCase();
        tableView.getItems().clear();
        for (Customer customer : customerList) {
            if (customer.getNameCustomer().toLowerCase().contains(name)) {
                tableView.getItems().add(customer);
            }
        }
    }

    public void numberPhoneCustomer(String nbpCustomer) {
        tableView.getItems().clear();
        for (Customer customer : customerList) {
            if (customer.getPhoneNumber().contains(nbpCustomer)) {
                tableView.getItems().add(customer);
            }
        }
    }


    public void search() {
        String search = txt_search.getText();
        int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();
        if (search.equals("")) {
            loadCustomer();
        } else {
            switch (selectedIndex) {
                case 0:
                    codeCustomer(search);
                    break;
                case 1:
                    nameCustomer(search);
                    break;
                case 2:
                    numberPhoneCustomer(search);
                    break;
            }
        }
    }

    //Edit
    public void edit() {
        Customer customer = tableView.getSelectionModel().getSelectedItem();
        if (customer == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("System information");
            alert.setContentText("No book selected. Please try again.");
        } else {
            txtCodeCustomer.setText(customer.getIdCustomer());
            txtNameCustomer.setText(customer.getNameCustomer());
            txtPhoneNumber.setText(customer.getPhoneNumber());
            txtAddress.setText(customer.getAddressCustomer());
            birthday.setValue(customer.getBirthday());
            txtCodeCustomer.setDisable(true);
            btnSave.setDisable(false);
        }
    }

    public void save() {
        Customer customer = tableView.getSelectionModel().getSelectedItem();
        customer.setNameCustomer(txtNameCustomer.getText());
        customer.setBirthday(birthday.getValue());
        customer.setAddressCustomer(txtAddress.getText());
        customer.setPhoneNumber(txtPhoneNumber.getText());
        btnSave.setDisable(true);
        txtCodeCustomer.setDisable(false);
        btnEdit.setDisable(false);
        loadCustomer();
    }

    //ghi file
    public void writeFile() throws Exception {
        FileManager<Customer> fileManager = new FileManager<>();
        fileManager.writeFile("src/customer/customer.txt", customerList);
    }

    //đọc file
    public void readFile() {
        FileManager<Customer> fileManager = new FileManager<>();
        customerList.clear();
        customerList.addAll(fileManager.readFile("src/customer/customer.txt"));
    }

//QL mượn sách
    public void qlms(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../QLmuonSach/MuonSachs.fxml"));
        Parent root = loader.load();
        QLMuonSach qlMuonSach = loader.getController();
        qlMuonSach.setCustomer(tableView.getSelectionModel().getSelectedItem());
        primaryStage.setTitle("Book Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void back(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../ManagerAplication/Manager.fxml"));
        primaryStage.setTitle("Book Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


}
