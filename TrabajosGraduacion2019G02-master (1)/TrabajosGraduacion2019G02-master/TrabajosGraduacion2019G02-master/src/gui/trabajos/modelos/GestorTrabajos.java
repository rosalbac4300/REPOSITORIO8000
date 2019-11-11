/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.trabajos.modelos;

import gui.areas.modelos.Area;
import gui.interfaces.IGestorTrabajos;
import gui.personas.modelos.Alumno;
import gui.personas.modelos.Profesor;
import gui.seminarios.modelos.Seminario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Rosalba Caldez
 */
public class GestorTrabajos implements IGestorTrabajos {
    public static GestorTrabajos gestor;
    private List<Trabajo> trabajos = new ArrayList<>();
    
    private GestorTrabajos(){
        
    }
    
    public static GestorTrabajos crear(){
        if(gestor == null){
            gestor = new GestorTrabajos();
        }
        return gestor;
    }
    
    
    /**
     * Crea un Trabajo nuevo y lo agrega a la lista siempre y cuando:
     *  - El titulo no sea ni nulo ni vacio
     *  - La duracion no sea menor o igual a cero
     *  - La fecha de presentacion no sea nula
     *  - Si la fecha de aprobacion no es nula, debe ser igual o posterior a la fecha de presentación
     *  - Haya un tutor. Si hay un cotutor, debe ser diferente al tutor.
     *  - El trabajo tenga una o mas areas. 
     *  - El jurado este formado por tres profesores distintos. Si el trabajo no tiene fecha de aprobabion, puede no tener jurados.
     *  - El jurado debe ser distinto al tutor y cotutor.
     *  - Debe haber por lo menos un alumno. Si hay mas de uno, deben ser distintos.
     *  - No exista un trabajo igual en la lista.
     * @param titulo
     * @param duracion
     * @param fechaPresentacion
     * @param fechaAprobacion
     * @param areas
     * @param profesores
     * @param aet
     * @return Un String que informa si se agregó o no el profesor a la Lista.
     */
    @Override
    public String nuevoTrabajo(String titulo, int duracion, LocalDate fechaPresentacion, LocalDate fechaAprobacion, List<Area> areas, List<RolEnTrabajo> profesores, List<AlumnoEnTrabajo> aet) {
        //Si algun alumno esta asociado a otro trabajo, devuelve null.
        for(Trabajo t: trabajos){
            for(AlumnoEnTrabajo aot: t.getAlumTrabajo()){
                for(AlumnoEnTrabajo al: aet){
                    if(aot.equals(al)){
                        return null;
                    }
                }
            }
        }
        
        //Si el titulo es nulo o esta vacio no agrega el trabajo.
        if((titulo == null)||(titulo.isEmpty())){
            return "No se agregó el trabajo a la lista.";
        }
        
        //Si la duracion es menor o igual a cero, no agrega el trabajo.
        if(duracion <= 0){
            return "No se agregó el trabajo a la lista.";
        }
        
        //Si no tiene fecha de presentación, no agrega el trabajo.
        if(fechaPresentacion == null){
            return "No se agregó el trabajo a la lista.";
        }
        
        //Si tiene fecha de aprobacion, y la fecha es antes de la de presentaion, no agrega el trabajo.
        if((fechaAprobacion != null) && (fechaAprobacion.isBefore(fechaPresentacion))){
            return "No se agregó el trabajo a la lista.";
        }
        
        int i = 0; //Contador para ver la cantidad de profesores tutores.
        for(RolEnTrabajo rot : profesores){
            if(rot.getRol() == Rol.TUTOR){
                i ++; 
            }
        }
        //Si hay un tutor, el contador va a ser distinto de cero, si es cero no se agrega el trabajo.
        if(i == 0){
            return "No se agregó el trabajo a la lista.";
        }
        i = 0; //Contador para contar profesores cotutores.
        for(RolEnTrabajo rot : profesores){
            if(rot.getRol() == Rol.COTUTOR){ //Recorre el arraylist de profesores para buscar los cotutores.
                for(RolEnTrabajo tut : profesores){
                    //Cuando encuentra el cotutor, recorre la lista de vuelta buscando el tutor. 
                    //Si el tutor es igual al cotutor, no se agrega el trabajo.
                    if(tut.getRol() == Rol.TUTOR){
                        if(tut.equals(rot)){
                            return "No se agregó el trabajo a la lista.";
                        }
                    }
                }
            }
        }
        
        if(areas == null){
            return "No se agregó el trabajo a la lista.";
        }
        else{
            if(areas.isEmpty()){
                return "No se agregó el trabajo a la lista.";
            }
        }
        
        //Creo un array para guardar todos los profesores que sean jurados. 
        List<Profesor> jurados =  new ArrayList<>();
        for(RolEnTrabajo rot : profesores){
            if(rot.getRol() == Rol.JURADO){
                jurados.add(rot.getProfesor());
            }
        }
        
        //Si no se agregó una fecha de aprobacion, no es necesario hacer este control.
        if(fechaAprobacion != null){
            if(jurados.isEmpty()){
                return "No se agregó el trabajo a la lista.";
            }
        }
        
        // El trabajo no puede tener mas de tres jurados.
        if(jurados.size() > 3){
            return "No se agregó el trabajo a la lista.";
        }
        
        //Creo un nuevo ArrayList para guardar los jurados controlando que no estén repetidos
        List<Profesor> jur = new ArrayList<>();
        for(Profesor p : jurados){
            if(!jur.contains(p)){
                jur.add(p);
            }
        }
        //Si hay tres jurados que son distintos, el tamano de jur no deberia ser menor que tres, entonces 
        //si es menor que tres no se agrega a la lista.
        if(jur.size() < 3){
            return "No se agregó el trabajo a la lista.";
        }
        
        //Controla que los jurados no sean cotutores ni tutores
        for(Profesor p : jurados){
            for(RolEnTrabajo rot : profesores){
                if(rot.getRol() == Rol.TUTOR){
                    if(p.equals(rot.getProfesor())){
                        return "No se agregó el trabajo a la lista.";
                    }
                }
                if(rot.getRol() == Rol.COTUTOR){
                    if(p.equals(rot.getProfesor())){
                        return "No se agregó el trabajo a la lista.";
                    }
                }
            }
        }
        
        //Controla que la lista de alumnos no sea nula ni este vacia. 
        if(aet == null){
            return "No se agregó el trabajo a la lista.";
        }    
        else{
            if(aet.isEmpty()){
                return "No se agregó el trabajo a la lista.";
            }
        }
        
        //Nuevo arrayList para guardar los alumnos no repetidos
        List<Alumno> alumnos = new ArrayList<>();
        for(AlumnoEnTrabajo a : aet){
            if(!alumnos.contains(a.getAlumno())){
                alumnos.add(a.getAlumno());
            }
        }
        //Si el tamano de los dos arraylist es el mismo, significa que no habia ninguno repetido
        if(aet.size() != alumnos.size()){
            return "No se agregó el trabajo a la lista.";
        }
        
        //Si paso todos los controles, puede crear el trabajo. 
        Trabajo trabajo = new Trabajo(titulo, areas, duracion, fechaPresentacion, aet, profesores);
        if(!this.trabajos.contains(trabajo)){
            this.trabajos.add(trabajo);
        }
        return "Se agregó el trabajo a la lista.";
    }

