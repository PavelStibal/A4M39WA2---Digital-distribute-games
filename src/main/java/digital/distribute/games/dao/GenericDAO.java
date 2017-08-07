package digital.distribute.games.dao;

import digital.distribute.games.entity.AbstractEntity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.NotFoundException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author Pavel Stibal
 */
public abstract class GenericDAO <T extends AbstractEntity> implements DAO<T> {
    @Inject
    private EntityManager em;

    private Class<T> type;

    GenericDAO() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public T save(final T entity) {
        this.em.persist(entity);
        this.em.flush();
        return entity;
    }

    @Override
    public T find(final Object id) {
        T entity = this.em.find(type, id);
        return entity != null ? entity : null;
    }

    @Override
    public T update(final T entity) {
        this.em.merge(entity);
        this.em.flush();
        return entity;
    }

    @Override
    public void delete(final Long id) {
        delete(find(id));
    }

    @Override
    public void delete(final T entity) {
        if (entity != null) {
            em.remove(entity);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public List<T> findAll(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        cq.from(type);
        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<T> findByProperty(String propertyName, Object value) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(type);
        Root<T> root = cq.from(type);
        cq.select(root).where(cb.equal(root.get(propertyName), value));
        return em.createQuery(cq).getResultList();
    }

    public EntityManager getEntityManager() {
        return em;
    }

}
