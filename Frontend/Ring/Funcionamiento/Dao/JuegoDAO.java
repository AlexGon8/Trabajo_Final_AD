package Dao;

import java.sql.Date;
import java.util.ArrayList;
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

	public double precioMayor() {
	    double precioMayor = 0;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        Query<Double> query = session.createQuery("select max(j.precio) from Juego j", Double.class);
	        precioMayor = query.uniqueResult();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return precioMayor;
	}

	public ArrayList<Juego> filtrarDatos(String nombreJuego, double precioIni, double precioFin, Date fechaIni,
			Date fechaFin, int id_plataforma, int pagina) {

		int pageSize = 10; // Tamaño de la página
		int firstResult = (pagina - 1) * pageSize; // Índice del primer resultado para la página dada

		ArrayList<Juego> lista = null;
		String query = "FROM Juego j JOIN j.consolas c WHERE 1=1 ";
		if (nombreJuego != null || precioIni != 0 || precioFin != 0 || fechaIni != null || fechaFin != null || id_plataforma != 0) {
		    if (nombreJuego != null) {
		        query += "AND j.nombre LIKE :nombreJuego ";
		    }
		    if (precioIni >= 0 && precioFin != 0 && precioFin > precioIni) {
		        query += "AND j.precio > :precioIni AND j.precio <= :precioFin ";
		    }
		    if (fechaIni != null && fechaFin != null) {
		        query += "AND j.annoSalida > :fechaIni AND j.annoSalida <= :fechaFin ";
		    }
		    if (id_plataforma != 0) {
		        query += "AND c.id = :id_plataforma ";
		    }
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		    Query<Juego> hqlQuery = session.createQuery(query, Juego.class);
		    if (nombreJuego != null) {
		        hqlQuery.setParameter("nombreJuego", nombreJuego);
		    }
		    if (precioIni != 0) {
		        hqlQuery.setParameter("precioIni", precioIni);
		    }
		    if (precioFin != 0) {
		        hqlQuery.setParameter("precioFin", precioFin);
		    }
		    if (fechaIni != null) {
		        hqlQuery.setParameter("fechaIni", fechaIni);
		    }
		    if (fechaFin != null) {
		        hqlQuery.setParameter("fechaFin", fechaFin);
		    }
		    if (id_plataforma != 0) {
		        hqlQuery.setParameter("id_plataforma", id_plataforma);
		    }
		    hqlQuery.setFirstResult(firstResult); // Índice del primer resultado de la página
		    hqlQuery.setMaxResults(pageSize); // Tamaño de la página
		    lista = (ArrayList<Juego>) hqlQuery.list();
		} catch (Exception e) {
		    e.printStackTrace();
		}


		return lista;
	}
}