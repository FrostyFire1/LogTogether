package io.github.frostyfire1.logtogether;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class LogTogetherLogger {

    public static final File logFile = new File("logs/LogTogether.log");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final FileWriter fileWriter;
    static {
        try {
            fileWriter = new FileWriter(logFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void info(String info) {
        String timestamp = ZonedDateTime.now(java.time.ZoneOffset.UTC)
            .format(formatter);
        try {
            fileWriter.write(String.format("[%s] %s\n", timestamp, info));
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
