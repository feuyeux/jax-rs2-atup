package org.feuyeux.jaxrs2.atup.core.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class AtupDao<T> {
    private final Logger log = LogManager.getLogger(AtupDao.class.getName());
    @PersistenceContext
    protected EntityManager entityManager;
    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public AtupDao() {
        final Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            final ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            this.entityClass = (Class<T>) actualTypeArguments[0];
        }
    }

    public T findById(final Integer id) {
        Assert.notNull(id);
        try {
            return entityManager.find(entityClass, id);
        } catch (final Exception e) {
            log.error(e);
            return null;
        }
    }

    public List<T> findAll() {
        return findAll(false, 0, 0);
    }

    public List<T> findAll(final int firstResult, final int maxResults) {
        return findAll(true, firstResult, maxResults);
    }

    private List<T> findAll(final boolean isPaging, final int firstResult, final int maxResults) {
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
    public boolean remove(final Integer entityId) {
        final T entity = findById(entityId);
        if (entity != null) {
            entityManager.remove(entity);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public T save(final T entity) {
        entityManager.persist(entity);
        entityManager.flush();
        return entity;
    }
}
