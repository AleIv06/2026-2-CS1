package pe.empresa.pedidos.notificacion;

public class NotificadorSms implements Notificador {

    @Override
    public void enviar(String mensaje) {
        System.out.println(
                "Enviando SMS: " + mensaje
        );
    }
}
