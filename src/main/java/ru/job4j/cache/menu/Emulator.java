package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {
    public void init() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите адрес директории:");
        AbstractCache<String, String> cache = new DirFileCache(scanner.nextLine());
        while (true) {
            System.out.println("Введите адрес файла относительно директории или 'Выход':");
            String input = scanner.nextLine();
            if (input.equals("Выход")) {
                break;
            }
            System.out.println(cache.get(input));
        }
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        emulator.init();
    }
}
