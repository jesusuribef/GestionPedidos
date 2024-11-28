package Enums.GestionPedidos.src;
import Enums.GestionPedidos.src.servicios.*;

public class Application {
    public static void main(String[] args) {
        System.out.println("\033\143");
        PedidoServicio pedidoServicio = new PedidoServicio(10); // Capacidad máxima de 10 pedidos
        MenuServicio menuServicio = new MenuServicio(pedidoServicio);
        menuServicio.mostrarMenu();
    }
}
