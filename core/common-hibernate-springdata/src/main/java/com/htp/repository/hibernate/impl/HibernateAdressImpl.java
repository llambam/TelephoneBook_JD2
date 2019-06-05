package com.htp.repository.hibernate.impl;

import com.htp.domain.HAdress;
import com.htp.repository.hibernate.HibernateAdress;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hibernateAdressDao")
public class HibernateAdressImpl implements HibernateAdress {

  @Autowired
  @Qualifier("sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public List<HAdress> findAll() {
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery("select tu from HAdress tu", HAdress.class).getResultList();
    }
  }

  @Override
  public HAdress findById(Long id) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(HAdress.class, id);
    }
  }

  @Override
  public void delete(Long id) {
    try (Session session = sessionFactory.openSession()) {
      session.remove(findById(id));
    }
  }

  @Override
  public HAdress create(HAdress entity) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      Long newAdressId = (Long) session.save(entity);
      transaction.commit();
      return session.find(HAdress.class, newAdressId);
    }
  }

  @Override
  public HAdress update(HAdress entity) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.saveOrUpdate(entity);
      transaction.commit();
      return session.find(HAdress.class, entity.getAdressId());
    }
  }
}
