package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameMap;
import com.codecool.quest.logic.Items.Items;
import com.codecool.quest.logic.MapLoader;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Npc;
import com.codecool.quest.logic.actors.Skeleton;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    GameMap level1 = MapLoader.loadMap("/map.txt");
    GameMap level2 = MapLoader.loadMap("/map2.txt");
    GameMap map = level1;
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    ListView inventory = new ListView();
    Button pickButton = new Button("Pick up");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(200);
        ui.setPadding(new Insets(10));
        ui.setVgap(10);

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Inventory:"), 0, 2);
        ui.add(pickButton, 0, 6, 2, 1);

        pickButton.setDisable(true);

        ui.add(inventory,0,3, 2, 1);
        inventory.setOnKeyPressed(this::onKeyPressed);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        pickButton.setOnAction(this::onPickButtonClick);

        primaryStage.setTitle("Codecool Quest");
        primaryStage.show();
    }

    private void onPickButtonClick(ActionEvent actionEvent) {
        map.getPlayer().pickItem();
        refresh();
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                refresh();
                break;
            case ENTER:
                if (map.getPlayer().getCell().getItem() != null) {
                    map.getPlayer().pickItem();
                    refresh();
                }
                break;

        }
    }

    private void refresh() {

        aiMove();

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null && !cell.getActor().isAlive()){
                    cell.removeActor();
                }
                if (cell.getActor() != null && cell.getActor().isAlive()) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else if (cell.getDoor() != null) {
                    Tiles.drawTile(context, cell.getDoor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        inventory.getItems().clear();
        for (Items item : map.getPlayer().getInventory().getItems()) {
            inventory.getItems().add(item.getTileName());
        }
        healthLabel.setText("" + map.getPlayer().getHealth());

        if (map.getPlayer().getCell().getItem() != null) {
            StringBuilder sb = new StringBuilder(pickButton.getText());
            sb.append(" ").append(map.getPlayer().getCell().getItem().getTileName());
            pickButton.setText(sb.toString());
            pickButton.setDisable(false);

        } else {
            pickButton.setDisable(true);
            pickButton.setText("Pick up");
        }
    }

    private void aiMove() {
        ArrayList<Actor> actors = new ArrayList<>();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() instanceof Npc && cell.getActor().isAlive()){
                    actors.add(cell.getActor());
                }
            }
        }
        for (Actor actor : actors){
            actor.moveAi();
        }
    }

}
