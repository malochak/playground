package dev.mkon.tree;

import java.util.List;

import dev.mkon.utils.FileLoader;

public class Main {

    private final FileLoader fileLoader = new FileLoader();

    private static final String INPUT_PATH = "tree/input_1.csv";

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        List<String> csvLines = fileLoader.loadLinesFromFile(INPUT_PATH);

        System.out.println(csvLines);
    }


}
