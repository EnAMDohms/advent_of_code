package main.adventOfCode2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DayFour {

    private static final int LOW_SECTION_BOUNDS = 0;
    private static final int HIGH_SECTION_BOUDNS = 1;

    public static void main(String[] args) {
        String testPath = "resources/test.txt";
        String inputPath = "resources/DayFour.txt";

        try {
            // List<String> allLines = Files.readAllLines(Paths.get(testPath));
            List<String> allLines = Files.readAllLines(Paths.get(inputPath));

            calculateOverlapsAndContains(allLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void calculateOverlapsAndContains(List<String> sectionPairs) {
        int overlaps = 0;
        int sectionContainsSection = 0;
        for (String sectionPair : sectionPairs) {

            List<Integer> sectionOne = splitPairsInSections(sectionPair.split(",")[0]);
            List<Integer> sectionTwo = splitPairsInSections(sectionPair.split(",")[1]);
            if (sectionsOverlaps(sectionOne, sectionTwo) || sectionsOverlaps(sectionTwo, sectionOne))
                overlaps++;
            if (sectionsContainsSection(sectionOne, sectionTwo) || sectionsContainsSection(sectionTwo, sectionOne))
                sectionContainsSection++;
        }
        System.out.println("Section contains section: " + sectionContainsSection);
        System.out.println("Overlaps: " + overlaps);
    }

    private static List<Integer> splitPairsInSections(String sectionString) {

        String[] sectionsString = sectionString.split("-");

        List<Integer> section = new ArrayList<>();
        section.add(Integer.valueOf(sectionsString[0]));
        section.add(Integer.valueOf(sectionsString[1]));

        return section;

    }

    private static boolean sectionsContainsSection(List<Integer> bigSection, List<Integer> smallSection) {
        if (smallSection.get(LOW_SECTION_BOUNDS) < bigSection.get(LOW_SECTION_BOUNDS)
                || smallSection.get(LOW_SECTION_BOUNDS) > bigSection.get(HIGH_SECTION_BOUDNS)) {
            return false;
        }

        return !(smallSection.get(HIGH_SECTION_BOUDNS) < bigSection.get(LOW_SECTION_BOUNDS)
                || smallSection.get(HIGH_SECTION_BOUDNS) > bigSection.get(HIGH_SECTION_BOUDNS));
    }

    private static boolean sectionsOverlaps(List<Integer> bigSection, List<Integer> smallSection) {

        if (smallSection.get(LOW_SECTION_BOUNDS) >= bigSection.get(LOW_SECTION_BOUNDS)
                && smallSection.get(LOW_SECTION_BOUNDS) <= bigSection.get(HIGH_SECTION_BOUDNS)) {
            return true;
        }

        return (smallSection.get(HIGH_SECTION_BOUDNS) >= bigSection.get(LOW_SECTION_BOUNDS)
                && smallSection.get(HIGH_SECTION_BOUDNS) <= bigSection.get(HIGH_SECTION_BOUDNS));
    }
}
