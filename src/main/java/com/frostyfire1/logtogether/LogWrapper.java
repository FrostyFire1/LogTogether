package com.frostyfire1.logtogether;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LogWrapper {

    public static final File logFile = new File("logs/LogTogether.log");
    public static final FileWriter fileWriter;
    static {
        try {
            fileWriter = new FileWriter(logFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void info(String info) throws IOException {
        LogTogether.LOG.info(info);
        fileWriter.write(info);
    }
}
