package pe.empresa.pedidos.pago;

public class PagoPlin implements MetodoPago {
    
    @Override
    public void procesar(double monto) {
        System.out.println(
                "Cobrando S/ " + monto + " Con plin"
        );
    }

    @Override
    public String nombre() {
        return "Plin";
    }
}
