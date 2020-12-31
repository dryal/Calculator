package com.company;

import java.util.Scanner;

/*Если ввести неправильно, а потом, не стирая, отредактировать ввод,
 * то сканер выдаст ошибку. Почему?*/
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print("Вы будете работать с арабскими(1) " +
                    "или римскими(2) числами? Что угодно другое для выхода\n>>>> ");
            String src = sc.nextLine().trim();
            if(src.equals("1")){
                ArabicNumerals arab = new ArabicNumerals();
                System.out.println("Введите выражение \"+, -, *, /\" " +
                        "с двумя только арабскими\nоперандами, с пробелами: ");
                src = sc.nextLine().trim();
                arab.calc(src);
            }
            else if(src.equals("2")){
                System.out.println("Введите выражение \"+, -, *, /\" " +
                        "с двумя только римскими\nоперандами, с пробелами: ");
                RomanNumerals rom = new RomanNumerals();
                src = sc.nextLine().trim();
                rom.calc(src);
            }
            else {
                System.out.println("Завершение программы");
                return;
            }
        }
    }
}


