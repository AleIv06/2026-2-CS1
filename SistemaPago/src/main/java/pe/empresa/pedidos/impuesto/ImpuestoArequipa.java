package pe.empresa.pedidos.impuesto;

public class ImpuestoArequipa implements CalculadoraImpuesto {

    @Override
    public double calcular(double subtotal) {
        return subtotal * 0.18;
    }

    @Override
    public String region() {
        return "Arequipa";
    }
}
