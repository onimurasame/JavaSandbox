package com.onimurasame.file;

import lombok.extern.slf4j.Slf4j;

import javax.jws.Oneway;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileManager {
    private Path filePath;

    public FileManager(String path) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + path);
        } else {
            this.filePath = Paths.get(resource.toURI());
        }

    }

    private FileManager() {}

    private FileManager(File file){}


    public String getFileAsString() throws IOException {
        return new String(Files.readAllBytes(this.filePath));
    }

    public String[] getFileLinesAsArray() throws IOException {
        return Files.readAllLines(this.filePath).toArray(new String[0]);
    }

    public String[] getFileLinesAsArray(String regex) throws IOException {
        return getFileAsString().split(regex);
    }

    public String[] getFileLinesOnlyTextArray(String regex) throws IOException {
        return cleanEmptyLinesFromStringArray(getFileLinesAsArray(regex));
    }

    private String[] cleanEmptyLinesFromStringArray(String[] strArr) {
        List<String> stringArrayList = new ArrayList<>();
        for (String str : strArr) {
            str = str.replaceAll("\\p{Cntrl}", "");
            if (str.length() > 0) {
                stringArrayList.add(str);
            }
        }

        return stringArrayList.toArray(new String[0]);

    }



}
