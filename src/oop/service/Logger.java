package oop.service;

import oop.controller.ConsoleColored;

public class Logger {

    private static Logger logger;

    private Logger() {

    }
    public static Logger getInstance() {
        if(logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    public void Info(String text){
        System.out.println(ConsoleColored.GREEN + ": " + text + ConsoleColored.RESET);

    }

    public void Error(String text) {
        System.out.println(ConsoleColored.RED + ": " + text + ConsoleColored.RESET);
    }
}
