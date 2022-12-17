package main.adventOfCode2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import main.adventOfCode2022.dataModel.DaySeven.Directory;

public class DaySeven {

    private static final int DISC_SIZE = 70000000;
    private static final int REQUIRED_SPACE = 30000000;

    public static void main(String[] args) {
        // String inputPath = "resources/test.txt";
        String inputPath = "resources/DaySeven.txt";

        try {
            List<String> commands = Files.readAllLines(Paths.get(inputPath));

            Directory rootDirectory = createFileSystem(commands);
            partOne(rootDirectory);
            partTwo(rootDirectory);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Directory createFileSystem(List<String> commands) {
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

        return rootDirectory;
    }

    private static void partOne(Directory rootDirectory) {

        System.out.println("part one: " + sumOfSmallSizeDirectories(rootDirectory));
    }

    private static void partTwo(Directory rootDirectory) {
        int spaceToFree = calculateSpaceToFree(calculateAvailableSpace(rootDirectory));
        System.out.println("part two: " + getDirectoryToDelete(rootDirectory, spaceToFree));
    }

    private static int calculateAvailableSpace(Directory rootDirectory) {
        return DISC_SIZE - rootDirectory.getSize();
    }

    private static int calculateSpaceToFree(int availabelSpace) {
        return REQUIRED_SPACE - availabelSpace;
    }

    private static int getDirectoryToDelete(Directory rootDirectory, int spaceToFree) {
        Directory directoryToDelete = rootDirectory;
        HashMap<String, Directory> directorySizes = new HashMap<>();
        populateMapOfDirectoriesToDelete(rootDirectory, directorySizes, spaceToFree);

        for (String directoryPath : directorySizes.keySet()) {
            if (directorySizes.get(directoryPath).getSize() < directoryToDelete.getSize()) {
                directoryToDelete = directorySizes.get(directoryPath);
            }
        }
        return directoryToDelete.getSize();
    }

    private static void populateMapOfDirectoriesToDelete(Directory directory, HashMap<String, Directory> directorySizes,
            int spaceToFree) {
        for (Directory subdirectory : directory.getSubDirectories()) {
            populateMapOfDirectoriesToDelete(subdirectory, directorySizes, spaceToFree);
        }
        if (directory.getSize() >= spaceToFree) {
            directorySizes.put(directory.getPath(), directory);
        }
    }

    private static int sumOfSmallSizeDirectories(Directory directory) {
        HashMap<String, Integer> directorySizes = new HashMap<>();
        populateDirctorySizeMap(directory, directorySizes);

        int sum = 0;
        for (int directorySize : directorySizes.values()) {
            sum += directorySize;
        }
        return sum;
    }

    private static void populateDirctorySizeMap(Directory directory, HashMap<String, Integer> directorySizes) {
        for (Directory subdirectory : directory.getSubDirectories()) {
            populateDirctorySizeMap(subdirectory, directorySizes);
        }
        if (directory.getSize() <= 100000) {
            directorySizes.put(directory.getPath(), directory.getSize());
        }
    }

}
