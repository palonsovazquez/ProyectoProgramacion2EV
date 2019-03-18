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
package proyectoprogramacion1.Parser;

import java.util.ArrayList;
import proyectoprogramacion1.Ficha.Ficha;
import proyectoprogramacion1.Parser.backend.ConversorAPosfijo;
import proyectoprogramacion1.Parser.backend.ProcesadorPosfijo;
import static proyectoprogramacion1.Libreria.MetodosReutilizables.*;
/**
 *
 * @author Pablo Alonso Vazquez <pav.vigo@gmail.com>
 */
public class Parser {

    private Ficha fic_Ficha = null;
    private ConversorAPosfijo convAPos = null;
    private ProcesadorPosfijo procPos = null;

    public Ficha getFic_Ficha() {
        return fic_Ficha;
    }
/**
 * constructor para el objeto parser.
 * Internamente instancia un objeto ConversorAPosfijo
 * y un objeto ProcesadorPosfijo con la 
 * @param ficha 
 */
    public Parser(Ficha ficha) {
        setFic_Ficha(ficha);

    }
/**
 * Ademas de ser un set para ficha verifica que la ficha recibida no sea nula.
 * y internamente instancia un objeto ConversorAPosfijo
 * y un objeto ProcesadorPosfijo con la ficha que se pasa como parametro.
 * @param ficha 
 */
    public void setFic_Ficha(Ficha ficha) {
        if (null != ficha) {
            this.fic_Ficha = ficha;
            
            convAPos = new ConversorAPosfijo(this.fic_Ficha);
            procPos = new ProcesadorPosfijo(this.fic_Ficha);
        } else {
            throw new NullPointerException("Ficha vacia");

        }
    }
    /**
     * Metodo principal al que se le envia una operacion y usa los objetos 
     * ConversorAPosfijo y ProcesadorPosfijo para calcular el valor y devolverlo.
     * @param operacion
     * @return 
     */
    public Double Procesar(String operacion){
    Double dou_aux = 0d;
        ////System.out.println("Operacion = "+operacion);
    ArrayList<String> strAL_Posfijo = convAPos.parsear(operacion);
   debug(">>Expresion posfija<<",strAL_Posfijo);
    dou_aux = procPos.calcular(strAL_Posfijo);
       System.out.println("dou_aux"+dou_aux);
    return dou_aux; }

}
