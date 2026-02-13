package persistencia;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import modelo.Pedido;
import vista.Mensaje;

//Para poner los pedidos en un .txt
import java.io.IOException;

public class RegistrarOperacion {
	
    private Mensaje mensaje; 
    
    
    public RegistrarOperacion() {
    

    	mensaje = new Mensaje();
    }

	public void guardarPedidoEnTxt(Pedido pedido, String codigo) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("pedidosConCapas.txt", true))) {

            LocalDateTime ahora = LocalDateTime.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            bw.write("Pedido: " + pedido);
            bw.newLine();
          
            bw.write("Codigo: " + codigo); // MISMO código
            bw.newLine();
           
            bw.write("Fecha: " + ahora.format(formato));
            bw.newLine();
            
            bw.write("------------------------------------");	
            bw.newLine();

        } catch (IOException e) {
        	
            mensaje.println("Error al guardar el pedido en el archivo");
        }
    }


}
