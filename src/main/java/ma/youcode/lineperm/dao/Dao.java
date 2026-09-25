package ma.youcode.lineperm.dao;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    T save(T entity);
    Optional<T> findById(Long id);
    // void delete(String id);
}
