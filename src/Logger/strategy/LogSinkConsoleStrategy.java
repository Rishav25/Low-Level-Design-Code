package strategy;

import models.Log;

public class LogSinkConsoleStrategy implements LogSinkStrategy {
    @Override
    public void printLog(Log log) {
        String logString = log.getMessage() + " " + log.getTimeStamp();
        System.out.println("Console : " + logString);
    }
}
