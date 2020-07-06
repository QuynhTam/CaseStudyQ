package bookmanage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

import java.awt.print.Book;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class bookManagerController implements Initializable {
    @FXML
    TableView<Books> tableView;
    //    @FXML
//    private ObservableList<Books> danhsach;
    @FXML
    TableColumn<Books, String> idColumn;
    @FXML
    TableColumn<Books, String> bookNameColumn;
    @FXML
    TableColumn<Books, String> authorColumn;
    @FXML
    TableColumn<Books, String> categoryColumn;
    @FXML
    TableColumn<Books, String> manufacColumn;
    @FXML
    TableColumn<Books, Integer> yearColumn;

    @FXML
    TextField txtCodeBook;
    @FXML
    TextField txtAuthor;
    @FXML
    TextField txtManufac;
    @FXML
    TextField txtName;
    @FXML
    TextField txtCategory;
    @FXML
    TextField txtYear;
    @FXML
    TextField txtSearch;
    List<Books> listBooks = new ArrayList<>();
    @FXML
    Button btnSave;
    @FXML
    Button btnEdit;
    @FXML
    ComboBox<String> comboboxSearch;
    List<String> categoryList = new ArrayList<>();
    String[] comboxList = {"Mã Sách", "Tên Sách", "Tác Giả", "Thể Loại", "Nhà Xuất Bản ", "Năm Xuất Bản"};
    Search searchName = new Search();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        listBooks = FXCollections.observableArrayList(new Books());
        idColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("codeBook"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("nameBook"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("category"));
        manufacColumn.setCellValueFactory(new PropertyValueFactory<Books, String>("publisher"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Books, Integer>("year"));
        btnEdit.setDisable(true);
        tableView.getItems().addAll(listBooks);
//        categoryList.add("Tiểu thuyết");
//        categoryList.add("Truyện tranh");

        comboboxSearch.getItems().addAll(comboxList);

    }

    public void loadBooklist() {
        tableView.getItems().clear();
        for (Books book : listBooks) {
            tableView.getItems().add(book);
        }
    }

    //them
    public void add(ActionEvent e) {
        Books books = new Books();
        books.setNameBook(txtName.getText());
        books.setCodeBook(txtCodeBook.getText());
        books.setAuthor(txtAuthor.getText());
        books.setCategory(txtCategory.getText());
        books.setPublisher(txtManufac.getText());
        books.setYear(Integer.parseInt(txtYear.getText()));
        for (Books book : listBooks) {
            if (book.getCodeBook().equals(txtCodeBook.getText())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("System information");
                alert.setContentText("The book id was exist . try again!");
                alert.showAndWait();
                return;
            }
        }
        listBooks.add(books);
        loadBooklist();
    }


//    public void Search(ActionEvent e) {
//        String search = txtSearch.getText();
//        int selectedIndex = comboboxSearch.getSelectionModel().getSelectedIndex();
//        System.out.println(selectedIndex);
//        if (search.equals("")) {
//            loadBooklist();
//        } else {
//            tableView.getItems().clear();
//            for (int i = 0; i < listBooks.size(); i++) {
////            tableView.getItems().clear();
//                if (Integer.parseInt(search) == (listBooks.get(i).getYear())) {
////                    tableView.getItems().clear();
//                    tableView.getItems().add(listBooks.get(i));
//
//                }
//            }
//        }
//
//
//    }

    public void searchCodeBook(String codeBook) {
        tableView.getItems().clear();
        for (Books books : listBooks) {
            if (codeBook.equals(books.getCodeBook())) {
                tableView.getItems().add(books);
            }
        }
    }

    public void searchCdeBook(String nameBook) {
        tableView.getItems().clear();
        String input = nameBook.toLowerCase();
        for (Books books : listBooks) {
            if (books.getNameBook().toLowerCase().contains(input)) {
                tableView.getItems().add(books);
            }
        }
    }

    public void searchNameBook(String nameBook) {
        tableView.getItems().clear();
        for (Books books : listBooks) {
            if (nameBook.equals(books.getNameBook())) {
                tableView.getItems().add(books);
            }
        }
    }

    public void searchAuthor(String author) {
        tableView.getItems().clear();
        for (Books books : listBooks) {
            if (author.equals(books.getAuthor())) {
                tableView.getItems().add(books);
            }
        }
    }

    public void searchCategory(String category) {
        tableView.getItems().clear();
        for (Books books : listBooks) {
            if (category.equals(books.getCategory())) {
                tableView.getItems().add(books);
            }
        }
    }

    public void searchmanufac(String manufac) {
        tableView.getItems().clear();
        for (Books books : listBooks) {
            if (manufac.equals(books.getNameBook())) {
                tableView.getItems().add(books);
            }
        }
    }

    public void searchyear(int year) {
        tableView.getItems().clear();
        for (Books books : listBooks) {
            if (year == books.getYear()) {
                tableView.getItems().add(books);
            }
        }
    }

    //lựa chọn chức năng tìm kiếm
    public void Search() {
        String search = txtSearch.getText();
        int selectedIndex = comboboxSearch.getSelectionModel().getSelectedIndex();
        if (search.equals("")) {
            loadBooklist();
        } else {
            switch (selectedIndex) {
                case 0:
                    searchCodeBook(txtSearch.getText());
                    break;
                case 1:
                    searchCdeBook(txtSearch.getText());
                    break;
                case 2:
                    searchAuthor(txtSearch.getText());
                    break;
                case 3:
                    searchCategory(txtSearch.getText());
                    break;
                case 4:
                    searchmanufac(txtSearch.getText());
                    break;
                case 5:
                    searchyear(Integer.parseInt(txtSearch.getText()));
                    break;
            }
        }
    }

    //xoa
    public void delete(ActionEvent e) {
        Books books = tableView.getSelectionModel().getSelectedItem();
        listBooks.remove(books);
//        loadBooklist();
    }

    public void TableSelectionChanged() {
        Books book = tableView.getSelectionModel().getSelectedItem();
        btnEdit.setDisable(book == null);
    }

    //sua
    public void edit(ActionEvent e) {
        Books book = tableView.getSelectionModel().getSelectedItem();
        if (book == null) {
            btnEdit.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("No book selected. Please try again.");
            alert.showAndWait();
        } else {
            txtCodeBook.setDisable(true);
            btnSave.setDisable(false);
            btnEdit.setDisable(true);
        }
    }

    //luu
    public void save(ActionEvent e) {
        Books book = tableView.getSelectionModel().getSelectedItem();
        if (book == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("System information");
            alert.setContentText("No book selected. Please try again.");
            alert.showAndWait();
        } else {
            book.setNameBook(txtName.getText());
            book.setAuthor(txtAuthor.getText());
            book.setCategory(txtCategory.getText());
            book.setPublisher(txtManufac.getText());
            book.setYear(Integer.parseInt(txtYear.getText()));
//            loadBooklist();
            btnSave.setDisable(true);
            txtCodeBook.setDisable(false);
            btnEdit.setDisable(false);
        }
    }

    public void back(ActionEvent event) throws IOException {
        Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../ManagerAplication/Manager.fxml"));
        primaryStage.setTitle("Book Manager");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
