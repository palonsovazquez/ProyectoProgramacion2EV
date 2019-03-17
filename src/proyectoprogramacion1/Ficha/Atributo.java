/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion1.Ficha;

/**
 *
 * @author Pablo Alonso Vazquez <pav.vigo@gmail.com>
 */
public class Atributo {

    private String str_Nombre = null;
    private String str_ID = null;
    private Double dou_Valor = null;

    public Atributo() {
    }

    public Atributo(String str_ID, String str_Nombre, Double dou_Valor) {
        this.str_Nombre = str_Nombre;
        this.str_ID = str_ID;
        this.dou_Valor = dou_Valor;

    }

    public String getStr_Nombre() {
        return str_Nombre;
    }

    public void setStr_Nombre(String str_Nombre) {
        this.str_Nombre = str_Nombre;
    }

    public String getStr_ID() {
        return str_ID; 
    }

    public void setStr_ID(String str_ID) {
        this.str_ID = str_ID;
    }

    public Double getDou_Valor() {
        return dou_Valor;
    }

    public void setDou_Valor(Double dou_Valor) {
        this.dou_Valor = dou_Valor;
    }

    @Override
    public String toString() {
        return "[" + str_Nombre + "," + str_ID + "," + dou_Valor + "]\n";
    }

}
