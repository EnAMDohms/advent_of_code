package main.adventOfCode2022.dataModel.DaySeven;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Directory {

    private List<Directory> subDirectories = new ArrayList<>();
    private Directory parentDirectory;
    private HashMap<String, Integer> files = new HashMap<>();
    private String name = "";
    private String path = "";
    private int size = 0;

    public Directory() {
    }

    public Directory(String name) {
        this.name = name;
        this.path = name;
        parentDirectory = this;
    }

    public Directory(Directory parentDirectory, String name) {
        this.name = name;
        this.path = parentDirectory.getPath().concat("\\").concat(name);
        this.parentDirectory = parentDirectory;
    }

    public Directory changeDirectory(String directoryName) {
        if (directoryName.equals("..")) {
            return parentDirectory;
        } else {
            return openSubDirectoryByName(directoryName);
        }
    }

    public Directory openSubDirectoryByName(String directoryName) {

        for (Directory subDirectory : subDirectories) {
            if (subDirectory.getName().equals(directoryName)) {
                return subDirectory;
            }
        }

        return null;
    }

    public void addFile(String fileName, int fileSize) {
        files.put(fileName, fileSize);
        addSize(fileSize);
    }

    public void addSize(int fileSize) {
        this.size += fileSize;
        if (!this.name.equals("/")) {
            parentDirectory.addSize(fileSize);
        }
    }

    public void addSubdirectory(Directory parentDirectory, String directoryName) {
        if (!hasSubdirectorty(directoryName)) {
            subDirectories.add(new Directory(parentDirectory, directoryName));
        }
    }

    public boolean hasSubdirectorty(String directoryName) {
        for (Directory subDirectory : this.subDirectories) {
            if (subDirectory.getName().equals(directoryName)) {
                return true;
            }
        }
        return false;
    }

    public List<Directory> getSubDirectories() {
        return subDirectories;
    }

    public void setSubDirectories(List<Directory> subDirectories) {
        this.subDirectories = subDirectories;
    }

    public HashMap<String, Integer> getFiles() {
        return files;
    }

    public void setFiles(HashMap<String, Integer> files) {
        this.files = files;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