    /**
     * Busca si existe un trabajo cuyo titulo coincida con el filtro especificado. 
     * Si el titulo coincide, lo devuelve, sino devuelve NULL
     * @param titulo
     * @return 
     */
    @Override
    public Trabajo dameTrabajo(String titulo) {
        for(Trabajo t : this.trabajos){
            if(t.getTitulo().equalsIgnoreCase(titulo)){
                return t;
            }
        }
        return null;
    }
    
    /**
     * Busca si existen trabajos cuyo titulo contenga el filtro especificado. Devuelve el resultado en una lista. 
     * Si no hay coincidencias retorna una lista vacia.
     * @param titulo
     * @return 
     */
    @Override
    public List<Trabajo> buscarTrabajos(String titulo) {
        List<Trabajo> coincidencias = new ArrayList<>();
        
        for(Trabajo t : this.trabajos){
            if(t.getTitulo().contains(titulo)){
                coincidencias.add(t);
            }
        }
        return coincidencias;
    }

    public List<Trabajo> getTrabajos() {
        return trabajos;
    }
    
    /**
     * Finaliza un trabajo si el mismo pertenece a la lista y le asigna una fecha de exposicion.
     * @param trabajo
     * @param fechaExposicion
     * @return 
     */
    @Override
    public String finalizarTrabajo(Trabajo trabajo, LocalDate fechaExposicion) {
        
        for(Trabajo t: trabajos){
            if(t.equals(trabajo)){
                t.setFechaFinal(fechaExposicion);
                return "La fecha de exposicion ha sido asignada al trabajo.";
            }
        }
        
        return "El trabajo no existe.";
    }
    
