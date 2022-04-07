package oop.service;

public interface Cryptor {

    String encrypt(String incomingFile, int key, String path);

    String decrypt(String incomingFile, int key, String path);

}
