/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package pe.edu.uni.preg1;

/**
 *
 * @author marina
 */
public class Main {

    public static void main(String[] args) {
        double base = 1000.00;
        Factura calculorus 
                = new Factura(new CalculoRus());
        Factura calculorer
                = new Factura(new CalculoRer());
        Factura calculogeneral = new Factura(new CalculoGeneral());
        System.out.println(calculorus.calcularTotal(base));
        System.out.println(calculorer.calcularTotal(base));
        System.out.println(calculogeneral.calcularTotal(base));
    }
}
