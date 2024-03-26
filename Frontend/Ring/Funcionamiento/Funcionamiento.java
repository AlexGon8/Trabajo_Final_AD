import java.sql.Date;
import java.util.ArrayList;

import Clases.Juego;
import Dao.CompaniaDAO;
import Dao.ConsolaDAO;
import Dao.JuegoDAO;
import Dao.PermisosDAO;
import Dao.RolDAO;
import Dao.UsuarioDAO;

public class Funcionamiento {

	private JuegoDAO juegoDAO;
	private ConsolaDAO consolaDAO;
	private CompaniaDAO companiaDAO;
	private PermisosDAO permisosDAO;
	private UsuarioDAO usuarioDAO;
	private RolDAO rolDAO;

	public Funcionamiento() {

	}

	public Funcionamiento(JuegoDAO juegoDAO, ConsolaDAO consolaDAO, CompaniaDAO companiaDAO, PermisosDAO permisosDAO,
			UsuarioDAO usuarioDAO, RolDAO rolDAO) {
		this.juegoDAO = juegoDAO;
		this.consolaDAO = consolaDAO;
		this.companiaDAO = companiaDAO;
		this.permisosDAO = permisosDAO;
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

	public PermisosDAO getPermisosDAO() {
		return permisosDAO;
	}

	public void setPermisosDAO(PermisosDAO permisosDAO) {
		this.permisosDAO = permisosDAO;
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

}
