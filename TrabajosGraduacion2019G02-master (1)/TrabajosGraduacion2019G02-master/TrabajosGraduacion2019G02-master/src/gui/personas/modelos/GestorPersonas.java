/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.personas.modelos;

import gui.interfaces.IGestorPersonas;
import gui.trabajos.modelos.AlumnoEnTrabajo;
import gui.trabajos.modelos.GestorTrabajos;
import gui.trabajos.modelos.RolEnTrabajo;
import gui.trabajos.modelos.Trabajo;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Rosalba Caldez
 */
public class GestorPersonas implements IGestorPersonas{
    public static GestorPersonas gestor;
    private List<Persona> personas = new ArrayList<>();
    
    private GestorPersonas(){
        
    }
    
    public static GestorPersonas crear(){
        if(gestor == null){
            gestor = new GestorPersonas();
        }
        return gestor;
    }
    
    
    /**
     * Crea una persona y la agrega a la lista siempre y cuando: 
     *  -  Los valores de los parámetros recibidos sean correctos.
     *  -  No exista otro profesor igual en la lista.
     * @param apellidos
     * @param nombres
     * @param dni
     * @param cargo
     * @return Informa si se agrego o no el profesor a la lista. 
     */
    @Override
    public String nuevoProfesor(String apellidos, String nombres, int dni, Cargo cargo) {
        if((apellidos == null) || (apellidos.isEmpty())){
            return "No se agregó el profesor a la lista.";
        }
        if((nombres == null) || (nombres.isEmpty())){
            return "No se agregó el profesor a la lista.";
        }
        if(dni <= 0){
            return "No se agregó el profesor a la lista.";
        }
        if(cargo == null){
            return "No se agregó el profesor a la lista.";
        }
        Profesor profesor = new Profesor(apellidos, nombres,dni,cargo);
        if(this.personas.contains(profesor)){
           return "No se agregó el profesor a la lista."; 
        }
        this.personas.add(profesor);
        return "Se agregó el profesor a la lista.";
    }
    /**
     * Crea un nuevo alumno y lo agrega a la lista siempre y cuando los parametros sean correctos y no exista ya un alumno igual en la lista
     * @param apellidos
     * @param nombres
     * @param dni
     * @param cx
     * @return Devuelve un String indicando si se agregó o no el alumno a la lista.
     */
    @Override
    public String nuevoAlumno(String apellidos, String nombres, int dni, String cx) {
        if((apellidos.isEmpty())||(apellidos == null)){
            return "No se agregó el alumno a la lista.";
        }
        if((nombres.isEmpty())||(nombres == null)){
            return "No se agregó el alumno a la lista.";
        }
        if(dni<=0){
            return "No se agregó el profesor a la lista.";
        }
        if((cx.isEmpty())||(cx == null)){
            return "No se agregó el profesor a la lista.";
        }
        Alumno alumno = new Alumno(apellidos, nombres,dni,cx);
        if(this.personas.contains(alumno)){
            return "No se agregó el profesor a la lista.";
        }
        personas.add(alumno);
        return "Se agregó el alumno a la lista.";
    }

    /**
     * Busca si existen Profesores cuyo apellido coincida con el filtro especificado. 
     * Devuelve el resultado en una lista, si no hay coincidencias, devuelve una lista vacia.
     * @param apellidos
     * @return 
     */
    @Override
    public List<Profesor> buscarProfesores(String apellidos) {
        List<Profesor> coincidencias = new ArrayList<>();
        if(apellidos == null){ 
            // Recorre la lista de personas
            for(Persona p : this.personas){
                // A todas las personas que sean instancias de Profesor las anade a la lista coincidencias
                // ya que el filtro es null
                if(p instanceof Profesor){
                    coincidencias.add((Profesor)p);
                }
            }
            return coincidencias; //Si el filtro es nulo, devuelve todos los profesores
        }
        
        for(Persona p : this.personas){
            if(p instanceof Profesor){
                if(p.getApellido().contains(apellidos)){
                    coincidencias.add((Profesor)p);
                }
            }
        }
        return coincidencias;
    }
    
    /**
     * Busca si existen alumnos cuyo apellido coincida con el filtro especificado. 
     * Devuelve el resultado en una lista, si no hay coincidencias retornará la lista vacía.
     * @param apellidos
     * @return 
     */
    @Override
    public List<Alumno> buscarAlumnos(String apellidos) {
        List<Alumno> coincidencias = new ArrayList<>();
        if(apellidos == null){ 
            // Recorre la lista de personas
            for(Persona p : this.personas){
                // A todas las personas que sean instancias de Alumno las anade a la lista coincidencias
                // ya que el filtro es null
                if(p instanceof Alumno){
                    coincidencias.add((Alumno)p);
                }
            }
            return coincidencias; //Si el filtro es nulo, devuelve todos los alumno
        }
        
        for(Persona p : this.personas){
            if(p instanceof Alumno){
                if(p.getApellido().contains(apellidos)){
                    coincidencias.add((Alumno)p);
                }
            }
        }
        return coincidencias;
    }
    /**
     * Busca si existe un profesor cuyo documento coincide con el filtro especificado. 
     * Si encuentra coincidencias devuelve el profesor encontrado, sino devuelve null.
     * @param documento
     * @return 
     */
    @Override
    public Profesor dameProfesor(int documento) {
        for(Persona p : this.personas){
            if(p instanceof Profesor){
                if(p.getDni() == documento){
                    return (Profesor) p;
                }
            }
        }
        return null;
    }
    /**
     * Busca si existe un Alumno cuyo cx coincida con el filtro especificado.
     * Si encuentra coincidencias, devuelve el alumno encontrado, sino devuelve null.
     * @param cx
     * @return 
     */
    @Override
    public Alumno dameAlumno(String cx) {
        for(Persona p : this.personas){
            if(p instanceof Alumno){
                Alumno a = (Alumno) p;
                if(a.getCx().equals(cx)){
                   return a; 
                }
            }
        }
        return null;
    }
    
