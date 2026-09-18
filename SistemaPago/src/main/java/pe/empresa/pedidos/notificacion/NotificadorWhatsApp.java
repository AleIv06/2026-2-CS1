package pe.empresa.pedidos.notificacion;

public class NotificadorWhatsApp implements Notificador {

    @Override
    public void enviar(String mensaje) {
        System.out.println(
                "Enviando WhatsApp: " + mensaje
        );
    }
}
