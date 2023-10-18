package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyPhonebook phonebook = new MyPhonebook();
        Scanner sc = new Scanner(System.in);
        int choose = 0;
        while (choose != 6) {
            System.out.println("Выберите действие:\n" +
                    "1. Добавить контакт\n" +
                    "2. Вывести все контакты\n" +
                    "3. Поиск контакта\n" +
                    "4. Редактировать контакт\n" +
                    "5. Удалить контакт\n" +
                    "6. Выход\n");
            choose = sc.nextInt();
            while (choose < 1 || choose > 6) {
                System.out.println("Некорректный ввод.\n" +
                        "Выберите действие: ");
                choose = sc.nextInt();
            }
            switch (choose) {
                case 1:
                    String name = phonebook.getName();
                    Integer number = phonebook.getNumber();
                    phonebook.add(name, number);
                    break;
                case 2:
                    System.out.println(phonebook.getPhoneBook());
                    break;
                case 3:
                    System.out.println("Введите имя контакта для поиска: ");
                    String searchedName = sc.nextLine();
                    phonebook.findByName(searchedName);
                    break;
                case 4:
                    System.out.println("Введите имя контакта для редактирования: ");

                    break;
                case 5:
                    System.out.println("Введите имя контакта для удаления: ");
                    String deletingName = sc.nextLine();
                    phonebook.delete(deletingName);
                    break;
                case 6:
                    System.out.println("До свидания!");

            }
        }
        sc.close();
    }
}

class MyPhonebook {
    private HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public String getName() {
        System.out.println("Введите имя: ");
        return inputString();
    }

    public Integer getNumber() {
        System.out.println("Введите номер: ");
        return inputInteger();
    }
    public void add(String name, Integer number) {
        if (map.containsKey(name)) {
            map.get(name).add(number);
        } else {
            ArrayList<Integer> phoneNumbers = new ArrayList<>();
            phoneNumbers.add(number);
            map.put(name, phoneNumbers);
            phoneNumbers.sort(Comparator.comparingInt(o -> o));
        }
    }

    public ArrayList<Integer> findByName(String name) {
        ArrayList<Integer> res = new ArrayList<>();
        if (map.containsKey(name)) {
            res = map.get(name);
        }
        return res;
    }

    public void delete(String name) {
        map.remove(name);
    }

    public HashMap<String, ArrayList<Integer>> getPhoneBook() {
        return map;
    }

    public void edit(HashMap<String, ArrayList<Integer>> map1, String delName) {
        map1.remove(delName);
    }

    public static String inputString() {
        return sc.nextLine();
    }

    public static Integer inputInteger() {
        return sc.nextInt();
    }
}
