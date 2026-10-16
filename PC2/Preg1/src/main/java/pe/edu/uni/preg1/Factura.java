/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.preg1;

/**
 *
 * @author marina
 */
public class Factura {

    private final EstrategiaCalculoTributario estrategia;

    public Factura(EstrategiaCalculoTributario estrategia) {
        this.estrategia = estrategia;
    }

    public double calcularTotal(double base) {
        double aumento = estrategia.calcular(base);
        return base + aumento;
    }

}
