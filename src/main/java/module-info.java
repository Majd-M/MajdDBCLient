module com.example.majddbclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.majddbclient to javafx.fxml;
    exports com.example.majddbclient;
}