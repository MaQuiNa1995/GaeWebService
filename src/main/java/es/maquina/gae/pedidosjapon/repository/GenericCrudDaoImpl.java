package es.maquina.gae.pedidosjapon.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;

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
public abstract class GenericCrudDaoImpl implements GenericCrudDao {

	private static final Logger LOGGER = Logger.getLogger(GenericCrudDaoImpl.class.getName());

	protected static final FetchOptions PAGINACION = FetchOptions.Builder.withLimit(300);

	/**
	 * Objeto que nos da acceso a la base de datos de google
	 */
	private DatastoreService datastore = null;

	/**
	 * Constructor por defecto que inicializa el objeto de la base de datos
	 */
	protected GenericCrudDaoImpl() {
		if (datastore == null) {
			datastore = DatastoreServiceFactory.getDatastoreService();
		}
	}

	@Override
	public Entity[] addOrUpdate(Entity... entidades) {

		Transaction transaccion = datastore.beginTransaction();

		// Se crea o updatean las entidades del array que se pasó como parametro
		// pasándolo a una lista
		datastore.put(Arrays.asList(entidades));

		transaccion.commit();

		LOGGER.info(String.format("Se añaden %1$s entidad/es a la base de datos", entidades.length));

		return entidades;
	}

	@Override
	public Entity readById(Key id) {

		Entity entidad = null;

		if (id != null) {
			try {

				// Se recupera la entidad de base de datos por Id
				entidad = datastore.get(id);
				LOGGER.info(String.format("Se ha recuperado la entidad con id %1$s", entidad.getKey().getId()));

			} catch (EntityNotFoundException exception) {
				entidad = null;
				LOGGER.warning(String.format("La entidad con id: %1$s no existe en la base de datos mas info: %2$s ",
						id, exception.getMessage()));
			}
		}

		return entidad;
	}

	@Override
	public List<Entity> readByIdList(List<Key> listaIds) {

		List<Entity> listaEntidades = new ArrayList<>();

		if (listaIds != null && listaIds.isEmpty() == Boolean.FALSE) {

			// Se hace un arraylist nuevo porque el .values() devuelve una coleccion y de
			// esta forma nos evitamos un cast explicito
			listaEntidades = new ArrayList<>(datastore.get(listaIds).values());
			LOGGER.info(String.format("Se ha recuperado las entidades con ids %1$s", listaIds.toArray()));

		} else {
			LOGGER.warning(
					"la lista que se le ha pasado como parametro al método está vacía o es nula se devuelve una lista vacía");
		}

		return listaEntidades;
	}

	@Override
	public void delete(Entity... entidades) {

		// Se crea una lista para hacer un borrado de una lista de Id
		List<Key> listaIdBorrar = new ArrayList<>();
		for (Entity entidad : entidades) {
			listaIdBorrar.add(entidad.getKey());
		}

		Transaction transaccion = datastore.beginTransaction();

		// Se borran las entidades
		datastore.delete(listaIdBorrar);

		transaccion.commit();

		LOGGER.info(String.format("Se han borrado entidad/es con id:  %1$s", listaIdBorrar.toArray()));
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
