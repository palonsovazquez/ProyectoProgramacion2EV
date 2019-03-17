package proyectoprogramacion1.Controlador;

import proyectoprogramacion1.Ficha.Atributo;
import proyectoprogramacion1.Ficha.Ficha;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import proyectoprogramacion1.Libreria.MetodosReutilizables;
import static proyectoprogramacion1.Libreria.MetodosReutilizables.cleanString;

public class Controlador {

    File fich;

    public void setFich(File fich) throws FileNotFoundException {
        if (fich.isFile() && fich.canRead()) {
            this.fich = fich;
            
        } else {
            //System.out.println("El fichero no funciona"); //!Alert cambiar por throw exception.
            throw new FileNotFoundException("Fichero inexistente o inaccesible");
        }
    }

    public Controlador(File fichero) throws FileNotFoundException {
        setFich(fichero);
    }

    public Ficha run() throws ParseException, IOException {
        Ficha ficha =  getDatosFichero(fich);
        
        return ficha;
    }



    private Ficha getDatosFichero(File fichero) // Modificar la salida al gusto
            throws FileNotFoundException, ParseException, IOException {
        // Peque�o control de estados
        int estado = 0; // 0 Inicial, 1 cargando atributos, 2 cargando formulas, 3 salir

        
        Ficha ficha = null;
        HashMap<String, Atributo> atributos = new HashMap<String, Atributo>();
        ArrayList<String> formulas = new ArrayList<String>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            if (fichero.exists()) {
                fileReader = new FileReader(fichero);
                bufferedReader = new BufferedReader(fileReader);
                String cadenaLeida;
                
                while (((cadenaLeida = bufferedReader.readLine()) != null) && estado != 3) {
                    // Aqui va la logica de proceso de las cadenas leidas desde el fichero
                    cadenaLeida = cadenaLeida.trim();
                    if (!cadenaLeida.isEmpty()) {
                        if (!cadenaLeida.substring(0, 2).equals("//")) {
                            // Cosas que hacemos despues de procesar los comentarios
                            switch (cadenaLeida) {
                                case "DECLARE": // if
                                    // //System.out.println("estado leyendo atributos");
                                    estado = 1;
                                    break;
                                case "BEGIN": // elseif
                                    // //System.out.println("estado leyendo formulas");
                                    estado = 2;
                                    break;
                                case "END": // elseif
                                    // //System.out.println("estado saliendo");
                                    estado = 3;
                                    break;
                                default: // else
                                    if (estado == 1) {
                                        //System.out.println("0--"+cadenaLeida);
                                        Atributo atributo = atributoFromString(cadenaLeida);
                                        //System.out.println("Atributo = "+ atributo.toString());
                                        atributos.put(atributo.getStr_ID(), atributo);
                                    } else if (estado == 2) {
                                        formulas.add(cleanString(cadenaLeida,';'));
                                    }
                                    break;
                            }

                        } else {
                            //System.out.println("Comentario: " + cadenaLeida);
                        }
                    }
                }
                ficha = new Ficha(atributos, formulas);
            }else{
            // throw exception
            }
        } catch (Exception e) {
            //System.out.println("Error parseando archivo.");
        }finally{
             bufferedReader.close();
            fileReader.close();
       
        
        }
        return ficha;
    }

    private Atributo atributoFromString(String cadenaDatos) throws ParseException, Exception {

    Atributo auxAtributo = new Atributo();
        cadenaDatos = cleanString(cadenaDatos, ';');
        Scanner scanAtri = new Scanner(cadenaDatos).useDelimiter(","); // divido la cadena de texto en 3 si esta bien formada(faltaria comprobar que esta bien )
        ArrayList<String> al_aux= new ArrayList<>();
        
        
        while(scanAtri.hasNext()){          // agrego a un arraylist de strings temporal los elementos separados
        al_aux.add(scanAtri.next());
               
        }
        if(3 == al_aux.size()){ // compruebo si son 3 elementos como se esperaria de un texto bien fomrado
        auxAtributo.setStr_ID(al_aux.get(0)); // asigno el dato correspondiente al id en esta estructura el primer elemento(0)
        auxAtributo.setStr_Nombre(al_aux.get(1));  // lo mismo con el nombre , elemento (1)
        auxAtributo.setDou_Valor(Double.parseDouble(al_aux.get(2)));  // lo mismo pero con un parse double ya que es lo que necesita el atributo(hay que vigilar la excepcion de bad number format)
        
        }
        else {
         throw new Exception("Atributo mal escrito en el fichero el formato es IDD,Nombre,Valor;");
        
        
        }
        return auxAtributo;
    }


}
