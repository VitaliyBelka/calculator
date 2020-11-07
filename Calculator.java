import java.util.ArrayList;

public class Calculator {
    public static void main(String[] args) {
        InputExample example = new InputExample();
        example.start();
        ArrayList<String> list = example.list;
        String operation = list.get(0);
        int num1 = Integer.parseInt(list.get(1));
        int num2 = Integer.parseInt(list.get(2));
        int sum;
        boolean check = example.check;

        if (operation.equals("+"))
            sum = num1 + num2;
        else if (operation.equals("-"))
            sum = num1 - num2;
        else if (operation.equals("*"))
            sum = num1 * num2;
        else
            sum = num1 / num2;

        if (check && sum == 0)
            System.out.println("N");
        else if (check)
            System.out.println(RomanNumerals.arabicToRoman(sum));
        else
            System.out.println(sum);
    }
}
