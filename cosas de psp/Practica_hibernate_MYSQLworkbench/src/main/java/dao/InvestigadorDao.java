package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import entidades.HibernateUtil;
import entidades.Investigador;

public class InvestigadorDao implements IDao<Investigador, Integer> {

    @Override
    public void persist(Investigador entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Investigador entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }

    @Override
    public Investigador findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Investigador investigador = session.get(Investigador.class, id);
        session.close();
        return investigador;
    }

    @Override
    public void delete(Investigador entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }

    @Override
    public List<Investigador> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Investigador> investigadores = session.createQuery("from Investigador").list();
        session.close();
        return investigadores;
    }

    @Override
    public void deleteAll() {
        List<Investigador> entityList = findAll();
        for (Investigador entity : entityList) {
            delete(entity);
        }
    }
}
