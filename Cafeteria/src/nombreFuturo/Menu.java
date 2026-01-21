package nombreFuturo;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
public class Menu {
	
	private static final String Separador_Pedido = " - ";
	private static final String Separador_Productos = ", ";
	private static final int Contador_Productos = 1;	
	
	
	
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList<String> pedidosArrayList = new ArrayList<>();
	public static LinkedList<String> pedidosLinkedlist = new LinkedList<>();
	public static Map <String, Double> menuHashMap = new HashMap<>();
	public static TreeMap <String, Double> menuTreeMap = new TreeMap<>(menuHashMap);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		loadProducto();
		int opcion;
		do {
			
			opcion = mostrarMenu();
			
			switch (opcion) {
				case 1:
					añadirpedido();
					break;
				case 2:
					mostrarPedido();
					break;
				case 3:
					mostrarmenu();
					break;
				case 4:
					atenderPedido();
					break;
				case 5:
					System.out.println("Gracias por usar el servicio ");
					break;
				default:
					System.out.println("Opción no válida. Inténtelo de nuevo.");
			}
		} while (opcion != 4);
	}

	// --- MENÚ PRINCIPAL ---
	public static int mostrarMenu() {
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
	
	public static void loadProducto() {
		menuHashMap.put("cafe", 1.50);
		menuHashMap.put("te verde", 1.20);
		menuHashMap.put("capuchino", 2.00);
		menuHashMap.put("donut", 1.00);
		menuHashMap.put("croissant", 1.30);

	}

	
	public static void añadirpedido() {
		System.out.print("Nombre del cliente: ");
		String cliente = sc.nextLine();

		ArrayList<String> productos = new ArrayList<>();
		String pedido;

		do {
			System.out.print("¿Qué desea pedir? (Fin para terminar): ");
			pedido = sc.nextLine().toLowerCase();

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


		
	
	public static void mostrarPedido() {

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

	
	
	public static void mostrarmenu() {
		
		System.out.println("\nMenu (HasMap,sin ordenar): ");
		for (Map.Entry<String, Double> entry : menuHashMap.entrySet()) {
			System.out.println(entry.getKey()+" - "+ entry.getValue()+ "€");
		}
		
		System.out.println("\nMenu (TreeMap,ordenado alfabeticamente): ");
		for (Map.Entry<String, Double> entry : menuTreeMap.entrySet()) {
			System.out.println(entry.getKey()+" - "+ entry.getValue()+ "€");
		}
		
		
	}
	/*public static void atenderPedido() {
		
		if (pedidosLinkedlist.isEmpty()) {
			System.out.println("No hay pedidos");
			return;
		} else {
			
			mostrarPedidoUnitario();
			pedidosLinkedlist.remove(0);

		}
	}
	*/
	public static void atenderPedido(){
		
		
		
	}

	public static void mostrarPedidoUnitario() {
			
		System.out.println(pedidosLinkedlist.get(0));
			
	}
	public static int readInt(String input) {
		System.out.print(input);
		while (!sc.hasNextInt()) { // Mientras no sea entero, pide de nuevo
			System.out.print("Valor inválido. Intente de nuevo: ");
			sc.next(); // Limpia el dato incorrecto
		}
		return sc.nextInt();
	}
	
	
		
	}
	