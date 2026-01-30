package nombreFuturo;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
public class Menu {
	
	private final String Separador_Pedido = " - ";
	private final String Separador_Productos = ", ";
	
	
	
	public final Scanner sc = new Scanner(System.in);
	public final ArrayList<String> pedidosArrayList = new ArrayList<>();
	public final LinkedList<String> pedidosLinkedlist = new LinkedList<>();
	public final Map <String, Double> menuHashMap = new HashMap<>();
	public final TreeMap <String, Double> menuTreeMap = new TreeMap<>(menuHashMap);
	
	public static void main(String[] args) {

	    Menu menu = new Menu();

	    menu.loadProducto();
	    int opcion;

	    do {
	        opcion = menu.mostrarMenu();

	        switch (opcion) {
	            case 1:
	                menu.añadirpedido();
	                break;
	            case 2:
	                menu.mostrarPedido();
	                break;
	            case 3:
	                menu.mostrarmenu();
	                break;
	            case 4:
	                menu.atenderPedido();
	                break;
	            case 5:
	                System.out.println("Gracias por usar el servicio");
	                break;
	            default:
	                System.out.println("Opción no válida. Inténtelo de nuevo.");
	        }
	    } while (opcion != 5);
	}


	// --- MENÚ PRINCIPAL ---
	public int mostrarMenu() {
		System.out.println("== Menú ==");
		System.out.println("1. añadirpedido");
		System.out.println("2. mostrar pedido");
		System.out.println("3. mostrar menu");
		System.out.println("4. atender siguinte pedido ");
		System.out.println("5. Salir");		
		int opcion = readInt("Seleccione una opción: ");
		sc.nextLine(); // limpia el búfer para evitar errores al leer texto después de números
		return opcion;
	}
	
	public void loadProducto() {
		menuHashMap.put("cafe", 1.50);
		menuHashMap.put("te verde", 1.20);
		menuHashMap.put("capuchino", 2.00);	
		menuHashMap.put("donut", 1.00);
		menuHashMap.put("croissant", 1.30);

		
		menuTreeMap.clear();// asegurarse que no hay nada en el treemap
		menuTreeMap.putAll(menuHashMap);// pasa todo el menu al treemas desde el hashmap
	}

	
	public void añadirpedido() {
		System.out.print("Nombre del cliente: ");
		String cliente = sc.nextLine();

		ArrayList<String> productos = new ArrayList<>();
		String pedido;

		do {
			System.out.print("¿Qué desea pedir? (Fin para terminar): ");
			pedido = sc.nextLine().toLowerCase(); //pasa todo el pedido a minuscula para evitar errores ent

			if (!pedido.equals("fin")) {

				if (menuHashMap.containsKey(pedido)) {
					productos.add(pedido);
				} else {
					System.out.println("Producto no disponible en el menú.");
				}
			}

		} while (!pedido.equals("fin"));

		if (productos.isEmpty()) {
			System.out.println("No se añadió ningún pedido.");
			return;
		}

		String pedidoFinal = cliente + Separador_Pedido +
				String.join(Separador_Productos, productos);

		pedidosLinkedlist.add(pedidoFinal);
		System.out.println("Pedido añadido correctamente.");
	}


		
	
	public void mostrarPedido() {

		if (pedidosLinkedlist.isEmpty()) {
			System.out.println("No hay pedidos pendientes.");
			return;
		}

		System.out.println("\nPedidos pendientes:");

		int contador = 1;
		for (String pedido : pedidosLinkedlist) {
			System.out.println(contador + ". " + pedido);
			contador++;
		}
	}

	
	
	public void mostrarmenu() {
		
		System.out.println("\nMenu (HasMap,sin ordenar): ");
		for (Map.Entry<String, Double> entry : menuHashMap.entrySet()) {
			System.out.println(entry.getKey()+" - "+ entry.getValue()+ "€");
		}
		
		System.out.println("\nMenu (TreeMap,ordenado alfabeticamente): ");
		for (Map.Entry<String, Double> entry : menuTreeMap.entrySet()) {
			System.out.println(entry.getKey()+" - "+ entry.getValue()+ "€");
		}
		
		
	}

	public void atenderPedido() {

		if (pedidosLinkedlist.isEmpty()) {
			System.out.println("No hay pedidos pendientes.");
			return;
		}

		String pedidoAtendido = pedidosLinkedlist.poll(); // obtiene y elimina el primer pedido 
		System.out.println("Atendiendo pedido:");
		System.out.println(pedidoAtendido);
	}


	
	public int readInt(String input) {
		System.out.print(input);
		while (!sc.hasNextInt()) { // Mientras no sea entero, pide de nuevo
			System.out.print("Valor inválido. Intente de nuevo: ");
			sc.next(); // Limpia el dato incorrecto
		}
		return sc.nextInt();
	}
}
	