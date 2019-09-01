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

@Repository
public class PedidoRepositoryImpl extends GenericCrudDaoImpl implements PedidoRepository {

	public static final String TABLA = "PEDIDO";

	/* (non-Javadoc)
	 * @see es.maquina.gae.pedidosjapon.repository.PedidoRepository#save(java.lang.String)
	 */
	public void save(String nombrePedido) {

		Entity entidadGuardar = new Entity(TABLA);

		entidadGuardar.setProperty("NOMBRE_PEDIDO", nombrePedido);

	}

}
