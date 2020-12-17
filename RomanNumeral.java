import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100);

    private final int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<RomanNumeral> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing(RomanNumeral::getValue).reversed())
                .collect(Collectors.toList());

    }

    public static int romanToArabic(String s) {
        String roman = s;
        int result = 0;
        List<RomanNumeral> romanNumerals = getReverseSortedValues();

        for (int i = 0; i < romanNumerals.size(); i++) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (roman.startsWith(symbol.name())) {
                result += symbol.getValue();
                roman = roman.substring(symbol.name().length());
                i--;
            }
            if (roman.length() == 0) {
                break;
            }
        }

        return result;
    }

    public static String arabicToRoman(int arabic) {
        if (arabic == 0) {
            return "N";
        }

        boolean negative = false;
        if (arabic < 0) {
            arabic = -arabic;
            negative = true;
        }

        List<RomanNumeral> romanNumerals = getReverseSortedValues();
        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < romanNumerals.size(); i++) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (symbol.getValue() <= arabic) {
                roman.append(symbol.name());
                arabic -= symbol.getValue();
                i--;
            }
            if (arabic == 0) {
                break;
            }
        }

        if (negative) {
            return roman.insert(0, "-").toString();
        } else {
            return roman.toString();
        }
    }
}