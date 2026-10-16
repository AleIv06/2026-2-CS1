/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package pe.edu.uni.preg2;
import java.time.LocalDate;
/**
 *
 * @author marina
 */
public class Main {

    public static void main(String[] args) {
        Contrato contrato = Contrato.builder()
                .codigo("20260001")
                .proveedor("AnaTorres")
                .fechafirma(LocalDate.of(2026, 12, 25))
                .monto(1000)
                .vigenciadias(12)
                .clausulapenalidad(true)
                .observaciones("ninguna")
                .build();
        System.out.println(contrato.getCodigo());
        System.out.println(contrato.getProveedor());
        System.out.println(contrato.getMonto());
    }
}
