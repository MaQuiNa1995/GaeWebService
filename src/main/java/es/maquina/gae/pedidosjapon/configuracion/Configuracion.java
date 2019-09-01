package es.maquina.gae.pedidosjapon.configuracion;

import org.springframework.cloud.gcp.data.datastore.repository.config.EnableDatastoreRepositories;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuracion de beans (contexto de spring) a traves de Beans
 * 
 * @author MaQuiNa1995
 */
@Configuration
@EnableDatastoreRepositories
public class Configuracion {

	private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "es.maquina.gae.pedidosjapon";

}
