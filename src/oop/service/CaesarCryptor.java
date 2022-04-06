package oop.service;

import oop.dao.FileDataDao;

import java.util.stream.IntStream;

public class CaesarCryptor implements Cryptor {

    FileDataDao fileDataDao = new FileDataDao();
    private static String ALPHABET = "абвгдеёжзийклмнопрстуфхцшщъыьэюя,.?!-\";:АБВГДЕЁЖЗИКЛМНОПРСТУФХЦШЩЪЫЬЭЮЯ";


    @Override
    public void encrypt(String incomingFile, int key, String path) {
        char[] chars = incomingFile.toCharArray();
        char[] copyChars = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            int alphabetCharPosition = ALPHABET.indexOf(chars[i]);
            if (alphabetCharPosition >= 0) if (alphabetCharPosition + key > ALPHABET.length())
                copyChars[i] = ALPHABET.charAt(alphabetCharPosition + key - ALPHABET.length());
            else if (alphabetCharPosition + key == ALPHABET.length()) copyChars[i] = ALPHABET.charAt(0);
            else {
                copyChars[i] = ALPHABET.charAt(alphabetCharPosition + key);
            }
            else copyChars[i] = chars[i];
            fileDataDao.writeData(new String(copyChars), key, path);
        }
    }


    @Override
    public void decrypt(String incomingFile, int key, String path) {
        char[] chars = incomingFile.toCharArray();
        char[] fullChars = new char[chars.length];
        IntStream.range(0, chars.length).forEach(i -> {
            int alphabetCharPosition = ALPHABET.indexOf(chars[i]);
            if (alphabetCharPosition >= 0) {
                if (alphabetCharPosition - key > 0) {
                    fullChars[i] = ALPHABET.charAt(alphabetCharPosition - key + ALPHABET.length());
                } else if (alphabetCharPosition + key == ALPHABET.length()) {
                    fullChars[i] = ALPHABET.charAt(0);
                } else {
                    fullChars[i] = ALPHABET.charAt(alphabetCharPosition - key);
                }
            } else {
                fullChars[i] = chars[i];
            }
            fileDataDao.writeData(new String(fullChars), key, path);
        });
    }
}
