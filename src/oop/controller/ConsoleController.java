package oop.controller;

import oop.dao.DataDao;
import oop.dao.FileDataDao;
import oop.service.CaesarBruteForce;
import oop.service.CaesarCryptor;
import oop.service.Cryptor;
import oop.service.Logger;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleController {

    public static final int SYMBOLS_COUNT = 25;
    public static final String APPLICATION_NAME = "CRYPTOR";
    private final Logger log = Logger.getInstance();
    private final DataDao dao = new FileDataDao();
    private final Cryptor cryptor = new CaesarCryptor();
    private CaesarBruteForce caesarBruteForce = new CaesarBruteForce();


    public void printMainMenu() throws IOException {
        printHeader();
        makeChoice();
    }

    private void makeChoice() throws IOException {
        do{
            printMainChoiceMenu();
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

    private void bruteForce() {
        String path = getPathToFile();
        String incomingFile = dao.getData(path);
        caesarBruteForce.decrypt(incomingFile, path);
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

    private String getPathToFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Path to file:");
        String path = scanner.nextLine();
        return path;
    }

    private int getKey() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the key:");
        int keyData = 0;
        try{
            keyData = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Incorrect key.");
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
        printColoredText("Make your choice", ConsoleColored.GREEN);
        printColoredText("1: Encrypt file", ConsoleColored.PURPLE);
        printColoredText("2: Decrypt file", ConsoleColored.PURPLE);
        printColoredText("3: BruteForce", ConsoleColored.PURPLE);
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
