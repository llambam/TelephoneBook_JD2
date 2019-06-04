package com.htp.repository.hibernate;

import com.htp.domain.HTelephones;
import com.htp.domain.HTelephones_;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
@Qualifier("hibernateTelephonesDao")
public class HibernateTelephonesDao implements HibernateTelephones {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<HTelephones> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select tu from HTelephones tu", HTelephones.class).getResultList();
        }
    }

    @Override
    public HTelephones findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(HTelephones.class, id);
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            session.remove(findById(id));
        }
    }

    @Override
    public HTelephones create(HTelephones entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long newUserID = (Long) session.save(entity);
            transaction.commit();
            return session.find(HTelephones.class, newUserID);
        }
    }

    @Override
    public HTelephones update(HTelephones entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);
            transaction.commit();
            return session.find(HTelephones.class, entity.getTelId());
        }
    }



    @Override
    public List<HTelephones> search(String searchQuery, Integer limit, Integer offset) {

        CriteriaBuilder cb = sessionFactory.getCriteriaBuilder();
        CriteriaQuery<HTelephones> query = cb.createQuery(HTelephones.class);
        Root<HTelephones> root = query.from(HTelephones.class);
        ParameterExpression<String> param = cb.parameter(String.class);
        Expression<Long> id = root.get(HTelephones_.telId);

        query.select(root)
                .distinct(true)
                .where(
                        cb.or(
                                cb.like(root.get("userName"), param),
                                cb.like(root.get("userSurname"), param)
                        )
                ).orderBy(cb.asc(root.get("userName")));

        try (Session session = sessionFactory.openSession()) {
            TypedQuery<HTelephones> resultQuery = session.createQuery(query);
            resultQuery.setParameter(param, searchQuery);
            resultQuery.setMaxResults(limit);
            resultQuery.setFirstResult(offset);
            return resultQuery.getResultList();
        }
    }
}
