package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Clases.Rol;
import util.HibernateUtil;

public class RolDAO implements IDao<Rol, Integer> {

	@Override
	public boolean crear(Rol ro) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();

			session.persist(ro);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Rol leer(Integer ro_id) {
		Rol ro = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Rol where id= :ro_id";
			Query<Rol> query = session.createQuery(hql, Rol.class);
			query.setParameter("ro_id", ro_id);
			query.setMaxResults(1);
			ro = (Rol) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ro;
	}

	@Override
	public boolean actualizar(Rol modelo, Integer id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Rol consolaPersistente = session.get(Rol.class, id);
			if (consolaPersistente != null) {

				consolaPersistente.setNombre(modelo.getNombre());

				session.merge(consolaPersistente);

				session.getTransaction().commit();
				return true;
			} else {

				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean eliminar(Integer id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Rol rol = session.get(Rol.class, id);
			if (rol != null) {
				session.remove(rol);
				session.getTransaction().commit();
				return true;
			} else {
				
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Rol> listar() {
		List<Rol> listaRol = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Rol";
			Query<Rol> query = session.createQuery(hql, Rol.class);
			listaRol = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaRol;
	}

}
