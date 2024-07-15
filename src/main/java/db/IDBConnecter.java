package db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBConnecter {

    boolean execute(String sql) throws SQLException;

    ResultSet executeQuery(String sql) throws SQLException;

    void close() throws SQLException;

}
