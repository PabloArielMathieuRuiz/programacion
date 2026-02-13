package controlador;

import vista.VistaConsola;
import vista.Mensaje;

import java.util.Map;

import logica.GestorCafeteria;



/**
 * Clase que controla el funcionamiento del programa.
 * 
 * Se encarga de mostrar el menú y ejecutar las opciones
 * que el usuario elige.
 */
public class Controlador {

    private VistaConsola vista;
    private GestorCafeteria logica;
    private Mensaje mensaje;
    
     
    /**
     * Constructor del controlador.
     * Crea los objetos necesarios para que el programa funcione.
     */
    public Controlador() {
        vista = new VistaConsola();
        logica = new GestorCafeteria();
        mensaje = new Mensaje();
        
    }

    /**
     * Ejecuta el menú principal.
     * El programa se repite hasta que el usuario elige salir.
     */
    public void ejecutar() {

        int opcion;
        do {
            opcion = vista.mostrarMenuPrincipal();
            procesarOpcion(opcion);
        } while (opcion != 0);
    }

    /**
     * Ejecuta la opción seleccionada por el usuario.
     * 
     * @param opcion número elegido en el menú
     */
    public void procesarOpcion(int opcion) {

        switch (opcion) {
            case 1 -> logica.añadirPedido();
            case 2 -> vista.mostrarPedidos(logica.getPedidosPendientes());
            case 3 -> mostrarMenuProductos();
            case 4 -> logica.atenderPedido();
            case 5 -> logica.eliminarPedido();
            case 6 -> vista.mostrarPedidos(logica.getPedidosAtendidos());
            case 0 -> vista.cerrarRecursos();
            default -> mensaje.println("Opción no válida");
        }
    }
    
    public void mostrarMenuProductos () {
    	
    	//recojemos los dos menus
    	Map<String, Double> menuHashMap= logica.getMenuHashMap();
    	Map<String, Double> menuTreeMap = logica.getMenuTreeMap();
    	
    	//y se los pasamos a los procedimientos como parametros
    	vista.mostrarHashMap(menuHashMap);
    	vista.mostrarTreeMap(menuTreeMap);	
    }
    
}
	