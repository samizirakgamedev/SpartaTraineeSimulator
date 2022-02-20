package com.purerangers.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class LogFileGenerator {
    private static File file;
    private static int backupFileLines;

    public static void backupLogFile() throws IOException
    {
        file = new File("logfile.log");
        Path filePath = file.toPath();

        String backupFileString = filePath.toString().replaceAll(".log", "Backup.log");
        File backupFile = new File(backupFileString);
        Path backupFilePath = backupFile.toPath();

        if (Files.exists(backupFilePath)) {
            Files.delete(backupFilePath);
        }

        Files.copy(filePath, backupFilePath);

        FileReader fileReader = new FileReader(file);
        LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
        backupFileLines = (int) lineNumberReader.lines().count() + 1;
        lineNumberReader.close();
    }

    public static void splitLogfile(String name) throws IOException
    {
        String[] filteredNames = name.replaceAll("\\.", "/").split("/");

        String createdName = filteredNames[filteredNames.length - 1];

        String logFolder = "src/logs/";
        createdName = new StringBuilder().append(logFolder).append(createdName).toString();

        String finalFileName;

        int i = 0;
        while (true) {
            finalFileName = new StringBuilder().append(createdName).append(" months (").append(i).append(")").append(".log").toString();
            if (!new File(finalFileName).exists()) break;
            i++;
        }

        Path logFolderPath = Path.of(logFolder);

        if (!Files.isDirectory(logFolderPath)) {
            Files.createDirectory(logFolderPath);
        }

        File finalFile = new File(finalFileName);

        File originalFile = file;
        File tempFile = new File(originalFile + ".tmp");
        ArrayList<String> allLinesAfterBackup = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(originalFile));
             PrintWriter pw = new PrintWriter(new FileWriter(tempFile))) {
            int currentLine = 0;

            for (String data; (data = in.readLine()) != null; ) {
                if (currentLine > backupFileLines) {
                    allLinesAfterBackup.add(data);
                }
                currentLine++;
            }

            if (!Files.isDirectory(logFolderPath))
            {
                Files.createDirectory(logFolderPath);
            }

            FileWriter writer = new FileWriter(finalFile);
            for (String str : allLinesAfterBackup) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
        }
    }
}
