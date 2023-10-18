package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        MyPhonebook phonebook = new MyPhonebook();
        int choose = 0;
        while (choose != 6) {
            System.out.println("Доступные опции:\n" +
                    "1. Добавить контакт\n" +
                    "2. Вывести все контакты\n" +
                    "3. Поиск контакта\n" +
                    "4. Редактировать контакт\n" +
                    "5. Удалить контакт\n" +
                    "6. Выход\n");
            choose = phonebook.getNumber("Выберите действие: ");
            while (choose < 1 || choose > 6) {
                System.out.println("Некорректный ввод.\n");
                choose = phonebook.getNumber("Выберите действие: ");
            }
            switch (choose) {
                case 1: // добавление контакта
                    String name = phonebook.getName("Введите имя: ");
                    Integer number = phonebook.getNumber("Введите номер: ");
                    phonebook.add(name, number);
                    break;
                case 2: // печать всех контактов пока что без сортировки
                    System.out.println(phonebook.getPhoneBook());
                    break;
                case 3: // поиск контакта по имени
                    String searchedName = phonebook.getName("Введите имя контакта для поиска: ");
                    System.out.println(phonebook.findByName(searchedName));
                    break;
                case 4: // редактор контакта
                    String oldName = phonebook.getName("Введите имя контакта для редактирования: ");
                    String newName = phonebook.getName("Введите новое имя: ");
                    phonebook.editContact(oldName, newName);
                    break;
                case 5: // удаление контакта
                    String deletingName = phonebook.getName("Введите имя контакта для удаления: ");
                    phonebook.delete(deletingName);
                    System.out.printf("Контакт %s удалён.%n", deletingName);
                    break;
                case 6:
                    System.out.println("До свидания!");
            }
        }
    }
}

class MyPhonebook {
    private HashMap<String, ArrayList<Integer>> map = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    public String getName(String msg) {
        System.out.println(msg);
        return inputString();
    }

    public Integer getNumber(String msg) {
        System.out.println(msg);
        return inputInteger();
    }

    public void add(String name, Integer number) {
        if (map.containsKey(name)) {
            map.get(name).add(number);
        } else {
            ArrayList<Integer> phoneNumbers = new ArrayList<>();
            phoneNumbers.add(number);
            map.put(name, phoneNumbers);
        }
    }

    public ArrayList<Integer> findByName(String name) {
        ArrayList<Integer> res = new ArrayList<>();
        if (map.containsKey(name)) {
            res = map.get(name);
        }
        return res;
    }

    public void editContact(String oldName, String newName) {
        if (map.containsKey(oldName)) {
            ArrayList<Integer> numbers = map.get(oldName);
            map.remove(oldName);
            map.put(newName, numbers);
        }
    }

    public void delete(String name) {
        map.remove(name);
    }

    public HashMap<String, ArrayList<Integer>> getPhoneBook() {
        return sortHashMapByArrayListLength(map);
    }

    public static String inputString() {
        return sc.next();
    }

    public static Integer inputInteger() {
        return sc.nextInt();
    }

    public static HashMap<String, ArrayList<Integer>> sortHashMapByArrayListLength(HashMap<String, ArrayList<Integer>> hashMap) {
        List<Map.Entry<String, ArrayList<Integer>>> list = new ArrayList<>(hashMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, ArrayList<Integer>>>() {
            @Override
            public int compare(Map.Entry<String, ArrayList<Integer>> entry1, Map.Entry<String, ArrayList<Integer>> entry2) {
                return Integer.compare(entry2.getValue().size(), entry1.getValue().size());
            }
        });
        LinkedHashMap<String, ArrayList<Integer>> sortedHashMap = new LinkedHashMap<>();
        for (Map.Entry<String, ArrayList<Integer>> entry : list) {
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}
