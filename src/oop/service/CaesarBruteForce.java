package oop.service;

public class CaesarBruteForce implements BruteForce{
private final Logger logger = new Logger();


    @Override
    public void decryptBruteForce(String incomingFile, String path) {
        char[] inFile = incomingFile.toCharArray();
        int key;
        for(key=1; key<10; key++)
            {
                for(int i=0;i<inFile.length;i++)
                    if (inFile[i] == ' ') {
                    }
                    else {
                        if (inFile[i] >= 'А' && inFile[i] <= 'Я') {
                            inFile[i] = (char) (inFile[i] - key);
                            if (inFile[i] < 'А') {
                                inFile[i] = (char) (inFile[i] + 33);
                            }
                        } else {
                            inFile[i] = (char) (inFile[i] - key);
                            if (inFile[i] < 'а') {
                                inFile[i] = (char) (inFile[i] + 33);
                            }
                        }
                    }
                System.out.println("Key = " + key + " Decrypted String : " + String.valueOf(inFile));
                inFile = incomingFile.toCharArray();
            }
        }

    }

