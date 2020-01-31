package com.codecool.quest.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoadGameBox {
    static boolean answear;

    public static boolean display(String title, String message) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        //Create two buttons
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
        Button buttonFile = new Button("File");
        Button buttonEdit = new Button("Edit");
        Button buttonView = new Button("View");

        yesButton.setOnAction(e -> {
            answear = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answear = false;
            window.close();
        });

        HBox topMenu = new HBox();
        topMenu.getChildren().addAll(noButton, yesButton, buttonFile, buttonEdit, buttonView);

        VBox leftMenu = new VBox();
        Button buttonD = new Button("D");
        Button buttonE = new Button("E");
        Button buttonG = new Button("G");
        leftMenu.getChildren().addAll(buttonD, buttonE, buttonG);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        borderPane.setLeft(leftMenu);

        Scene scene = new Scene(borderPane, 300, 250);
        window.setScene(scene);
        window.showAndWait();

        return answear;

    }
}

