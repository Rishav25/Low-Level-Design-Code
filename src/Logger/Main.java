import enums.LogType;
import models.Log;
import strategy.LogSinkConsoleStrategy;
import strategy.LogSinkDBStrategy;
import strategy.LogSinkFileStrategy;

public class Main {
    public static void main(String[] args) {
        LoggerService ls = new LoggerService(null);
        ls.addLog(new Log(LogType.DEBUG, "This is Debug Log 1"));
        ls.addLog(new Log(LogType.ERROR, "This is Error Log 1"));
        ls.addLog(new Log(LogType.ERROR, "This is Error Log 2"));
        ls.addLog(new Log(LogType.DEBUG, "This is Debug Log 2"));
        ls.addLog(new Log(LogType.INFO, "This is Info Log 1"));
        ls.addLog(new Log(LogType.INFO, "This is Info Log 1"));
        ls.addLog(new Log(LogType.DEBUG, "This is Debug Log 3"));
        ls.addLog(new Log(LogType.INFO, "This is Info Log 3"));
        ls.addLog(new Log(LogType.DEBUG, "This is Debug Log 4"));

        ls.printLogs(LogType.ERROR, new LogSinkConsoleStrategy());
        ls.printLogs(LogType.INFO, new LogSinkDBStrategy());
        ls.printLogs(LogType.INFO, new LogSinkFileStrategy());
        ls.printLogs(LogType.DEBUG, new LogSinkDBStrategy());
        ls.printLogs(LogType.ERROR, new LogSinkFileStrategy());
        ls.printLogs(LogType.DEBUG, new LogSinkConsoleStrategy());
    }
}