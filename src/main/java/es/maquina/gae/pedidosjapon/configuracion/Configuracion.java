package es.maquina.gae.pedidosjapon.configuracion;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * Clase de configuracion de beans (contexto de spring) a traves de Beans
 * 
 * @author MaQuiNa1995
 */
@Configuration
public class Configuracion {

	private static final String ENTITYMANAGER_PACKAGES_TO_SCAN = "es.maquina.gae.pedidosjapon";

	/**
	 * Creación del bean encargado de la conexión con base de datos
	 * 
	 * @return {@link javax.sql.DataSource} objeto encargado de la conexión a base
	 *         de datos
	 */
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
		dataSource.setUrl("jdbc:hsqldb:mem:maquina1995");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		dataSource.setInitialSize(5);
		dataSource.setMaxIdle(10);
		dataSource.setPoolPreparedStatements(Boolean.TRUE);
		dataSource.setMaxOpenPreparedStatements(5);
		return dataSource;

	}

	/**
	 * Creación del bean encargado de las transacciones
	 * 
	 * @return {@link org.springframework.orm.jpa.JpaTransactionManager} objeto
	 *         encargado de las transacciones en base de datos
	 */
	@Bean
	public JpaTransactionManager jpaTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
		return transactionManager;
	}

	/**
	 * Creación del bean encargado de gestionar las entidades que podamos tener en
	 * la aplicación
	 * 
	 * @return {@link org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean}
	 *         objeto encargado de la gestion de entidades
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdaptor());
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceUnitName("MaQuiNaPersistenceUnit");
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);

		return entityManagerFactoryBean;
	}

	/**
	 * Método encargado de la configuración de la base de datos que se va a usar en
	 * la aplicación
	 * 
	 * @return {@link org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter}
	 */
	private HibernateJpaVendorAdapter hibernateJpaVendorAdaptor() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setShowSql(Boolean.TRUE);
		vendorAdapter.setGenerateDdl(Boolean.TRUE);
		vendorAdapter.setDatabase(Database.HSQL);
		return vendorAdapter;
	}

}
