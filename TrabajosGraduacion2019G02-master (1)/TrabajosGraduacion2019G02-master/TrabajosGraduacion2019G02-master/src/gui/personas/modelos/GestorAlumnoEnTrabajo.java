/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.personas.modelos;

import gui.interfaces.IGestorAlumnosEnTrabajos;
import gui.trabajos.modelos.AlumnoEnTrabajo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rosalba Caldez
 */
public class GestorAlumnoEnTrabajo implements IGestorAlumnosEnTrabajos{
    public static GestorAlumnoEnTrabajo gestor;
    private List<AlumnoEnTrabajo> aet = new ArrayList<>();
    
    private GestorAlumnoEnTrabajo(){
        
    }
    public GestorAlumnoEnTrabajo crear(){
        if(gestor == null){
            gestor = new GestorAlumnoEnTrabajo();
        }
        return gestor;
    }
    
    @Override
    public AlumnoEnTrabajo nuevoAlumnoEnTrabajo(LocalDate fechaDesde, Alumno unAlumno) {
        if((fechaDesde != null) && (unAlumno != null)){
            AlumnoEnTrabajo alumno = new AlumnoEnTrabajo(fechaDesde, unAlumno);
            aet.add(alumno);
            return alumno;
        }
        return null;
    }
    
}
