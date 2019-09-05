package es.maquina.gae.pedidosjapon.persistencia.dominio;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Pedido implements Persistible<Long>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3663741852066684082L;

	@Id
	@JsonIgnore
	private Long id;
	@Index
	private String nombrePedido;
	@Index
	private String solicitante;
	private Double importeMaximo = 0D;
	private Boolean pagado = Boolean.FALSE;
	private String descripcion;
	private Date fechaAlta;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaAlta() {
		return (Date) fechaAlta.clone();
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = (Date) fechaAlta.clone();
	}

}
