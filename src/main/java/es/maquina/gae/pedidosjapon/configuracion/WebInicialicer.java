package es.maquina.gae.pedidosjapon.configuracion;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import es.maquina.gae.pedidosjapon.controller.PedidoControllerImpl;

public class WebInicialicer implements WebApplicationInitializer {
	public void onStartup(ServletContext container) throws ServletException {

		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();

		ctx.register(PedidoControllerImpl.class);

		container.addListener(new ContextLoaderListener(ctx));

		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new ServletInitializer());
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
}
