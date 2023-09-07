package ru.job4j.fool;

import java.util.Scanner;

public class Fool {

    public static boolean answerCheck(String out, Scanner io) {
        boolean success = true;
        String answer = io.nextLine();
        if (!out.equals(answer)) {
            System.out.println("Ошибка. Начинай снова.");
            success = false;
        }
        return success;
    }

    static class OutFormat {
        public static String formatting(int num) {
            String out = String.format("%s%s",
                    num % 3 == 0 ? "Fizz" : "",
                    num % 5 == 0 ? "Buzz" : "");
            return out.isEmpty() ? String.valueOf(num) : out;
        }
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        int startAt = 1;
        boolean turn = false;
        Scanner io = new Scanner(System.in);
        while (startAt < 100) {
            String out = Fool.OutFormat.formatting(startAt);
            if (turn) {
                startAt = answerCheck(out, io) ? startAt : 0;
            } else {
                System.out.println(out);
            }
            turn = !turn;
            startAt++;
        }
    }
}