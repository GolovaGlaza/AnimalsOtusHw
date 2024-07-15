package animals;

public abstract class Animal {

    private String name;
    private int age;
    private int weight;
    private String color;
    private String type;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight( int weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }




    public abstract void say();

    public void go() {
        System.out.println("Я иду");
    }

    public void drink() {
        System.out.println("Я пью");
    }

    public void eat() {
        System.out.println("Я пью");
    }

    @Override
    public String toString() {
        return String.format("Привет! Меня зовут %s, мне %d %s, я вешу - %d кг, мой цвет - %s",
                getName(), getAge(), getYearPadej(), getWeight(), getColor());
    }

    private String getYearPadej() {
        int ostatok = getAge() % 10;
        if (ostatok > 5 || getAge() >= 11 && getAge() <= 14) {
            return "лет";
        }
        if (ostatok == 1) {
            return "год";
        }
        if (ostatok >= 2 && ostatok <= 4) {
            return "года";
        }
        return "ошибка возраста";
    }


}
