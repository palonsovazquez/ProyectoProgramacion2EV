/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoprogramacion1.Parser.backend;
// #Alert comentario importante as atender cuando se acabe el programa.

import java.util.*;

import proyectoprogramacion1.Ficha.Ficha;
import static proyectoprogramacion1.Libreria.MetodosReutilizables.*;

/**
 *
 * @author Pablo Alonso Vazquez <pav.vigo@gmail.com>
 */
public class ConversorAPosfijo {

    private Ficha fic_ficha = null;

    Stack pilaNumeros = new Stack();
    Stack pilaOperadores = new Stack();
    String[] operadoresConocidos = {"+", "-", "*", "/", "%", "(", ")"
//        , "^"    ,
//        "pi",
//        "ln(",
//        "log(", "abs(", "sen(", "sin(", "cos(", "tan(", "sec(", "csc(", "cot(", "sgn(",
//        "rnd()", "asen(", "asin(", "acos(", "atan(", "asec(", "acsc(", "acot(", "senh(", "sinh(", "cosh(", "tanh(", "sech(", "csch(", "coth(", "sqrt(",
//        "round(", "asenh(", "acosh(", "atanh(", "asech(", "acsch(", "acoth("
    };
//    final private String parentesis = "( ln log abs sen sin cos tan sec csc cot asen asin acos atan asec acsc acot senh sinh cosh tanh sech csch coth sqrt round";
    // final private String operadoresBinarios = "+ - * / ^ %";
    private ArrayList<ArrayList<String>> funciones = new ArrayList<ArrayList<String>>();

    /**
     * Unico constructor permitidoConstructor
     *
     * @param ficha Internamente usa el metodo rellenarExpresiones para añadir
     * los Identificadores de los Atributos a las funciones reconocibles por el
     * programa.
     */
    public ConversorAPosfijo(Ficha ficha) {
        this.fic_ficha = ficha;
        rellenarExpresiones();
    }

    public Ficha getFicha() {
        return fic_ficha;
    }

    public void setFicha(Ficha ficha) {
        this.fic_ficha = ficha;
    }

    /**
     * Metodo que recoge los identificadores de los atributos de la ficha para
     * agregarselo a las funciones reconocidas por el programa.
     *
     *
     */
    private void rellenarExpresiones() {
        Iterator ite_Atributos = fic_ficha.getHam_ListaAtributos().keySet().iterator();
        String str_aux = "";
        String expresion = "";

        for (int i = 0; i < operadoresConocidos.length; i++) {
            expandirArrayList(operadoresConocidos[i]);
            funciones.get(operadoresConocidos[i].length() - 1).add(operadoresConocidos[i]);

        }

        while (ite_Atributos.hasNext()) {
            str_aux = (String) ite_Atributos.next();

            expandirArrayList(str_aux);
            funciones.get(str_aux.length() - 1).add(str_aux);
        }

        for (ArrayList<String> auxAL : funciones) {
            for (String auxstr : auxAL) {

            }

        }

    }

    private void expandirArrayList(String str_aux) {

        while (str_aux.length() > funciones.size()) {
            funciones.add(new ArrayList<String>());
        }

    }

    /**
     * Este metodo lee a partir del caracter actual marcado en posicion y no
     * para hasta encontrarse algo que no sea un numero o una , o .
     *
     * @param posicion posicion del primer caracter detectado como numero
     * @param expresion string completo con la expresion a convertir a posfijo
     * @return devuleve el string con el numero extraido.
     */
    private String extractorNumeros(int posicion, String expresion) {
        String str_aux = "";
        int int_tamanho = 0;
        int int_pos = posicion;
        while (int_pos + int_tamanho < expresion.length() && int_tamanho < expresion.length() - 1
                && (isNumber(expresion.charAt(int_pos + int_tamanho))
                || ((expresion.charAt(int_pos + int_tamanho) == ',' && int_tamanho > 0)
                || (expresion.charAt(int_pos + int_tamanho) == '.' && int_tamanho > 0)))) {

            int_tamanho++;

        }

        str_aux = expresion.substring(int_pos, int_pos + int_tamanho);

        return str_aux;
    }

