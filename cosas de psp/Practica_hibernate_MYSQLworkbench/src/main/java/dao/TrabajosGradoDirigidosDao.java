package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import entidades.HibernateUtil;
import entidades.TrabajosGradoDirigidos;

public class TrabajosGradoDirigidosDao implements IDao<TrabajosGradoDirigidos, Integer> {

    @Override
    public void persist(TrabajosGradoDirigidos entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void update(TrabajosGradoDirigidos entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }

    @Override
    public TrabajosGradoDirigidos findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        TrabajosGradoDirigidos trabajosGradoDirigidos = session.get(TrabajosGradoDirigidos.class, id);
        session.close();
        return trabajosGradoDirigidos;
    }

    @Override
    public void delete(TrabajosGradoDirigidos entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }

    @Override
    public List<TrabajosGradoDirigidos> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<TrabajosGradoDirigidos> trabajosGradoDirigidosList = session.createQuery("from TrabajosGradoDirigidos").list();
        session.close();
        return trabajosGradoDirigidosList;
    }

    @Override
    public void deleteAll() {
        List<TrabajosGradoDirigidos> entityList = findAll();
        for (TrabajosGradoDirigidos entity : entityList) {
            delete(entity);
        }
    }
}
