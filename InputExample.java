import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class InputExample {
    int[] arabic = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    String[] operations = {"+", "-", "*", "/"};
    String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String example;
    String[] example1;
    ArrayList<String> list = new ArrayList<>();
    boolean check;

    void start() {
        try {
            example = reader.readLine();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isOperations(example);
        isDoubleOperations(example);
        if (list.get(0) == null)
            throw new IllegalArgumentException(example + " unknown calculation operation");

        example1 = example.split("[ /*+-]");
        list.addAll(Arrays.asList(example1));
        list.removeAll(Arrays.asList("", null));
        if (isRomanNumber(list.get(1))) {
            String tmp = "" + RomanNumerals.romanToArabic(list.get(1));
            list.set(1, tmp);
            tmp = "" + RomanNumerals.romanToArabic(list.get(2));
            list.set(2, tmp);
            check = true;
        }
        if (list.size() != 3 ^ !isArabicNumber(list.get(1)) ^ !isArabicNumber(list.get(2)))
            throw new IllegalArgumentException(example + " unknown calculation operation");
    }

    boolean isArabicNumber(String s) {
        int a = Integer.parseInt(s);
        for (int i : arabic) {
            if (a == i)
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

    void isOperations(String s) {
        for (String c : operations)
            if (s.contains(c))
                list.add(c);
    }

    boolean isOper(String s) {
        for (String c : operations) {
            if (s.contains(c))
                return true;
        }
        return false;
    }

    boolean isDoubleOperations(String s) {
        char[] chars = s.toCharArray();
        int count = 0;
        for (char aChar : chars) {
            String tmp = "" + aChar;
            if (isOper(tmp))
                count++;
        }
        if (count == 1)
            return true;
        else
            throw new IllegalArgumentException(example + " unknown calculation operation");
    }
}