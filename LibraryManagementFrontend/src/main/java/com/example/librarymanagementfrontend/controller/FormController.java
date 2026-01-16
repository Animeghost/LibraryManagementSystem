package com.example.librarymanagementfrontend.controller;

import com.example.librarymanagementfrontend.model.Book;
import com.example.librarymanagementfrontend.service.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    @FXML
    private Button addBtn;

    @FXML
    private TableColumn<Book, String> authorColumn;

    @FXML
    private TextField authorField;

    @FXML
    private TableView<Book> bookTableView;

    @FXML
    private Button clearBtn;

    @FXML
    private DatePicker datePublishedField;

    @FXML
    private TableColumn<Book,String > datePublishedColumn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<Book, String> isbnColumn;

    @FXML
    private TextField isbnField;

    @FXML
    private Button searchBtn;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TextField titleField;

    @FXML
    private Button updateBtn;

    @FXML
    private HBox createButtons;

    @FXML
    private HBox editModeButtons;

    @FXML
    private HBox removeButtons;


    private BookService bookService;

    ObservableList<Book> listOfBooks = FXCollections.observableArrayList();
    //return empty arraylist when you start app

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bookService = new BookService("http://localhost:8080/api");
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("isbn"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        datePublishedColumn.setCellValueFactory(new PropertyValueFactory<Book,String>("publishedDate"));

        bookTableView.setItems(listOfBooks);

        try {
            loadAllBooks();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addBook(MouseEvent event) throws IOException {
        Book newBook = bookService.createBook(mapFieldsToDto(new Book()));
        loadAllBooks();
        clearForm(event);
        System.out.println(newBook);
    }

    Book mapFieldsToDto(Book book) throws IOException {
        book.setIsbn(isbnField.getText());
        book.setAuthor(authorField.getText());
        book.setTitle(titleField.getText());
        book.setPublishedDate(datePublishedField.getValue() != null
                ? datePublishedField.getValue().toString()
                : null);
        return book;
    }

    void loadAllBooks() throws IOException {
        List<Book> allBooks = bookService.getAllBooks();
        listOfBooks.setAll(FXCollections.observableArrayList(allBooks));
        System.out.println(allBooks);
    }

    @FXML
    void bookSearch(MouseEvent event) {

    }

    @FXML
    void clearForm(MouseEvent event) {
        isbnField.clear();
        titleField.clear();
        authorField.clear();
        datePublishedField.setValue(null);
    }

    @FXML
    void deleteBook(MouseEvent event) throws IOException {
        Long bookId = bookTableView.getSelectionModel().getSelectedItem().getId();
        bookService.deleteBook(bookId);
        loadAllBooks();
    }

    @FXML
    void updateBook(MouseEvent event) throws IOException {
        createButtons.setVisible(false);
        removeButtons.setVisible(false);
        editModeButtons.setVisible(true);

        Book selectedBook = bookService.getBookById(bookTableView.getSelectionModel().getSelectedItem().getId());
        isbnField.setText(selectedBook.getIsbn());
        titleField.setText(selectedBook.getTitle());
        authorField.setText(selectedBook.getAuthor());
//        datePublishedField.setValue((LocalDate) selectedBook.getPublishedDate());
        bookTableView.setDisable(true);
    }

    @FXML
    void cancelEdit(MouseEvent event) {
        bookTableView.setDisable(false);
        createButtons.setVisible(true);
        removeButtons.setVisible(true);
        editModeButtons.setVisible(false);
        clearForm(event);
    }

    @FXML
    void saveEditedBook(MouseEvent event) throws IOException {
        Book editedBook= mapFieldsToDto(new Book());
        bookService.updateBook(bookTableView.getSelectionModel().getSelectedItem().getId(),editedBook);
        loadAllBooks();
        cancelEdit(event);
    }
}
