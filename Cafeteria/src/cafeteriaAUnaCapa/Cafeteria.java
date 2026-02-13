package cafeteriaAUnaCapa;

import java.util.Scanner;
import java.util.TreeMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.HashMap;

//Para poner los pedidos en un .txt
import java.io.IOException;


 
public class Cafeteria {

    // Separador entre nombre del cliente y productos
    private final String SEPARADOR_PEDIDO = " - ";
    
    // Separador entre productos
    private final String SEPARADOR_PRODUCTOS = ", ";
    
    
    // Scanner para leer datos por teclado
    private Scanner sc;
    
    // Lista para pedidos pendientes (FIFO: primero en entrar, primero en salir)
    private LinkedList<String> pedidosPendientes;
    
    // Lista para pedidos ya atendidos
    private ArrayList<String> pedidosAtendidos;
    
    // HashMap: menú sin ordenar
    private Map<String, Double> menuHashMap;
    
    // TreeMap: menú ordenado alfabéticamente
    private TreeMap<String, Double> menuTreeMap;
    
    
    public Cafeteria() {
        // Inicializamos el scanner
        this.sc = new Scanner(System.in);

        // Inicializamos las listas de pedidos
        this.pedidosPendientes = new LinkedList<>();
        this.pedidosAtendidos = new ArrayList<>();

        // Inicializamos los mapas del menú
        this.menuHashMap = new HashMap<>();
        this.menuTreeMap = new TreeMap<>();

        // Cargamos los productos al crear el objeto
        cargarProductos();
    }

    
    
    public static void main(String[] args) {

        // Creamos el menú y arrancamos el programa
    	new Cafeteria().ejecutar();
      
    }
    
    public void ejecutar() {
    	
    	int opcion;
    		
        // Bucle principal: se repite hasta que el usuario elija 0
    	do {
    		opcion= mostrarMenuPrincipal();
    		procesarOpcion(opcion);
    	} while (opcion !=0);	   
    }
    	
    
    // --- MENÚ PRINCIPAL ---
    public int mostrarMenuPrincipal() {
    	
        // Mostramos las opciones por pantalla
        System.out.println("\n== MENÚ ==");
        System.out.println("1. Añadir pedido");
        System.out.println("2. Mostrar pedidos pendientes");
        System.out.println("3. Mostrar menú");
        System.out.println("4. Atender siguiente pedido");
        System.out.println("5. Eliminar pedido por número");
        System.out.println("6. Ver pedidos atendidos");
        System.out.println("0. Salir");

        // Leemos la opción como entero
        int opcion = readInt("Seleccione una opción: ");
        
        // Limpiamos el salto de línea que deja nextInt()
        sc.nextLine();
        
        return opcion;
    }
    
    // --- PROCESA LA OPCIÓN ELEGIDA ---
    public void procesarOpcion(int opcion) {
        
        // Según la opción, llamamos al método correspondiente
            switch (opcion) {
                case 1 -> añadirPedido();
                case 2 -> mostrarPedidos();
                case 3 -> mostrarMenuProductos();
                case 4 -> atenderPedido();
                case 5 -> eliminarPedido();
                case 6 -> mostrarPedidosAtendidos();
                case 0 -> cerrarRecursos();// salida de la aplicacion
                default -> System.out.println("Opción no válida.");   
            }
    }

    // --- CARGA DE PRODUCTOS ---
    public void cargarProductos() {
        
        // Añadimos los productos al HashMap
        menuHashMap.put("cafe", 1.50);
        menuHashMap.put("te verde", 1.20);
        menuHashMap.put("capuchino", 2.00);
        menuHashMap.put("donut", 1.00);
        menuHashMap.put("croissant", 1.30);

        // Limpiamos el TreeMap por si tenía datos
        menuTreeMap.clear();
        
        // Copiamos el HashMap al TreeMap para ordenarlo
        menuTreeMap.putAll(menuHashMap);
    }

    // --- AÑADIR PEDIDO ---
    public void añadirPedido() {

        String cliente = pedirNombre();

        ArrayList<String> productos = new ArrayList<>();

        pedirProductos(productos);

        if (productos.isEmpty()) {
            System.out.println("No se añadió ningún pedido.");
            return;
        }

        String pedidoFinal = cliente + SEPARADOR_PEDIDO +
                String.join(SEPARADOR_PRODUCTOS, productos);

        pedidosPendientes.add(pedidoFinal);

        System.out.println("Pedido añadido correctamente.");
    }

    // --- RECOLECTAMOS TODOS LOS PRODUCTOS QUE QUIERA EL CLIENTE ---
    public void pedirProductos(ArrayList<String> productos) {

        String producto= "";
        // Hasta que el cliente no ponga la palabra "fin" no para de pedir productos
        do {
            System.out.print("¿Qué desea pedir? (fin para terminar): ");
            //Lo pasamos todo a minuscula para que de igual la marera en la que ponga "FiN"
            producto = sc.nextLine().toLowerCase();
            //Que hara dependiendo si pono fin o no
            if (!producto.equals("fin")) {
            	//Comprueba que el pedido del cliente esta el en menu
                if (menuHashMap.containsKey(producto)) {
                    productos.add(producto);
                } else {
                	//Evita que se cree un pedido vacio ya que seria un desperdicio
                    System.out.println("Producto no disponible.");
                    mostrarHashMap();
                }
            }

        } while (!producto.equals("fin"));
    }

    
    // --- PEDIMOS EL NOMBRE DEL CLIENTE --- 
    public String pedirNombre() {
    	
    	 System.out.print("Nombre del cliente: ");
         String cliente = sc.nextLine();
         return cliente;
    	
    	
    }
    
