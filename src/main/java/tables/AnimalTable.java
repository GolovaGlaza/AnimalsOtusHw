package tables;

import animals.Animal;
import db.IDBConnecter;
import db.MySqlDBConnecter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalTable extends AbsTables {


    public AnimalTable(){
        super("animals");
    }


    public void create() throws SQLException {
        List<String> columns = new ArrayList<>();
        columns.add("id INT AUTO_INCREMENT PRIMARY KEY");
        columns.add("Имя VARCHAR(255)");
        columns.add("Возраст INT");
        columns.add("Вес INT");
        columns.add("Цвет VARCHAR(255)");
        columns.add("Тип VARCHAR(255)");
        super.create(columns);
    }

}
