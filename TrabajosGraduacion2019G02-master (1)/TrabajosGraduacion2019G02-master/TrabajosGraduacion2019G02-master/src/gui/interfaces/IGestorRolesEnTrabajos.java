/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.interfaces;

import gui.personas.modelos.Profesor;
import gui.trabajos.modelos.Rol;
import gui.trabajos.modelos.RolEnTrabajo;
import java.time.LocalDate;

/**
 *
 * @author Rosalba Caldez
 */
public interface IGestorRolesEnTrabajos {
    RolEnTrabajo nuevoRolEnTrabajo(Profesor unProfesor, Rol unRol, LocalDate fechaDesde);
}
