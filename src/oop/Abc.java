package oop;

import java.util.*;

public class Abc {
    private static final Character FROM_CHAR = 'А';
    private static final Character TO_CHAR = 'Я';
    private static final char[] ADDITIONAL_CHARS =  {'.', ',', ':', '-', '!', '?', ' ', '\n'};


    private static final List<Character> CHARACTER_LIST = new ArrayList<>();
    private static final Map<Character, Integer> MAP_ALPHABET = new HashMap<>();

    static {
        initAlphabet();
    }

    private static void initAlphabet() {
        int charIndexCounter = 0;
        int i = FROM_CHAR;
        while (i <= TO_CHAR) {
            MAP_ALPHABET.put((char) i, charIndexCounter++);
            CHARACTER_LIST.add((char) i);
            i++;
        }
        for (Character symbol : ADDITIONAL_CHARS) {
            MAP_ALPHABET.put(symbol, charIndexCounter++);
            CHARACTER_LIST.add(symbol);
        }
    }

    public static char getChar(char currentChar, int offset) {
        if (getCharIndex(currentChar) == -1) {
            return currentChar;
        }
        int realOffset = -getCharIndex(currentChar)-offset;
        Collections.rotate(CHARACTER_LIST, -getCharIndex(currentChar)-offset);
        char result = CHARACTER_LIST.get(0);
        Collections.rotate(CHARACTER_LIST, -realOffset);
        return result;
    }

    public static int getCharIndex(char c) {
        c = charToUpperCase(c);
        Integer result = MAP_ALPHABET.get(c);
        if (result == null)
            result = -1;
        return result;
    }

    private static char charToUpperCase(char currentChar) {
        String upperCaseChar = "" + currentChar;
        upperCaseChar = upperCaseChar.toUpperCase();
        currentChar = upperCaseChar.charAt(0);
        return currentChar;
    }

    public static String containsAdditional(String str) {
        String result = null;
        for (char ch : ADDITIONAL_CHARS) {
            if (str.contains(ch + "")) {
                result = ch + "";
                break;
            }
        }
        return result;
    }

    public static int containsAdditionalCount(String str) {
        int result = 0;
        for (char ch : ADDITIONAL_CHARS) {
            if (str.contains(ch + "")) {
                result++;
            }
        }
        return result;
    }

    public static boolean startWithAdditional(String str) {
        boolean result = false;
        for (char ch : Abc.ADDITIONAL_CHARS) {
            if (str.startsWith(ch + "")) {
                result = true;
                break;
            }
        }
        return result;
    }
    public static List<Character> getCharacterList() {
        return CHARACTER_LIST;
    }

}

