public class Calculator {
    EnteringExample enteringExample = new EnteringExample();

    void calculation() {
        enteringExample.start();
        String operation = enteringExample.getList().get(0);
        int number1 = Integer.parseInt(enteringExample.getList().get(1));
        int number2 = Integer.parseInt(enteringExample.getList().get(2));
        boolean check = enteringExample.isCheckRoman();

        int sum = switch (operation) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            default -> number1 / number2;
        };

        if (check && sum == 0)
            System.out.println("N");
        else if (check)
            System.out.println(RomanNumeral.arabicToRoman(sum));
        else
            System.out.println(sum);
    }
}