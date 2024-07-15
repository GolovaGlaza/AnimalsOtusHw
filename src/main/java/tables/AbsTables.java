package tables;

import db.IDBConnecter;
import db.MySqlDBConnecter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbsTables {


    public AbsTables(String name){
        this.name = name;
    }


    private IDBConnecter idbConnecter = new MySqlDBConnecter();

    private String name = "";

    public void create(List<String> columns) throws SQLException {
        if (!isTableExist()) {
            String sqlRequest = String.format("CREATE TABLE %s (%s)", name, String.join(", ", columns));
            idbConnecter.execute(sqlRequest);
        }
    }

    private boolean isTableExist () throws SQLException{
        String sqlRequest = String.format("SHOW TABLES");
        ResultSet resultSet = idbConnecter.executeQuery(sqlRequest);

        while (resultSet.next()){
            if (resultSet.getString(1).equals(name)){
                return true;
            }
        }
        return false;
    }

    public void updateAnimal(int id, String name, int age, int weight,String color) throws SQLException {
        String sqlRequest = String.format("UPDATE animals SET Имя ='%s', Возраст =%d, Вес=%d, Цвет='%s' WHERE id=%d",
                name, age, weight, color, id);
        idbConnecter.execute(sqlRequest);
    }

    public ResultSet listAll() throws SQLException {
        String sqlRequest = String.format("SELECT * FROM animals");
        return idbConnecter.executeQuery(sqlRequest);
    }

    public ResultSet listByType(String animalType) throws SQLException {
        String sqlRequest = String.format("SELECT * FROM %s WHERE Тип = '%s'", name, animalType.toUpperCase());
        return idbConnecter.executeQuery(sqlRequest);
    }


    public IDBConnecter getIdbConnecter(){
        return idbConnecter;
    }

}
