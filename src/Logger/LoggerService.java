import enums.LogType;
import java.util.ArrayList;
import java.util.List;
import models.Log;
import strategy.LogSinkStrategy;

public class LoggerService {
    List<Log> logList;

    public LoggerService(List<Log> logList) {
        if (logList != null)
            this.logList = logList;
        else
            this.logList = new ArrayList<>();
    }

    public void addLog(Log log) {
        logList.add(log);
    }

    public void printLogs(LogType logLevel, LogSinkStrategy logSinkStrategy) {
        int logLevelOrdinal = logLevel.ordinal();
        if (logList != null && !logList.isEmpty()) {
            System.out.println("----------------------------------------------");
            for (Log log : logList) {
                if (log.getLogType().ordinal() <= logLevelOrdinal) {
                    logSinkStrategy.printLog(log);
                }
            }
            System.out.println("----------------------------------------------\n");
        }
    }
}
