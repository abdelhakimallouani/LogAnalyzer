package ma.youcode.lineperm.dao;

import java.net.ConnectException;
import java.sql.Connection;
import java.util.*;

import ma.youcode.lineperm.db.DBConnection;

public abstract class AbstractDao<T> implements Dao<T> {
    protected Connection connection;
    public AbstractDao(){
        this.connection = DBConnection.getConnection();
        System.out.println("dao connec" + connection);
    }
}
