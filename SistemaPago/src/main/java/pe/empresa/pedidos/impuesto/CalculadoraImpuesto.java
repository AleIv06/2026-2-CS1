package pe.empresa.pedidos.impuesto;

public interface CalculadoraImpuesto {

    double calcular(double subtotal);

    String region();
}
