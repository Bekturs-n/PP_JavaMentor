package DAO;

import Model.User;
import org.hibernate.cfg.Configuration;
import util.PropertyReader;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class UserDaoFactory {

    public static UserDAO getDAO() {
        Properties properties = PropertyReader.getProperties("dao.properties");
        String result = properties.getProperty("daoProperties");
        switch (result) {
            case "hibernate":
                return new UserHibernateDAO();
            case "jdbc":
                return new UserJdbcDAO();
            default:
                return null;
        }
    }
}

