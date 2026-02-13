package logica;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.util.TreeMap;

import modelo.Pedido;
import persistencia.RegistrarOperacion;
import validacion.ValidacionInt;
import vista.Mensaje;
import vista.VistaConsola;

/**
 * Clase que gestiona el funcionamiento interno de la cafetería.
 * 
 * Aquí se controlan los pedidos pendientes, los atendidos
 * y los productos disponibles en el menú.
 */
public class GestorCafeteria {

    private LinkedList<Pedido> pedidosPendientes;
    private ArrayList<Pedido> pedidosAtendidos;
    private HashMap<String, Double> menuHashMap;
    private TreeMap<String, Double> menuTreeMap;
    
    private VistaConsola vista;
    private Mensaje mensaje;
	private ValidacionInt validacion;
	
	private Scanner sc;
	private RegistrarOperacion persistencia;

    /**
     * Constructor de la clase.
     * Inicializa las listas y carga los productos del menú.
     */
	public GestorCafeteria() {
	    pedidosPendientes = new LinkedList<>();
	    pedidosAtendidos = new ArrayList<>();
	    menuHashMap = new HashMap<>();
	    menuTreeMap = new TreeMap<>();
	    cargarProductos();

	    mensaje = new Mensaje();
	    sc = new Scanner(System.in);
	    validacion = new ValidacionInt();
	    persistencia = new RegistrarOperacion();
	    vista = new VistaConsola();
	}


    /**
     * Carga los productos en el menú con sus precios.
     * Se guardan en un HashMap (sin ordenar)
     * y en un TreeMap (ordenado).
     */
	public void cargarProductos() {
        menuHashMap.put("cafe", 1.50);
        menuHashMap.put("te verde", 1.20);
        menuHashMap.put("capuchino", 2.00);
        menuHashMap.put("donut", 1.00);
        menuHashMap.put("croissant", 1.30);

        menuTreeMap.clear();
        menuTreeMap.putAll(menuHashMap);
	}

    /**
     * Añade un pedido a la lista de pendientes.
     * 
     * @param pedido pedido que se quiere añadir
     */
    public void añadirPedidoAColeccion(Pedido pedido) {
        pedidosPendientes.add(pedido);
    }

    /**
     * Atiende el primer pedido pendiente.
     * Lo elimina de pendientes y lo pasa a atendidos.
     * 
     * @return el pedido atendido o null si no hay pedidos
     */
    public Pedido pedidosPendientesInEmpty() {
        if (pedidosPendientes.isEmpty()) {
            return null;
        }
        Pedido pedido = pedidosPendientes.poll();
        pedidosAtendidos.add(pedido);
        return pedido;
    }

    /**
     * Devuelve el menú en formato HashMap.
     * 
     * @return menú sin ordenar
     */
    public Map<String, Double> getMenuHashMap() {
        return menuHashMap;
    }

    /**
     * Devuelve el menú en formato TreeMap.
     * 
     * @return menú ordenado
     */
    public Map<String, Double> getMenuTreeMap() {
        return menuTreeMap;
    }

    /**
     * Devuelve la lista de pedidos pendientes.
     * 
     * @return lista de pedidos pendientes
     */
    public List<Pedido> getPedidosPendientes() {
        return pedidosPendientes;
    }

    /**
     * Devuelve la lista de pedidos atendidos.
     * 
     * @return lista de pedidos atendidos
     */
    public List<Pedido> getPedidosAtendidos() {
        return pedidosAtendidos;
    }

    /**
     * Pide los datos por pantalla y añade un nuevo pedido.
     */
    public void añadirPedido() {

        String cliente = vista.pedirNombreCliente();

        ArrayList<String> productos = new ArrayList<>();
        vista.pedirProductos(productos, menuHashMap);


        if (productos.isEmpty()) {
            mensaje.println("Pedido vacío, no se añadió.");
            return;
        }

        Pedido pedido = new Pedido(cliente, productos);
        añadirPedidoAColeccion(pedido);
        
        mensaje.println("Pedido añadido correctamente.");
    }

    /**
     * Atiende el siguiente pedido pendiente.
     * También guarda la operación en un archivo.
     */
    public void atenderPedido() {

        Pedido pedido = pedidosPendientesInEmpty();

        if (pedido == null) {
        	mensaje.println("No hay pedidos pendientes.");
        } else {
        	mensaje.println("Atendiendo: " + pedido);
        }
        
        String codigo = vista.generarCodigoOperacion();
        vista.registrarOperacion(codigo);
        persistencia.guardarPedidoEnTxt(pedido, codigo);
    }

    /**
     * Permite eliminar un pedido pendiente
     * eligiéndolo por número.
     */
    public void eliminarPedido() {
        if (getPedidosPendientes().isEmpty()) {
        	mensaje.println("No hay pedidos pendientes.");
          return;
      }

      vista.mostrarPedidos(getPedidosPendientes());
      
      mensaje.print("Introduzda el Nº de pedido a eliminar: ");
      int numero = validacion.readInt(sc);
      sc.nextLine();

      if (numero < 1 || numero > getPedidosPendientes().size()) {
    	  mensaje.println("Número inválido.");
          return;
      }

      getPedidosPendientes().remove(numero - 1);
      
      mensaje.println("Pedido eliminado correctamente.");
  }
}