    /**
     * Metodo que separa en Strings que representan o un operando o un operador
     * en la expresion original
     *
     * @param expresion
     * @return devuelve un arraylist con los elementos separados.
     */
    private ArrayList<String> separadorEnElementos(String expresion) {
        expresion = unspaceString(expresion);
        expresion = cleanString(expresion, ';');

        int int_pos = 0;
        int int_tamanho = 0;
        int int_contador;
        boolean encontrado = false;
        String str_subString = "";
        ArrayList<String> strAL_auxArray = new ArrayList<String>();
        if (expresion != null && expresion.length() > 0) {
            do {
                if (isNumber(expresion.charAt(int_pos))) { // es un numero?
                    strAL_auxArray.add(extractorNumeros(int_pos, expresion));
                    str_subString = extractorNumeros(int_pos, expresion);
                    int_pos = int_pos + str_subString.length(); //   puntero   desplazado hasta el caracter siguiente al numero recogido               
                } else {
                    int_contador = 1;

                    do {

                        str_subString = expresion.substring(int_pos, int_pos + int_contador);

                        encontrado = isFunction(str_subString); // es funcion o atributo?

                        if (encontrado) {
                            strAL_auxArray.add(str_subString);
                            int_pos = int_pos + str_subString.length() - 1;
                        }
                        int_contador++;

                    } while (!encontrado && (int_contador <= funciones.size() && int_contador + int_pos < expresion.length()));

                    if (!encontrado) {
                        throw new SintaxException("Error de sintaxis en el polinomio expresion no valida");
                    }
                    int_contador = 0;
                    int_pos++;
                }

            } while (int_pos < expresion.length());

        } else {
            throw new SintaxException("Error de sintaxis en el polinomio texto nulo o vacio ");//throw SintaxException Expresion mal formada
        }

        return strAL_auxArray;
    }

    /**
     * Cuenta cuantos elementos de un char tiene un string.
     *
     * @param strAL_Infija
     * @param elemento
     * @return
     */
    private int contadorElementos(ArrayList<String> strAL_Infija, String elemento) {
        int int_contador = 0;
        for (String str_aux : strAL_Infija) {
            if (0 == str_aux.compareTo(elemento)) {
                int_contador++;
            }
        }
        return int_contador;
    }

