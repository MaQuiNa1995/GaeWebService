package es.maquina.gae.pedidosjapon.repository;

import java.util.List;

import es.maquina.gae.pedidosjapon.persistencia.dominio.Pedido;

public interface PedidoRepository {

	void save(String nombrePedido);

	List<Pedido> findAll();

}