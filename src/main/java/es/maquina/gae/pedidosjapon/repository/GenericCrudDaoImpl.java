package es.maquina.gae.pedidosjapon.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

import es.maquina.gae.pedidosjapon.persistencia.dominio.Persistible;

/**
 * Implementación de la interfaz {@link GenericCrudDao} que contiene las
 * operaciones básicas de base de datos
 * <p>
 * Esta clase no tiene javadoc porque se obtiene de la interfaz y está linkada a
 * esta si quieres mas información ve a la interfaz anteriormente mencionada
 * 
 * @author cmunozas
 *
 */
public abstract class GenericCrudDaoImpl<T extends Persistible<Long>> implements GenericCrudDao<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericCrudDaoImpl.class);

	/**
	 * Paginación por defecto
	 */
	protected static final FetchOptions PAGINACION = FetchOptions.Builder.withLimit(300);

	/**
	 * Objeto que nos da acceso a la base de datos de google
	 */
	private DatastoreService datastore = null;

	/**
	 * Constructor por defecto que inicializa el objeto de la base de datos
	 */
	private GenericCrudDaoImpl() {
		if (datastore == null) {
			datastore = DatastoreServiceFactory.getDatastoreService();
		}
	}

	@Override
	public abstract T entityToPojo(Entity entidad);

	@Override
	public abstract Entity pojoToEntity(T pojo);

	@Override
	public abstract String getNombreTabla();

	@Override
	public void addOrUpdate(List<T> entidades) {
		Transaction transaccion = datastore.beginTransaction();

		List<Entity> listaEntidades = new ArrayList<>();

		entidades.stream().forEach(e -> {
			listaEntidades.add(pojoToEntity(e));
		});

		datastore.put(listaEntidades);

		transaccion.commit();

		LOGGER.info(String.format("Se añaden %1$s entidad/es a la base de datos", entidades.size()));

	}

	@Override
	public T readById(Long id) {

		T entidad = null;

		String tabla = getNombreTabla();

		if (id != null && StringUtils.isBlank(tabla) == Boolean.FALSE) {

			Key idBaseDatos = KeyFactory.createKey(getNombreTabla(), id);

			try {
				entidad = entityToPojo(datastore.get(idBaseDatos));
				LOGGER.info("Se ha recuperado la entidad satisfactoriamente");
			} catch (EntityNotFoundException e) {
				LOGGER.error("No se ha encontrado la entidad con el id especificado");
			}

		} else {
			LOGGER.error("La id o la tabla pasados como parametro son inválidos");
		}

		return entidad;
	}

	@Override
	public List<T> readByIdList(List<Long> listaIds) {

		List<T> listaObjetos = new ArrayList<>();

		String tabla = getNombreTabla();

		if (listaIds != null && listaIds.isEmpty() == Boolean.FALSE && StringUtils.isBlank(tabla) == Boolean.FALSE) {
			List<Key> listaKeys = new ArrayList<>();

			listaIds.stream().filter(Objects::nonNull).forEach(e -> {
				listaKeys.add(KeyFactory.createKey(getNombreTabla(), e));
			});

			List<Entity> listaEntidades = (List<Entity>) datastore.get(listaKeys).values();

			LOGGER.info(String.format("Se ha recuperado  %1$s Entidades", listaEntidades.size()));

			listaEntidades.stream().forEach(e -> {
				listaObjetos.add(entityToPojo(e));
			});

		} else {
			LOGGER.error(
					"la lista que se le ha pasado como parametro al método está vacía o es nula se devuelve una lista vacía");
		}

		return listaObjetos;
	}

	@Override
	public List<T> findAll() {

		List<T> listaEntidades = new ArrayList<>();

		String tabla = getNombreTabla();

		if (StringUtils.isBlank(tabla) == Boolean.FALSE) {
			Query query = new Query(tabla);
			List<Entity> listaEntidadesBd = getDatastore().prepare(query).asList(FetchOptions.Builder.withDefaults());

			listaEntidadesBd.stream().forEach(e -> {
				listaEntidades.add(entityToPojo(e));
			});

		} else {
			LOGGER.error("el nombre de la tabla pasado como argumento no es válido");

		}

		return listaEntidades;

	}

	@Override
	public void delete(List<T> listaEntidades) {

		// Se crea una lista para hacer un borrado de una lista de Id
		List<Key> listaKeys = new ArrayList<>();

		listaEntidades.stream().map(e -> e.getId()).forEach(e -> {
			listaKeys.add(KeyFactory.createKey(getNombreTabla(), e));
		});

		Transaction transaccion = datastore.beginTransaction();

		datastore.delete(listaKeys);

		transaccion.commit();

		LOGGER.info("Se han borrado {0} entidad/es ", listaEntidades.size());
	}

	/**
	 * Método usado para obtener la base de datos para poder hacer consultas mas
	 * específicas fuera del rango del Crud básico
	 * 
	 * @return {@link com.google.appengine.api.datastore.DatastoreService} objeto
	 *         que permite el acceso a base de datos para consultas mas
	 *         especializadas
	 */
	protected DatastoreService getDatastore() {
		return datastore;
	}

}
