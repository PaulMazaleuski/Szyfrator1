package oop.service;

public interface Cryptor {

    void encrypt(String incomingFile, int key, String path);

    void decrypt(String incomingFile, int key, String path);

}
