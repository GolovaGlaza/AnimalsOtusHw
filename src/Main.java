import animals.Animal;
import animals.birds.IFlying;
import data.AnimalTypeData;
import data.ColorData;
import data.CommandData;
import factory.AnimalFactory;

import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Animal> animals = new ArrayList<>();

        while (true) {
            String commandStr;
            do {
                System.out.println("Введите одну из команд add/list/exit");
                commandStr = scanner.next().toUpperCase().trim();
                if (!commandStr.equals("ADD") && !commandStr.equals("LIST") && !commandStr.equals("EXIT")) {
                    System.out.println("Неверная команда. Пожалуйста, попробуйте снова.");
                }

            }
            while (!commandStr.equals("ADD") && !commandStr.equals("LIST") && !commandStr.equals("EXIT"));
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

                case EXIT:
                    System.exit(0);
            }

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




