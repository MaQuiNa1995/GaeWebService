/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package es.maquina.gae.pedidosjapon.repository;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Entity;

import es.maquina.gae.pedidosjapon.persistencia.dominio.Pedido;

@Repository
public class PedidoRepositoryImpl extends GenericCrudRepositoryImpl<Pedido> implements PedidoRepository {

	private String tablaRepository = "PEDIDO";

	private static final String IMPORTE_MAXIMO = "IMPORTE_MAXIMO";
	private static final String SOLICITANTE = "SOLICITANTE";
	private static final String NOMBRE_PEDIDO = "NOMBRE_PEDIDO";

	@Override
	public Pedido entityToPojo(Entity entidad) {

		String nombrePedido = (String) entidad.getProperty(NOMBRE_PEDIDO);
		String solicitante = (String) entidad.getProperty(SOLICITANTE);
		Long importeMaximo = (Long) entidad.getProperty(IMPORTE_MAXIMO);

		Pedido pedido = new Pedido();
		pedido.setNombrePedido(nombrePedido);
		pedido.setSolicitante(solicitante);
		pedido.setImporteMaximo(importeMaximo);

		return pedido;
	}

	@Override
	public Entity pojoToEntity(Pedido pojo) {
		Entity entidadGuardar = new Entity(tablaRepository);

		entidadGuardar.setIndexedProperty(NOMBRE_PEDIDO, pojo.getNombrePedido());
		entidadGuardar.setIndexedProperty(SOLICITANTE, pojo.getSolicitante());
		entidadGuardar.setProperty(IMPORTE_MAXIMO, pojo.getImporteMaximo());

		return entidadGuardar;
	}

	@Override
	public String getNombreTabla() {
		return tablaRepository;
	}
}