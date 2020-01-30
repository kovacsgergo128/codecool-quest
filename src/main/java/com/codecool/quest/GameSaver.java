package com.codecool.quest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.codecool.quest.logic.GameMap;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;


public class GameSaver {

    private static String BASE_SAVE_PATH = "./src/main/resources/saved_games/";
    private static ObjectMapper mapper = new ObjectMapper();

    public static void writeSaveFile(String filename, GameMap[] levels, Integer levelIndex) {
        String fullPathNAme = BASE_SAVE_PATH + filename + ".json";

        try {
            File file = new File(fullPathNAme);
            FileWriter fileWriter = new FileWriter(file, true);

            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            SequenceWriter writer = mapper.writer().writeValuesAsArray(fileWriter);
            writer.write(levels);
            writer.write(levelIndex);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSavedFile(String fileName) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
