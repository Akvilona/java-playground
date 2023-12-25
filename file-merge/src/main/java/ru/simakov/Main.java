package ru.simakov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

@SuppressWarnings("PMD.AssignmentInOperand")
public class Main {
    public static void main(final String[] args) throws IOException {
        Path newFilePath = Paths.get("Pride and Prejudice_4.srt");
        if (Files.exists(newFilePath)) {
            Files.delete(newFilePath);
        }

        Files.createFile(newFilePath);

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        try
            (InputStream inputStreamEn = classloader.getResourceAsStream("Pride and Prejudice_1.srt");
             InputStreamReader streamReaderEn = new InputStreamReader(Objects.requireNonNull(inputStreamEn), StandardCharsets.UTF_8);
             BufferedReader readerEn = new BufferedReader(streamReaderEn);

             InputStream inputStreamRu = classloader.getResourceAsStream("Pride and Prejudice_2.srt");
             InputStreamReader streamReaderRu = new InputStreamReader(Objects.requireNonNull(inputStreamRu), StandardCharsets.UTF_8);
             BufferedReader readerRu = new BufferedReader(streamReaderRu)) {

            String lineEn;
            String lineRu;

            while ((lineEn = readerEn.readLine()) != null && (lineRu = readerRu.readLine()) != null) {
                String string = lineEn.isEmpty()
                                    || lineEn.charAt(0) == '0'
                                    || lineEn.charAt(0) == '1'
                                    || lineEn.charAt(0) == '2'
                                    || lineEn.charAt(0) == '3'
                                    || lineEn.charAt(0) == '4'
                                    || lineEn.charAt(0) == '5'
                                    || lineEn.charAt(0) == '6'
                                    || lineEn.charAt(0) == '7'
                                    || lineEn.charAt(0) == '8'
                                    || lineEn.charAt(0) == '9'
                                ? lineEn + System.lineSeparator()
                                :   lineRu.replaceAll(",","").replaceAll("\\.", "") + " - " + lineEn.replaceAll(",","").replaceAll("\\.", "") + System.lineSeparator();

                Files.writeString(newFilePath, string, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            }
        }
    }
}
