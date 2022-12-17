package main.adventOfCode2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import main.adventOfCode2022.dataModel.DaySeven.Directory;

public class DaySeven {

    public static void main(String[] args) {
        // String inputPath = "resources/test.txt";
        String inputPath = "resources/DaySeven.txt";

        try {
            List<String> commands = Files.readAllLines(Paths.get(inputPath));

            partOne(commands);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void partOne(List<String> commands) {
        Directory rootDirectory = new Directory("/");
        Directory activeDirectory = rootDirectory;
        for (String command : commands) {
            if (command.equals("$ cd /")) {
                activeDirectory = rootDirectory;
            } else if (command.startsWith("$ cd ")) {
                activeDirectory = activeDirectory.changeDirectory(command.substring(5));
            } else if (command.matches("\\d+\\s[\\w\\W]+")) {
                String[] file = command.split(" ");
                activeDirectory.addFile(file[1], Integer.parseInt(file[0]));
            } else if (command.startsWith("dir ")) {
                activeDirectory.addSubdirectory(activeDirectory, command.substring(4));
            }
        }

        System.out.println("result: " + sumOfSmallSizeDirectories(rootDirectory));
    }

    private static int sumOfSmallSizeDirectories(Directory directory) {
        List<Integer> directorySizes = new ArrayList<Integer>();
        populateDirctorySizeMap(directory, directorySizes);

        int sum = 0;
        for (int directorySize : directorySizes) {
            sum += directorySize;
        }
        return sum;
    }

    private static void populateDirctorySizeMap(Directory directory, List<Integer> directorySizes) {
        for (Directory subdirectory : directory.getSubDirectories()) {
            populateDirctorySizeMap(subdirectory, directorySizes);
        }
        if (directory.getSize() <= 100000) {
            directorySizes.add(directory.getSize());
        }
    }
}
