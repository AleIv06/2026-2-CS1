package pe.empresa.pedidos.notificacion;

public class NotificadorEmail implements Notificador {

    @Override
    public void enviar(String mensaje) {
        System.out.println(
                "Enviando email: " + mensaje
        );
    }
}
