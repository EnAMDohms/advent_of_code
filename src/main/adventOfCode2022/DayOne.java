package main.adventOfCode2022;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DayOne {

    public static void main(String[] args) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("resources/DayOne.txt"));
            String line = reader.readLine();

            int maxCalories = 0;
            int secondMost = 0;
            int thirdMost = 0;

            int caloryCount = 0;

            while (line != null) {
                if (!line.isEmpty()) {
                    caloryCount += Integer.valueOf(line);
                } else {
                    int tempCalories;
                    if (caloryCount > maxCalories) {
                        tempCalories = maxCalories;
                        maxCalories = caloryCount;
                        caloryCount = tempCalories;
                    }
                    if (caloryCount > secondMost) {
                        tempCalories = secondMost;
                        secondMost = caloryCount;
                        caloryCount = tempCalories;
                    }
                    if (caloryCount > thirdMost) {
                        tempCalories = thirdMost;
                        thirdMost = caloryCount;
                        caloryCount = tempCalories;
                    }
                    caloryCount = 0;
                }

                // read next line
                line = reader.readLine();
            }

            System.out.println("max calories: " + maxCalories);
            System.out.println("second calories: " + secondMost);
            System.out.println("third calories: " + thirdMost);
            System.out.println("Total calories of the three : " + (maxCalories + secondMost + thirdMost));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
