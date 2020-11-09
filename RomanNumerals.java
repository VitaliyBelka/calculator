import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum RomanNumerals {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100);

    private final int value;

    RomanNumerals(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<RomanNumerals> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumerals e) -> e.value).reversed())
                .collect(Collectors.toList());
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input;
        if (romanNumeral.isEmpty())
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        int result = 0;

        List<RomanNumerals> romanNumerals = RomanNumerals.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumerals symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }
        return result;
    }

    public static String arabicToRoman(int number) {
        boolean checkPos = false;
        if (number < 0) {
            number = -number;
            checkPos = true;
        }
        if (number > 100) {
            throw new IllegalArgumentException(number + " is not in range 1 - 100");
        }

        List<RomanNumerals> romanNumerals = RomanNumerals.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumerals currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        if (checkPos)
            return "-" + sb.toString();
        else
            return sb.toString();
    }
}