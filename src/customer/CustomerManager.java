package customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerManager {
    public void back(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../ManagerAplication/Manager.fxml"));
        primaryStage.setTitle("Book Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
