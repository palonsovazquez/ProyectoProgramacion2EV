package proyectoprogramacion1;
import  proyectoprogramacion1.Controlador.Controlador;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import proyectoprogramacion1.Ficha.Ficha;
import proyectoprogramacion1.Parser.Parser;
import proyectoprogramacion1.ProyectoProgramacion1;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pablo Alonso Vazquez <pav.vigo@gmail.com>
 */
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
}else{ // fin de if
    // File fichero = new File("/home/pablo/NetBeansProjects/prueba_ficheros23/datos.txt");
          Controlador controlador = new Controlador(fichero);
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
        //System.out.println(ficha.toString());
        
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
