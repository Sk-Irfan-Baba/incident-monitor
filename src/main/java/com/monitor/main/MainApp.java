package com.monitor.main;

import com.monitor.api.ApiHealthChecker;
import com.monitor.cli.ReportCLI;
import com.monitor.log.LogScanner;

public class MainApp {

    public static void main(String[] args) {
        LogScanner.scan("app.log");
        ApiHealthChecker.check("https://httpstat.us/500");
        ReportCLI.run();
    }
}
