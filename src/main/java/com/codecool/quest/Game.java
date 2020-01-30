package com.codecool.quest;

import com.codecool.quest.logic.*;
import com.codecool.quest.logic.Items.Items;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Npc;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.SortedMap;

public class Game {
    GameMap[] levels = new GameMap[MapFile.values().length];
    GameMap map;
    Canvas canvas;
    GraphicsContext context;
    Label healthLabel = new Label();
    ListView<String> inventory = new ListView<>();
    Button pickButton = new Button("Pick up");
    Label playerNameLabel = new Label();
    TextField nameInput = new TextField();
    Button setNameButton = new Button("Set Name");
    Button saveButton = new Button("Save game");
    Button cancelSaveButton = new Button("Cancel");
    TextField savedGameName = new TextField();
    Integer Aimove = 0;

    Stage window;
    Scene menuSecene;
    Button startGameButton = new Button("Start game!");
    Button exitMenuButton = new Button("Exit");
    Button loadGameButton = new Button("Load game");

    public void gameStart(Stage primaryStage) throws IOException {
        System.out.println(Arrays.toString(GameSaver.getSavedFiles()));
        window = primaryStage;
//      2D graphics canvas
        this.loadLevels();
        map = levels[0];
        canvas = new Canvas(
                map.getWidth() * Tiles.TILE_WIDTH,
                map.getHeight() * Tiles.TILE_WIDTH);
        context = canvas.getGraphicsContext2D();

//      HUD for health, inventory, playername

//      TOP part of HUD
        GridPane topGrid = new GridPane();
        Label health = new Label("Health:");
        health.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        topGrid.add(health, 0, 1);
        healthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        topGrid.add(healthLabel, 1, 1);
//      RIGHT part of HUD
        GridPane ui = new GridPane();
        ui.setPrefWidth(210);
        ui.setPadding(new Insets(10));
        ui.setVgap(10);
        ui.add(new Label("Inventory:"), 0, 2);
        ui.add(pickButton, 0, 5, 2, 1);
        pickButton.setDisable(true);
        ui.add(inventory, 0, 3, 2, 1);
        inventory.setFocusTraversable(false);
        nameInput.setPrefWidth(120);
        ui.add(nameInput, 0, 7);
        ui.add(setNameButton, 0, 8);
        setNameButton.setOnAction(this::onSetNameButtonClick);
        ui.add(new Label("Name: "), 0, 0);
        ui.add(playerNameLabel, 1, 0);
        ui.add(saveButton, 0, 10);
        ui.add(savedGameName, 0, 9);
        ui.add(cancelSaveButton, 1, 10);
        saveButton.setOnAction(this::onSaveGameButtonClick);
        saveButton.setFocusTraversable(false);
        cancelSaveButton.setFocusTraversable(false);
        cancelSaveButton.setOnAction(this::onCancelButton);
        cancelSaveButton.setVisible(false);
        savedGameName.setVisible(false);
        savedGameName.setPrefWidth(120);
        nameInput.setFocusTraversable(false);
        setNameButton.setFocusTraversable(false);



//      Canvas, HUD wrapper
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        borderPane.setCenter(canvas);
        borderPane.setRight(ui);
        borderPane.setTop(topGrid);
        BackgroundFill myBF = new BackgroundFill(Color.BLANCHEDALMOND, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        borderPane.setBackground(new Background(myBF));
        Scene scene = new Scene(borderPane);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
        pickButton.setOnAction(this::onPickButtonClick);
        pickButton.setFocusTraversable(false);

//      Layout & Scene for menu
        VBox menuLayout = new VBox(8);
        startGameButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        exitMenuButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        loadGameButton.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        menuLayout.getChildren().addAll(startGameButton, loadGameButton, exitMenuButton);
        menuLayout.setAlignment(Pos.CENTER);
        startGameButton.setOnAction(e -> window.setScene(scene));
        exitMenuButton.setOnAction(e -> window.close());
        loadGameButton.setOnAction(actionEvent -> {
            try {
                onLoadGameButtonClick(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        BackgroundImage myBI = new BackgroundImage(new Image("CClogo.png", 300, 300, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        menuLayout.setBackground(new Background(myBI));
        menuSecene = new Scene(menuLayout, 300, 300);

//      Start of JavaFX
        window.setScene(menuSecene);
        window.setTitle("Codecool Quest");
        window.show();
    }

    private void onLoadGameButtonClick(ActionEvent actionEvent) throws IOException {
        String[] savedFileNames = GameSaver.getSavedFiles();
        System.out.println(Arrays.toString(savedFileNames));

    }

    private void onCancelButton(ActionEvent actionEvent) {
        savedGameName.clear();
        savedGameName.setVisible(false);
        cancelSaveButton.setVisible(false);
        savedGameName.setFocusTraversable(true);
        canvas.requestFocus();
        savedGameName.setFocusTraversable(false);
    }

    private void onSetNameButtonClick(ActionEvent actionEvent) {
        String input = nameInput.getText();
        map.getPlayer().setName(input);
        if (input.equals("iddqd"))
            map.getPlayer().setGod(true);

        canvas.requestFocus();
        nameInput.clear();
        refresh();
    }

    private void onPickButtonClick(ActionEvent actionEvent) {
        map.getPlayer().pickItem();
        refresh();
    }

    private void onSaveGameButtonClick(ActionEvent actionEvent) {
        if (savedGameName.isVisible()) {
            String filename = savedGameName.getText();
            if (!filename.equals("")){
                GameSaver.writeSaveFile(filename, levels, map.getCurrentLevel());
                savedGameName.clear();
                savedGameName.setVisible(false);
                savedGameName.setFocusTraversable(true);
                cancelSaveButton.setVisible(false);
                canvas.requestFocus();
                savedGameName.setFocusTraversable(false);
            }
        }
        else {
            savedGameName.setVisible(true);
            cancelSaveButton.setVisible(true);
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        Cell nextCell;
        switch (keyEvent.getCode()) {
            case UP:
                nextCell = map.getPlayer().move(Direction.NORTH);
                changeLevel(nextCell);
                refresh();
                break;
            case DOWN:
                nextCell = map.getPlayer().move(Direction.SOUTH);
                changeLevel(nextCell);
                refresh();
                break;
            case LEFT:
                nextCell = map.getPlayer().move(Direction.WEST);
                changeLevel(nextCell);
                refresh();
                break;
            case RIGHT:
                nextCell = map.getPlayer().move(Direction.EAST);
                changeLevel(nextCell);
                refresh();
                break;
            case ENTER:
                if (map.getPlayer().getCell().getItem() != null) {
                    map.getPlayer().pickItem();
                    refresh();
                }
                break;
            case SHIFT:
                if (map.getPlayer().getInventory().contains("turkey leg")) {
                    map.getPlayer().getInventory().removeItemByItemName("turkey leg");
                    map.getPlayer().changeHealth(5);
                    refresh();
                }
                break;
            case ESCAPE:
                nameInput.setFocusTraversable(true);
                canvas.requestFocus();
                nameInput.setFocusTraversable(false);
        }
    }

    private void refresh() {
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.BLACK);

        if (this.Aimove == 0) {
            StartAi();
            this.Aimove++;
        }
        restartGameIfPlayerDies();
        refreshTiles();
        refreshPlayerHealthLabel();
        refreshPlayerNameLabel();
        refreshInventoryView();
        handlePickupButton();
    }

    private void refreshAi() {
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.BLACK);

        if (this.Aimove == 0) {
            StartAi();
            this.Aimove++;
        }
        restartGameIfPlayerDies();
        refreshTiles();
        refreshPlayerHealthLabel();
        refreshPlayerNameLabel();
        handlePickupButton();
    }

    private void changeLevel(Cell nextCell) {
        if (nextCell != null && nextCell.getStairs() != null) {
            Object[] playerAttributes = this.map.getPlayer().getAttributesForLevelChange();
            int levelTo = nextCell.getStairs().getLevel();
            this.levels[map.getCurrentLevel()] = this.map;
            this.map = this.levels[levelTo];
            this.map.getPlayer().setAttributesOnNewLevel(playerAttributes);
            refresh();
        }
    }

    private void aiMove() {
        ArrayList<Actor> actors = new ArrayList<>();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() instanceof Npc && cell.getActor().isAlive()) {
                    actors.add(cell.getActor());
                }
            }
        }
        for (Actor actor : actors) {
            actor.moveAi();
        }
    }

    public void loadLevels() {
        int actualIndex = 0;
        for (MapFile level : MapFile.values()) {
            levels[actualIndex] = MapLoader.loadMap(level.getLevelMap(), level.getLevelIndex());
            actualIndex++;
        }
    }

    public void restartGameIfPlayerDies() {
        if (!this.map.getPlayer().isAlive()) {
            loadLevels();
            this.map = levels[0];
            this.map.getPlayer().setHealth(10);
        }
    }

    public void refreshTiles() {
        final int NORTH = 0;
        final int WEST = 1;
        final int SOUTH = 2;
        final int EAST = 3;

        int[] params = setParametersForMapMove();
        int targetCellX = 0;
        for (int x = params[WEST]; x < params[EAST]; x++) {
            int targetCellY = 0;
            for (int y = params[NORTH]; y < params[SOUTH]; y++) {

                if (x >= this.map.getWidth() || x < 0 || y >= this.map.getHeight() || y < 0) {
                    Tiles.drawTile(context, new Cell(0, 0, CellType.EMPTY), targetCellX, targetCellY);
                    targetCellY++;
                    continue;
                }
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null && !cell.getActor().isAlive()) {
                    cell.removeActor();
                }
                if (cell.getActor() != null && cell.getActor().isAlive()) {
                    Tiles.drawTile(context, cell.getActor(), targetCellX, targetCellY);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), targetCellX, targetCellY);
                } else if (cell.getDoor() != null) {
                    Tiles.drawTile(context, cell.getDoor(), targetCellX, targetCellY);
                } else if (cell.getStairs() != null) {
                    Tiles.drawTile(context, cell.getStairs(), targetCellX, targetCellY);
                } else if (cell.getDecor() != null) {
                    Tiles.drawTile(context, cell.getDecor(), targetCellX, targetCellY);
                } else {
                    Tiles.drawTile(context, cell, targetCellX, targetCellY);
                }
                targetCellY++;
            }
            targetCellX++;
        }
    }

