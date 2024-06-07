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

        ArrayList<Animal> animals = new ArrayList();

        while (true){
            String commandStr;
            do {
                System.out.println("Введите одну из команд add/list/exit");
                commandStr = scanner.next().toUpperCase().trim();
            }
            while (!commandStr.equals("ADD") && !commandStr.equals("LIST") && !commandStr.equals("EXIT"));
            CommandData commandData = CommandData.valueOf(commandStr);
            switch (commandData){
                case ADD:
                    String animalStr;
                  do{
                      System.out.println("Введите тип животного cat/dog/duck");
                      animalStr = scanner.next().trim().toUpperCase();
                  }
                  while (!animalStr.equals("CAT") && !animalStr.equals("DOG") && !animalStr.equals("DUCK"));
                    AnimalTypeData animalTypeData = AnimalTypeData.valueOf(animalStr);
                    AnimalFactory animalFactory = new AnimalFactory();
                    Animal animal = animalFactory.creatAnimal(animalTypeData);

                    if (animal instanceof IFlying){
                        ((IFlying) animal).Fly();
                    }

                    Animal animal1 = newAnimal(animal);
                    animals.add(animal);
                    animal.say();
                    break;


                case LIST:
                    if (animals.size() == 0){
                        System.out.println("Вы не добавили ни одного животного");
                    }
                    else {
                        System.out.println(animals.toString());
                    }
                    break;

                case EXIT:
                    System.exit(0);
            }

            }

        }
        public static Animal newAnimal(Animal animals){
        System.out.println("Введите имя животного");
        String name = scanner.next();
        animals.setName(name);

        System.out.println("Введите возраст животного");
        int age;
        int i = 1;
        while (i < 2) {
            age = scanner.nextInt();
            if (age > 0 & age < 1000 ) {
                i = 2;
            }
            else {
                System.out.println("Неверный возраст. Введите возраст животного снова");
            }
            animals.setAge(age);
        }

        System.out.println("Введите вес животного");
        int weight;
        int s = 2;
        while (s < 3){
            weight = scanner.nextInt();
            if (weight > 0 & weight < 1000){
                s = 3;
            }
            else {
                System.out.println("Неверный вес. Введите вес животно снова");
            }
            animals.setWeight(weight);
        }

        String colorStr;
        do {
            System.out.println("Введите цвет животного: BLACK/WHITE/RED/GREY");
            colorStr = scanner.next().trim().toUpperCase();
        }
        while (!colorStr.equals("BLACK") & !colorStr.equals("WHITE") & !colorStr.equals("RED") & !colorStr.equals("GREY"));
        ColorData colorData = ColorData.valueOf(colorStr);
        animals.setColor(colorData.getName());



        return animals;
        }
        }





