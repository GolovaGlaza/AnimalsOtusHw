package db;

import config.DBCongifurator;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class MySqlDBConnecter implements IDBConnecter {

    private DBCongifurator dbCongifurator = new DBCongifurator();


    private static Statement statement = null;

    private static Connection connection = null;

    public MySqlDBConnecter() {
        connect();
    }

    private void connect() {
        try {
            Properties configuration = dbCongifurator.getDBConfig();
            connection = DriverManager.getConnection(
                    String.format("%s/%s", configuration.getProperty("db_url"), configuration.getProperty("db_name")),
                    configuration.getProperty("username"),
                    configuration.getProperty("password")
            );
            if (connection != null) {
                statement = connection.createStatement();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        if (statement == null) {
            throw new SQLException("Statement is not initialized.");
        }
        return statement.execute(sql);
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        if (statement == null) {
            throw new SQLException("Statement is not initialized.");
        }
        return statement.executeQuery(sql);
    }

    @Override
    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
}