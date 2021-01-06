package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArabicNumerals implements CalculateOperations {

    @Override
    public void calc(String exp) {
        if (check(exp) == true) {
            List<Integer> digits = new ArrayList<Integer>();
            List<Character> signs = new ArrayList<Character>();
            fillLists(exp, digits, signs);
            int result = 0;
            switch (signs.get(0)) {
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
            System.out.println("Результат: " + result);
        } else {
            try {
                throw new WrongStringFormatException("Wrong string format");
            } catch (WrongStringFormatException e) {
                System.out.println("ошибочную строку\nОшибка:Неверный формат строки: " +
                        "Строка должна содержать только 2 операнда" +
                        "\nи 1 оператор из \"+, -, *, /\" между ними. Через пробелы.");
            }
            System.exit(0);
        }
    }

    /* Вопрос к методу check(). Почему, если убрать операции выводов символов,
     * то программа перестаёт второй раз заходить на проверку операнда? */
    @Override
    // проверяет правлильность формата строки
    public boolean check(String exp) {
        Scanner sc = new Scanner(exp);
        int scInt, i = 0;
        System.out.print("Вы ввели: ");
        while (sc.hasNext()) {
            if (i > 2)
                return false;
            if (sc.hasNextInt()){
                scInt = sc.nextInt();
                // проверка, начинается ли строка с числа
                // и идёт ли число после оператора
                if(i % 2 == 0 && scInt > 0 && scInt < 11){
                    System.out.print(scInt);
                }
                else {
                    try {
                        throw new OneToTenBoundsException("Bounds from 1 to 10");
                    } catch (OneToTenBoundsException e) {
                        System.out.println("большое или маленькое число" +
                                "\nОшибка: Должны использоваться числа от 1 до 10.");
                    }
                    System.out.println();
                    System.exit(0);
                }
            }
            // также проверка того, является ли вторым символом оператор
            else if ((sc.hasNext("\\+") || sc.hasNext("-")
                    || sc.hasNext("\\*") || sc.hasNext("/"))
                    && i % 2 == 1) {
                System.out.print(sc.next());
            }
            else{
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
    // Вносит значения из строки в списки чисел и знаков
    public void fillLists(String exp, List digits, List signs) {
        Scanner sc = new Scanner(exp);
        int i = 0;
        while (sc.hasNext()) {
            if (i % 2 == 0)
                digits.add(sc.nextInt());
            else
                signs.add(sc.next().charAt(0));
            i++;
        }
    }
}
