package data;

public enum AnimalTypeData {
    CAT("Кошка"),
    DOG("Собака"),
    DUCK("Утка");

    private String name;

    AnimalTypeData(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
