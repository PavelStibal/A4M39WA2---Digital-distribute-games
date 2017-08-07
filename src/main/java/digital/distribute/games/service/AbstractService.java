package digital.distribute.games.service;

import java.util.List;

/**
 * @author Pavel Stibal
 */
public interface AbstractService<T> {
    T get(final Long id);

    void create(final T instance);

    void delete(final Long id);

    void delete(final T entity);

    void update(final T instance);

    List<T> findAll();
}
