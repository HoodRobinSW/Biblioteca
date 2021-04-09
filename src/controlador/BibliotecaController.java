package controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import excepciones.CampoObligatorioException;
import excepciones.IsbnException;
import modelo.Libro;

public class BibliotecaController {

	private List<Libro> biblioteca;
	
	public BibliotecaController() throws IOException, CampoObligatorioException, IsbnException, ParseException {
		// TODO Auto-generated constructor stub
		this.biblioteca=leerFichero();
	}

	public List<Libro> leerFichero() throws IOException, CampoObligatorioException, IsbnException, ParseException{
		List<Libro> bibliotecaLeer=new ArrayList<>();
		FileReader fr=new FileReader("libros.txt");
		BufferedReader br=new BufferedReader(fr);
		String isbn=null,titulo=null,autor=null,editorial=null,
				fechaRegistro=null,fechaPrestado=null,precio=null,prestado=null;
		String linea=br.readLine();
		String lineaSplit[]=null;
		Libro libro=null;
		while (!(linea==null)) {
			lineaSplit=linea.split(",");
			isbn=lineaSplit[0];titulo=lineaSplit[1];autor=lineaSplit[2];editorial=lineaSplit[3];fechaRegistro=lineaSplit[4];
			fechaPrestado=lineaSplit[5];precio=lineaSplit[6];prestado=lineaSplit[7];
			libro=new Libro(isbn, titulo, autor, editorial, fechaRegistro, fechaPrestado, precio, prestado);
			bibliotecaLeer.add(libro);
			libro=null;
			linea=br.readLine();
		}
		return bibliotecaLeer;
	}
	
	public void mostrarLibros() {
		if (biblioteca.isEmpty())
			System.err.println("No hay libros en la biblioteca");
		for (Libro lib:biblioteca)
			System.out.println(lib.toString());
	}
	
	public boolean añadirLibro(String isbn, String titulo, String autor, String editorial, String fechaRegistro,
			String fechaPrestado, String precio, String prestado) throws CampoObligatorioException, IsbnException, ParseException {
		boolean añadido=false;
		Libro libro=new Libro(isbn, titulo, autor, editorial, fechaRegistro, fechaPrestado, precio, prestado);
		if (!biblioteca.contains(libro)) {
			biblioteca.add(libro);
			añadido=true;
		}
		
		return añadido;
	}
	
	public Libro buscarLibro(String isbn) {
		Libro libro=null;
		for (Libro lib:biblioteca) {
			if (isbn.equals(lib.getIsbn()))
				libro=lib;
		}
		
		return libro;
	}
	
	public boolean eliminarLibro(String isbn) {
		boolean eliminado=false;
		Libro libro=buscarLibro(isbn);
		if (!(libro==null)) {
			biblioteca.remove(libro);
			eliminado=true;
		}
		
		return eliminado;
	}
	
	public boolean prestarLibro(String isbn) throws ParseException {
		boolean prestado=false;
		Libro libro=buscarLibro(isbn);
		if (!(libro==null)) {
			java.util.Date fecha=new java.util.Date();
			java.sql.Timestamp fechaFormateda=new java.sql.Timestamp(fecha.getTime());
			libro.setFechaPrestado(fechaFormateda.toString());
			libro.setPrestado("true");
			prestado=true;
		}
		return prestado;
	}
	
	public boolean entregarLibro(String isbn) {
		boolean entregado=false;
		Libro libro=buscarLibro(isbn);
		if (!(libro==null)) {
			libro.setPrestado("false");
			entregado=true;
		}
			
		return entregado;
	}
	
	public boolean editarLibro(String isbn,String precio) throws NumberFormatException{
		boolean editado=false;
		Libro libro=buscarLibro(isbn);
		if (!(libro==null)) {
			libro.setPrecio(precio);
			editado=true;
		}
		
		return editado;
	}
	
	public List<Libro> filtrarLibros(String autor) {
		List<Libro> filtrado=new ArrayList<>();
		for (Libro lib:biblioteca) {
			if (autor.equals(lib.getAutor())) {
				filtrado.add(lib);
			}
		}
		
		return filtrado;
	}
	
	public List<Libro> getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(List<Libro> biblioteca) {
		this.biblioteca = biblioteca;
	}
	
}
