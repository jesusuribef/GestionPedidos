package Enums.GestionPedidos.src.servicios;

import java.util.Scanner;

import Enums.GestionPedidos.src.Enums.EstadoPedido;
import Enums.GestionPedidos.src.modelos.Pedido;

public class PedidoServicio {
    private Pedido[] pedidos;
    private int contadorPedidos;

    public PedidoServicio(int capacidadMaxima) {
        this.pedidos = new Pedido[capacidadMaxima];
        this.contadorPedidos = 0;
    }

    public void crearPedido(String cliente, String[] listaDeProductos) {
        if (contadorPedidos < pedidos.length) {
            Pedido nuevoPedido = new Pedido(contadorPedidos + 1, cliente, listaDeProductos, EstadoPedido.PENDIENTE);
            pedidos[contadorPedidos++] = nuevoPedido;
            System.out.println("Pedido creado exitosamente:\n" + nuevoPedido);
        } else {
            System.out.println("No se pueden agregar más pedidos. Capacidad máxima alcanzada.");
        }
    }

    public void actualizarEstadoPedido(int numeroDePedido, EstadoPedido nuevoEstado) {
        Pedido pedido = buscarPedidoPorNumero(numeroDePedido);
        if (pedido != null) {
            pedido.setEstadoDelPedido(nuevoEstado);
            System.out.println("Estado del pedido actualizado exitosamente:\n" + pedido);
        } else {
            System.out.println("Pedido no encontrado.");
        }
    }

    public void buscarPedidosPorEstado(EstadoPedido estado) {
        boolean encontrado = false;
        for (Pedido pedido : pedidos) {
            if (pedido != null && pedido.getEstadoDelPedido() == estado) {
                System.out.println(pedido);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron pedidos con el estado " + estado + ".");
        }
    }
    public void mostrarPedidos() {
        for (Pedido pedido : pedidos) {
            if (pedido != null) {
                System.out.println(pedido);
                System.out.println("----------------------------------");
            }
        }
    }


    public void mostrarPedidosConFiltro(Scanner scanner) { // Usamos el Scanner del menú principal
        while (true) {
            System.out.println("\n=== Mostrar pedidos con filtro ===");
            System.out.println("1. Mostrar todos los pedidos");
            System.out.println("2. Mostrar pedidos por estado");
            System.out.println("3. Regresar al menú principal");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
    
            switch (opcion) {
                case 1 -> {
                    mostrarPedidos(); // Mostrar todos los pedidos
                    return; // Regresamos al menú principal
                }
                case 2 -> {
                    System.out.println("Selecciona un estado:");
                    System.out.println("1. Pendiente");
                    System.out.println("2. Enviado");
                    System.out.println("3. Entregado");
                    System.out.print("Tu opción: ");
                    int estadoOpcion = scanner.nextInt();
    
                    EstadoPedido estadoSeleccionado;
                    switch (estadoOpcion) {
                        case 1 -> estadoSeleccionado = EstadoPedido.PENDIENTE;
                        case 2 -> estadoSeleccionado = EstadoPedido.ENVIADO;
                        case 3 -> estadoSeleccionado = EstadoPedido.ENTREGADO;
                        default -> {
                            System.out.println("Estado no válido. Intenta de nuevo.");
                            continue; // Volver al submenú
                        }
                    }
                    buscarPedidosPorEstado(estadoSeleccionado);
                    return; // Regresamos al menú principal tras mostrar los pedidos
                }
                case 3 -> {
                    return; // Salimos del submenú al menú principal
                }
                default -> System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }
    

    private Pedido buscarPedidoPorNumero(int numeroDePedido) {
        for (Pedido pedido : pedidos) {
            if (pedido != null && pedido.getNumeroDePedido() == numeroDePedido) {
                return pedido;
            }
        }
        return null;
    }

}
