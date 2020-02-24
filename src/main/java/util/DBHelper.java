package util;

import DAO.UserDaoFactory;
import DAO.UserJdbcDAO;
import Model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {

    private static SessionFactory sessionFactory;
    private static Connection connection;

    private static DBHelper instance;

    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }

    public SessionFactory getConfiguration() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    public Connection getConnection() {
        if (connection == null) {
            connection = getMysqlConnection();
        }
        return connection;
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").                        //db type
                    append("localhost:").                           //host name
                    append("3306/").                                //port
                    append("exam?").                                //db name
                    append("user=root&").                           //login
                    append("password=root").                        //password
                    append("&serverTimezone=Europe/Moscow").        //timeZone
                    append("&useSSL=false");                        //SSL error
            System.out.println("URL: " + url + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public static Configuration getMySQLConfiguration() {

        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        Properties properties = PropertyReader.getProperties("hibernate.properties");
        configuration.setProperty("hibernate.dialect", properties.getProperty("dialect"))
                .setProperty("hibernate.connection.driver_class", properties.getProperty("connection.driver_class"))
                .setProperty("hibernate.connection.url", properties.getProperty("connection.url"))
                .setProperty("hibernate.connection.username", properties.getProperty("connection.username"))
                .setProperty("hibernate.connection.password", properties.getProperty("connection.password"))
                .setProperty("hibernate.show_sql", properties.getProperty("show_sql"))
                .setProperty("hibernate.hbm2ddl.auto", properties.getProperty("hbm2ddl.auto"));
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration = getMySQLConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
