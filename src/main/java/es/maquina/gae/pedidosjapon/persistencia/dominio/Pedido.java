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
package es.maquina.gae.pedidosjapon.persistencia.dominio;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Pedido implements Persistible<Long>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3663741852066684082L;

	@Id
	@JsonIgnore
	private Long id;
	private String nombrePedido;
	private String solicitante;
	private Double importeMaximo = 0D;
	private Boolean pagado = Boolean.FALSE;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNombrePedido() {
		return nombrePedido;
	}

	public void setNombrePedido(String nombrePedido) {
		this.nombrePedido = nombrePedido;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	public Double getImporteMaximo() {
		return importeMaximo;
	}

	public void setImporteMaximo(Double importeMaximo) {
		this.importeMaximo = importeMaximo;
	}

	public Boolean isPagado() {
		return pagado;
	}

	public void setPagado(Boolean pagado) {
		this.pagado = pagado;
	}

}
