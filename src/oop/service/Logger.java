package oop.service;

import oop.controller.ConsoleColored;

public class Logger {

    private static Logger logger;

    public Logger() {

    }
    public static Logger getInstance() {
        if(logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void Info(String text, String green){
        System.out.println(ConsoleColored.GREEN + ": " + text + ConsoleColored.RESET);

    }

    public void Error(String text) {
        System.out.println(ConsoleColored.RED + "Please, enter absolute path: " + text + ConsoleColored.RESET);
    }
}