    public int[] setParametersForMapMove() {
        int[] params = new int[4];
        int charX = map.getPlayer().getX();
        int charY = map.getPlayer().getY();
        int xOffset = 12;
        int yOffset = 10;

        if (this.map.getHeight() > 20 || this.map.getWidth() > 25) {
            params[0] = charY - yOffset;
            params[1] = charX - xOffset - 1;
            params[2] = charY + yOffset;
            params[3] = charX + xOffset + 1;
        } else {
            params[0] = 0;
            params[1] = 0;
            params[2] = this.map.getHeight();
            params[3] = this.map.getWidth();
        }
        return params;
    }

    public void handlePickupButton() {
        if (map.getPlayer().getCell().getItem() != null) {
            pickButton.setText("Pick up " + map.getPlayer().getCell().getItem().getTileName());
            pickButton.setDisable(false);

        } else {
            pickButton.setDisable(true);
            pickButton.setText("Pick up");
        }
        canvas.requestFocus();
    }

    public void refreshInventoryView() {
        inventory.getItems().clear();
        for (Items item : map.getPlayer().getInventory().getItems()) {
            inventory.getItems().add(item.getTileName());
        }
    }

    public void refreshPlayerHealthLabel() {
        healthLabel.setText("" + map.getPlayer().getHealth());
    }

    public void StartAi() {
        Runnable task = () -> {
            while (true) {
                aiMove();
                refreshAi();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("hey");
                }
            }
        };

        Thread backgroundThread = new Thread(task);
        backgroundThread.start();
    }
    public void refreshPlayerNameLabel() {
        playerNameLabel.setText(map.getPlayer().getName());
    }
}


