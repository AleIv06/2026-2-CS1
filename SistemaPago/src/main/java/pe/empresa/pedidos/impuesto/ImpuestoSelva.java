package pe.empresa.pedidos.impuesto;

public class ImpuestoSelva implements CalculadoraImpuesto {

    private final double porcentaje;

    public ImpuestoSelva(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    @Override
    public double calcular(double subtotal) {
        return subtotal * porcentaje;
    }

    @Override
    public String region() {
        return "Selva";
    }
}
