/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.interfaces;

import gui.areas.modelos.Area;
import gui.personas.modelos.Alumno;
import gui.personas.modelos.Profesor;
import gui.trabajos.modelos.AlumnoEnTrabajo;
import gui.trabajos.modelos.RolEnTrabajo;
import gui.trabajos.modelos.Trabajo;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Rosalba Caldez
 */
public interface IGestorTrabajos {
    String nuevoTrabajo(String titulo, int duracion, LocalDate fechaPresentacion, LocalDate fechaAprobacion, List<Area> areas, List<RolEnTrabajo> profesores, List<AlumnoEnTrabajo> aet);
    Trabajo dameTrabajo(String titulo);
    List<Trabajo> buscarTrabajos(String titulo);
    void mostrarTrabajos();
    String finalizarTrabajo(Trabajo trabajo, LocalDate fechaExposicion);
    String borrarTrabajo(Trabajo trabajo);
    String reemplazarProfesor(Trabajo trabajo, Profesor profesorReemplazado, LocalDate fechaHasta, String razon, Profesor nuevoProfesor);
    String finalizarAlumno(Trabajo trabajo, Alumno alumno, LocalDate fechaHasta, String razon);
}
