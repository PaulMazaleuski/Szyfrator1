package oop.dao;

import java.io.IOException;

public interface DataDao {

    String getData(String dataName) throws IOException;
    void writeData(String dataName, String data);
}
