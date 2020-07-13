package QLtraSach;

import QLmuonSach.MuonSach;
import filemanager.FileManager;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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

import static java.time.temporal.ChronoUnit.DAYS;

public class QLTraSach implements Initializable {
    @FXML
    TableView<MuonSach> tableViewQLTS;
    @FXML
    TableView<ThanhToanTien> paymentTable;
    @FXML
    TableColumn<ThanhToanTien, Integer> soNgayThueColunm;
    @FXML
    TableColumn<ThanhToanTien, Integer> donGiaColunm;
    @FXML
    TableColumn<ThanhToanTien, Integer> soNgayQuaHanColunm;
    @FXML
    TableColumn<ThanhToanTien, Integer> tienPhatColunm;
    @FXML
    TableColumn<ThanhToanTien, Integer> thanhTienColunm;
    @FXML
    TableColumn<ThanhToanTien, Integer> DatCocColunm;
    @FXML
    TableColumn<MuonSach, String> maKHColunm;
    @FXML
    TableColumn<MuonSach, String> maSachColunm;
    @FXML
    TableColumn<MuonSach, LocalDate> NgayMuonColunm;
    @FXML
    TableColumn<MuonSach, LocalDate> hanTraColunm;
    @FXML
    TableColumn<MuonSach, Integer> soLuongColunm;
    @FXML
    TableColumn<MuonSach, Integer> tienCocColunm;
    @FXML
    Label SumMoney;
    @FXML
    Label SumMoney2;
    @FXML
    Label SumMoney3;
    @FXML
    Label status;

    @FXML
    TextField txtMaKH;
    @FXML
    DatePicker dpkNgayMuon;
    @FXML
    DatePicker dpkHanTra;
    @FXML
    DatePicker dpkNgayTra;
    @FXML
    TextField txtTienCoc;
    @FXML
    TextField txtSoLuong;
    @FXML
    TextField txtSearch;

    List<MuonSach> muonSachList = new ArrayList<>();
    List<ThanhToanTien> thanhToanTienList = new ArrayList<>();
    //
    @FXML
    RadioButton rd1;
    @FXML
    RadioButton rd;

