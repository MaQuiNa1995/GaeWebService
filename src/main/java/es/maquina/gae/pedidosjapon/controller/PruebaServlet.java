package es.maquina.gae.pedidosjapon.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "pruebaServlet")
public class PruebaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2494956134327682249L;

//	@Override
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//		Properties properties = System.getProperties();
//
//		response.setContentType("text/plain");
//		response.getWriter().println("Hello App Engine - Standard using " + SystemProperty.version.get() + " Java "
//				+ properties.get("java.specification.version"));
//	}

	public static String getInfo() {
		return "Version: " + System.getProperty("java.version") + " OS: " + System.getProperty("os.name") + " User: "
				+ System.getProperty("user.name");
	}

}
