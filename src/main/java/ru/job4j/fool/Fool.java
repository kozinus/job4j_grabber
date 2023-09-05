package ru.job4j.fool;

import java.util.Scanner;

public class Fool {

    public boolean isDivByInt(int num, int div) {
        return num % div == 0;
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        Fool fool = new Fool();
        int startAt = 1;
        Scanner io = new Scanner(System.in);
        while (startAt < 100) {
            String out = String.format("%s%s",
                    fool.isDivByInt(startAt, 3) ? "Fizz" : "",
                    fool.isDivByInt(startAt, 5) ? "Buzz" : "");
            out = out.isEmpty() ? String.valueOf(startAt) : out;
            String answer = io.nextLine();
            if (!out.equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }
}