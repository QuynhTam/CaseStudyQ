package ManagerAplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Manager implements Initializable {
    @FXML
    public void books(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../bookmanage/bookmanager.fxml"));
        primaryStage.setTitle("Book Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void customer(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../Customer/Customer.fxml"));
        primaryStage.setTitle("Customer Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void qlMuon(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../QLmuonSach/MuonSachs.fxml"));
        primaryStage.setTitle("Book Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public void qlTra(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../QLtraSach/TraSach.fxml"));
        primaryStage.setTitle("Book Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
