package ru.job4j.gc.leak;

import java.util.Random;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Random random = new Random();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        menu.start(commentGenerator, scanner, userGenerator, postStore);
    }

    private void start(CommentGenerator commentGenerator, Scanner scanner, UserGenerator userGenerator, PostStore postStore) {
        boolean run = true;
        while (run) {
            System.out.println("Выберите меню");
            System.out.println("""
                Введите 1 для создание поста.
                Введите 2, чтобы создать определенное количество постов.
                Введите 3, чтобы показать все посты.
                Введите 4, чтобы удалить все посты.
                Введите любое другое число для выхода.
            """);
            String userChoice = scanner.nextLine();
            System.out.println(userChoice);
            switch (userChoice) {
                case "1" -> {
                    System.out.println("Введите текст");
                    String text = scanner.nextLine();
                    userGenerator.generate();
                    commentGenerator.generate();
                    postStore.add(new Post(text, commentGenerator.getComments()));
                }
                case "2" -> {
                    System.out.println("Введите текст");
                    String text = scanner.nextLine();
                    System.out.println("Выберите количество создаваемых постов");
                    String count = scanner.nextLine();
                    for (int i = 0; i < Integer.parseInt(count); i++) {
                        createPost(commentGenerator, userGenerator, postStore, text);
                    }
                }
                case "3" -> System.out.println(postStore.getPosts());
                case "4" -> {
                    System.out.println("Все посты удалены");
                    postStore.removeAll();
                }
                default -> {
                    run = false;
                    System.out.println("Конец работы");
                }
            }
        }
    }

    private void createPost(CommentGenerator commentGenerator,
                                   UserGenerator userGenerator, PostStore postStore, String text) {
        userGenerator.generate();
        commentGenerator.generate();
        postStore.add(new Post(text, commentGenerator.getComments()));
    }
}