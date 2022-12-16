package main.adventOfCode2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DaySix {

    private static final int START_OF_PACKET_SIZE = 4;
    private static final int START_OF_MESSAGE_SIZE = 14;

    public static void main(String[] args) {
        // String inputPath = "resources/test.txt";
        String inputPath = "resources/DaySix.txt";

        try {
            String signal = Files.readAllLines(Paths.get(inputPath)).get(0);
            partOne(signal);
            partTwo(signal);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void partOne(String signal) {
        System.out.println("part one: " + calculateMarkerPosition(signal, START_OF_PACKET_SIZE));
    }

    private static void partTwo(String signal) {
        System.out.println("part two: " + calculateMarkerPosition(signal, START_OF_MESSAGE_SIZE));
    }

    public static int calculateMarkerPosition(String signal, int markerSize) {
        for (int index = 0; index < signal.length(); index++) {
            if (isMarker(signal.substring(index, index + markerSize))) {
                return index + markerSize;
            }
        }
        return -1;
    }

    private static boolean isMarker(String potentialMarker) {

        for (int index = 0; index < potentialMarker.length() - 1; index++) {
            if (potentialMarker.indexOf(potentialMarker.charAt(index), index + 1) > 0) {
                return false;
            }
        }
        return true;
    }

}
