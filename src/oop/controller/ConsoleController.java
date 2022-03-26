package oop.controller;

import oop.dao.DataDao;
import oop.dao.FileDataDao;
import oop.service.CaesarCryptor;
import oop.service.Cryptor;
import oop.service.Logger;

import java.util.Scanner;

public class ConsoleController {

    public static final int SYMBOLS_COUNT = 25;
    public static final String APPLICATION_NAME = "CRYPTOR";
    private Logger log = Logger.getInstance();

    private DataDao dao = new FileDataDao();
    private Cryptor cryptor = new CaesarCryptor();

    public void printMainMenu() {
        printHeader();

        makeChoice();
    }

    private void makeChoice() {
        do{
            printMainChoiceMenu();
            Scanner scanner = new Scanner(System.in);
            int choice  = scanner.nextInt();
            if (isExitCodeChoose(choice)) break;

            callChoice(choice);
        } while (true);
    }

    private void callChoice(int choice) {
        switch (choice) {
            case 1:
                log.logInfo("Called 1");
                break;
            case 2:
                log.logInfo("Called 2");
                break;
            default: printColoredText("Make correct choice", ConsoleColored.RED);

        }
    }

    private boolean isExitCodeChoose(int choise) {
        if (choise == 0) {
            printColoredText("GoodBye", ConsoleColored.BLUE);
            return true;
        }
        return false;
    }

    private void printMainChoiceMenu() {
        System.out.println();
        printColoredText("Make your choice", ConsoleColored.GREEN);
        printColoredText("1: Encrypt file", ConsoleColored.YELLOW_BOLD);
        printColoredText("2: Decrypt file", ConsoleColored.PURPLE);
        System.out.println();
        printColoredText("0: Exit", ConsoleColored.CYAN);
    }

    private void printHeader() {
        printStars();
        System.out.println(ConsoleColored.RED + "\t\t" + APPLICATION_NAME + ConsoleColored.RESET);
        printStars();
    }

    private void printStars(){
        for (int i = 0; i < SYMBOLS_COUNT; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
    private void printColoredText(String text, String color) {
        System.out.println(color + text + ConsoleColored.RESET);
    }
}
