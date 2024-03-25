package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Clases.Juego;
import util.HibernateUtil;

public class JuegoDAO implements IDao<Juego, Integer> {

	@Override
	public boolean crear(Juego ju) {
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
	public Juego leer(Integer ju_id) {
		Juego ju = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Juego where id= :ju_id";
			Query<Juego> query = session.createQuery(hql, Juego.class);
			query.setParameter("ju_id", ju_id);
			query.setMaxResults(1);
			ju = (Juego) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ju;
	}

	@Override
	public boolean actualizar(Juego modelo, Integer id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Juego juegoPersistente = session.get(Juego.class, id);
			if (juegoPersistente != null) {

				juegoPersistente.setId(modelo.getId());
				juegoPersistente.setNombre(modelo.getNombre());
				juegoPersistente.setPrecio(modelo.getPrecio());
				juegoPersistente.setAnnoDeSalida(modelo.getAnnoDeSalida());
				juegoPersistente.setCompannia(modelo.getCompannia());
				juegoPersistente.setStock(modelo.getStock());
				juegoPersistente.setDescuento(modelo.getDescuento());

				session.merge(juegoPersistente);

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

			Juego juego = session.get(Juego.class, id);
			if (juego != null) {
				session.remove(juego);
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
	public List<Juego> listar() {
		List<Juego> listaJuego = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Juego";
			Query<Juego> query = session.createQuery(hql, Juego.class);
			listaJuego = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaJuego;
	}
}