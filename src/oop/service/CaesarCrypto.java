package oop.service;

import oop.dao.FileDataDao;

import java.util.stream.IntStream;

public class CaesarCrypto implements Cryptor {

    FileDataDao fileDataDao = new FileDataDao();
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцшщъыьэюя,.?!-\";:АБВГДЕЁЖЗИКЛМНОПРСТУФХЦШЩЪЫЬЭЮЯ";


    @Override
    public String encrypt(String incomingFile, int key, String path) {
        char[] chars = incomingFile.toCharArray();
        char[] copyChars = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            int positionAbc = ALPHABET.indexOf(chars[i]);
            if (positionAbc >= 0) if (positionAbc + key <= ALPHABET.length())
                if (positionAbc + key == ALPHABET.length()) copyChars[i] = ALPHABET.charAt(0);
                else {
                    copyChars[i] = ALPHABET.charAt(positionAbc + key);
                }
            else {
                copyChars[i] = ALPHABET.charAt(positionAbc + key - ALPHABET.length());
            }
            else copyChars[i] = chars[i];
            fileDataDao.writeData(new String(copyChars), key, path);
        }
        return incomingFile;

    }

    @Override
    public String decrypt(String incomingFile, int key, String path) {

       return encrypt(incomingFile, -key, path);
    }
}
