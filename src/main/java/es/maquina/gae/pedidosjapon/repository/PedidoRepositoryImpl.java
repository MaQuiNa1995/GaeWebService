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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

import es.maquina.gae.pedidosjapon.persistencia.dominio.Pedido;

@Repository
public class PedidoRepositoryImpl extends GenericCrudDaoImpl implements PedidoRepository {

	private static final String NOMBRE_PEDIDO = "NOMBRE_PEDIDO";
	public static final String TABLA = "PEDIDO";

	@Override
	public void save(String nombrePedido) {

		Entity entidadGuardar = new Entity(TABLA);

		entidadGuardar.setProperty(NOMBRE_PEDIDO, nombrePedido);

		getDatastore().put(entidadGuardar);

	}

	@Override
	public List<Pedido> findAll() {
		Query query = new Query(TABLA);

		Iterator<Entity> iteradorEntidades = getDatastore().prepare(query)
				.asIterable(FetchOptions.Builder.withDefaults()).iterator();

		List<Pedido> listaPedidos = new ArrayList<>();

		while (iteradorEntidades.hasNext()) {
			Entity entidad = iteradorEntidades.next();

			String nombrePedido = (String) entidad.getProperty(NOMBRE_PEDIDO);

			Pedido pedido = new Pedido();
			pedido.setNombrePedido(nombrePedido);

			listaPedidos.add(pedido);

		}

		return listaPedidos;

	}

}
