package animals.birds;

import animals.Animal;

public class Duck extends Animal implements IFlying {


    @Override
    public void say() {
        System.out.println("Кря");
    }

    @Override
    public void fly() {
        System.out.println("Я лечу");
    }

}
