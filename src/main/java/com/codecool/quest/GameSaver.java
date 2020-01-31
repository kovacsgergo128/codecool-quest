package com.codecool.quest;

import java.io.*;

import com.codecool.quest.logic.GameMap;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import org.apache.commons.io.FilenameUtils;

public class GameSaver {

    private static GameMap[] levels;
    private static int currentLevel;

    private static String BASE_SAVE_PATH = "./src/main/resources/saved_games/";
    private static ObjectMapper mapper = new ObjectMapper();

    public static void writeGameMapToJsonFile(String filename, GameMap[] levels) {
        String levelsFullPath = BASE_SAVE_PATH + filename + ".json";
        PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().build();

        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(ptv);

        try {
            File levelsFile = new File(levelsFullPath);

            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            writer.writeValue(levelsFile, levels);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeNumberToTXTFile(String filename, int number) {
        String levelIndexFullPath = BASE_SAVE_PATH + filename + ".txt";

        try {
            File levelIndex = new File(levelIndexFullPath);

            BufferedWriter bWriter = new BufferedWriter(new FileWriter(levelIndex));
            bWriter.write(String.valueOf(number));
            bWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadSavedFile(String fileName) {
        try {
            File levels = new File(BASE_SAVE_PATH + fileName + ".json");
            mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
            mapper.configure(MapperFeature.INFER_CREATOR_FROM_CONSTRUCTOR_PROPERTIES,false);
            PolymorphicTypeValidator ptv = BasicPolymorphicTypeValidator.builder().build();
            mapper.activateDefaultTyping(ptv);
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            GameSaver.levels = mapper.readValue(levels, GameMap[].class);
        }
        catch (Exception e) {
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
