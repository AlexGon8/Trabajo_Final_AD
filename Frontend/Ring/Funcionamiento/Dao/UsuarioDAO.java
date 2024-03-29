package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import Clases.Usuario;
import util.HibernateUtil;

public class UsuarioDAO implements IDao<Usuario, Integer> {

	@Override
	public boolean crear(Usuario us) {
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
	public Usuario leer(Integer us_id) {
		Usuario us = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "from Usuario where id= :us_id";
			Query<Usuario> query = session.createQuery(hql, Usuario.class);
			query.setParameter("us_id", us_id);
			query.setMaxResults(1);
			us = (Usuario) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return us;
	}

	@Override
	public boolean actualizar(Usuario modelo, Integer id) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			Usuario usuarioPersistente = session.get(Usuario.class, id);
			if (usuarioPersistente != null) {

				usuarioPersistente.setNombre(modelo.getNombre());
				usuarioPersistente.setNombreUsuario(modelo.getNombreUsuario());
				usuarioPersistente.setContrasenna(modelo.getContrasenna());
				usuarioPersistente.setNombre(modelo.getNombre());
				usuarioPersistente.setApellidos(modelo.getApellidos());
				usuarioPersistente.setDomicilio(modelo.getDomicilio());
				usuarioPersistente.setTelefono(modelo.getTelefono());
				usuarioPersistente.setCorreo(modelo.getCorreo());
				usuarioPersistente.setFechaNac(modelo.getFechaNac());

				session.merge(usuarioPersistente);

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

			Usuario usuario = session.get(Usuario.class, id);
			if (usuario != null) {
				session.remove(usuario);
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
	public List<Usuario> listar() {
		List<Usuario> listaUsuario = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Usuario";
			Query<Usuario> query = session.createQuery(hql, Usuario.class);
			listaUsuario = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaUsuario;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * ------------------------
	 * 
	 * Añadido por Elton, esta incompleto, se debe añadir la logica segun lo que
	 * tengamos en la base de datos *********Por MAIL!!!!!
	 * 
	 * -----------------------------------------------------------------------------
	 * ---------------------
	 */
	public static Usuario buscarPorGoogleId(String googleId) {
		// Logica para buscar en la base de datos y devolver un usuario basado en el
		// googleId
		// Esto es solo un esqueleto y necesitarás implementar la lógica real basada en
		// cómo esté configurada tu base de datos y tus entidades de Hibernate.

		Session session = null;
		Usuario usuario = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			// Asumiendo que tienes una entidad Usuario y un campo googleId mapeado
			// adecuadamente.
			String query = "from Usuario where googleId = :googleId";
			usuario = (Usuario) session.createQuery(query).setParameter("googleId", googleId).uniqueResult();
		} catch (Exception e) {
			// Manejar excepción
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return usuario;
	}

	public static Usuario buscarPorCorreo(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * ------------------------
	 * 
	 * Añadido por Elton, se debe comprobar si funciona insertando mail o nombre de
	 * usuario *********Por MAIL!!!!!
	 * 
	 * -----------------------------------------------------------------------------
	 * ---------------------
	 */
	public Usuario buscarPorNombreUsuarioOCorreo(String identificador) {
		Usuario usuario = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String hql = "FROM Usuario u WHERE u.nombreUsuario = :identificador OR u.correo = :identificador";
			Query<Usuario> query = session.createQuery(hql, Usuario.class);
			query.setParameter("identificador", identificador);
			query.setMaxResults(1);
			usuario = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

}