    // --- MOSTRAR PEDIDOS PENDIENTES ---
    public void mostrarPedidos() {
        
        // Si no hay pedidos, avisamos
        if (pedidosPendientes.isEmpty()) {
            System.out.println("No hay pedidos pendientes.");
            return;
        }

        int contador = 1;
        
        // Mostramos los pedidos numerados
        for (String pedido : pedidosPendientes) {
            System.out.println(contador + ". " + pedido);
            contador++;
        }
    }

    // --- MOSTRAR MENÚ ---
    public void mostrarMenuProductos() {
        
        // Mostramos los dos mapas
    	mostrarHashMap();
    	mostrarTreeMap();
    }
    
    // --- MOSTRAR MENÚ (HASHMAP) ---
    public void mostrarHashMap() {
    	
    	System.out.println("\nMenu (HashMap, sin ordenar): ");
		for (Map.Entry<String, Double> entry : menuHashMap.entrySet()) {
			System.out.println(entry.getKey()+" - "+ entry.getValue()+ "€");
		}
    }
    
    // --- MOSTRAR MENÚ (TREEMAP) ---
    public void mostrarTreeMap() {
    	
    	System.out.println("\nMenu (TreeMap, ordenado alfabéticamente): ");
		for (Map.Entry<String, Double> entry : menuTreeMap.entrySet()) {
			System.out.println(entry.getKey()+" - "+ entry.getValue()+ "€");
		}
    }
    

    // --- ATENDER PEDIDO ---
    public void atenderPedido() {
        
        // Si no hay pedidos, no hacemos nada
        if (pedidosPendientes.isEmpty()) {
            System.out.println("No hay pedidos pendientes.");
            return;
        }

        // Sacamos el primer pedido de la cola
        String pedido = pedidosPendientes.poll();
        
        // Lo guardamos como atendido
        pedidosAtendidos.add(pedido);

        // GENERAR EL CÓDIGO AQUÍ
        String codigo = generarCodigoOperacion();
        
        System.out.println("Atendiendo pedido:");
        System.out.println(pedido);
        
        registrarOperacion(codigo);
        // Lo guardamos en un .txt
        guardarPedidoEnTxt(pedido, codigo);
        // Registramos fecha y código
        
    }

    // --- ELIMINAR PEDIDO ---
    public void eliminarPedido() {
        
        if (pedidosPendientes.isEmpty()) {
            System.out.println("No hay pedidos pendientes.");
            return;
        }

        mostrarPedidos();
        
        // Pedimos el número a eliminar
        int numero = readInt("Número del pedido a eliminar: ");
        sc.nextLine();

        // Comprobamos que el número sea válido
        if (numero < 1 || numero > pedidosPendientes.size()) {
            System.out.println("Número inválido.");
            return;
        }

        // Eliminamos el pedido elegido
        pedidosPendientes.remove(numero - 1);
        
        System.out.println("Pedido eliminado correctamente.");
    }

    // --- MOSTRAR HISTORIAL ---
    public void mostrarPedidosAtendidos() {
        
        if (pedidosAtendidos.isEmpty()) {
            System.out.println("No hay pedidos atendidos.");
            return;
        }

        System.out.println("\nPedidos atendidos:");
        
        pedidosAtendidos.forEach(System.out::println);
    }

    // --- REGISTRO DE OPERACIÓN ---
 	public void registrarOperacion(String codigo) {
 		
 		// Fecha y hora actual
 		LocalDateTime ahora = LocalDateTime.now();
 		
        // Formato de fecha
 		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

 		// Mostramos código y fecha
 		System.out.println("Código de operación: " + generarCodigoOperacion());
 		System.out.println("Fecha y hora: " + ahora.format(formato));	
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

    // --- CERRAR PROGRAMA ---
    public void cerrarRecursos() {

    	System.out.println("Gracias por usar el servicio");
        
        // Cerramos el scanner
        sc.close();
    }
    
    
    // --- LEE UN ENTERO DE FORMA SEGURA ---
    public int readInt(String mensaje) {
        
        System.out.print(mensaje);
        
        // Mientras no sea un entero, vuelve a pedirlo
        while (!sc.hasNextInt()) {
            System.out.print("Valor inválido. Intente de nuevo: ");
            sc.next();
        }
        
        return sc.nextInt();
    }
    
    
    //--- GUARDAMOS LOS PEDIDOS EN UN .TXT ---
    public void guardarPedidoEnTxt(String pedido, String codigo) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("pedidos.txt", true))) {

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
            System.out.println("Error al guardar el pedido en el archivo");
        }
    }
    	
}
    
    

