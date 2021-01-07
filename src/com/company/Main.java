package com.company;

import jdk.dynalink.linker.ConversionComparator;

import java.util.Scanner;
import java.util.concurrent.ConcurrentNavigableMap;

/*Если ввести неправильно, а потом, не стирая, отредактировать ввод,
 * то сканер выдаст ошибку. Почему?*/
public class Main {
    public static void main(String[] args) {

      Scanner sc = new Scanner(System.in);
      System.out.println("Введите выражение \"+, -, *, /\" " +
              "с двумя операндами, с пробелами: ");
      String src = sc.nextLine().trim();
      if(src.charAt(0) > 48 && src.charAt(0) < 58) {
          ArabicNumerals arab = new ArabicNumerals();
          arab.calc(src);
      }
      else {
          RomanNumerals rom = new RomanNumerals();
          rom.calc(src);
      }
    }
}


