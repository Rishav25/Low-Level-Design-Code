package models;

import java.time.Instant;

import enums.LogType;

public class Log {
    LogType logType;
    String message;
    String timestamp;

    public Log(LogType logType, String message) {
        this.logType = logType;
        this.message = message;
        this.timestamp = Instant.now().plusSeconds((long) (Math.random() * 3000)).toString();
    }

    public LogType getLogType() {
        return this.logType;
    }

    public String getMessage() {
        return this.message;
    }

    public String getTimeStamp() {
        return this.timestamp;
    }
}
