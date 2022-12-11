package main.adventOfCode2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class DayThree {

    public static void main(String[] args) {
        String testPath = "resources/test.txt";
        String inputPath = "resources/DayThree.txt";

        try {
            // List<String> backpacks = Files.readAllLines(Paths.get(testPath));
            List<String> allLines = Files.readAllLines(Paths.get(inputPath));

            calculatePriorityDoubledItems(allLines);
            calculatePriorityBadges(allLines);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void calculatePriorityBadges(List<String> backpacks) {
        int sumOfPriorities = 0;

        for (int index = 0; index < backpacks.size(); index += 3) {
            char item = retrieveItemFromThreeBackpacks(backpacks.get(index), backpacks.get(index + 1),
                    backpacks.get(index + 2));
            sumOfPriorities += calculatePriorityFromItem(item);
        }

        System.out.println("sumOfPriorities part two: " + sumOfPriorities);
    }

    private static char retrieveItemFromThreeBackpacks(String backpackOne, String backpackTwo, String backpackThree) {
        char item = '1';

        char[] backpackOneItems = backpackOne.toCharArray();

        for (char backpackOneItem : backpackOneItems) {
            if (backpackTwo.indexOf(backpackOneItem) >= 0 && backpackThree.indexOf(backpackOneItem) >= 0) {
                item = backpackOneItem;
            }
        }

        return item;
    }

    private static void calculatePriorityDoubledItems(List<String> backpacks) {
        int sumOfPriorities = 0;

        for (String backpack : backpacks) {

            String compartmentOne = backpack.substring(0, backpack.length() / 2);
            String compartmentTwo = backpack.substring(backpack.length() / 2);

            char doubledItem = getItemInBothCompartments(compartmentOne, compartmentTwo);

            sumOfPriorities += calculatePriorityFromItem(doubledItem);
        }

        System.out.println("sumOfPriorities part one: " + sumOfPriorities);
    }

    private static char getItemInBothCompartments(String compartmentOne, String compartmentTwo) {

        char item = '1';

        char[] compartmentOneItems = compartmentOne.toCharArray();

        for (char compartmentOneItem : compartmentOneItems) {
            if (compartmentTwo.indexOf(compartmentOneItem) >= 0) {
                item = compartmentOneItem;
            }
        }

        return item;
    }

    private static int calculatePriorityFromItem(char item) {
        if (item - 96 < 1) {
            return item - 38;
        } else {
            return item - 96;
        }
    }
}
