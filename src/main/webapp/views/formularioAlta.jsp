<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<style type="text/css" media="screen">
     <%@ include file="css/formularioAlta.css" %>
</style>
<html>
<form action="Formulario Alta De Pedidos">

	<div>
		<h1>Nombre Del Pedido:</h1>
		<input name="nombrePedidoInput" type="text" value="" required="required">
	</div>
	<p>
	<div>
		<h1>Solicitante:</h1>
		<input name="solicitanteInput" type="text" value="" required="required">
	</div>
	<p>
	<div>
		<h1>
		Importe Máximo (En Euros):
		</h1>
		<input name="importeMaximoInput" type="number" value=0 required="required">
	</div>

	<p>
	<div>
		<input type="submit" value="Enviar Pedido">
		
		<%@ page import="es.maquina.gae.pedidosjapon.persistencia.dominio.Pedido"%>
		<%@ page import="es.maquina.gae.pedidosjapon.repository.PedidoRepositoryImpl"%>
		
		<%
			Pedido pedido = new Pedido();
			pedido.setSolicitante(request.getParameter("solicitanteInput"));

			//FIXME Arreglar cuando sepa mas de los jsp esta sentencia se ejecuta cuando carga la pagina y no se porque
			Double importe = 0D;
			if (request.getParameter("importeMaximo") != null) {
				importe = Double.valueOf(request.getParameter("importeMaximoInput"));
			}

			pedido.setImporteMaximo(importe);
			pedido.setNombrePedido(request.getParameter("nombrePedidoInput"));

			// FIXME pequeña ñapa para guardar objetos sustituir por una llamada AJAX
			PedidoRepositoryImpl repository = new PedidoRepositoryImpl();
			repository.addOrUpdate(pedido);
		%>
	</div>
</form>
</html>
