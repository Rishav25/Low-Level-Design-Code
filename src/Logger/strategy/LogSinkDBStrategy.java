package strategy;

import models.Log;

public class LogSinkDBStrategy implements LogSinkStrategy {
    @Override
    public void printLog(Log log) {
        String logString = log.getMessage() + " " + log.getTimeStamp();
        System.out.println("DB : " + logString);
    }
}
