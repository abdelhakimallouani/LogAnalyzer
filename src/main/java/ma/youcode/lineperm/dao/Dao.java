package ma.youcode.lineperm.dao;
import java.util.List;

public interface Dao {
    void save(Object obj);
    Object findById(String id);
    List<Object> findAll();
    void update(Object obj);
    void delete(String id);
}
