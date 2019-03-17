
package proyectoprogramacion1.Ficha;

import java.util.ArrayList;
import java.util.HashMap;
import static proyectoprogramacion1.Libreria.MetodosReutilizables.muestraMapa;

/**
 *
 * @author Kinkalla
 */
public class Ficha {
     private HashMap<String,Atributo> Ham_ListaAtributos = null;
    private ArrayList<String> AL_listaOperaciones = null;

   
    
   

   
    
    public Ficha(HashMap arl_ListaOperaciones, ArrayList<String> listaOperaciones) {
        this.Ham_ListaAtributos = arl_ListaOperaciones;
        this.AL_listaOperaciones = listaOperaciones;
    }

    public HashMap getHam_ListaAtributos() {
        return Ham_ListaAtributos;
    }

    public void setHam_ListaAtributos(HashMap Ham_ListaAtributos) {
        this.Ham_ListaAtributos = Ham_ListaAtributos;
    }

    public ArrayList<String> getAL_listaOperaciones() {
        return AL_listaOperaciones;
    }

    public void setAL_listaOperaciones(ArrayList<String> AL_listaOperaciones) {
        this.AL_listaOperaciones = AL_listaOperaciones;
    }

    @Override
    public String toString() {
        String string = "";
       string += "\t\t\t Ficha:\n";
       string += "Atributos.\n";
       string += muestraMapa(Ham_ListaAtributos);
       string += "Operaciones:\n";
       for(String auxstring : AL_listaOperaciones){
       string += " -"+ auxstring + ";\n";     
       }
        
        
        
        return string;
    }
    
}

