package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

enum Rome {
    I(1), II(2), III(3), IV(4), V(5),
    VI(6), VII(7), VIII(8), IX(9), X(10),
    L(50), C(100);
    private int value;

    Rome(int value) {
        this.value = value;
    }

    // сверяем поступивший элемент с перечисляемыми
    static boolean rCheck(String src) {
        for (Rome i : Rome.values()) {
            if (i.name().equals(src))
                return true;
        }
        return false;
    }

    static int convertToInt(String src) {
        for (Rome i : Rome.values()) {
            if (i.name().equals(src))
                return i.value;
        }
        return -1;
    }

    static String convertFromInt(int src) {
        String result = "";
        int count, tmp = src;
        // смотрим наличие сотни и полусотни
        if(src / 50 != 0){
            count = src / 50;
            src -= 50 * count;
            if(count == 2)
                result = result.concat(C.name());
            else
                result = result.concat(L.name());
        }
        if (src == 0) return result;
        // смотрим, сколько раз встречается 10
        if (src / 10 != 0) {
            count = src / 10;
            src -= 10 * count;
            // проверка остатка на ровно 40
            if(L.value - 10 * count == 10 && result.equals("L")) {
                result = "";
                result = result.concat(X.name()) + result.concat(C.name());
            }
            else if(L.value - 10 * count == 10 && result.equals("")) {
                result = "";
                result = result.concat(X.name()) + result.concat(L.name());
            }
            else {
                for (int i = 0; i < count; i++) {
                    result = result.concat(X.name());
                }
            }
        }
        if(src == 0) return result;
        // смотрим, встречается ли 5
        if (src % 5 == 0) {
            result = result.concat(V.name());
            src -= 5;
        }
        else if(src < 5) {
            switch (src) {
                case 1:
                    result = result.concat(I.name());
                    break;
                case 2:
                    result = result.concat(II.name());
                    break;
                case 3:
                    result = result.concat(III.name());
                    break;
                case 4:
                    result = result.concat(IV.name());
                    break;
            }
        }
        else if(src > 5){
            switch (src){
                case 6:
                    result = result.concat(VI.name());
                    break;
                case 7:
                    result = result.concat(VII.name());
                    break;
                case 8:
                    result = result.concat(VIII.name());
                    break;
                case 9:
                    result = result.concat(IX.name());
                    break;
            }
        }
        return result;
    }

}

public class RomanNumerals  implements CalculateOperations{

    @Override
    public void calc(String exp) {
        if (check(exp) == true) {
            List<Integer> digits = new ArrayList<Integer>();
            List<Character> signs = new ArrayList<Character>();
            fillLists(exp, digits, signs);
            int result = 0;
            switch(signs.get(0)){
                case '+':
                    result = digits.get(0) + digits.get(1);
                    break;
                case '-':
                    result = digits.get(0) - digits.get(1);
                    break;
                case '*':
                    result = digits.get(0) * digits.get(1);
                    break;
                case '/':
                    result = digits.get(0) / digits.get(1);
                    break;
            }
            System.out.println("Результат: " + Rome.convertFromInt(result));
        }
        else {
            try {
                throw new WrongStringFormatException("Wrong string format");
            } catch (WrongStringFormatException e) {
                System.out.println("ошибочную строку\nОшибка: " +
                        "Неверный формат строки: Строка должна содержать только 2 операнда" +
                        "\nи 1 оператор из \"+, -, *, /\" между ними. Через пробелы.");
            }
            System.exit(0);
        }
    }

    @Override
    public boolean check(String exp) {
        System.out.print("Вы ввели: ");
        Scanner sc = new Scanner(exp);
        int i = 0;
        String nextElement = "";
        while (sc.hasNext()) {
            nextElement = sc.next();
            if(i > 2)
                return false;
            if (Rome.rCheck(nextElement) && i % 2 == 0)
                System.out.print(nextElement);
            else if ((nextElement.equals("+") || nextElement.equals("-")
                    || nextElement.equals("*") || nextElement.equals("/"))
                    && i % 2 == 1) {
                System.out.print(nextElement);
            }
            else {
                System.out.println();
                return false;
            }
            System.out.print(" ");
            i++;
        }
        System.out.println();
        return true;
    }

    @Override
    public void fillLists(String exp, List digits, List signs) {
        Scanner sc = new Scanner(exp);
        int i = 0;
        String nextElement = "";
        while (sc.hasNext()) {
            nextElement = sc.next();
            if (i % 2 == 0)
                digits.add(Rome.convertToInt(nextElement));
            else
                signs.add(nextElement.charAt(0));
            i++;
        }
    }
}
