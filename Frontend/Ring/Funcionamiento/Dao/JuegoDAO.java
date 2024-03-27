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
		double aux=0;
		List<Juego> listaJuego = null;
		
		listaJuego=listar();
		for(Juego juego:listaJuego) {
			aux=juego.getPrecio();
			if(aux>precioMayor) {
				precioMayor=aux;
			}
		}
		/**try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			listaJuego = session.createNativeQuery("select max(precio) from Juego", Juego.class).getResultList();
			precioMayor=listaJuego.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}**/
		return precioMayor;
	}

	public ArrayList<Juego> filtrarNombre(String nombreJuego, double precioIni, double precioFin, Date fechaIni,
			Date fechaFin, int id_plataforma, int pagina) {

		pagina = (pagina - 1) * 10;
		ArrayList<Juego> lista = null;
		String query = "SELECT * FROM juego as j inner join juego_consola as jc on j.id=jd.juego_id";
		if (nombreJuego != null || precioIni != 0 || precioFin != 0 || fechaIni != null || fechaFin != null
				|| id_plataforma != 0) {
			query = query + " WHERE ";
			if (nombreJuego != null) {
				query = query + "j.nombre like nombreJuego ";
				query = query + "AND ";
			}
			if (precioIni >= 0 && precioFin != 0 && precioFin > precioIni) {
				query = query + "j.precio > precioIni AND j.precio <= PrecioFin ";
				query = query + "AND ";
			}
			if (fechaIni != null && fechaFin != null) {
				query = query + "j.annoSalida > fechaIni AND j.annoSalida <= fechaFin ";
				query = query + "AND ";
			}
			// no tenemos a que consola pertenece en la base de datos, tratamiento despues
			// de la consulta de las demas o consulta se complica
			if (id_plataforma != 0) {
				query = query + "jd.id_consola = id_plataforma ";
			}
			query = query + "LIMIT 10 OFFSET pagina";
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			lista = (ArrayList<Juego>) session.createNativeQuery(query, Juego.class)
					.setParameter("nombreJuego", nombreJuego).setParameter("precioIni", precioIni)
					.setParameter("precioFin", precioFin).setParameter("fechaIni", fechaIni)
					.setParameter("fechaFin", fechaFin).setParameter("id_plataforma", id_plataforma)
					.setParameter("pagina", pagina).list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}
}