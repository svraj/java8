package com.tektrill.learning.java8.functional;

import java.util.Scanner;
import java.util.stream.IntStream;

public class MultiplicationTable {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        IntStream.rangeClosed(1, 10)
                .forEach(counter -> printIt(number,counter));

    }

    public static void printIt(int input, int counter){
        System.out.println(input +"*"+counter+"="+input*counter);
    }
}
