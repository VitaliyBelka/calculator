import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class EnteringExample {
    private final int[] arabic = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    private final String[] operations = {"+", "-", "*", "/"};
    private final String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    private String expression;
    private final ArrayList<String> list = new ArrayList<>();
    private boolean checkRoman;

    public ArrayList<String> getList() {
        return list;
    }

    public boolean isCheckRoman() {
        return checkRoman;
    }

    void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            expression = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addOperationToList(expression);
        checkDoubleOperations(expression);

        if (list.get(0) == null)
            throw new IllegalArgumentException(expression + " unknown calculation operation");

        String[] brokenExample = expression.split("[/*+-]");
        for (int i = 0; i < brokenExample.length; i++) {
            brokenExample[i] = brokenExample[i].replaceAll("\\s+", "");
        }
        list.addAll(Arrays.asList(brokenExample));
        list.removeAll(Arrays.asList("", null));

        if (isRomanNumber(list.get(1))) {
            String tmp = "" + RomanNumeral.romanToArabic(list.get(1));
            list.set(1, tmp);
            tmp = "" + RomanNumeral.romanToArabic(list.get(2));
            list.set(2, tmp);
            checkRoman = true;
        }

        if (list.size() != 3 ^ isArabicNumber(list.get(1)) ^ isArabicNumber(list.get(2)))
            throw new IllegalArgumentException(expression + " unknown calculation operation");
    }

    void addOperationToList(String s) {
        for (String c : operations)
            if (s.contains(c))
                list.add(c);
    }

    void checkDoubleOperations(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (char aChar : chars) {
            String tmp = "" + aChar;
            if (checkOperation(tmp))
                count++;
        }
        if (count != 1)
            throw new IllegalArgumentException(expression + " unknown calculation operation");
    }

    boolean checkOperation(String s) {
        for (String c : operations) {
            if (s.contains(c))
                return true;
        }
        return false;
    }

    boolean isRomanNumber(String s) {
        for (String s1 : roman) {
            if (s.equals(s1))
                return true;
        }
        return false;
    }

    boolean isArabicNumber(String s) {
        int a = Integer.parseInt(s);
        for (int i : arabic) {
            if (a == i)
                return false;
        }
        return true;
    }
}