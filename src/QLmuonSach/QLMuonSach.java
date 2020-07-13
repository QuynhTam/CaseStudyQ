package QLmuonSach;

import bookmanage.Books;
import customer.Customer;
import filemanager.FileManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class QLMuonSach extends Books implements Initializable {
    private
    @FXML
    TableView<Books> tableViewBook;
    @FXML
    TableView<MuonSach> tableViewInfo;
    @FXML
    TableColumn<Books, String> codeBookColumn;
    @FXML
    TableColumn<Books, String> nameBookColunm;
    @FXML
    TableColumn<MuonSach, String> makhColunm;
    @FXML
    TableColumn<MuonSach, String> maSachColunm;
    @FXML
    TableColumn<MuonSach, LocalDate> dayMuonColunm;
    @FXML
    TableColumn<MuonSach, LocalDate> deadLineColunm;
    @FXML
    TableColumn<MuonSach, Integer> SoLuongColunm;
    @FXML
    TableColumn<MuonSach, Integer> moneyColunm;
    @FXML
    TextField txtMakh;
    @FXML
    DatePicker dpkNgayMuon;
    @FXML
    DatePicker dpkhantra;
    @FXML
    TextField txtMaSack;
    @FXML
    TextField txtMoney;
    @FXML
    TextField txtSoLuong;
    List<Books> listBooks = new ArrayList<>();
    List<MuonSach> muonSachList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readFile();
        readFileMuonSach();
        codeBookColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("codeBook"));
        nameBookColunm.setCellValueFactory(new PropertyValueFactory<Books, String>("nameBook"));
        makhColunm.setCellValueFactory(new PropertyValueFactory<MuonSach, String>("customerCode"));
        maSachColunm.setCellValueFactory(new PropertyValueFactory<MuonSach, String>("bookCode"));
        dayMuonColunm.setCellValueFactory(new PropertyValueFactory<MuonSach, LocalDate>("rentalDate"));
        deadLineColunm.setCellValueFactory(new PropertyValueFactory<MuonSach, LocalDate>("returnDate"));
        SoLuongColunm.setCellValueFactory(new PropertyValueFactory<MuonSach, Integer>("quantity"));
        moneyColunm.setCellValueFactory(new PropertyValueFactory<MuonSach, Integer>("money"));

        tableViewBook.getItems().addAll(listBooks);
        tableViewInfo.getItems().addAll(muonSachList);
        dpkNgayMuon.setValue(LocalDate.now());
        dpkhantra.setValue(LocalDate.now().plusDays(15));
        dpkhantra.setDisable(true);
        dpkNgayMuon.setDisable(true);
    }

    public void readFile() {
        FileManager<Books> fileManager = new FileManager<>();
        listBooks.clear();
        listBooks.addAll(fileManager.readFile("src/bookmanage/books.txt"));
    }

    public void readFileMuonSach() {
        FileManager<MuonSach> fileManager = new FileManager<>();
        muonSachList.clear();
        muonSachList.addAll(fileManager.readFile("src/QLmuonSach/MuonSach.txt"));
    }

    public void writeMuonSach() throws Exception {
        FileManager<MuonSach> fileManager = new FileManager<>();
        fileManager.writeFile("src/QLmuonSach/MuonSach.txt", muonSachList);
    }

    public void eventTableView() {
        Books books = tableViewBook.getSelectionModel().getSelectedItem();
        txtMaSack.setText(books.getCodeBook());
    }

    public void loadListBook() {
        tableViewBook.getItems().clear();
        for (Books books : listBooks) {
            tableViewBook.getItems().add(books);
        }
    }

    //set id customer
    public void setCustomer(Customer customer) {
        txtMakh.setText(customer.getIdCustomer());
        txtMakh.setDisable(true);
    }

    public void load() {
        tableViewInfo.getItems().clear();
        for (MuonSach muonSach : muonSachList) {
            tableViewInfo.getItems().add(muonSach);
        }
    }

    //add QL mượn sách
    public void addInfomation() throws Exception {
        MuonSach muonSach = new MuonSach();
        muonSach.setBookCode(txtMaSack.getText());
        muonSach.setCustomerCode(txtMakh.getText());
        muonSach.setMoney(Integer.parseInt(txtMoney.getText()));
        muonSach.setQuantity(Integer.parseInt(txtSoLuong.getText()));
        muonSach.setRentalDate(dpkNgayMuon.getValue());
        muonSach.setReturnDate(dpkhantra.getValue());
        muonSachList.add(muonSach);
        load();
        writeMuonSach();
    }

    public void back(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../ManagerAplication/Manager.fxml"));
        primaryStage.setTitle("Book Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


}
