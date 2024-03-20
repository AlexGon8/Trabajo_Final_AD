package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Clases.Compannia;
import util.HibernateUtil;

public class CompaniaDAO implements IDao<Compannia, Integer> {

	@Override
	public boolean crear(Compannia ju) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction tx = session.beginTransaction();

			session.persist(ju);
			tx.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Compannia leer(Integer cm_id) {
		Compannia cm = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Compannia where id= :cm_id";
			Query<Compannia> query = session.createQuery(hql, Compannia.class);
			query.setParameter("cm_id", cm_id);
			query.setMaxResults(1);
			cm = (Compannia) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cm;
	}

	@Override
	public boolean actualizar(Compannia modelo, Integer id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Compannia companniaPersistente = session.get(Compannia.class, id);
			if (companniaPersistente != null) {

				companniaPersistente.setId(modelo.getId());
				companniaPersistente.setNombre(modelo.getNombre());
				companniaPersistente.setAnnoDeFundacion(modelo.getAnnoDeFundacion());

				session.merge(companniaPersistente);

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

			Compannia compannia = session.get(Compannia.class, id);
			if (compannia != null) {
				session.remove(compannia);
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
	public List<Compannia> listar() {
		List<Compannia> listaCompannia = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Compannia";
			Query<Compannia> query = session.createQuery(hql, Compannia.class);
			listaCompannia = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaCompannia;
	}
}
