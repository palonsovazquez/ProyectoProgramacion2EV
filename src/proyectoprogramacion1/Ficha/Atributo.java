/**
 * module of  FoFiUn (provisional name)
 * Copyright (C) Pablo Alonso Vázquez.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 *
 * @author Pablo Alonso Vazquez <pav.vigo@gmail.com>
 **/
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
