package org.feuyeux.jaxrs2.atup.core.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

public class AtupDao<T> {
    private static final Logger LOGGER = Logger.getLogger(AtupDao.class);
    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public AtupDao() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            this.entityClass = (Class<T>) actualTypeArguments[0];
        }
    }

    public T findById(final Integer id) {
        Assert.notNull(id);
        try {
            return entityManager.find(entityClass, id);
        } catch (final Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    public List<T> findAll() {
        return findAll(false, 0, 0);
    }

    public List<T> findAll(final boolean isPaging, final int firstResult, final int maxResults) {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<T> cq = cb.createQuery(entityClass);
        final TypedQuery<T> q = entityManager.createQuery(cq);
        if (isPaging) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Transactional
    public boolean remove(final Integer bookId) {
        final T book0 = findById(bookId);
        if (book0 != null) {
            entityManager.remove(book0);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public T store(final T entity) {
        return entityManager.merge(entity);
    }

    @Transactional
    public void save(final T entity) {
        entityManager.persist(entity);
    }

}
