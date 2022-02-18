package com.purerangers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LogfileGenerator
{
    public static void splitLogfile(String name) throws IOException
    {
        String logFolder = "src/logs/";

        String[] filteredNames = name.replaceAll("\\.", "/").split("/");

        String createdName = filteredNames[filteredNames.length-1];

        createdName = new StringBuilder().append(logFolder).append(createdName).toString();

        File file = new File("logfile.log");
        String finalFileName;

        int i = 0;
        while (true)
        {
            finalFileName = new StringBuilder().append(createdName).append(" months (").append(i).append(")").append(".log").toString();
            if (!new File(finalFileName).exists()) break;
            System.out.println(createdName);
            i++;
        }

        Path path = Path.of(logFolder);

        if (!Files.isDirectory(path))
        {
            Files.createDirectory(path);
        }

        Files.copy(file.toPath(), Path.of(finalFileName));
    }
}
