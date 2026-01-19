package nombreFuturo;

import java.util.Scanner;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
public class Menu {

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
		System.out.print("Elija una opción: ");
		int opcion = sc.nextInt();
		sc.nextLine(); // limpia el búfer para evitar errores al leer texto después de números
		return opcion;
	}
	
	public static void loadProducto() {
		menuHashMap.put("Cafe",1.50);
		menuHashMap.put("Te verde",1.20);
		menuHashMap.put("Capuchino",2.00);
		menuHashMap.put("Donut",1.00);
		menuHashMap.put("Croissant",1.30);
	}
	
	public static void añadirpedido() {
		System.out.print("Nombre del cliente: ");
		String nombre = sc.nextLine();
		String pedido = "";
		while (!pedido.equalsIgnoreCase("fin")){
			
		}
		System.out.print("Cual es su pedido 'Fin para terminad de pedir': ");
		pedido =sc.nextLine();
		
		
	}
	public static void mostrarPedido() {
		
		for (Map.Entry<String, Double> entry : menuHashMap.entrySet()) {
			System.out.println(entry.getKey()+" - "+ entry.getValue()+ "€");
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
	public static void atenderPedido() {
	   
	}


 
}




