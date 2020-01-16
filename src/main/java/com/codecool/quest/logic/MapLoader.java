package com.codecool.quest.logic;

import com.codecool.quest.logic.Items.Key;
import com.codecool.quest.logic.Items.Riches;
import com.codecool.quest.logic.Items.Sword;
import com.codecool.quest.logic.Items.TurkeyLeg;
import com.codecool.quest.logic.actors.Boss;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.actors.Skeleton;
import com.codecool.quest.logic.decorations.Bonfire;
import com.codecool.quest.logic.decorations.Skull;
import com.codecool.quest.logic.decorations.Spikes;
import com.codecool.quest.logic.decorations.Web;
import com.codecool.quest.logic.interactable.Door;
import com.codecool.quest.logic.interactable.Stairs;

import java.io.InputStream;
import java.util.Scanner;

public class MapLoader {
    public static GameMap loadMap(String levelMap) {
        InputStream is = MapLoader.class.getResourceAsStream(levelMap);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        int previousLevel = scanner.nextInt();
        int currentLevel = scanner.nextInt();
        int nextLevel = scanner.nextInt();
        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        map.setCurrentLevel(currentLevel);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'D':
                            cell.setType(CellType.FLOOR);
                            new Door(cell);
                            break;
                        case 'k':
                            cell.setType(CellType.FLOOR);
                            new Key(cell);
                            break;
                        case 'S':
                            cell.setType(CellType.FLOOR);
                            new Sword(cell);
                            break;
                        case 'd':
                            cell.setType(CellType.FLOOR);
                            new Stairs(cell, nextLevel, "stairs_down");
                            break;
                        case 'u':
                            cell.setType(CellType.FLOOR);
                            new Stairs(cell, previousLevel, "stairs_up");
                            break;
                        case 'l':
                            cell.setType(CellType.FLOOR);
                            new TurkeyLeg(cell);
                            break;
                        case 'x':
                            cell.setType(CellType.FLOOR);
                            new Skull(cell);
                            break;
                        case 'b':
                            cell.setType(CellType.FLOOR);
                            new Bonfire(cell);
                            break;
                        case 't':
                            cell.setType(CellType.FLOOR);
                            new Spikes(cell);
                            break;
                        case 'w':
                            cell.setType(CellType.FLOOR);
                            new Web(cell);
                            break;
                        case 'B':
                            cell.setType(CellType.FLOOR);
                            new Boss(cell);
                            break;
                        case 'r':
                            cell.setType(CellType.FLOOR);
                            new Riches(cell);
                            break;

                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }

}
