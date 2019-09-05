<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!-- <style type="text/css" media="screen"> -->
<%--      <%@ include file="css/formularioAlta.css" %> --%>
<!-- </style> -->
<html>
<form action="Formulario Alta De Pedidos">

	<div>
		<h1>Nombre Del Pedido:</h1>
		<input name="nombrePedidoInput" type="text" value="" required="required">
	</div>
	<p>
	
	<div>
		<h1>Descripción Del Pedido:</h1>
		<input name="descripcionInput" type="text" value="" required="required">
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
		// Se hace una comprobación de si algun campo es nulo (solo puede ser nulo cuando se inicia la pagina) para evitar meter objetos basura en la base de datos
		if (request.getParameter("solicitanteInput") != null) {

			Pedido pedido = new Pedido();
			pedido.setSolicitante(request.getParameter("solicitanteInput"));
			pedido.setImporteMaximo(Double.parseDouble(request.getParameter("importeMaximoInput")));
			pedido.setNombrePedido(request.getParameter("nombrePedidoInput"));
			pedido.setDescripcion(request.getParameter("descripcionInput"));
			
			// FIXME pequeña ñapa para guardar objetos sustituir por una llamada AJAX
			PedidoRepositoryImpl repository = new PedidoRepositoryImpl();
			repository.addOrUpdate(pedido);
		}
		%>
	</div>
</form>
</html>
