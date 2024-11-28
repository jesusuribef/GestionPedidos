package Enums.GestionPedidos.src.servicios;

import Enums.GestionPedidos.src.Enums.EstadoPedido;

import java.util.Scanner;

public class MenuServicio {
    private final PedidoServicio pedidoServicio;

    public MenuServicio(PedidoServicio pedidoServicio) {
        this.pedidoServicio = pedidoServicio;
    }
    
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in); // Único Scanner compartido
        int opcion;
    
        do {
            System.out.println("\n=== Menú de Gestión de Pedidos ===");
            System.out.println("1. Crear un nuevo pedido");
            System.out.println("2. Actualizar estado de un pedido");
            System.out.println("3. Buscar pedidos por estado");
            System.out.println("4. Mostrar todos los pedidos");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
    
            switch (opcion) {
                case 1 -> crearPedido(scanner);
                case 2 -> actualizarEstado(scanner);
                case 3 -> buscarPedidosPorEstado(scanner);
                case 4 -> pedidoServicio.mostrarPedidosConFiltro(scanner); // Pasamos Scanner como argumento
                case 5 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 5);
    
        scanner.close(); // Cerramos el Scanner solo aquí
    }
    

    private void crearPedido(Scanner scanner) {
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Nombre del cliente: ");
        String cliente = scanner.nextLine();
        System.out.print("Ingrese los productos separados por comas: ");
        String productos = scanner.nextLine();
        String[] listaDeProductos = productos.split(",");
        pedidoServicio.crearPedido(cliente, listaDeProductos);
    }

    private void actualizarEstado(Scanner scanner) {
        System.out.print("Número del pedido a actualizar: ");
        int numeroDePedido = scanner.nextInt();
        System.out.println("Estados disponibles:");
        for (EstadoPedido estado : EstadoPedido.values()) {
            System.out.println("- " + estado);
        }
        System.out.print("Nuevo estado: ");
        String estadoStr = scanner.next().toUpperCase();

        try {
            EstadoPedido nuevoEstado = EstadoPedido.valueOf(estadoStr);
            pedidoServicio.actualizarEstadoPedido(numeroDePedido, nuevoEstado);
        } catch (IllegalArgumentException e) {
            System.out.println("Estado no válido.");
        }
    }

    private void buscarPedidosPorEstado(Scanner scanner) {
        System.out.println("Estados disponibles:");
        for (EstadoPedido estado : EstadoPedido.values()) {
            System.out.println("- " + estado);
        }
        System.out.print("Estado a buscar: ");
        String estadoStr = scanner.next().toUpperCase();

        try {
            EstadoPedido estado = EstadoPedido.valueOf(estadoStr);
            pedidoServicio.buscarPedidosPorEstado(estado);
        } catch (IllegalArgumentException e) {
            System.out.println("Estado no válido.");
        }
    }
}
