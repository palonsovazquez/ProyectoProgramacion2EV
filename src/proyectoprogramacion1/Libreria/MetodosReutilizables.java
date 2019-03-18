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
package proyectoprogramacion1.Libreria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import proyectoprogramacion1.Ficha.Atributo;

/**
 *
 * @author Pablo Alonso Vazquez <pav.vigo@gmail.com>
 */
public class MetodosReutilizables {
     public static  String unspaceString(String str_OriginalString) {
        
        return cleanString(str_OriginalString,' ');
    }//fin unspaceString
      /**
     * Esta clase elimina un caracter de la expresion,para poder gestionarla
     * posteriormente con mas facilidad
     *
     * @param str_OriginalString es la cadena original sin tratar que recibe
     * 
     * @return devuelve una cadena sin el caracter indeseado.
     */
     
     public static String cleanString(String str_OriginalString,char patronAEliminar) {
        String str_UnspacedString = "";
        if (str_OriginalString.contains(patronAEliminar+"")) {

            // Aqui compruebo si el caracter en la posicion i es el deseado y si no lo es 
            // añado el caracter a la cadena que devolvere al acabar el bucle.
            for (int i = 0; i < str_OriginalString.length(); i++) {
                if (str_OriginalString.charAt(i) != patronAEliminar) {
                    str_UnspacedString += str_OriginalString.charAt(i);

                }

            }
        } else {
            str_UnspacedString = str_OriginalString;
        }

        return str_UnspacedString;
    }//fin cleanString
     
     /**
      * Esta clase recibe un caracter y si es un numero devuelve true,
      * en caso contrario devuelve false.
      * @param character
      * @return 
      */
     
     public static boolean isNumber(char character) {
        Boolean auxBoolean = false;

        auxBoolean = (character >= '0' && character <= '9');

        return auxBoolean;
    }
     
     /**
      * Esta clase recibe un caracter y dependiendo de cual sea devuelve su prioridad de operacion.
      * @param character
      * @return 
      */
      public static int priority(char character) {
        int int_aux = -1;

        switch (character) { // asigna una prioridad segun el char que recibe.
            case '(': {
                int_aux = 1;
                break;
            }
            case ')': {
                int_aux = 2;
                break;
            }

            case '+':
            case '-': {
                int_aux = 3;
                break;
            }

            case '*':
            case '/':
            case '%': {
                int_aux = 4;
                break;
            }

            case '^': {
                int_aux = 5;

            }
            break;
            default: {
                int_aux = -1;
                break;
            }

        } // fin switch

        return int_aux;
    }// fin priority
     /**
      * Una forma comoda de leer que va haciendo el programa al que se le envia un 
      * ArrayList y te muestra lo que contiene con cierto formato que puedes modificar
      * en la llamada
      * @param codigoDep
      * @param alAux 
      */
      public static void debug(String codigoDep, ArrayList<String> alAux) {
        System.out.print("--" + codigoDep + "--");
        for (String aux : alAux) {
            System.out.print(" " + aux);

        };

        System.out.print("--" + codigoDep + "--\n");

    }
      /**
       * Una forma de mostrar el contenido del hashmap de forma formateada
       * devolviendo el string 
       * @param atributos
       * @return 
       */
      
      public static String muestraMapa(HashMap<String, Atributo> atributos) {
	String auxString = "";	
          for (Map.Entry<String, Atributo> registro : atributos.entrySet()) {
			String key = registro.getKey();
			Atributo value = registro.getValue();	
			
			auxString += value.toString();
                        auxString +="\n";
                               
		}
                
	return auxString;}
}
