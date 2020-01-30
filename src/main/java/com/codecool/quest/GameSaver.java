package com.codecool.quest;

import java.io.*;

import com.codecool.quest.logic.GameMap;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class GameSaver {

    private static String BASE_SAVE_PATH = "./src/main/resources/saved_games/";
    private static ObjectMapper mapper = new ObjectMapper();

    public static void writeSaveFile(String filename, GameMap[] levels, int actualLevel) {
        String levelsFullPath = BASE_SAVE_PATH + filename + ".json";
        String levelIndexFullPath = BASE_SAVE_PATH + filename + "LevelIndex" + ".txt";

        try {
            File levelsFile = new File(levelsFullPath);
            File levelIndex = new File(levelIndexFullPath);

            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(levelsFile, levels);

            BufferedWriter bWriter = new BufferedWriter(new FileWriter(levelIndex));
            bWriter.write(String.valueOf(actualLevel));

            bWriter.close();

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

    public static String[] getSavedFiles() throws IOException {
        File saveFolder = new File(BASE_SAVE_PATH);
        FilenameFilter filter = (dir, name) -> name.toLowerCase().endsWith(".json");
        File[] savedFiles = saveFolder.listFiles(filter);

        if (savedFiles.length != 0){
            String[] savedFileNames = new String[savedFiles.length];
            int indexCounter = 0;
            for (File savedFile : savedFiles) {
                savedFileNames[indexCounter] = FilenameUtils.getBaseName(savedFile.getName());
                indexCounter++;
            }
            return savedFileNames;
        }
        return null;
    }
}
