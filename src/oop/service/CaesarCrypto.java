package oop.service;

import oop.Abc;
import oop.dao.FileDataDao;

public class CaesarCrypto implements Cryptor {

    FileDataDao fileDataDao = new FileDataDao();
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцшщъыьэюя,.?!-\";:АБВГДЕЁЖЗИКЛМНОПРСТУФХЦШЩЪЫЬЭЮЯ";


    @Override
    public String encrypt(String incomingFile, int key, String path) {
        char[] chars = incomingFile.toCharArray();
        char[] copyChars = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            int alphabetCharPosition = ALPHABET.indexOf(chars[i]);
            if (alphabetCharPosition >= 0) if (alphabetCharPosition + key <= ALPHABET.length())
                if (alphabetCharPosition + key == ALPHABET.length()) copyChars[i] = ALPHABET.charAt(0);
                else {
                    copyChars[i] = ALPHABET.charAt(alphabetCharPosition + key);
                }
            else {
                copyChars[i] = ALPHABET.charAt(alphabetCharPosition + key - ALPHABET.length());
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
