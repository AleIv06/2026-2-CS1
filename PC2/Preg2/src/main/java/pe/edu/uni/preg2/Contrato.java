/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.preg2;
import java.time.LocalDate;
/**
 *
 * @author marina
 */
public class Contrato {

    private final String codigo;
    private final String proveedor;
    private final LocalDate fechafirma;
    private final double monto;
    private final int vigenciadias;
    private final boolean clausulapenalidad;
    private final String observaciones;

    private Contrato(Builder builder) {
        this.codigo = builder.codigo;
        this.proveedor = builder.proveedor;
        this.fechafirma = builder.fechafirma;
        this.monto = builder.monto;
        this.vigenciadias = builder.vigenciadias;
        this.clausulapenalidad = builder.clausulapenalidad;
        this.observaciones = builder.observaciones;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String codigo;
        private String proveedor;
        private LocalDate fechafirma;
        private double monto;
        private int vigenciadias;
        private boolean clausulapenalidad;
        private String observaciones;

        public Builder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder proveedor(String proveedor) {
            this.proveedor = proveedor;
            return this;
        }

        public Builder fechafirma(LocalDate fechafirma) {
            this.fechafirma = fechafirma;
            return this;
        }

        public Builder monto(double monto) {
            this.monto = monto;
            return this;
        }

        public Builder vigenciadias(int vigenciadias) {
            this.vigenciadias = vigenciadias;
            return this;
        }

        public Builder clausulapenalidad(boolean clausulapenalidad) {
            this.clausulapenalidad = clausulapenalidad;
            return this;
        }
        
        public Builder observaciones(String observaciones) {
            this.observaciones = observaciones;
            return this;
        }

        public Contrato build() {
            if (codigo == null || codigo.isBlank()) {
                throw new IllegalStateException(
                        "El código es obligatorio"
                );
            }
            if (proveedor == null || proveedor.isBlank()) {
                throw new IllegalStateException(
                        "El proveedor es obligatorio"
                );
            }
            if (monto <=0) {
                throw new IllegalStateException(
                        "El monto es obligatorio"
                );
            }
            return new Contrato(this);
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public LocalDate getFechaFirma() {
        return fechafirma;
    }

    public double getMonto() {
        return monto;
    }

    public int getVigenciaDias() {
        return vigenciadias;
    }

    public boolean isClausulapenalidad() {
        return clausulapenalidad;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
}
