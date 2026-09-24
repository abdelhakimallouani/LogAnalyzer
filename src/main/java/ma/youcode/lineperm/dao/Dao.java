package ma.youcode.lineperm.dao;
import java.util.List;

public interface Dao<T> {
    T save(T entity);
    T findById(Long id);
    List<T> findAll();
    T update(T obj);
    // void delete(String id);
}
