package com.example.majddbclient;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBController {

    @FXML
    private TableView<ObservableList<String>> dataTable;

    @FXML
    public Button goButton;

    @FXML
    private TextArea stmtText;


    public ResultSet rs = null;
    public String statement;

    public void stmtTyped(){
        statement=stmtText.getText();
//        System.out.println(statement);
    }

    public void populate(){

        ClearTable();

        //Creating the connection
        //********* MAKE SURE TO CHANGE THE USER & PASSWORD TO WHAT CORRESPONDS WITH YOUR SYSTEM ****************
        String conn_url = "jdbc:mysql://localhost:3306/employees?user=root&password=Mwe4we455&serverTimezone=UTC";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(conn_url);
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(statement);

            //Getting Column Names
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount= meta.getColumnCount();
//            System.out.println("Column Count: "+ columnCount);


            //Adding the column names to an Arraylist for processing (To Add later to the actual TableView)
            List<String> columnNames = new ArrayList<>();
            for(int j=1;j<=columnCount;j++){
                columnNames.add(meta.getColumnName(j));
            }
//            System.out.println(columnNames);

            //Adding the Table columns to the table View
            for (int i = 0; i < columnCount; i++) {
                final int finalIdx = i;
                TableColumn<ObservableList<String>, String> column = new TableColumn<>(
                        columnNames.get(i)
                );
                column.setCellValueFactory(param ->
                        new ReadOnlyObjectWrapper<>(param.getValue().get(finalIdx))
                );
                dataTable.getColumns().add(column);
            }

            //Adding Rows to the table View
            while (rs != null && rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i));
                }
                dataTable.getItems().add(row);
            }

        //Handling Exceptions
        } catch (SQLSyntaxErrorException syntaxErrorException) {
            AlertBox.display("Error","SQL Syntax Error");
            syntaxErrorException.printStackTrace();
        }catch (SQLException e){
            AlertBox.display("Error","Something went wrong");
            e.printStackTrace();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //Clear the table before clicking the go button everytime
    public void ClearTable(){
        dataTable.getItems().clear();
        dataTable.getColumns().clear();
    }



}
