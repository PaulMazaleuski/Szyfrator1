package oop.dao;

public interface DataDao {

    String getData(String incomingFile);
    void writeData(String incomingFile, int key, String path);

}
