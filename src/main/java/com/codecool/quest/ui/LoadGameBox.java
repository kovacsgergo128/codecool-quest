package com.codecool.quest.ui;

import com.codecool.quest.Game;
import com.codecool.quest.GameSaver;
import com.codecool.quest.logic.GameMap;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoadGameBox {
    private static ListView<String> savedGames = new ListView<>();
    private static Stage window = new Stage();

    public static void display(String[] fileNames){

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Load game");
        window.setMinWidth(300);

        Label label = new Label("Choose a saved game to load");
        label.setStyle("-fx-text-fill: red; -fx-font-family: 'Helvetica Neue'; -fx-font-size: 14px; ");
        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> onLoadButtonClick());
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());


        VBox layout = new VBox(10);
        layout.getChildren().add(label);
        for (String fileName : fileNames)
            savedGames.getItems().add(fileName);

        layout.getChildren().add(savedGames);

        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(loadButton, cancelButton);
        buttonContainer.setAlignment(Pos.CENTER);
        layout.getChildren().add(buttonContainer);
        layout.setAlignment(Pos.CENTER);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

    private static void onLoadButtonClick() {
        String fileName = savedGames.getSelectionModel().getSelectedItem();
        window.close();
        GameSaver.loadSavedFile(fileName);
    }
}