    /**
     * Muestra las personas de la lista.
     */
    @Override
    public void mostrarPersonas() {
        Comparator<Persona> compApellidoYNombre = new Comparator<Persona>(){
            @Override
            public int compare(Persona o1, Persona o2) {
                if(o1.getApellido().compareToIgnoreCase(o2.getApellido()) == 0){
                    return o1.getNombre().compareToIgnoreCase(o2.getNombre());
                }
                else{
                    return o1.getApellido().compareToIgnoreCase(o2.getApellido());
                }
            }
            
        };

        this.personas.sort(compApellidoYNombre);

        for(Persona p : this.personas){
            p.mostrar();
        }
    }
    
    /**
     * Muestra los Alumnos de la lista
     */
    @Override
    public void mostarAlumnos() {
        for(Persona p : this.personas){
            if(p instanceof Alumno){
                p.mostrar();
            }       
        }
    }
    
    /**
     * Muestra los Profesores de la lista
     */
    @Override
    public void mostrarProfesores() {
        for(Persona p : this.personas){
            if(p instanceof Profesor){
                p.mostrar();
            }       
        }
    }
    
    /**
     * permite modificar los datos de un profesor/alumno
     * realiza las validaciones de datos que sean necesarias
     * no se debe poder modificar el DNI
     * deberá informar el resultado de la operación
     * @param profesor
     * @param apellidos
     * @param nombres
     * @param cargo
     * @return 
     */
    @Override
    public String modificarProfesor(Profesor profesor, String apellidos, String nombres, Cargo cargo) {
        if((apellidos == null) || (apellidos.isEmpty())){
            return "Apellido invalido.";
        }
        if((nombres == null) || (nombres.isEmpty())){
            return "Nombre invalido.";
        }
        if(cargo == null){
            return "Cargo invalido.";
        }
        
        if(!this.personas.contains(profesor)){
            return "El profesor no está en la lista.";
        }
        profesor.setApellido(apellidos);
        profesor.setNombre(nombres);
        profesor.setCargo(cargo);
        
        return "La informacion ha sido modificada.";
    }

    @Override
    public String modificarAlumno(Alumno alumno, String apellidos, String nombres, String cx) {
        if((apellidos == null) || (apellidos.isEmpty())){
            return "Apellido invalido.";
        }
        if((nombres == null) || (nombres.isEmpty())){
            return "Nombre invalido.";
        }
        if((cx == null) || (cx.isEmpty())){
            return "CX invalido.";
        }
        
        if(!this.personas.contains(alumno)){
            return "El alumno no se encuentra en la lista.";
        }
        
        alumno.setApellido(apellidos);
        alumno.setNombre(nombres);
        alumno.setCx(cx);
        
        return "La informacion ha sido modificada.";
    }
    
    /**
     * permite borrar un profesor/alumno.
     * no se pueden borrar profesores / alumnos que se encuentren vinculados a un Trabajo.
     * Informa el resultado de la operación.
     * @param profesor
     * @return 
     */
    @Override
    public String borrarProfesor(Profesor profesor) {
        GestorTrabajos gestor = GestorTrabajos.crear();
        
        for(Trabajo t : gestor.getTrabajos()){
            for(RolEnTrabajo rot : t.getRolTrabajo()){
                if(rot.getProfesor().equals(profesor)){
                    return "El profesor esta vinculado al trabajo " + t.getTitulo().toUpperCase();
                }
            }
        }
        
        if(!this.personas.contains(profesor)){
            return "El profesor no está en la lista.";
        }
        
        this.personas.remove(profesor);
        return "El profesor ha sido eliminado.";
    }

    @Override
    public String borrarAlumno(Alumno alumno) {
        GestorTrabajos gestor = GestorTrabajos.crear();
        
        for(Trabajo t : gestor.getTrabajos()){
            for(AlumnoEnTrabajo aot : t.getAlumTrabajo()){
                if(aot.getAlumno().equals(alumno)){
                    return "El alumno esta vinculado al trabajo " + t.getTitulo().toUpperCase();
                }
            }
        }
        
        if(!this.personas.contains(alumno)){
            return "El alumno no está en la lista.";
        }
        
        this.personas.remove(alumno);
        return "El alumno ha sido eliminado.";
    }
    
    
    
}
