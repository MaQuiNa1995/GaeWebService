<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>
<form action="index.jsp">

	<div>
		<h1>Nombre Del pedido:</h1>
		<input name="nombrePedido" type="text"
			value=<%=request.getParameter("nombrePedido")%>>
	</div>
	<p>
	<div>
		<h1>Solicitante:</h1>
		<input name="solicitante" type="text"
			value=<%=request.getParameter("solicitante")%>>
	</div>
	<p>
	<div>
		<h1>Importe Maximo:</h1>
		<input name="importeMaximo" type="text"
			value=<%=request.getParameter("importeMaximo")%>>
	</div>
	<p>
	<div>
		<input type="submit" value="Enviar">
		<%@page
			import="es.maquina.gae.pedidosjapon.persistencia.dominio.Pedido"%>
		<%
			Pedido pedido = new Pedido();
			pedido.setSolicitante(request.getParameter("solicitante"));
			pedido.setSolicitante(request.getParameter("importeMaximo"));
			pedido.setNombrePedido(request.getParameter("nombrePedido"));

			// FIXME quitar prueba para ver como se formaba el objeto
			System.out.println(pedido.toString());
		%>
	</div>
</form>
</html>