    /**
     * Elimina un trabajo si el mismo existe y:
     *  -Si su lista de seminarios esta vacia o
     *  -Si los seminarios que contiene ya fueron presentados
     * @param trabajo
     * @return 
     */
    @Override
    public String borrarTrabajo(Trabajo trabajo) {
        
        for(Trabajo t: trabajos){
            if(t.equals(trabajo)){
                if(t.getSeminarios().isEmpty() || t.getSeminarios() == null){
                    this.trabajos.remove(trabajo);
                    return "El trabajo ha sido eliminado.";
                }
                else{
                    for(Seminario sem: t.getSeminarios()){
                        if(sem.getFechaExposicion().isBefore(LocalDate.now())){
                            this.trabajos.remove(trabajo);
                            return "El trabajo ha sido eliminado.";
                        }
                    }
                }
                
            }
        }
        
        return "El trabajo no puede ser eliminado.";
    }

    /**
     * Reemplaza el profesor de un trabajo si:
     *  - el trabajo existe;
     *  - el profesor a reemplazar pertenece al trabajo
     *  - el profesor que reemplaza no pertenece al trabajo
     * Se le asigna una fecha de finalizacion y una razon al profesor reemplazado
     * @param trabajo
     * @param profesorReemplazado
     * @param fechaHasta
     * @param razon
     * @param nuevoProfesor
     * @return 
     */
    @Override
    public String reemplazarProfesor(Trabajo trabajo, Profesor profesorReemplazado, LocalDate fechaHasta, String razon, Profesor nuevoProfesor) {
        
        if(fechaHasta == null){
            return null;
        }
        
        for(Trabajo t: trabajos){
            if(t.equals(trabajo)){
                for(RolEnTrabajo rot: t.getRolTrabajo()){
                    if(rot.getProfesor().equals(nuevoProfesor)){
                        return "El profesor ya ocupa tiene otro rol.";
                    }
                }
                for(RolEnTrabajo rot: t.getRolTrabajo()){
                    if(rot.getProfesor().equals(profesorReemplazado)){
                        rot.setFechaHasta(fechaHasta);
                        rot.setRazon(razon);
                        rot.setProfesor(nuevoProfesor);
                        rot.setFechaDesde(fechaHasta);
                        return "Se ha reemplazado el profesor.";
                    }
                }
            }
            
        }
        
        return "El trabajo no pertenece a la lista.";
    }

    /**
     * Se da de baja un alumno si:
     *  - el trabajo existe
     *  - el alumno pertenece al trabajo
     * Se le asigna al mismo una fecha de finalizacion y una razon.
     * @param trabajo
     * @param alumno
     * @param fechaHasta
     * @param razon
     * @return 
     */
    @Override
    public String finalizarAlumno(Trabajo trabajo, Alumno alumno, LocalDate fechaHasta, String razon) {
             
        for(Trabajo t: trabajos){
            if(t.equals(trabajo)){
                for(AlumnoEnTrabajo aot: t.getAlumTrabajo()){
                    if(aot.getAlumno().equals(alumno)){
                        aot.setFechaHasta(fechaHasta);
                        aot.setRazon(razon);
                        aot.setAlumno(null);
                        return "El alumno ha sido eliminado del trabajo.";
                    }
                }
                return "El alumno no pertenece al trabajo.";
            }
        }
        return "El trabajo no pertenece a la lista.";
    }
    
    
    
    /**
     * Muestra todos los trabajos.
     */
    @Override
    public void mostrarTrabajos() {
        Collections.sort(trabajos);
        for(Trabajo t : this.trabajos){
            t.mostrar();
        }        
    }
    
}
