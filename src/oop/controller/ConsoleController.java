package oop.controller;

import oop.dao.DataDao;
import oop.dao.FileDataDao;
import oop.service.CaesarBruteForce;
import oop.service.CaesarCrypto;
import oop.service.Cryptor;
import java.util.Scanner;

public class ConsoleController {

    public static final int SYMBOLS_COUNT = 25;
    public static final String APPLICATION_NAME = "CRYPTOR";
    private final DataDao dao = new FileDataDao();
    private final Cryptor cryptor = new CaesarCrypto();
    private final CaesarBruteForce caesarBruteForce = new CaesarBruteForce();


    public void printMainMenu() {
        printHeader();
        makeChoice();
    }

    private void makeChoice() {
        do{ printMainChoiceMenu();
            Scanner scanner = new Scanner(System.in);
            int choice  = scanner.nextInt();
            if (isExitCodeChoose(choice)) break;

            callChoice(choice);
        } while (true);
    }

    private void callChoice(int choice){
        switch (choice) {
            case 1:
                encryptData();
                break;
            case 2:
                decryptData();
                break;
            case 3:
                bruteForce();
            default:
                printColoredText("Make correct choice", ConsoleColored.RED);

        }
    }

    public void encryptData() {
        String path = getPathToFile();
        int key = getKey();
        String incomingFile = dao.getData(path);
        cryptor.encrypt(incomingFile, key, path);
    }

    private void decryptData() {
        String path = getPathToFile();
        int key = getKey();
        String incomingFile = dao.getData(path);
        cryptor.decrypt(incomingFile, key, path);
    }

    private void bruteForce() {
        String path = getPathToFile();
        String incomingFile = dao.getData(path);
        caesarBruteForce.decryptBruteForce(incomingFile, path);
    }

    private String getPathToFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleColored.GREEN + "Absolut path to file:");
        return scanner.nextLine();
    }

    private int getKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(ConsoleColored.GREEN + "Enter the key:" );
        int keyData = 0;
        try{
            keyData = scanner.nextInt();
        } catch (Exception e) {
            System.out.println(ConsoleColored.RED + "Incorrect key.");
        }
        return keyData;
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
        printColoredText("  Make your choice", ConsoleColored.GREEN);
        printColoredText("\t1: Encrypt file", ConsoleColored.PURPLE);
        printColoredText("\t2: Decrypt file", ConsoleColored.PURPLE);
        printColoredText("\t3: BruteForce", ConsoleColored.PURPLE);
        System.out.println();
        printColoredText("\t0: Exit", ConsoleColored.CYAN);
    }

    private void printHeader() {
        printStars();
        System.out.println(ConsoleColored.RED + "\t\t" + APPLICATION_NAME + ConsoleColored.RESET);
        printStars();
    }

    private void printStars(){
        for (int i = 0; i < SYMBOLS_COUNT; i++) {
            System.out.print(ConsoleColored.YELLOW + "*");
        }
        System.out.println();
    }
    private void printColoredText(String text, String color) {
        System.out.println(color + text + ConsoleColored.RESET);
    }
}
