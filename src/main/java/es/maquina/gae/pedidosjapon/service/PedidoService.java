package es.maquina.gae.pedidosjapon.service;

import java.util.List;

import es.maquina.gae.pedidosjapon.persistencia.dominio.Pedido;

public interface PedidoService {

	void guardarPedido(Pedido pedido);

	List<Pedido> verPedidos();

}