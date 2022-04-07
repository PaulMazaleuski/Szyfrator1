package oop.service;
import oop.Abc;
import oop.controller.ConsoleColored;
import java.util.HashMap;
import java.util.Map;


public class CaesarBruteForce implements BruteForce{
Logger logger = new Logger();


    @Override
    public void decryptBruteForce(String incomingFile, String path) {

        Map<Integer, String> searchResults = new HashMap<>();
        Cryptor cryptor = new CaesarCrypto();
        if (incomingFile == null || incomingFile.length() <= 0) {
            return;
        }
        int key = 0;
        boolean stop = false;
        while (!stop) {
            if (key != 0 && key - Abc.getCharacterList().size() == 0) { stop = true; }
            String encData = cryptor.decrypt(incomingFile, key,path);
            if (isBrutForceDone(encData)) { searchResults.put(key, encData);
            }
            if (!stop)
                key++;
        }
        if (searchResults.size() > 0) {
            showBrutForceResults(logger, searchResults);
        }
    }


    private boolean isBrutForceDone(String dataForAnalyze) {
        String data = dataForAnalyze.toUpperCase();
        if (data.endsWith(":") || data.endsWith(",") || data.endsWith("-"))
            return false;
        String[] splitString = data.split("\\s");
        int countOfRealWords = 0;

        for (String s : splitString) {
            if (!s.equals("")) {
                String additionalCh = Abc.containsAdditional(s);
                if (additionalCh != null) {
                    if (failResult(countOfRealWords, s)) { return false;}
                    if (!s.startsWith(additionalCh)) if (s.endsWith(additionalCh)) {
                        String[] lastSplit = s.split("\\s" + additionalCh);
                        if (lastSplit.length == 1 && Abc.containsAdditional(lastSplit[0]) == null) countOfRealWords++;
                        else countOfRealWords--;
                    } else if (Abc.containsAdditionalCount(s) != 1 || !s.contains("-")) {
                        countOfRealWords--;
                    } else { countOfRealWords++;
                    }
                    else { countOfRealWords--;
                    }
                } else countOfRealWords++;
            }
        }
        return countOfRealWords > 0;
    }


    private boolean failResult(int countOfRealWords, String s) {
        if (!Abc.startWithAdditional(s) && Abc.containsAdditionalCount(s) > 2 && !s.equals("-")) {
            return true;
        } else return Abc.startWithAdditional(s) && countOfRealWords <= 0;
    }


    private void showBrutForceResults(Logger logger, Map<Integer, String> searchResults) {
        StringBuilder stringBuilder = new StringBuilder();
        logger.Info(searchResults.size() > 1 ? "POSSIBLE KEYS: " : "POSSIBLE KEY: ", ConsoleColored.PURPLE);
        StringBuilder keysString = new StringBuilder();
        for (Map.Entry<Integer, String> entry : searchResults.entrySet()) {
            keysString.append(entry.getKey()).append(", ");
            stringBuilder.append("key '").append(entry.getKey()).append("':\n\n");
            stringBuilder.append(entry.getValue()).append("\n\n");
        }
        logger.Info(keysString.substring(0, keysString.length() - 2), ConsoleColored.PURPLE);
    }
}
