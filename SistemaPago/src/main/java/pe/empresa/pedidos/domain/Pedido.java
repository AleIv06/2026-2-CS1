package pe.empresa.pedidos.domain;

import pe.empresa.pedidos.pago.MetodoPago;
import pe.empresa.pedidos.impuesto.CalculadoraImpuesto;
import pe.empresa.pedidos.notificacion.Notificador;

public class Pedido {

    private final EstrategiaDescuento estrategia;
    private final MetodoPago metodoPago;
    private final CalculadoraImpuesto calculadoraImpuesto;
    private final Notificador notificador;

    public Pedido(
            EstrategiaDescuento estrategia,
            MetodoPago metodoPago,
            CalculadoraImpuesto calculadoraImpuesto,
            Notificador notificador) {

        this.estrategia = estrategia;
        this.metodoPago = metodoPago;
        this.calculadoraImpuesto = calculadoraImpuesto;
        this.notificador = notificador;
    }

    public double procesar(double subtotal) {

        // PUNTO DE VARIACIÓN:
        // La estrategia de descuento puede cambiar.
        double subtotalConDescuento =
                estrategia.aplicar(subtotal);

        // PUNTO DE VARIACIÓN:
        // El impuesto depende de la región.
        double impuesto =
                calculadoraImpuesto.calcular(
                        subtotalConDescuento
                );

        double total =
                subtotalConDescuento + impuesto;

        metodoPago.procesar(total);

        notificador.enviar(
                "Pedido confirmado. Total: S/ " + total
        );

        return total;
    }
}
