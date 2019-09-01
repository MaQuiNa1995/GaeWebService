package es.maquina.gae.pedidosjapon.repository;

import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

/**
 * Dao genérico para las operaciones CRUD básicas con implementación de
 * transacciones
 * 
 * <UL>
 * <LI>Create y Update</LI>
 * <UL>
 * {@link #addOrUpdate(Entity...)}
 * </UL>
 * </UL>
 * <UL>
 * <LI>Read</LI>
 * <UL>
 * {@link #readById(Key)}
 * </UL>
 * <UL>
 * {@link #readByIdList(List)}
 * </UL>
 * </UL>
 * <UL>
 * <LI>Delete</LI>
 * <UL>
 * {@link #delete(Entity...)}
 * </UL>
 * </UL>
 * 
 * @author cmunozas
 * 
 * @see <a href="https://es.wikipedia.org/wiki/CRUD">Documentación CRUD</a>
 * 
 */
public interface GenericCrudDao {

	/**
	 * Metodo usado para crear o updatear una entidad
	 * 
	 * @param arrayEntidades array de
	 *                       {@link com.google.appengine.api.datastore.Entity} para
	 *                       crear o modificar (depende de si tienen id o no las
	 *                       crea o las updatea)
	 * @return array de {@link com.google.appengine.api.datastore.Entity} con los
	 *         nuevos ids asignados en caso de ser un create o en el otro caso de
	 *         que sea un update la misma entidad
	 */
	Entity[] addOrUpdate(Entity... arrayEntidades);

	/**
	 * Método usado para obtener una lista de entidades de base de datos a traves de
	 * su Id {@link com.google.appengine.api.datastore.Key}
	 * 
	 * @param id {@link com.google.appengine.api.datastore.Key} de la entidad que
	 *           queremos obtener de base de datos
	 * 
	 * @return {@link com.google.appengine.api.datastore.Entity} de la que queremos
	 *         sacar información en caso de que no se encuentre la entidad o el id
	 *         sea null , se devuelve una entidad nula
	 */
	Entity readById(Key id);

	/**
	 * Método usado para obtener una lista de entidades de base de datos a traves de
	 * su Id {@link com.google.appengine.api.datastore.Key}
	 * 
	 * @param listaIds lista de {@link com.google.appengine.api.datastore.Key} de
	 *                 las entidades que queremos obtener de base de datos
	 * 
	 * @return {@link java.util.List} de
	 *         {@link com.google.appengine.api.datastore.Entity} que se han
	 *         recuperado de base de datos, si no encuentra ninguna o la lista
	 *         pasada como parámetro esta vacía o nula se devuelve una lista vacía
	 */
	List<Entity> readByIdList(List<Key> listaIds);

	/**
	 * Operacion de Delete en base de datos
	 * 
	 * @param arrayEntidades array de
	 *                       {@link com.google.appengine.api.datastore.Entity} que
	 *                       se se borrarán de base de datos
	 */
	void delete(Entity... entidades);

}