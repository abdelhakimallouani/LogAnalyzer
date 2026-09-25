package ma.youcode.lineperm.dao;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    T save(T entity);
    // T findById(Long id);
    // Optional<T> findByLogin(String userName);
    // List<T> findAll();
    // T update(T obj);
    // void delete(String id);
}
