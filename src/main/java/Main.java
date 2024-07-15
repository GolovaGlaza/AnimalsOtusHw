import animals.Animal;
import animals.birds.IFlying;
import data.AnimalTypeData;
import data.ColorData;
import data.CommandData;
import db.MySqlDBConnecter;
import factory.AnimalFactory;
import tables.AbsTables;
import tables.AnimalTable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static AnimalTable animalTable = new AnimalTable();


    public static void main(String[] args) throws SQLException {
        animalTable.create();
        ArrayList<Animal> animals = new ArrayList<>();
        while (true) {
            String commandStr;
            do {
                System.out.println("Введите одну из команд add/list/listall/update/filter/exit");
                commandStr = scanner.next().toUpperCase().trim();
                if (!commandStr.equals("ADD") && !commandStr.equals("LIST") && !commandStr.equals("EXIT") && !commandStr.equals("LISTALL")
                && !commandStr.equals("UPDATE") && !commandStr.equals("FILTER")) {
                    System.out.println("Неверная команда. Пожалуйста, попробуйте снова.");
                }

            }
            while (!commandStr.equals("ADD") && !commandStr.equals("LIST") && !commandStr.equals("EXIT") && !commandStr.equals("LISTALL")
            && !commandStr.equals("UPDATE") && !commandStr.equals("FILTER"));
            CommandData commandData = CommandData.valueOf(commandStr);
            switch (commandData) {
                case ADD:
                    String animalStr;
                    do {
                        System.out.println("Введите тип животного cat/dog/duck");
                        animalStr = scanner.next().trim().toUpperCase();
                        if (!animalStr.equals("CAT") && !animalStr.equals("DOG") && !animalStr.equals("DUCK")) {
                            System.out.println("Неверный тип животного");
                        }
                    }
                    while (!animalStr.equals("CAT") && !animalStr.equals("DOG") && !animalStr.equals("DUCK"));
                    AnimalTypeData animalTypeData = AnimalTypeData.valueOf(animalStr);
                    AnimalFactory animalFactory = new AnimalFactory();
                    Animal animal = animalFactory.creatAnimal(animalTypeData);
                    newAnimal(animal);

                    String sql = String.format("INSERT INTO animals (Имя, Возраст, Вес, Цвет, Тип) VALUES ('%s', %d, %d, '%s', '%s')",
                        animal.getName(), animal.getAge(), animal.getWeight(), animal.getColor(), animalTypeData.getName());
                    animalTable.getIdbConnecter().execute(sql);
                    animals.add(animal);
                    System.out.println("Животное добавлено");


                    if (animal instanceof IFlying) {
                        ((IFlying) animal).fly();
                    }

                    animal.say();
                    break;


                case LIST:
                    if (animals.size() == 0) {
                        System.out.println("Вы не добавили ни одного животного");
                    } else {
                        System.out.println(animals);
                    }
                    break;

                case LISTALL:
                    listAnimals();
                    break;


                case UPDATE:
                    updateAnimal();
                    break;


                case FILTER:
                    listAnimalsByType();
                    break;


                case EXIT:
                    System.exit(0);
            }

        }

    }


    private static void updateAnimal() throws SQLException {
        System.out.println("Введите ID животного для обновления:");
        int id = scanner.nextInt();

        System.out.println("Введите новое имя животного:");
        String name = scanner.next();

        System.out.println("Введите новый возраст животного:");
        int age;

        while (true) {
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                if (age > 0 & age < 1000) {
                    break;
                } else {
                    System.out.println("Неверный возраст. Введите возраст животного снова");
                }
            } else {
                System.out.println("Пожалуйста, введите целое число.");
                scanner.next(); // Очистка неправильного ввода
            }
        }

        System.out.println("Введите новый вес животного:");
        int weight;

        while (true) {
            if (scanner.hasNextInt()) {
                weight = scanner.nextInt();
                if (weight > 0 & weight < 1000) {
                    break;
                } else {
                    System.out.println("Неверный вес. Введите вес животно снова");
                }
            } else {
                System.out.println("Пожалуйста, введите целое число.");
                scanner.next(); // Очистка неправильного ввода
            }
        }

        String colorStr;
        do {
            System.out.println("Введите цвет животного: BLACK/WHITE/RED/GREY");
            colorStr = scanner.next().trim().toUpperCase();
            if (!colorStr.equals("BLACK") & !colorStr.equals("WHITE") & !colorStr.equals("RED") & !colorStr.equals("GREY")) {
                System.out.println("Неверный цвет. Пожалуйста, попробуйте снова");
            }

        }
        while (!colorStr.equals("BLACK") & !colorStr.equals("WHITE") & !colorStr.equals("RED") & !colorStr.equals("GREY"));

        ColorData colorData = ColorData.valueOf(colorStr);
        String color = colorData.getName();
        animalTable.updateAnimal(id, name, age, weight, color);
        System.out.println("Животное обновлено");
    }




    private static void listAnimals() throws SQLException {
        ResultSet resultSet = animalTable.listAll();
        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("Имя");
            int age = resultSet.getInt("Возраст");
            int weight = resultSet.getInt("Вес");
            String color = resultSet.getString("Цвет");
            String type = resultSet.getString("Тип");
            System.out.printf("id: %d, Имя: %s, Возраст: %d, Вес: %d, Цвет: %s, Тип: %s%n", id,
                    name, age, weight, color, type);
        }
    }

    private static void listAnimalsByType() throws SQLException {
        String typeStr;
        do{
            System.out.println("Введите тип животного для фильтрации: cat/dog/duck");
            typeStr = scanner.next().toUpperCase();
            if (!typeStr.equals("CAT") && !typeStr.equals("DOG") && !typeStr.equals("DUCK")){
                System.out.println("Неверный тип животного. Пожалуйста, попробуйте снова.");
            }
        }
        while (!typeStr.equals("CAT") && !typeStr.equals("DOG") && !typeStr.equals("DUCK"));

        AnimalTypeData animalTypeData = AnimalTypeData.valueOf(typeStr);
        String animalType = animalTypeData.getName();

        ResultSet resultSet = animalTable.listByType(animalType);
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("Имя");
            int age = resultSet.getInt("Возраст");
            int weight = resultSet.getInt("Вес");
            String color = resultSet.getString("цвет");
            String type = resultSet.getString("Тип");
            System.out.printf("id: %d, Имя: %s, Возраст: %d, Вес: %d, Цвет: %s, Тип: %s%n",
                   id, name, age, weight, color, type);
        }
    }

    private static Animal newAnimal(Animal animals) {
        System.out.println("Введите имя животного");
        String name = scanner.next();
        animals.setName(name);



        System.out.println("Введите возраст животного");
        int age;

        while (true) {
            if (scanner.hasNextInt()) {
                age = scanner.nextInt();
                if (age > 0 & age < 1000) {
                    break;
                } else {
                    System.out.println("Неверный возраст. Введите возраст животного снова");
                }
            } else {
                System.out.println("Пожалуйста, введите целое число.");
                scanner.next(); // Очистка неправильного ввода
            }
        }
        animals.setAge(age);

        System.out.println("Введите вес животного");
        int weight;

        while (true) {
            if (scanner.hasNextInt()) {
                weight = scanner.nextInt();
                if (weight > 0 & weight < 1000) {
                    break;
                } else {
                    System.out.println("Неверный вес. Введите вес животно снова");
                }
            } else {
                System.out.println("Пожалуйста, введите целое число.");
                scanner.next(); // Очистка неправильного ввода
            }
        }
        animals.setWeight(weight);


        String colorStr;
        do {
            System.out.println("Введите цвет животного: BLACK/WHITE/RED/GREY");
            colorStr = scanner.next().trim().toUpperCase();
            if (!colorStr.equals("BLACK") & !colorStr.equals("WHITE") & !colorStr.equals("RED") & !colorStr.equals("GREY")) {
                System.out.println("Неверный цвет. Пожалуйста, попробуйте снова");
            }

        }
        while (!colorStr.equals("BLACK") & !colorStr.equals("WHITE") & !colorStr.equals("RED") & !colorStr.equals("GREY"));
        ColorData colorData = ColorData.valueOf(colorStr);
        animals.setColor(colorData.getName());


        return animals;
    }
}




