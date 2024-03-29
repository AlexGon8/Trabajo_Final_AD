package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Clases.Consola;
import util.HibernateUtil;

public class ConsolaDAO implements IDao<Consola, Integer> {

	@Override
	public boolean crear(Consola co) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();

			session.persist(co);
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
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Consola where id= :co_id";
			Query<Consola> query = session.createQuery(hql, Consola.class);
			query.setParameter("co_id", co_id);
			query.setMaxResults(1);
			co = (Consola) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return co;
	}
	
	public Consola leer(String co_nombre) {
		Consola co = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Consola where nombre like :co_nombre";
			Query<Consola> query = session.createQuery(hql, Consola.class);
			query.setParameter("co_nombre", co_nombre);
			query.setMaxResults(1);
			co = (Consola) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return co;
	}

	@Override
	public boolean actualizar(Consola modelo, Integer id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Consola consolaPersistente = session.get(Consola.class, id);
			if (consolaPersistente != null) {

				consolaPersistente.setNombre(modelo.getNombre());
				consolaPersistente.setAnnoSalida(modelo.getAnnoSalida());

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

			Consola consola = session.get(Consola.class, id);
			if (consola != null) {
				session.remove(consola);
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
	public List<Consola> listar() {
		List<Consola> listaConsola = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Consola";
			Query<Consola> query = session.createQuery(hql, Consola.class);
			listaConsola = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaConsola;
	}

	public List<String> listarNombres() {
		
		List<String> listaNombreConsola = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "SELECT DISTINCT c.nombre FROM Consola c";
			Query<String> query = session.createQuery(hql, String.class);
			listaNombreConsola = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaNombreConsola;
	}
}
