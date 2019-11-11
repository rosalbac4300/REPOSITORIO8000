/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.trabajos.modelos;

//import gui.areas.modelos.Area;
//import gui.personas.modelos.Alumno;
//import gui.personas.modelos.Profesor;
import gui.areas.modelos.Area;
import gui.personas.modelos.Alumno;
import gui.seminarios.modelos.GestorSeminarios;
import gui.seminarios.modelos.NotaAprobacion;
import gui.seminarios.modelos.Seminario;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


/**
 *
 * @author gabinete
 */
public class Trabajo implements Comparable<Trabajo>{
    private String titulo;
    private int duracion; //Duracionn del proyecto en meses
    private LocalDate presentacion; //fecha de aprobacion en el CA
    private LocalDate comienzo; // fecha de comienzo del trabajo, presentacion en el CA
    private LocalDate fechaFinal; // fecha de persentacion final
    private List<Area> areasInvolucrada =new ArrayList<>();
    private List<RolEnTrabajo> rolTrabajo = new ArrayList<>();
    private List<AlumnoEnTrabajo> alumTrabajo = new ArrayList<>();
    private List<Area> area = new ArrayList<>();
    private List<Seminario> seminarios = new ArrayList<>();

    public Trabajo(String titulo, List area, int duracion, LocalDate comienzo, List alumTrabajo, List rolTrabajo) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.comienzo = comienzo;
        this.alumTrabajo = alumTrabajo;
        this.rolTrabajo = rolTrabajo;
        this.area = area;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFechaAprobacion() {
        return comienzo;
    }

    public void setFechaAprobacion(LocalDate comienzo) {
        this.comienzo = comienzo;
    }

    public LocalDate getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(LocalDate presentacion) {
        this.presentacion = presentacion;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public List<Area> getArea() {
        return area;
    }

    public List<RolEnTrabajo> getRolTrabajo() {
        return rolTrabajo;
    }

    public List<AlumnoEnTrabajo> getAlumTrabajo() {
        return alumTrabajo;
    }

    public List<Seminario> getSeminarios() {
        return seminarios;
    }
     
    
    public void agregarSeminario(Seminario s){
        if (!this.seminarios.contains(s)){
            this.seminarios.add(s);
            System.out.println();
            System.out.println("Se agregó el seminario.");
        }
        else{
            System.out.println();
            System.out.println("No se agregó el seminario.");
        }
    }   
            
    public void agregarArea(Area area){
        this.areasInvolucrada.add(area);
    }

    public String agregarProfesor(RolEnTrabajo rol){
        if(!rolTrabajo.contains(rol)){
            this.rolTrabajo.add(rol);
            String s = "Se agrego un rol en el array.";
            return s;
        }
        else{
            String s = "No se agrego un rol en el array.";
            return s;
        }
    }
    
    public void agregarAlumnoEnTrabajo(LocalDate fechaDesde, Alumno unAlumno){
        AlumnoEnTrabajo alumno = new AlumnoEnTrabajo(fechaDesde, unAlumno);
        this.alumTrabajo.add(alumno);
    }
        
    public void agregarAreasInvolucrada(String nombre){
        Area AreasInvolucradas = new Area (nombre);
        this.areasInvolucrada.add(AreasInvolucradas);
    }
    
    public void mostrar(){
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        
        System.out.println("Trabajo: " + titulo.toUpperCase());
        System.out.println("Duración: " + duracion + " meses");
        System.out.println("Fecha Presentacion en la CA: " + formatter.format(comienzo));
        System.out.print("Fecha Aprobacion: ");
        if(this.presentacion== null){
            System.out.print("-");
        }
        else{
            System.out.print(formatter.format(presentacion));
        }
        System.out.println();
        System.out.print("Fecha Exposición: ");
        if(fechaFinal== null){
            System.out.print("-");
        }
        else{
            System.out.print(formatter.format(fechaFinal));
        }
        
        System.out.println("\n\nAlumnos:");
        System.out.println("----------------------");
        for(AlumnoEnTrabajo a : alumTrabajo){
            System.out.println("CX" + a.getAlumno().getCx() + " - " + a.getAlumno().getApellido().toUpperCase() + ", " + a.getAlumno().getNombre().toUpperCase());  
        }
        
        System.out.println("\nTutor ");
        System.out.println("----------------------");
        for(RolEnTrabajo r : rolTrabajo){
            if(r.getRol() == Rol.TUTOR){
                System.out.println(r.getProfesor().getNombre().toUpperCase() + " " + r.getProfesor().getApellido().toUpperCase());
            }
        }
        System.out.println("\nJurado ");
        System.out.println("----------------------");
        for(RolEnTrabajo r : rolTrabajo){
            if(r.getRol() == Rol.JURADO){
                System.out.println(r.getProfesor().getNombre().toUpperCase() + " " + r.getProfesor().getApellido().toUpperCase());
            }
        }
        
    }
    
    /**
     * Solicita al GestorSeminarios que se validen los datos del seminario.
     * Se verifica tambien que la fecha de Exposicion no sea antes que la de Aprobacion.
     * Si no existe otro seminario igual, se crea y se agrega el seminario en el gestor de seminarios. 
     * Devuelve un string informando el resultado de la operación. 
     * @param fechaExposicion
     * @param nota
     * @param observaciones
     * @return 
     */
    public String nuevoSeminario(LocalDate fechaExposicion, NotaAprobacion nota, String observaciones){
        String validar = GestorSeminarios.gestor.validarSeminario(fechaExposicion, nota, observaciones);
        if(!validar.equals("Se validó el seminario.")){
            return "El seminario no es valido, no se lo agregó a la lista.";
        }
        if(fechaExposicion.isBefore(this.presentacion)){
            return "La fecha no es valida, no se agregó el seminario a la lista.";
        }
        if(GestorSeminarios.gestor.contiene(new Seminario(fechaExposicion, nota, observaciones))){
            return "El seminario ya existe, no se agrego a la lista.";
        }
        GestorSeminarios.gestor.agregarSeminario(new Seminario(fechaExposicion, nota, observaciones));
        return "El seminario se agregó a la lista.";
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.titulo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trabajo other = (Trabajo) obj;
        if (!Objects.equals(this.titulo.toLowerCase(), other.titulo.toLowerCase())) {
            return false;
        }
        return true;
    }  
    
    /**
     * Trabajos ordenarlos descendentemente según la ​ fecha de presentación . ​
     * En caso de haber 2 trabajos con la misma fecha de presentación, deberán aparecer ordenados según el ​ título ​ ascendentemente.
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Trabajo o) {
        if(this.presentacion.equals(o.getPresentacion())){
            return this.titulo.compareToIgnoreCase(o.getTitulo());
        }
        else{
            return o.getPresentacion().compareTo(this.presentacion);
        }
    }
    
    
}
