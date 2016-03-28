package es.ric.sql;

public class PromocionVO {

	private int id;
	private String titulo;
	private String descripcion;
	private String url_imagen;
	private String fecha_ini;
	private String fecha_fin;
	
	public PromocionVO(int id, String titulo, String descripcion, String url_imagen, String fecha_ini, String fecha_fin) {
		this.id = id;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.url_imagen = url_imagen;
		this.fecha_ini = fecha_ini;
		this.fecha_fin = fecha_fin;
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getUrl_imagen() {
		return url_imagen;
	}

	public String getFecha_ini() {
		return fecha_ini;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	@Override
	public String toString() {
		return id + ", " + titulo + ", "+ descripcion + ", " + url_imagen + ", "
				+ fecha_ini + ", " + fecha_fin;
	}
	
}
