package com.example.majddbclient;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

//This Class is for the alert message box to handle exeptions

public class AlertBox {

    public static void display(String title, String message){
        //initialazing the window and setting its parameters
        Stage window= new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(130);

        //Creating the controls
        Label errorMsg= new Label();
        errorMsg.setText(message);

        Button closeButton= new Button("CLose");
        closeButton.setOnAction(e-> window.close());

        VBox layout = new VBox();
        layout.getChildren().addAll(errorMsg, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout);
        window.setScene(scene);
        //show and wait to stop the user from inputing anything while the message box is active
        window.showAndWait();

    }
}