    /**
     * Programa principal que va leyendo la expresion convertida a ArrayList y
     * la va modificando hasta que esta en notacion posfija.
     *
     * @param expresion
     * @return
     */
    public ArrayList<String> parsear(String expresion) {
        ArrayList<String> strAL_Infija = separadorEnElementos(expresion);
        ArrayList<String> strAL_Posfija = new ArrayList<String>();
        Stack pilaNumeros = new Stack();
        Stack pilaOperadores = new Stack();
        int contador = 0;
        int int_parIz = contadorElementos(strAL_Infija, "(");
        int int_parDer = contadorElementos(strAL_Infija, ")");
        if (int_parIz > int_parDer) {
            throw new SintaxException("Error en la formulacion falta un parentesis derecho.");

        } else if (int_parIz < int_parDer) {
            throw new SintaxException("Error en la formulacion falta un parentesis Izquierdo.");
        } else {
            for (String str_aux : strAL_Infija) {

                // primero compruebo si es un numero o algo asemejado a un numero( un atributo) o una constante conocida.
                if (isNumber(str_aux.charAt(0)) || fic_ficha.getHam_ListaAtributos().containsKey(str_aux) || 0 == str_aux.compareToIgnoreCase("pi")) {

                    strAL_Posfija.add(str_aux);

                } else {

                    if (!pilaOperadores.isEmpty()) {

                    }

                    switch (str_aux.length()) {
                        case 0: {
                            throw new SintaxException("Error Operador vacio"); // tiene que fallar el separadorEnElementos para que se de este caso
                        }

                        case 1: {

                            switch (priority(str_aux.charAt(0))) {

                                case 1: // ( --> insertar el operador en la pila

                                    pilaOperadores.push(str_aux);

                                    break;

                                case 2: // mientras la pila no este vacia y la cima de pila de operadores no sea un ( extraer elemento e insertarlo en el arraylist de salida

                                    while (!pilaOperadores.isEmpty() && pilaOperadores.peek().toString().charAt(0) != '(') {
                                        strAL_Posfija.add((String) pilaOperadores.pop());
                                    }

                                    if (!pilaOperadores.isEmpty() && pilaOperadores.peek().toString().charAt(0) == '(') { // si lo ultimo que ha sacado es un ( lo elimina del arraylist, si no encuentra uno da una excepcion
                                        pilaOperadores.pop();

                                    } else {

                                        throw new SintaxException("Se esperaba un (.");

                                    }

                                    break;
                                /**
                                 * Si la pila esta vacia o el operador str_aux
                                 * tengra una precedencia menor que la del
                                 * operador cima de la pila añadirlo a la pila
                                 *
                                 * mientra la pila no este vacía y su cima sea
                                 * un operador de precedencia mayor o igual que
                                 * la de str_aux extraer elemento de la pila
                                 * Insertarlo al final del arraylist de salida
                                 *
                                 *
                                 */
                                case 3: // La pila no este vacía y su cima sea un operador  de precedencia mayor o igual que la de str_aux Extraer elemento de la pila Insertarlo al final de la lista de salida
                                   
                                    if (pilaOperadores.empty() || priority(pilaOperadores.peek().toString().charAt(0)) < 3) {
                                        pilaOperadores.push(str_aux);
                                    } else {
                                        
                                        while (!(pilaOperadores.isEmpty()) && priority(pilaOperadores.peek().toString().charAt(0)) >= 3) {

                                           
                                            strAL_Posfija.add((String) pilaOperadores.pop());
                                        }
                                        pilaOperadores.push(str_aux);
                                    }
                                    break;

                                case 4:// * / % 
                                    
                                    if (pilaOperadores.empty() || priority(pilaOperadores.peek().toString().charAt(0)) < 4) {
                                        pilaOperadores.push(str_aux);
                                    } else {
                                        
                                        while (!(pilaOperadores.isEmpty()) && priority(pilaOperadores.peek().toString().charAt(0)) >= 4) {

                                           
                                            strAL_Posfija.add((String) pilaOperadores.pop());
                                        }
                                        pilaOperadores.push(str_aux);
                                    }

                                    break;

                                case 5: // ^ siempre se agrega a la pila de operadores
                                   
                                    pilaOperadores.push(str_aux);

                                    break;
                                case -1: // nunca deberia dar este resultado
                                   
                                    break;

                            }
                            break;
                        }

                    }
                }


            }
            
            while (!pilaOperadores.isEmpty()) {
                strAL_Posfija.add((String) pilaOperadores.pop());
            }
            
        }

        return strAL_Posfija;
    }
   

    /**
     * Eevisa la lista de funciones para ver si se corresponde con el texto que recibe.
     * @param text
     * @return 
     */
    public boolean isFunction(String text) {//!Alert debe ser privada
        Boolean auxboolean = false;
        if (text.length() <= funciones.size()) {
            //////System.out.println("dafs: " + text);
            auxboolean = funciones.get(text.length() - 1).contains(text);
        }
        return auxboolean;
    }
/**
 * Excepcion especifica para esta clase.
 */
    private class SintaxException extends ArithmeticException {

        public SintaxException() {
            super("Error de sintaxis en el polinomio");
        }

        public SintaxException(String e) {
            super(e);
        }
    }

}
