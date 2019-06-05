package com.htp.repository.hibernate.impl;

import com.htp.domain.HFavorite;
import com.htp.repository.hibernate.HibernateFavorite;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("hibernateFavoriteDao")
public class HibernateFavoriteImpl implements HibernateFavorite {
  @Autowired
  @Qualifier("sessionFactory")
  private SessionFactory sessionFactory;

  @Override
  public List<HFavorite> findAll() {
    try (Session session = sessionFactory.openSession()) {
      return session.createQuery("select tu from HFavorite tu", HFavorite.class).getResultList();
    }
  }

  @Override
  public HFavorite findById(Long id) {
    try (Session session = sessionFactory.openSession()) {
      return session.find(HFavorite.class, id);
    }
  }

  @Override
  public void delete(Long id) {
    try (Session session = sessionFactory.openSession()) {
      session.remove(findById(id));
    }
  }

  @Override
  public HFavorite create(HFavorite entity) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      Long newFavoriteId = (Long) session.save(entity);
      transaction.commit();
      return session.find(HFavorite.class, newFavoriteId);
    }
  }

  @Override
  public HFavorite update(HFavorite entity) {
    try (Session session = sessionFactory.openSession()) {
      Transaction transaction = session.getTransaction();
      transaction.begin();
      session.saveOrUpdate(entity);
      transaction.commit();
      return session.find(HFavorite.class, entity.getFavoriteId());
    }
  }
}
