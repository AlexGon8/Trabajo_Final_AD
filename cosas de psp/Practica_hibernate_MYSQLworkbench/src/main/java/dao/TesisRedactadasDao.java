package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import entidades.HibernateUtil;
import entidades.TesisRedactadas;

public class TesisRedactadasDao implements IDao<TesisRedactadas, Integer> {

    @Override
    public void persist(TesisRedactadas entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void update(TesisRedactadas entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }

    @Override
    public TesisRedactadas findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        TesisRedactadas tesisRedactadas = session.get(TesisRedactadas.class, id);
        session.close();
        return tesisRedactadas;
    }

    @Override
    public void delete(TesisRedactadas entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }

    @Override
    public List<TesisRedactadas> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<TesisRedactadas> tesisRedactadasList = session.createQuery("from TesisRedactadas").list();
        session.close();
        return tesisRedactadasList;
    }

    @Override
    public void deleteAll() {
        List<TesisRedactadas> entityList = findAll();
        for (TesisRedactadas entity : entityList) {
            delete(entity);
        }
    }
}
