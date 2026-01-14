package strategy;

import models.Log;

public interface LogSinkStrategy {
    public void printLog(Log log);
}
