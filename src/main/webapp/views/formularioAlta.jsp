<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<form action="index.jsp">

	<div>
		<h1>Nombre Del pedido:</h1>
		<input name="nombrePedido" type="text" value="">
	</div>
	<p>
	<div>
		<h1>Solicitante:</h1>
		<input name="solicitante" type="text" value="">
	</div>
	<p>
	<div>
		<h1>Importe Maximo (En Euros):</h1>
		<input name="importeMaximo" type="number" value=0>
	</div>

	<p>
	<div>
		<input type="submit" value="Enviar Pedido">
		<%@ page
			import="es.maquina.gae.pedidosjapon.persistencia.dominio.Pedido"%>
		<%@ page
			import="es.maquina.gae.pedidosjapon.repository.PedidoRepositoryImpl"%>
		<%
			Pedido pedido = new Pedido();
			pedido.setSolicitante(request.getParameter("solicitante"));

			//FIXME Arreglar cuando sepa mas de los jsp esta sentencia se ejecuta cuando carga la pagina y no se porque
			Double importe = 0D;
			if (request.getParameter("importeMaximo") != null) {
				importe = Double.valueOf(request.getParameter("importeMaximo"));
			}

			pedido.setImporteMaximo(importe);
			pedido.setNombrePedido(request.getParameter("nombrePedido"));

			// FIXME pequeña ñapa para guardar objetos sustituir por una llamada AJAX
			PedidoRepositoryImpl repository = new PedidoRepositoryImpl();
			repository.addOrUpdate(pedido);
		%>
	</div>
</form>
</html>
