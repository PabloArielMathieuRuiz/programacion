package vista;

import java.util.Scanner;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



import java.util.Map;
import java.util.Random;

import validacion.ValidacionInt;
import modelo.Pedido;

public class VistaConsola {
    private Mensaje mensaje; 

    private Scanner sc;
    private ValidacionInt validacion;
    

    public VistaConsola() {
        sc = new Scanner(System.in);
        validacion = new ValidacionInt();
        mensaje = new Mensaje();
    }

    // --- MENÚ PRINCIPAL ---
    public int mostrarMenuPrincipal() {
        mensaje.println("\n== MENÚ ==");
        mensaje.println("1. Añadir pedido");
        mensaje.println("2. Mostrar pedidos pendientes");
        mensaje.println("3. Mostrar menú");
        mensaje.println("4. Atender pedido");
        mensaje.println("5. Eliminar pedido");
        mensaje.println("6. Ver pedidos atendidos");
        mensaje.println("0. Salir");
        mensaje.print("Introduzca su elección: ");

        int opcion = validacion.readInt(sc);
        sc.nextLine(); // limpiar buffer
        return opcion;
    }

    // --- PEDIR NOMBRE DEL CLIENTE ---
    public String pedirNombreCliente() {
        mensaje.print("Nombre del cliente: ");
        return sc.nextLine();
    }

    // --- PEDIR PRODUCTOS ---
    public void pedirProductos( ArrayList<String> productos, Map<String, Double> menu) {

        String producto = "";

        do {
            mensaje.print("¿Qué desea pedir? (fin para terminar): ");

            producto = sc.nextLine().toLowerCase().trim();

            if (!producto.equals("fin")) {

                if (menu.containsKey(producto)) {
                    productos.add(producto);
                } else {
                    mensaje.println("Producto no disponible.");
                    mostrarHashMap(menu);
                }
            }

        } while (!producto.equals("fin"));
    }


    // --- MOSTRAR PEDIDOS ---
    public void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            mensaje.println("No hay pedidos.");
            return;
        }
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }

    
    //recibe los menus como parametro
    public void mostrarHashMap(Map<String, Double> menuHashMap) {
    	
        mensaje.println("\nMenú (HashMap - sin ordenar):");
        for (Map.Entry<String, Double> entry : menuHashMap.entrySet()) {
            mensaje.println(entry.getKey() + " - " + entry.getValue() + "€");
        }
    }
    
    //recibe los menus como parametro
    public void mostrarTreeMap(Map<String, Double> menuTreeMap) {
        mensaje.println("\nMenú (TreeMap - ordenado alfabéticamente):");
        for (Map.Entry<String, Double> entry : menuTreeMap.entrySet()) {
            mensaje.println(entry.getKey() + " - " + entry.getValue() + "€");
        }
    }
    
    public void registrarOperacion(String codigo) {
 		
 		// Fecha y hora actual
 		LocalDateTime ahora = LocalDateTime.now();
 		
        // Formato de fecha
 		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

 		// Mostramos código y fecha
 		mensaje.println("Código de operación: " + codigo);
 		mensaje.println("Fecha y hora: " + ahora.format(formato));	
 	}

 	// --- GENERADOR DE CÓDIGO ---
 	public String generarCodigoOperacion() {
 		
 		Random random = new Random();
 		String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
 		String codigo = "";

 		// Genera 3 letras
 		for (int i = 0; i < 3; i++) {
 			int indiceLetra = random.nextInt(letras.length());
 			codigo += letras.charAt(indiceLetra);
 		}

 		// Genera 4 números
 		for (int i = 0; i < 4; i++) {
 			int numero = random.nextInt(10);
 			codigo += numero;
 		}

 		return codigo;
 	}

    // --- CERRAR RECURSOS ---
    public void cerrarRecursos() {
        mensaje.println("Gracias por usar el servicio");
        sc.close();
    }
}
