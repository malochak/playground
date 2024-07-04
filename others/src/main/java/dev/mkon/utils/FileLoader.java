package dev.mkon.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FileLoader {


    public List<String> loadLinesFromFile(String fileLocation) {
        ClassLoader classLoader = getClass().getClassLoader();

        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(classLoader.getResourceAsStream(fileLocation)))) {
            return reader.lines().skip(1).toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