    //
    public void back(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../ManagerAplication/Manager.fxml"));
        primaryStage.setTitle("Book Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readFileTraSach();
        maKHColunm.setCellValueFactory(new PropertyValueFactory<MuonSach, String>("customerCode"));
        maSachColunm.setCellValueFactory(new PropertyValueFactory<MuonSach, String>("bookCode"));
        NgayMuonColunm.setCellValueFactory(new PropertyValueFactory<MuonSach, LocalDate>("rentalDate"));
        hanTraColunm.setCellValueFactory(new PropertyValueFactory<MuonSach, LocalDate>("returnDate"));
        soLuongColunm.setCellValueFactory(new PropertyValueFactory<MuonSach, Integer>("quantity"));
        tienCocColunm.setCellValueFactory(new PropertyValueFactory<MuonSach, Integer>("money"));
        //
        soNgayThueColunm.setCellValueFactory(new PropertyValueFactory<ThanhToanTien, Integer>("soNgayThue"));
        donGiaColunm.setCellValueFactory(new PropertyValueFactory<ThanhToanTien, Integer>("donGia"));
        soNgayQuaHanColunm.setCellValueFactory(new PropertyValueFactory<ThanhToanTien, Integer>("soNgayQuaHan"));
        tienPhatColunm.setCellValueFactory(new PropertyValueFactory<ThanhToanTien, Integer>("tienPhat"));
        thanhTienColunm.setCellValueFactory(new PropertyValueFactory<ThanhToanTien, Integer>("ThanhTien"));
        DatCocColunm.setCellValueFactory(new PropertyValueFactory<ThanhToanTien, Integer>("moneyCoc"));
        //
        txtSearch.textProperty().addListener((o, old, value) -> {
            search(txtSearch.getText());
        });
        paymentTable.getItems().addAll(thanhToanTienList);
        tableViewQLTS.getItems().addAll(muonSachList);
        dpkNgayTra.setValue(LocalDate.now());
        dpkNgayTra.setDisable(true);
        txtMaKH.setDisable(true);
        txtTienCoc.setDisable(true);
        txtSoLuong.setDisable(true);
        dpkNgayMuon.setDisable(true);
        dpkHanTra.setDisable(true);
    }

    public void search(String mkh) {
        tableViewQLTS.getItems().clear();
        for (MuonSach msl : muonSachList) {
            if (msl.getCustomerCode().toLowerCase().contains(mkh.toLowerCase())) {

                tableViewQLTS.getItems().add(msl);
            }
        }
    }

    public void load() {
        tableViewQLTS.getItems().clear();
        for (MuonSach muonSach : muonSachList) {
            tableViewQLTS.getItems().add(muonSach);
        }
    }

    public void click() {
        MuonSach muonSach = tableViewQLTS.getSelectionModel().getSelectedItem();
        txtMaKH.setText(muonSach.getBookCode());
        txtSoLuong.setText(String.valueOf(muonSach.getQuantity()));
        txtTienCoc.setText(String.valueOf(muonSach.getMoney()));
        dpkNgayMuon.setValue(muonSach.getRentalDate());
        dpkHanTra.setValue(muonSach.getReturnDate());
    }

    public void payment() throws Exception {
        int totalMoney = 0;
        int downPayment = 0;
        int moneyOutOfDates = 0;
        int moneyPay = 0;
        int moneyToPay;
        int unitPrice = 2000;
        LocalDate rentalDate = dpkNgayMuon.getValue().minusDays(1);
        LocalDate returnDate = dpkNgayTra.getValue();
        LocalDate deadLine = dpkHanTra.getValue();
        long numberDayRent = DAYS.between(rentalDate.atStartOfDay(), returnDate.atStartOfDay());
        long outOfDate = DAYS.between(deadLine.atStartOfDay(), returnDate.atStartOfDay());
        int intoMoney = (int) numberDayRent * unitPrice;
        if (outOfDate <= 0) {
            outOfDate = 0;
        }
        //
        ThanhToanTien thanhToanTien = new ThanhToanTien();
        thanhToanTien.setSoNgayThue(Integer.parseInt(String.valueOf(numberDayRent)));
        thanhToanTien.setDonGia(unitPrice);
        thanhToanTien.setSoNgayQuaHan((int) outOfDate);
        thanhToanTien.setTienPhat((int) outOfDate * 5000);
        thanhToanTien.setThanhTien(intoMoney);
        thanhToanTien.setMoneyCoc(Integer.parseInt(txtTienCoc.getText()));
        thanhToanTienList.add(thanhToanTien);
        paymentTable.getItems().clear();
        paymentTable.getItems().addAll(thanhToanTienList);
        for (ThanhToanTien payMoney : thanhToanTienList) {
            totalMoney = totalMoney + payMoney.getThanhTien();
            downPayment = downPayment + payMoney.getMoneyCoc();
            moneyOutOfDates = moneyOutOfDates + payMoney.getTienPhat();
        }
        moneyPay = totalMoney + moneyOutOfDates;
        moneyToPay = totalMoney - downPayment + moneyOutOfDates;
        SumMoney2.setText(String.valueOf(downPayment));
        SumMoney.setText(String.valueOf(moneyPay));
        if (moneyToPay < 0) {
            SumMoney3.setText(String.valueOf(Math.abs(moneyToPay)));
            status.setText("Còn thừa :");
        } else if (moneyToPay == 0) {
//            SumMoney3.setText(String.valueOf(Math.abs(moneyToPay)));
            status.setText("Đã trả đủ");
        } else {
            SumMoney3.setText(String.valueOf(Math.abs(moneyToPay)));
            status.setText("Còn thiếu :");
        }
        txtMaKH.setText("");
        txtTienCoc.setText("");
        txtSoLuong.setText("");
        MuonSach muonSach = tableViewQLTS.getSelectionModel().getSelectedItem();
        muonSachList.remove(muonSach);
        writeTraSach();
//        load();
    }

    public void readFileTraSach() {
        FileManager<MuonSach> fileManager = new FileManager<>();
        muonSachList.clear();
        muonSachList.addAll(fileManager.readFile("src/QLmuonSach/MuonSach.txt"));
    }

    public void writeTraSach() throws Exception {
        FileManager<MuonSach> fileManager = new FileManager<>();
        fileManager.writeFile("src/QLmuonSach/MuonSach.txt", muonSachList);
    }
}
