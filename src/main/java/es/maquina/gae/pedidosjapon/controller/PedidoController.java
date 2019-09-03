package es.maquina.gae.pedidosjapon.controller;

import java.util.List;

import es.maquina.gae.pedidosjapon.persistencia.dominio.Pedido;

public interface PedidoController {

	List<Pedido> verPedidos();

	void guardarPedido(Pedido pedido);

}