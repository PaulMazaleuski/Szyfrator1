package oop;

import oop.controller.ConsoleController;
import oop.dao.DataDao;
import oop.dao.FileDataDao;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ConsoleController controller = new ConsoleController();
        controller.printMainMenu();
    }
}
