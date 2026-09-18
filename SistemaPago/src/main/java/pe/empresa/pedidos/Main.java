package pe.empresa.pedidos;

import pe.empresa.pedidos.domain.Pedido;
import pe.empresa.pedidos.pago.PagoYape;
import pe.empresa.pedidos.impuesto.ImpuestoSelva;
import pe.empresa.pedidos.notificacion.NotificadorWhatsApp;
import pe.empresa.pedidos.domain.DescuentoVip;

public class Main {

    public static void main(String[] args) {

        try {

            Configuracion configuracion =
                    new Configuracion();

            Pedido pedido = new Pedido(
                    new DescuentoVip(),
                    new PagoYape(),
                    new ImpuestoSelva(
                            configuracion.getImpuestoSelva()
                    ),
                    new NotificadorWhatsApp()
            );

            double total = pedido.procesar(200.00);

            System.out.println(
                    "Total final: S/ " + total
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
}
