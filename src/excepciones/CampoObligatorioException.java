package excepciones;

public class CampoObligatorioException extends Exception{

	public CampoObligatorioException() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMessage() {
		return "Este Campo no puede estar vacio";
	}
}
