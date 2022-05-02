package com.onimurasame.file;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class FileManagerTest {

    private static final String FIXEDWIDTH_FILE_EMPTYLINES_END_PATH = "FixedWidthFile_EmptyLinesAtTheEnd.txt";

    private static final FileManager fileManagerEmptyLinesEnd;

    static {
        try {
            fileManagerEmptyLinesEnd = new FileManager(FIXEDWIDTH_FILE_EMPTYLINES_END_PATH);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getFileAsString() throws IOException {
        String str = fileManagerEmptyLinesEnd.getFileAsString();

        int length = str.length();

        log.info("String's length is {}", length);
        log.debug("File Contents: \n{}", str);
        assertTrue(length > 0, "String's length is " + length);

    }


    @Test
    void getFileLinesAsArrayOfStrings() throws IOException {
        String[] strArr = fileManagerEmptyLinesEnd.getFileLinesAsArray("\n");

        int length = strArr.length;
        log.info("Array's length is {}", length);
        String str = arrayAsString(strArr);
        System.out.println(str);
        log.debug("Array's Contents: \n==============================\n {} \n============EOF===============", str);

        assertEquals(5, length, "Number of elements should be 5, actual length was " + length);
    }

    @Test
    void getFileLinesAsArraysOfStringsWithoutEmptyLines() throws IOException {
        String[] strArr = fileManagerEmptyLinesEnd.getFileLinesOnlyTextArray("\n");

        int length = strArr.length;
        log.info("Array's length is {}", length);
        String str = arrayAsString(strArr);
        System.out.println(str);
        log.debug("Array's Contents: \n==============================\n {} \n============EOF===============", str);

        assertEquals(4, length, "Number of elements should be 4, due to an empty line, actual length was " + length);

    }

    @Test
    String arrayAsString(String[] array) {
        StringBuilder arrStr = new StringBuilder();

        for (String o : array) {
            arrStr.append(o);
        }

        return arrStr.toString();

    }
}