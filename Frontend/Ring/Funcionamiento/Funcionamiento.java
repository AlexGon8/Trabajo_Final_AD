import java.sql.Date;
import java.util.ArrayList;

import Clases.Juego;
import Clases.Rol;
import Dao.CompaniaDAO;
import Dao.ConsolaDAO;
import Dao.JuegoDAO;
import Dao.RolDAO;
import Dao.UsuarioDAO;

public class Funcionamiento {

	private JuegoDAO juegoDAO = new JuegoDAO();
	private ConsolaDAO consolaDAO = new ConsolaDAO();
	private CompaniaDAO companiaDAO = new CompaniaDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	private RolDAO rolDAO = new RolDAO();

	public Funcionamiento() {

	}

	public Funcionamiento(JuegoDAO juegoDAO, ConsolaDAO consolaDAO, CompaniaDAO companiaDAO, UsuarioDAO usuarioDAO,
			RolDAO rolDAO) {
		this.juegoDAO = juegoDAO;
		this.consolaDAO = consolaDAO;
		this.companiaDAO = companiaDAO;
		this.usuarioDAO = usuarioDAO;
		this.rolDAO = rolDAO;
	}

	public JuegoDAO getJuegoDAO() {
		return juegoDAO;
	}

	public void setJuegoDAO(JuegoDAO juegoDAO) {
		this.juegoDAO = juegoDAO;
	}

	public ConsolaDAO getConsolaDAO() {
		return consolaDAO;
	}

	public void setConsolaDAO(ConsolaDAO consolaDAO) {
		this.consolaDAO = consolaDAO;
	}

	public CompaniaDAO getCompaniaDAO() {
		return companiaDAO;
	}

	public void setCompaniaDAO(CompaniaDAO companiaDAO) {
		this.companiaDAO = companiaDAO;
	}

	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	public RolDAO getRolDAO() {
		return rolDAO;
	}

	public void setRolDAO(RolDAO rolDAO) {
		this.rolDAO = rolDAO;
	}

	public void generarRbac() {

		Rol admin = new Rol("admin");
		Rol distribuidor = new Rol("distribuidor");
		Rol cliente = new Rol("cliente");
		Rol almacen = new Rol("almacen");
		Rol soporte_tecnico = new Rol("soporte_tecnico");
		Rol editor = new Rol("editor");
	}

	public void generarRoles() {

	}
}
