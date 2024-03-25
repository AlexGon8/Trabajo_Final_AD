package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import entidades.ExperienciaLaboral;
import entidades.HibernateUtil;

public class ExperienciaLaboralDao implements IDao<ExperienciaLaboral, Integer> {

    @Override
    public void persist(ExperienciaLaboral entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(entity);
        tx.commit();
        session.close();
    }

    @Override
    public void update(ExperienciaLaboral entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(entity);
        tx.commit();
        session.close();
    }

    @Override
    public ExperienciaLaboral findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ExperienciaLaboral experienciaLaboral = session.get(ExperienciaLaboral.class, id);
        session.close();
        return experienciaLaboral;
    }

    @Override
    public void delete(ExperienciaLaboral entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(entity);
        tx.commit();
        session.close();
    }

    @Override
    public List<ExperienciaLaboral> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ExperienciaLaboral> experienciasLaborales = session.createQuery("from ExperienciaLaboral").list();
        session.close();
        return experienciasLaborales;
    }

    @Override
    public void deleteAll() {
        List<ExperienciaLaboral> entityList = findAll();
        for (ExperienciaLaboral entity : entityList) {
            delete(entity);
        }
    }
}
