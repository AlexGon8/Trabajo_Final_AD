package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import entidades.Educacion;
import entidades.HibernateUtil;

public class EducacionDao implements IDao<Educacion, Integer> {

    @Override
    public void persist(Educacion entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void update(Educacion entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }

    @Override
    public Educacion findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Educacion educacion = session.get(Educacion.class, id);
        session.close();
        return educacion;
    }

    @Override
    public void delete(Educacion entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }

    @Override
    public List<Educacion> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Educacion> educaciones = session.createQuery("from Educacion").list();
        session.close();
        return educaciones;
    }

    @Override
    public void deleteAll() {
        List<Educacion> entityList = findAll();
        for (Educacion entity : entityList) {
            delete(entity);
        }
    }
}
