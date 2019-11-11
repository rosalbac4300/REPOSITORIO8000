/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.trabajos.modelos;

import gui.interfaces.IGestorRolesEnTrabajos;
import gui.personas.modelos.Profesor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rosalba Caldez
 */
public class GestorRolEnTrabajo implements IGestorRolesEnTrabajos{
    public static GestorRolEnTrabajo gestor;
    private List<RolEnTrabajo> ret = new ArrayList<>();
    
    private GestorRolEnTrabajo(){
        
    }
    
    public GestorRolEnTrabajo crear(){
        if(gestor == null){
            gestor = new GestorRolEnTrabajo();
        }
        return gestor;
    }
    
    /**
     * Controla que los valores de los parámetros sean correctos. Si es así, crea un nuevo RolEnTrabajo
     * Si no puede crear el rol, retorna null, sino devuelve el rol creado. 
     * @param unProfesor
     * @param unRol
     * @param fechaDesde
     * @return 
     */
    @Override
    public RolEnTrabajo nuevoRolEnTrabajo(Profesor unProfesor, Rol unRol, LocalDate fechaDesde) {
        if((unProfesor != null) && (unRol != null) && (fechaDesde != null)){
            RolEnTrabajo rol = new RolEnTrabajo(fechaDesde, unProfesor, unRol);
            ret.add(rol);
            return rol;
        }
        return null;
    }
    
}
