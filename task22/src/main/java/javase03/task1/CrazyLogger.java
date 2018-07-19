package javase03.task1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CrazyLogger {
    private StringBuilder builder;

    private CrazyLogger() {
        this.builder = new StringBuilder();
    }

    private void Log(String log) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY ':' HH:mm ");
        builder.append(dateFormat.format(new Date())).append("- ").append(log).append("\n");
    }

    private void printLog(){
        System.out.println(builder);
    }

    private StringBuilder getLog(String logMessage) {
        StringBuilder result = new StringBuilder();
        String[] messages = builder.toString().split("\n");
        for (String message : messages)
            if (message.contains(logMessage))
                result.append(message).append("\n");
        return result;
    }

    public static void main(String[] args) {
        CrazyLogger logger = new CrazyLogger();
        logger.Log("err");
        logger.Log("err2");
        logger.Log("err33");
        logger.printLog();
        System.out.println(logger.getLog("20.06.2018"));
    }

}
