/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.interfaces;

import gui.personas.modelos.Alumno;
import gui.trabajos.modelos.AlumnoEnTrabajo;
import java.time.LocalDate;

/**
 *
 * @author Rosalba Caldez
 */
public interface IGestorAlumnosEnTrabajos {
    AlumnoEnTrabajo nuevoAlumnoEnTrabajo(LocalDate fechaDesde, Alumno unAlumno);
}
