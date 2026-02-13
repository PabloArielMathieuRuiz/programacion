package validacion;
import java.util.Scanner;
import vista.Mensaje;

public class ValidacionInt {
    private Mensaje mensaje; 

	public ValidacionInt() {
		
		mensaje = new Mensaje();
		
	}
	
	
    // --- LEE UN ENTERO DE FORMA SEGURA ---
    public int readInt(Scanner sc) {
        
        
        // Mientras no sea un entero, vuelve a pedirlo
        while (!sc.hasNextInt()) {
            mensaje.print("Porfavor introduce un numero: ");
            sc.next();
        }
        
        return sc.nextInt();
    }
}
