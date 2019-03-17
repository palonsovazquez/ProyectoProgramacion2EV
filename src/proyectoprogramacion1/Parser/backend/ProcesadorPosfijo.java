/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion1.Parser.backend;

import java.util.ArrayList;
import java.util.Stack;
import proyectoprogramacion1.Ficha.Atributo;
import proyectoprogramacion1.Ficha.Ficha;
import static proyectoprogramacion1.Libreria.MetodosReutilizables.*;

/**
 *
 * @author Pablo Alonso Vazquez <pav.vigo@gmail.com>
 */
public class ProcesadorPosfijo {

    private Ficha fic_ficha = null;

    public ProcesadorPosfijo(Ficha fic_ficha) {
        setFic_ficha(fic_ficha);
    }

    public Ficha getFic_ficha() {
        return fic_ficha;
    }

    public void setFic_ficha(Ficha fic_ficha) {
        this.fic_ficha = fic_ficha;
    }

    public Double calcular(ArrayList<String> strAL_listaPostfija) {
        Double dou_PrimerOperando = null, dou_SegundoOperando = null, dou_resultado = null;

        Stack<Double> stackNumeros = new Stack<Double>();

        for (String str_aux : strAL_listaPostfija) {

            if (isNumber(str_aux.charAt(0))) { // es un numero
                
                stackNumeros.push(Double.parseDouble(str_aux));

            } else if (priority(str_aux.charAt(0)) != -1) { // es un operador
                
                if (stackNumeros.size() >= 2) {

                    dou_SegundoOperando = stackNumeros.pop();
                    
                    dou_PrimerOperando = stackNumeros.pop();

                    
                    switch (str_aux.charAt(0)) {
                        case '+': {
                            dou_resultado = dou_PrimerOperando + dou_SegundoOperando;
                            stackNumeros.push(dou_resultado);
                            break;
                        }
                        case '-': {  
                            dou_resultado = dou_PrimerOperando - dou_SegundoOperando;
                            stackNumeros.push(dou_resultado);
                            break;
                        }
                        case '*': {
                            dou_resultado = dou_PrimerOperando * dou_SegundoOperando;
                            stackNumeros.push(dou_resultado);
                            break;
                        }
                        case '/': {
                            if (dou_SegundoOperando != 0) {
                                dou_resultado = dou_PrimerOperando / dou_SegundoOperando;
                                stackNumeros.push(dou_resultado);
                            } else {
                                throw new OperationException("Error, no se puede dividor por 0");

                            }
                            break;
                        }
                        case '%': {
                            dou_resultado = dou_PrimerOperando % dou_SegundoOperando;
                            stackNumeros.push(dou_resultado);
                            break;
                        }
//                        case '^':{  // sin implementar
//                            dou_resultado = dou_PrimerOperando % dou_SegundoOperando;
//                            break;}
                        default:
                            throw new OperationException("operacion no soportada");
                    }
                }
            } else if (fic_ficha.getHam_ListaAtributos().containsKey(str_aux)) { // verifica si es un atributo
                Atributo atr_aux = null;
                Object obj_aux = fic_ficha.getHam_ListaAtributos().get(str_aux); // si lo es extrae el atributo del hashmap por su id
                if (obj_aux instanceof Atributo) { // verifica si el atributo es correcto y si lo es lo castea a atributo.
                    atr_aux = (Atributo) obj_aux;
                    
                    stackNumeros.add(atr_aux.getDou_Valor()); // usa el valor del atributo para añadirlo como un numero a la pila de numeros.
                }
            } else {
                
            }

        }
       
        return dou_resultado;
    }
/**
 * Excepcion especifica de esta clase.
 */
    private class OperationException extends ArithmeticException {

        public OperationException() {
            super("Error de operacion");
        }

        public OperationException(String e) {
            super(e);
        }
    }

}
