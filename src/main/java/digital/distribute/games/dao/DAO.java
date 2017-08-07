package digital.distribute.games.dao;

import digital.distribute.games.entity.AbstractEntity;

import java.util.List;

/**
 * @author Pavel Stibal
 */
public interface DAO<T extends AbstractEntity> {
    T save(T entity);

    void delete(Long id);

    void delete(T entity);

    T find(Object id);

    T update(T entity);

    List<T> findAll();

    List<T> findByProperty(String propertyName, Object value);
}
