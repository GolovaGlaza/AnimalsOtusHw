package config;

import java.io.*;
import java.util.Properties;

public class DBCongifurator {


    public Properties getDBConfig() throws IOException {
        Properties properties = new Properties();
        InputStream fileInputStream = new FileInputStream(
                System.getProperty("user.dir")+"/src/main/resources/db.properties");
        properties.load(fileInputStream);

        return properties;
    }

}
