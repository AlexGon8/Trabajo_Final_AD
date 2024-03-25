package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Clases.Permisos;
import util.HibernateUtil;

public class PermisosDAO implements IDao<Permisos, Integer> {

	@Override
	public boolean crear(Permisos us) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();

			session.persist(us);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Permisos leer(Integer pe_id) {
		Permisos pe = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Permisos where id= :pe_id";
			Query<Permisos> query = session.createQuery(hql, Permisos.class);
			query.setParameter("pe_id", pe_id);
			query.setMaxResults(1);
			pe = (Permisos) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pe;
	}

	@Override
	public boolean actualizar(Permisos modelo, Integer id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Permisos permisosPersistente = session.get(Permisos.class, id);
			if (permisosPersistente != null) {

				permisosPersistente.setIdpermisos(modelo.getIdpermisos());
				permisosPersistente.setPermisoPublicacion(modelo.getPermisoPublicacion());
				permisosPersistente.setPermisoEliminacion(modelo.getPermisoEliminacion());
				permisosPersistente.setPermisoStock(modelo.getPermisoStock());
				permisosPersistente.setPermisoBaneo(modelo.getPermisoBaneo());
				permisosPersistente.setPermisoPrecio(modelo.getPermisoPrecio());
				permisosPersistente.setPermisoDescuento(modelo.getPermisoDescuento());

				session.merge(permisosPersistente);

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

			Permisos permisos = session.get(Permisos.class, id);
			if (permisos != null) {
				session.remove(permisos);
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
	public List<Permisos> listar() {
		List<Permisos> listaPermisos = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Permisos";
			Query<Permisos> query = session.createQuery(hql, Permisos.class);
			listaPermisos = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaPermisos;
	}
}
