package modelo;

import java.util.List;
import java.util.ArrayList;

/**
 * Clase que representa un pedido de la cafetería.
 * 
 * Guarda el número del pedido, el nombre del cliente
 * y los productos que ha pedido.
 */
public class Pedido {

    private static int contadorPedidos = 1;

    private final int numPedido;
    private String cliente;
    private List<String> productos;

    /**
     * Constructor del pedido.
     * 
     * Crea un pedido nuevo con el nombre del cliente
     * y la lista de productos.
     * 
     * @param cliente nombre del cliente
     * @param productos lista de productos del pedido
     * @throws IllegalArgumentException si el cliente o los productos están vacíos
     */
    public Pedido(String cliente, List<String> productos) {

        if (cliente == null || cliente.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede ser nulo o vacío");
        }
        if (productos == null || productos.isEmpty()) {
            throw new IllegalArgumentException("La lista de productos no puede ser nula o vacía");
        }

        this.numPedido = contadorPedidos++;
        this.cliente = cliente;
        this.productos = new ArrayList<>(productos);
    }

    /**
     * Devuelve el número del pedido.
     * 
     * @return número del pedido
     */
    public int getNumPedido() {
        return numPedido;
    }

    /**
     * Devuelve el nombre del cliente.
     * 
     * @return nombre del cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Devuelve la lista de productos del pedido.
     * 
     * @return lista de productos
     */
    public List<String> getProductos() {
        return new ArrayList<>(productos);
    }

    /**
     * Devuelve el pedido en formato texto
     * para guardarlo en un archivo.
     * 
     * @return pedido en formato String
     */
    public String toFormatoArchivo() {
        return "Pedido Nº" + numPedido + ": " + cliente + " - " +
               String.join(", ", productos);
    }

    /**
     * Devuelve el pedido en formato texto.
     */
    @Override
    public String toString() {
        return toFormatoArchivo();
    }
}
