package diegogil.com.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
    public static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("archivo.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar el archivo de propiedades", e);
        }
        return properties;
    }

    public static String buildConnectionUrl(Properties properties) {
        return String.format(
                "jdbc:%s://%s:%s/%s",
                properties.getProperty("hibernate.connection.motor"),
                properties.getProperty("hibernate.connection.host"),
                properties.getProperty("hibernate.connection.port"),
                properties.getProperty("hibernate.connection.database")
        );
    }
}