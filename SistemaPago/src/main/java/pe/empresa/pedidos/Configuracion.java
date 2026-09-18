package pe.empresa.pedidos;

import java.io.InputStream;
import java.util.Properties;

public class Configuracion {

    private final Properties props = new Properties();

    public Configuracion() throws Exception {

        try (InputStream in = getClass()
                .getClassLoader()
                .getResourceAsStream("application.properties")) {

            if (in == null) {
                throw new Exception(
                        "No se encontró application.properties"
                );
            }

            props.load(in);
        }
    }

    public String getUrlPagos() {
        return props.getProperty("pagos.url");
    }

    public double getUmbralVip() {
        return Double.parseDouble(
                props.getProperty("pagos.umbral.vip")
        );
    }

    public double getImpuestoSelva() {
        return Double.parseDouble(
                props.getProperty("impuesto.selva")
        );
    }
}
