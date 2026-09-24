package ma.youcode.lineperm.dao;

import java.net.ConnectException;
import java.sql.Connection;
import java.util.*;

import ma.youcode.lineperm.db.DBConnection;

public abstract class AbstractDao implements Dao {
    protected final Connection connection;
    protected AbstractDao(){
        this.connection = DBConnection.getConnection();
    }
}
