package oop.dao;

import oop.service.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDataDao implements DataDao{

    private Logger log = Logger.getInstance();

    @Override
    public String getData(String incomingFile) {
        byte[] dataBytes = new byte[0];
        try {
            dataBytes = Files.readAllBytes(Path.of(incomingFile));
        } catch (IOException e) {
            log.Error(e.getMessage());
        }
        return new String(dataBytes);
    }

    @Override
    public void writeData(String incomingFile, int key, String path) {
       try{
           Files.write(Path.of(path), incomingFile.getBytes());
       } catch (IOException e) {
           log.Error(e.getMessage());
       }
    }
}
