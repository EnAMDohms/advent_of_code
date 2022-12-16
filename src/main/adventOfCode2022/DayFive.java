package main.adventOfCode2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayFive {

    public static void main(String[] args) {
        // String inputPath = "resources/test.txt";
        String inputPath = "resources/DayFive.txt";

        try {
            List<String> allLines = Files.readAllLines(Paths.get(inputPath));

            partOne(allLines);
            partTwo(allLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void partOne(List<String> allLines) {

        List<Integer> towerAndInstructionIndices = getIndicesFromLines(allLines);
        String towerNames = allLines.get(towerAndInstructionIndices.get(0));
        List<String> towers = splitTowerInArray(allLines, towerAndInstructionIndices.get(0),
                getNumberOfTowers(towerNames));

        moveAllElementsOneByOne(allLines.subList(towerAndInstructionIndices.get(1), allLines.size()), towers);
        buildResultString(towers);
    }

    private static void partTwo(List<String> allLines) {
        List<Integer> towerAndInstructionIndices = getIndicesFromLines(allLines);
        String towerNames = allLines.get(towerAndInstructionIndices.get(0));
        List<String> towers = splitTowerInArray(allLines, towerAndInstructionIndices.get(0),
                getNumberOfTowers(towerNames));

        moveAllElementsInBulk(allLines.subList(towerAndInstructionIndices.get(1), allLines.size()), towers);
        buildResultString(towers);
    }

    private static int getNumberOfTowers(String towerNames) {
        String[] splitString = towerNames.split("   ");
        return Integer.valueOf(splitString[splitString.length - 1].trim());
    }

    private static List<Integer> getIndicesFromLines(List<String> towersAndInstructions) {
        List<Integer> lineIndices = new ArrayList<Integer>();

        for (int index = 0; index < towersAndInstructions.size(); index++) {

            if (towersAndInstructions.get(index).startsWith(" 1  ")) {
                lineIndices.add(index);
            }

            if (towersAndInstructions.get(index).contains("move")) {
                lineIndices.add(index);
                break;
            }
        }

        return lineIndices;
    }

    private static List<String> splitTowerInArray(List<String> towerAndInstructions, int towerHeight,
            int numberOfTowers) {

        List<String> towers = new ArrayList<>(numberOfTowers);

        for (int index = 0; index < towerHeight; index++) {
            int insertIndex = 0;
            for (int stringIndex = 1; stringIndex < towerAndInstructions.get(index).length(); stringIndex += 4) {
                if (towers.size() < numberOfTowers) {
                    towers.add("");
                }

                String block = towerAndInstructions.get(index).substring(stringIndex, stringIndex + 1);
                towers.set(insertIndex, (towers.get(insertIndex)
                        + block).trim());
                insertIndex++;

            }
        }
        return towers;
    }

    private static List<Integer> getInstructionsFromString(String instruction) {
        int amountStringStartIndex = instruction.indexOf("move ") + 5;
        int amountStringEndIndex = instruction.indexOf(" from");
        int amount = Integer.parseInt(instruction.substring(amountStringStartIndex, amountStringEndIndex));

        int sourceStringStartIndex = instruction.indexOf("from ") + 5;
        int sourceStringEndIndex = instruction.indexOf(" to");
        int sourceIndex = Integer.parseInt(instruction.substring(sourceStringStartIndex, sourceStringEndIndex)) - 1;

        int targetStringIndex = instruction.indexOf("to ") + 3;
        int targetIndex = Integer.parseInt(instruction.substring(targetStringIndex, instruction.length())) - 1;

        List<Integer> instructionArray = new ArrayList<>();
        instructionArray.add(sourceIndex);
        instructionArray.add(targetIndex);
        instructionArray.add(amount);

        return instructionArray;
    }

    private static void moveAllElementsOneByOne(List<String> instructions, List<String> tower) {
        for (String instruction : instructions) {
            List<Integer> instructionArray = getInstructionsFromString(instruction);
            moveSingleBlock(tower, instructionArray.get(0), instructionArray.get(1), instructionArray.get(2));
        }
    }

    private static void moveAllElementsInBulk(List<String> instructions, List<String> tower) {
        for (String instruction : instructions) {
            List<Integer> instructionArray = getInstructionsFromString(instruction);
            moveMultipleBlocks(tower, instructionArray.get(0), instructionArray.get(1), instructionArray.get(2));
        }
    }

    private static void moveMultipleBlocks(List<String> towers, int sourceIndex, int targetIndex, int amount) {

        String sourceString = towers.get(sourceIndex);
        String moveElements = "";
        String restElements = "";

        if (amount >= sourceString.length()) {
            moveElements = sourceString;
        } else {
            moveElements = sourceString.substring(0, amount);
            restElements = sourceString.substring(amount);
        }

        towers.set(targetIndex, moveElements.concat(towers.get(targetIndex)));
        towers.set(sourceIndex, restElements);
    }

    private static void moveSingleBlock(List<String> towers, int sourceIndex, int targetIndex, int amount) {
        String sourceString = towers.get(sourceIndex);
        String moveElements = "";
        String restElements = "";

        for (int index = 0; index < amount; index++) {
            moveElements = sourceString.charAt(index) + moveElements;
        }

        if (amount < sourceString.length()) {
            restElements = sourceString.substring(amount);
        }

        towers.set(targetIndex, moveElements.concat(towers.get(targetIndex)));
        towers.set(sourceIndex, restElements);
    }

    private static void buildResultString(List<String> towers) {
        String resultString = "";
        for (String tower : towers) {
            if (tower.length() > 0) {
                resultString += tower.substring(0, 1);
            }
        }
        System.out.println(resultString);
    }
}