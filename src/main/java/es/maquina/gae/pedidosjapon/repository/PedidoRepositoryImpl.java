package es.maquina.gae.pedidosjapon.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Entity;

import es.maquina.gae.pedidosjapon.persistencia.dominio.Pedido;

@Repository
public class PedidoRepositoryImpl extends GenericCrudRepositoryImpl<Pedido> implements PedidoRepository {

	private static final Double VALOR_YENES_EURO = 117.195D;

	private String tablaRepository = "PEDIDO";

	private static final String IMPORTE_MAXIMO = "IMPORTE_MAXIMO";
	private static final String SOLICITANTE = "SOLICITANTE";
	private static final String NOMBRE_PEDIDO = "NOMBRE_PEDIDO";
	private static final String PAGADO = "PAGADO";
	private static final String DESCRIPCION = "DESCRIPCION";
	private static final String FECHA = "FECHA";

	@Override
	public Pedido entityToPojo(Entity entidad) {

		String nombrePedido = (String) entidad.getProperty(NOMBRE_PEDIDO);
		String solicitante = (String) entidad.getProperty(SOLICITANTE);
		Boolean isPagado = (Boolean) entidad.getProperty(PAGADO);
		String descripcion = (String) entidad.getProperty(DESCRIPCION);
		// FIXME arreglar ya que en base de datos se guarda como Double
		Double importeMaximo = (Double) entidad.getProperty(IMPORTE_MAXIMO);

		Pedido pedido = new Pedido();
		pedido.setNombrePedido(nombrePedido);
		pedido.setSolicitante(solicitante);
		pedido.setImporteMaximo(importeMaximo);
		pedido.setPagado(isPagado);
		pedido.setDescripcion(descripcion);

		return pedido;
	}

	@Override
	public Entity pojoToEntity(Pedido pojo) {
		Entity entidadGuardar = new Entity(tablaRepository);

		entidadGuardar.setIndexedProperty(NOMBRE_PEDIDO, pojo.getNombrePedido());
		entidadGuardar.setIndexedProperty(SOLICITANTE, pojo.getSolicitante());

		// FIXME se hace aqui como medida temporal hasta que sepa como hacer llamadas
		// ajax al controller
		entidadGuardar.setProperty(IMPORTE_MAXIMO, euroToYen(pojo.getImporteMaximo()));
		entidadGuardar.setProperty(PAGADO, Boolean.FALSE);
		entidadGuardar.setProperty(DESCRIPCION, pojo.getDescripcion());
		entidadGuardar.setProperty(FECHA, new Date());

		return entidadGuardar;
	}

	/**
	 * 
	 * @param euro
	 * @return
	 */
	private Double euroToYen(Double euro) {
		return euro * VALOR_YENES_EURO;
	}

	@Override
	public String getNombreTabla() {
		return tablaRepository;
	}
}