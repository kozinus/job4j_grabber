package ru.job4j.fool;

import java.util.Scanner;

public class Fool {

    private boolean isDivByInt(int num, int div) {
        return num % div == 0;
    }

    private boolean answerCheck(String out, Scanner io) {
        boolean success = true;
        String answer = io.nextLine();
        if (!out.equals(answer)) {
            System.out.println("Ошибка. Начинай снова.");
            success = false;
        }
        return success;
    }

    public void init() {
        System.out.println("Игра FizzBuzz.");
        int startAt = 1;
        boolean turn = false;
        Scanner io = new Scanner(System.in);
        while (startAt < 100) {
            String out = String.format("%s%s",
                    this.isDivByInt(startAt, 3) ? "Fizz" : "",
                    this.isDivByInt(startAt, 5) ? "Buzz" : "");
            out = out.isEmpty() ? String.valueOf(startAt) : out;
            if (turn) {
                startAt = answerCheck(out, io) ? startAt : 0;
            } else {
                System.out.println(out);
            }
            turn = !turn;
            startAt++;
        }
    }

    public static void main(String[] args) {
        Fool fool = new Fool();
        fool.init();
    }
}