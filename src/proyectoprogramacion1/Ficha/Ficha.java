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

import java.util.ArrayList;
import java.util.HashMap;
import static proyectoprogramacion1.Libreria.MetodosReutilizables.muestraMapa;


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

