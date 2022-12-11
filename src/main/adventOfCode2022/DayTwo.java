package main.adventOfCode2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class DayTwo {

    private static final int LOSE = 0;
    private static final int DRAW = 3;
    private static final int WIN = 6;

    private static final int ROCK = 1;
    private static final int PAPER = 2;
    private static final int SCISSOR = 3;

    public static void main(String[] args) {

        HashMap<String, Integer> results = new HashMap<String, Integer>();
        results.put("A X", DRAW + ROCK);
        results.put("A Y", WIN + PAPER);
        results.put("A Z", LOSE + SCISSOR);

        results.put("B X", LOSE + ROCK);
        results.put("B Y", DRAW + PAPER);
        results.put("B Z", WIN + SCISSOR);

        results.put("C X", WIN + ROCK);
        results.put("C Y", LOSE + PAPER);
        results.put("C Z", DRAW + SCISSOR);

        try {
            int score = 0;
            List<String> matches = Files.readAllLines(Paths.get("resources/DayTwo.txt"));
            for (String match : matches) {
                int matchScore = results.get(match);
                System.out.println("macth fix: " + match);
                System.out.println("match score: " + matchScore);
                score += matchScore;
            }
            System.out.println("Score: " + score);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
