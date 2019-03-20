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
import java.io.IOException;
import java.text.ParseException;
import proyectoprogramacion1.Parser.backend.ConversorAPosfijo;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import proyectoprogramacion1.Ficha.Ficha;
import proyectoprogramacion1.Parser.*;
import proyectoprogramacion1.Parser.*;
import proyectoprogramacion1.Parser.backend.ProcesadorPosfijo;
import static proyectoprogramacion1.Libreria.MetodosReutilizables.*;

/**
 *
 * @author Pablo Alonso Vazquez <pav.vigo@gmail.com>
 */
public class ProyectoProgramacion1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Demo1().run();
      
    }
    
}