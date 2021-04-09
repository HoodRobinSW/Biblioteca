package vista;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

import controlador.BibliotecaController;
import excepciones.CampoObligatorioException;
import excepciones.IsbnException;
import modelo.Libro;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String isbn="978-84-415-2682-2",titulo="Un titulo",autor="Un autor",editorial="Un editorial",
				fechaRegistro="1999-05-26",fechaPrestado="2003-06-14 17:40:35",precio="5.5",prestado="False";
		try {
			Libro libro=new Libro(isbn, titulo, autor, editorial, fechaRegistro, fechaPrestado, precio, prestado);
			System.out.println(libro.toString());
		} catch (CampoObligatorioException | IsbnException | ParseException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}*/
		BibliotecaController bc=null;
		Scanner sc=null;
		try {
			bc=new BibliotecaController();
		} catch (IOException | CampoObligatorioException | IsbnException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Bienvenido a la biblioteca");
		Libro libro=null;
		boolean salir=false;
		while (!salir) {
			System.out.println("1. Mostrar libros");
			System.out.println("2. Añadir libro");
			System.out.println("3. Buscar libro");
			System.out.println("4. Eliminar libro");
			System.out.println("5. Prestar libro");
			System.out.println("6. Entregar libro");
			System.out.println("7. Editar libro");
			System.out.println("8. Filtrar por titulo");
			sc=new Scanner(System.in);
			String opcion=sc.next();
			sc=null;
			int opc=0;
			try {
				opc=Integer.parseInt(opcion);
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
			switch (opc) {
				case 1: bc.mostrarLibros();break;
				case 2: String isbn="978-84-415-2682-2",titulo="EL mejor titulo",autor="El mejor autor",editorial="La mejor editorial",
						fechaRegistro="1893-04-12",fechaPrestado="2005-01-02 22:05:43",precio="12.45",prestado="true";
					try {
						boolean añadido=bc.añadirLibro(isbn, titulo, autor, editorial, fechaRegistro, fechaPrestado, precio, prestado);
						if (añadido)
							System.out.println("Se añadio correctamente");
						else System.err.println("El libro ya se encontraba en la biblioteca");
					} catch (CampoObligatorioException | IsbnException | ParseException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					isbn="978-84-415-2682-2";titulo="EL mejor titulo";autor="El mejor autor";editorial="La mejor editorial";
							fechaRegistro="1893-04-12";fechaPrestado="2005-01-02 22:05:43";precio="12.45";prestado="true";
						try {
							boolean añadido=bc.añadirLibro(isbn, titulo, autor, editorial, fechaRegistro, fechaPrestado, precio, prestado);
							if (añadido)
								System.out.println("Se añadio correctamente");
							else System.err.println("El libro ya se encontraba en la biblioteca");
						} catch (CampoObligatorioException | IsbnException | ParseException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}break;
				case 3: System.out.println("Introduzca isbn por el que desea buscar: ");
					sc=new Scanner(System.in);
					isbn=sc.next();
					sc=null;
					libro=bc.buscarLibro(isbn);
					if (libro==null)
						System.err.println("No se encontro su libro");
					else System.out.println(libro.toString());
					libro=null;break;
				case 4: System.out.println("Introduzca isbn del libro que desea eliminar: ");
					sc=new Scanner(System.in);
					isbn=sc.next();
					sc=null;
					boolean eliminado=bc.eliminarLibro(isbn);
					if (eliminado)
						System.out.println("Su libro se eliminó correctamente");
					else System.err.println("No se encontro el libro");
					break;
				case 5: System.out.println("Introduzca isbn del libro que desea prestar: ");
					sc=new Scanner(System.in);
					isbn=sc.next();
					sc=null;
					boolean seHaPrestado=false;
					try {
						seHaPrestado = bc.prestarLibro(isbn);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());;
					}
					if (seHaPrestado)
						System.out.println("Su libro se presto correctamente");
					else System.err.println("No se encontro su libro");
					break;
				case 6: System.out.println("Introduzca isbn del libro que desea entregar: ");
					sc=new Scanner(System.in);
					isbn=sc.next();
					sc=null;
					boolean entregado=bc.entregarLibro(isbn);
					if (entregado)
						System.out.println("Se entrego su libro");
					else System.err.println("No se encontró su libro");
					break;
				case 7: System.out.println("Introduzca el isbn del libro que desea editar: ");
					sc=new Scanner(System.in);
					isbn=sc.next();
					sc=null;
					System.out.println("Introduzca el nuevo precio");
					sc=new Scanner(System.in);
					precio=sc.next();
					sc=null;
					boolean editado=false;
					try {
						editado=bc.editarLibro(isbn, precio);
						if (editado)
							System.out.println("Se edito su libro correctamente");
						else System.out.println("No se encontró su libro");
					} catch (NumberFormatException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 8: System.out.println("Introduzca el autor por el que desea buscar: ");
					sc=new Scanner(System.in);
					autor=sc.nextLine();
					sc=null;
					List<Libro> filtrado=bc.filtrarLibros(autor);
					if (filtrado.isEmpty())
						System.err.println("No se encontraron libros con ese autor");
					else {
						for (Libro lib:filtrado) {
							System.out.println(lib.toString());
						}
					}break;
				case 9: salir=true;break;
			}				
		}
	}

}
