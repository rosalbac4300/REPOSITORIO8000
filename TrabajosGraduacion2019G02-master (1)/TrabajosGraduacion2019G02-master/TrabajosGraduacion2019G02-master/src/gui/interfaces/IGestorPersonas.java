/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.interfaces;

import gui.personas.modelos.Alumno;
import gui.personas.modelos.Cargo;
import gui.personas.modelos.Profesor;
import java.util.List;

/**
 *
 * @author Rosalba Caldez
 */
public interface IGestorPersonas {
    String nuevoProfesor (String apellidos, String nombres, int dni, Cargo cargo);
    String nuevoAlumno (String apellidos, String nombres, int dni, String cx);
    List<Profesor> buscarProfesores (String apellidos);
    List<Alumno> buscarAlumnos (String apellidos);
    Profesor dameProfesor(int documento);
    Alumno dameAlumno(String cx);
    void mostrarPersonas();
    void mostarAlumnos();
    void mostrarProfesores();
    String modificarProfesor(Profesor profesor, String apellidos, String nombres, Cargo cargo);
    String modificarAlumno(Alumno alumno, String apellidos, String nombres, String cx);
    String borrarProfesor(Profesor profesor);
    String borrarAlumno(Alumno alumno);
}
