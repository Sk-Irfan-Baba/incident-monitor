package com.monitor.log;

import com.monitor.db.IncidentDAO;

import java.io.*;

public class LogScanner {

    public static void scan(String path) {
        try (BufferedReader reader =
                     new BufferedReader(new FileReader(path))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("ERROR") || line.contains("FATAL")) {
                    IncidentDAO.save("LOG_ERROR", line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
