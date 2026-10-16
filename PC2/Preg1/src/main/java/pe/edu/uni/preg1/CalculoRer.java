/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.uni.preg1;

/**
 *
 * @author marina
 */
public class CalculoRer implements EstrategiaCalculoTributario {

    @Override
    public double calcular(double base) {
        return base * 0.18;
    }
}
