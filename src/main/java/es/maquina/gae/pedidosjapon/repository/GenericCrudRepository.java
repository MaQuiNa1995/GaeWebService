package es.maquina.gae.pedidosjapon.repository;

import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

import es.maquina.gae.pedidosjapon.persistencia.dominio.Persistible;

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
 * @author MaQuiNa1995
 * 
 * @see <a href="https://es.wikipedia.org/wiki/CRUD">Documentación CRUD</a>
 * 
 */
public interface GenericCrudRepository<T extends Persistible<Long>> {

	/**
	 * Metodo usado para crear o updatear una entidad
	 * 
	 * @param entidad objeto a insertar en base de datos
	 */
	void addOrUpdate(T entidad);

	/**
	 * Metodo usado para crear o updatear una lista de entidades
	 * 
	 * @param arrayEntidades java.util.ArrayList lista de objetos genericos para
	 *                       crear o modificar (depende de si tienen id o no las
	 *                       crea o las updatea)
	 */
	void addOrUpdate(List<T> entidades);

	/**
	 * Método usado para recuperar de base de datos un objeto por su id
	 * 
	 * @param id ID en base de datos del objeto
	 * 
	 * @return objeto recuperado de base de datos
	 */
	T readById(Long id);

	/**
	 * Método usado para obtener una lista de entidades de base de datos a traves de
	 * su Id
	 * 
	 * @param listaIds java.util.List de la entidad que queremos obtener de base de
	 *                 datos
	 * 
	 * @return java.util.List de objetos recuperados
	 */
	List<T> readByIdList(List<Long> listaIds);

	/**
	 * Método usado para obtener una lista de objetos de base de datos haciendo un
	 * SELECT * FROM {@link #getNombreTabla()} paginado a
	 * {@link GenericCrudRepositoryImpl#PAGINACION}
	 * 
	 * @return {@link java.util.List} de objetos leidos
	 */
	List<T> findAll();

	/**
	 * Operacion de Delete en base de datos
	 * 
	 * @param listaEntidades lista de objetos que se borraran de BD
	 */
	void delete(List<T> listaEntidades);

	/**
	 * Método usado para devolver el nombre de la tabla
	 * 
	 * @return java.lang.String que ahce referencia al nombre de la tabla del
	 *         repository
	 */
	String getNombreTabla();

	/**
	 * Método usado para pasar de un POJO a una
	 * com.google.appengine.api.datastore.Entity
	 * 
	 * @param pojo objeto a transformar en com.google.appengine.api.datastore.Entity
	 * 
	 * @return com.google.appengine.api.datastore.Entity transformada
	 */
	Entity pojoToEntity(T pojo);

	/**
	 * Método usado para pasar de un una com.google.appengine.api.datastore.Entity a
	 * un POJO
	 * 
	 * @param com.google.appengine.api.datastore.Entity a transformar
	 * 
	 * @return pojo convertido
	 */
	T entityToPojo(Entity entidad);

}