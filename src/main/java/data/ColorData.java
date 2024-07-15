package data;

public enum ColorData {
BLACK("Черный"),
WHITE("Белый"),
RED("Рыжий"),
GREY("Серый");

private String name;

ColorData(String name){
    this.name = name;
}

    public String getName() {
        return this.name;
    }
}
