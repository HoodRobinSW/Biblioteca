package modelo;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import excepciones.CampoObligatorioException;
import excepciones.IsbnException;

public class Libro {

	private String isbn;
	private String titulo;
	private String autor;
	private String editorial;
	private java.sql.Date fechaRegistro;
	private java.sql.Timestamp fechaPrestado;
	private double precio;
	private boolean prestado;
		
	public Libro() {
		// TODO Auto-generated constructor stub
		this.isbn=null;
		this.titulo=null;
		this.autor=null;
		this.editorial=null;
		this.fechaRegistro=null;
		this.fechaPrestado=null;
		this.precio=0;
		this.prestado=false;
	}

	
	
	public Libro(String isbn, String titulo, String autor, String editorial, String fechaRegistro,
			String fechaPrestado, String precio, String prestado) throws CampoObligatorioException, IsbnException, ParseException {
		super();
		this.setIsbn(isbn);
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setEditorial(editorial);
		this.setFechaRegistro(fechaRegistro);
		this.setFechaPrestado(fechaPrestado);
		this.setPrecio(precio);
		this.setPrestado(prestado);
	}



	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn)  throws CampoObligatorioException,IsbnException{
		if (isbn.isEmpty())
			throw new CampoObligatorioException();
		String compruebaIsbn=isbn.replace("-", "");
		if (compruebaIsbn.length()!=13)
			throw new IsbnException();
		int dc=Integer.parseInt(Character.toString(compruebaIsbn.charAt(12)));
		int sumaIsbn=0;
		for (int x=0;x<compruebaIsbn.length()-1;x++) {
			if (x%2==0)
				sumaIsbn+=Integer.parseInt(Character.toString(compruebaIsbn.charAt(x)));
			else sumaIsbn+=Integer.parseInt(Character.toString(compruebaIsbn.charAt(x)))*3;
		}
		int compruebaDc=10-(sumaIsbn%10);
		if (compruebaDc==10)
			compruebaDc=0;
		if (compruebaDc!=dc)
			throw new IsbnException();
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) throws CampoObligatorioException{
		if (titulo.isEmpty())
			throw new CampoObligatorioException();
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) throws CampoObligatorioException{
		if (autor.isEmpty())
			throw new CampoObligatorioException();
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public java.sql.Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) throws ParseException {
		java.util.Date fecha=null;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false);
		fecha=sdf.parse(fechaRegistro);
		this.fechaRegistro = new java.sql.Date(fecha.getTime());
	}

	public java.sql.Timestamp getFechaPrestado() {
		
		return fechaPrestado;
	}

	public void setFechaPrestado(String fechaPrestado) throws ParseException {
		if (fechaPrestado.equals("null")||fechaPrestado.equals(""))
			this.fechaPrestado=null;
		else {
			java.util.Date fecha=null;
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setLenient(false);
			fecha=sdf.parse(fechaPrestado);
			this.fechaPrestado = new java.sql.Timestamp(fecha.getTime());
		}
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) throws NumberFormatException{
		this.precio = Double.parseDouble(precio);
	}

	public boolean isPrestado() {
		return prestado;
	}

	public void setPrestado(String prestado) {
		this.prestado = Boolean.parseBoolean(prestado);
	}

	@Override
	public String toString() {
		return isbn+","+titulo+","+autor+","+editorial+","+fechaRegistro+","+fechaPrestado+","+precio+","+prestado;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		return true;
	}

}
