package es.maquina.gae.pedidosjapon.persistencia.dominio;

public interface Persistible<T extends Number> {

	public T getId();

	public void setId(T id);
}
