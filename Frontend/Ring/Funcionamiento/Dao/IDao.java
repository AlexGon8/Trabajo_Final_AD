package Dao;

import java.util.List;

public interface IDao<T, V> {

	public boolean crear(T modelo);

	public T leer(V id);

	public boolean actualizar(T modelo, V id);

	public boolean eliminar(V id);

	public List<T> listar();
}
