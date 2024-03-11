package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Dao.IDao;
import tables.Educacion;
import util.HibernateUtil;

public class ConsolaDAO implements IDao<Consola, Integer>{
	
	@Override
	public boolean crear(Consola co) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			Transaction tx = session.beginTransaction();
			
			session.persist(ed);
			tx.commit();	
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Consola leer(Integer co_id) {
		Consola co = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()){
			String hql = "from consola where id= :co_id" ;
	        Query query = session.createQuery(hql,Consola.class);
	        query.setParameter("invId", co_id);
	        query.setMaxResults(1);
			ed = (Consola)  query.getSingleResult();					
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return co;	
	}

	@Override
	public boolean actualizar(Consola modelo, Integer id) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()){
	        session.beginTransaction();
	        
	        Consola educacionPersistente = session.get(Consola.class, id);
	        if (educacionPersistente != null) {
	            // Actualizar los campos de la educación persistente con los valores del modelo
	            educacionPersistente.setEdNombre(modelo.getEdNombre());
	            educacionPersistente.setEdGradoe(modelo.getEdGradoe());
	            educacionPersistente.setEdTermino(modelo.getEdTermino());
	            
	            session.merge(educacionPersistente);
	            
	            session.getTransaction().commit();
	            return true;
	        } else {
	            // No se encontró la educación con el id proporcionado
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public boolean eliminar(Integer id) {
	    try (Session session = HibernateUtil.getSessionFactory().openSession()){
	        session.beginTransaction();
	        
	        Educacion educacion = session.get(Educacion.class, id);
	        if (educacion != null) {
	            session.remove(educacion);
	            session.getTransaction().commit();
	            return true;
	        } else {
	            // No se encontró la educación con el id proporcionado
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public List<Educacion> listar() {
	    List<Educacion> listaEducaciones = null;
	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        String hql = "FROM educacion";
	        Query<Educacion> query = session.createQuery(hql, Educacion.class);
	        listaEducaciones = query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return listaEducaciones;
	}

	
}
