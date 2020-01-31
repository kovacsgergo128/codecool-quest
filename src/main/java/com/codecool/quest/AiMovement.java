package com.codecool.quest;

import com.codecool.quest.logic.*;
import com.codecool.quest.logic.Items.Items;
import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Npc;
import com.codecool.quest.logic.actors.Player;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;





import java.util.ArrayList;

public class AiMovement extends Service {
    GameMap[] levelss;


    GameMap mapp;
    GraphicsContext contextt;
    Canvas Canvass;
    Label Playerr;
    Label Healthh;
    private Thread threadToStopOnCancel;
    ListView<String> Inventory;


    public <Gamemap> AiMovement(Gamemap map, GraphicsContext context, Canvas Canvas, Label Player, Label Health, ListView<String> inventory){
        this.mapp = (GameMap) map;
        this.contextt = context;
        this.Canvass = Canvas;
        this.Playerr = Player;
        this.Healthh = Health;
        this.Inventory = inventory;
    }



    @Override
    protected Task<String> createTask() {

        return new Task<>(){

            @Override
            protected String call() throws Exception {
                while (true) {
                    aiMove();
                    refreshAi();


                    if (isCancelled())
                    {
                        throw new InterruptedException();


                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        System.out.println("hey");

                    }
                }

            }
        };
    }

    private void aiMove() {

        ArrayList<Actor> actors = new ArrayList<>();
        for (int x = 0; x < mapp.getWidth(); x++) {
            for (int y = 0; y < mapp.getHeight(); y++) {
                Cell cell = mapp.getCell(x, y);
                if (cell.getActor() instanceof Npc && cell.getActor().isAlive()) {
                    actors.add(cell.getActor());
                }
                if(cell.getActor() instanceof Player && cell.getStairs() != null){

                }
            }
        }
        for (Actor actor : actors) {
            actor.moveAi();
        }



    }

    private void refreshAi(){
        contextt.fillRect(0, 0, Canvass.getWidth(), Canvass.getHeight());
        contextt.setFill(Color.BLACK);



        restartGameIfPlayerDies();
        refreshTiles();
        //refreshPlayerHealthLabel();
        //refreshPlayerNameLabel();
        //refreshInventoryView();
        //handlePickupButton();


    }
    public void refreshTiles() {
        final int NORTH = 0;
        final int WEST = 1;
        final int SOUTH = 2;
        final int EAST = 3;

        int[]params = setParametersForMapMove();
        int targetCellX = 0;
        for (int x = params[WEST]; x < params[EAST]; x++) {
            int targetCellY = 0;
            for (int y = params[NORTH]; y < params[SOUTH]; y++) {

                if (x >= this.mapp.getWidth() || x < 0 || y >= this.mapp.getHeight() || y < 0) {
                    Tiles.drawTile(contextt, new Cell(0, 0, CellType.EMPTY), targetCellX, targetCellY);
                    targetCellY++;
                    continue;
                }
                Cell cell = mapp.getCell(x, y);
                if (cell.getActor() != null && !cell.getActor().isAlive()) {
                    cell.removeActor();
                }
                if (cell.getActor() != null && cell.getActor().isAlive()) {
                    Tiles.drawTile(contextt, cell.getActor(), targetCellX, targetCellY);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(contextt, cell.getItem(), targetCellX, targetCellY);
                } else if (cell.getDoor() != null) {
                    Tiles.drawTile(contextt, cell.getDoor(), targetCellX, targetCellY);
                } else if (cell.getStairs() != null) {
                    Tiles.drawTile(contextt, cell.getStairs(), targetCellX, targetCellY);
                } else if (cell.getDecor() != null) {
                    Tiles.drawTile(contextt, cell.getDecor(), targetCellX, targetCellY);
                } else {
                    Tiles.drawTile(contextt, cell, targetCellX, targetCellY);
                }
                targetCellY++;
            }
            targetCellX++;
        }
    }
    public int[] setParametersForMapMove () {
        int[] params = new int[4];
        int charX = mapp.getPlayer().getX();
        int charY = mapp.getPlayer().getY();
        int xOffset = 12;
        int yOffset = 10;

        if (this.mapp.getHeight() > 20 || this.mapp.getWidth() > 25) {
            params[0] = charY - yOffset;
            params[1] = charX - xOffset - 1;
            params[2] = charY + yOffset;
            params[3] = charX + xOffset + 1;
        } else {
            params[0] = 0;
            params[1] = 0;
            params[2] = this.mapp.getHeight();
            params[3] = this.mapp.getWidth();
        }
        return params;
    }

    public void restartGameIfPlayerDies() {
        if (!this.mapp.getPlayer().isAlive()) {
            loadLevels();
            this.mapp = levelss[0];
            this.mapp.getPlayer().setHealth(10);
        }
    }

    public void loadLevels() {
        int actualIndex = 0;
        for (MapFile level : MapFile.values()) {
            levelss[actualIndex] = MapLoader.loadMap(level.getLevelMap(),level.getLevelIndex());
            actualIndex++;
        }
    }

    public void refreshPlayerNameLabel() {
        Playerr.setText(mapp.getPlayer().getName());
    }


    public void refreshPlayerHealthLabel() {
        Healthh.setText("" + mapp.getPlayer().getHealth());
    }

    public void refreshInventoryView() {
        Inventory.getItems().clear();
        for (Items item : mapp.getPlayer().getInventory().getItems()) {
            Inventory.getItems().add(item.getTileName());
        }
    }




}
