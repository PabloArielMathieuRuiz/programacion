package modelo;

/**
 * Clase que representa un producto de la cafetería.
 * 
 * Guarda el nombre del producto y su precio.
 */
public class Producto {

	private String nombre;
	private double precio;

	/**
	 * Constructor vacío.
	 * Crea un producto sin asignar y con precio 0.
	 */
	public Producto() {
		this.nombre = "sin asignar";
		this.precio = 0.0;
	}	

	/**
	 * Constructor con parámetros.
	 * 
	 * @param nombre nombre del producto
	 * @param precio precio del producto
	 * @throws IllegalArgumentException si el nombre está vacío o el precio es negativo
	 */
	public Producto(final String nombre, final double precio) {
		
		if(nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacio");
		} 
		if(precio < 0) {
			throw new IllegalArgumentException("El precio no puede ser negativo");
		}

		this.nombre = nombre;
		this.precio = precio;

		setNombre(nombre);
		setPrecio(precio);
	}
	
	/**
	 * Devuelve el nombre del producto.
	 * 
	 * @return nombre del producto
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modifica el nombre del producto.
	 * 
	 * @param nombre nuevo nombre
	 * @throws IllegalArgumentException si el nombre está vacío
	 */
	public void setNombre(String nombre) {
		if(nombre == null || nombre.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre del producto no puede ser nulo o vacio");
		}
		this.nombre = nombre;
	}

	/**
	 * Devuelve el precio del producto.
	 * 
	 * @return precio del producto
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * Modifica el precio del producto.
	 * 
	 * @param precio nuevo precio
	 * @throws IllegalArgumentException si el precio es negativo
	 */
	public void setPrecio(double precio) {
		if(precio < 0) {
			throw new IllegalArgumentException("El precio no puede ser negativo");
		}
		this.precio = precio;
	}
	
	/**
	 * Devuelve el producto en formato texto.
	 */
	@Override
	public String toString() {
		return String.format("%s - %.2f€", nombre, precio);
	}
}
