package com.example.majddbclient;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class DBController {

    @FXML
    private TableView<ObservableList<String>> dataTable;

    @FXML
    private Button goButton;

    @FXML
    private TextArea stmtText;

}
