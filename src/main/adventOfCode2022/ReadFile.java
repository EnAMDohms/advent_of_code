package main.adventOfCode2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadFile {

    public static void main(String[] args) {
        String inputPath = "resources/test.txt";
        // String inputPath = "resources/DayXXX.txt";

        try {
            List<String> allLines = Files.readAllLines(Paths.get(inputPath));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}