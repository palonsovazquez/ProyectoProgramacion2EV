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
package proyectoprogramacion1;
import  proyectoprogramacion1.Controlador.Controlador;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import proyectoprogramacion1.Ficha.Ficha;
import proyectoprogramacion1.Parser.Parser;



public class Demo1 {

    public Demo1() {
    }
   public void run(){
       JFileChooser jfiCho = new JFileChooser();
       jfiCho.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
       int resultado = jfiCho.showOpenDialog(null);
       File fichero = jfiCho.getSelectedFile();
       if ((fichero == null) || (fichero.getName().equals(""))) {
 JOptionPane.showMessageDialog(null, "Nombre de archivo inválido", "Nombre de archivo inválido", JOptionPane.ERROR_MESSAGE);
}else{ 
    
          Controlador controlador = null;
           try {
               controlador = new Controlador(fichero);
           } catch (FileNotFoundException ex) {
               System.out.println(ex);
           }
          Ficha ficha = null;
          String str_auxString ="\n-------------------------------------\n";
          
        try {
           
           ficha = controlador.run();
          
        } catch (ParseException ex) {
            Logger.getLogger(ProyectoProgramacion1.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProyectoProgramacion1.class.getName()).log(Level.SEVERE, null, ex);
        }
        Parser parser = new Parser(ficha);
        
        
        if (ficha != null){
        
            str_auxString += ficha.getHam_ListaAtributos().toString();
            str_auxString += "\nOperaciones:\n";
            for(String operacion: ficha.getAL_listaOperaciones()){
            try{
                System.out.println(operacion);
                str_auxString += operacion +" = "+ parser.Procesar(operacion)+".\n";
            }catch (Exception e){
                System.out.println(e);
            
            }
            
            }
            str_auxString += "\n-------------------------------------\n";
            
            System.out.println(str_auxString);
        
            FileWriter filWri = null;
              try {
                   filWri = new FileWriter(fichero,true);
                    filWri.write(str_auxString);
                    
              } catch (IOException ex) {
                  Logger.getLogger(Demo1.class.getName()).log(Level.SEVERE, null, ex);
              }finally{
                try {
                    filWri.close();
                } catch (IOException ex) {
                    Logger.getLogger(Demo1.class.getName()).log(Level.SEVERE, null, ex);
                }
              
              }
              
        }
   }
    
   } 
    
}
