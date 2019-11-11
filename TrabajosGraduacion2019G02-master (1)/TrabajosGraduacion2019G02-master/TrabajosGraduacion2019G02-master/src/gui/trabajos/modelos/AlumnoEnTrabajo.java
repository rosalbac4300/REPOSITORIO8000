/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.trabajos.modelos;

import gui.personas.modelos.Alumno;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Rosalba Caldez
 */
public class AlumnoEnTrabajo {
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private String razon;
    private Alumno unAlumno;
    
    public AlumnoEnTrabajo(LocalDate fechaDesde, Alumno unAlumno){
        this.fechaDesde = fechaDesde;
        this.unAlumno = unAlumno;
    }
    
    public void setAlumno(Alumno unAlumno){
        this.unAlumno = unAlumno;
    }
    
    public Alumno getAlumno(){
        return this.unAlumno;
    }
    
    public void setFechaDesde(LocalDate fechaDesde){
        this.fechaDesde = fechaDesde;
    }
    
    public LocalDate getFechaDesde(){
        return fechaDesde;
    }
    
    public void setFechaHasta(LocalDate fechaHasta){
        this.fechaHasta = fechaHasta;
    }
    
    public LocalDate getFechaHasta(){
        return fechaDesde;
    }
    
    public void setRazon(String razon){
        this.razon = razon;
    }
    
    public String getRazon(){
        return this.razon;
    }
    
    public void mostrar(){
        System.out.println("Nombre: " + unAlumno.getApellido().toUpperCase() + ", " + unAlumno.getNombre().toUpperCase());
        System.out.println("CX : " + unAlumno.getCx());
        String dni = Integer.toString(unAlumno.getDni());
        System.out.println("DNI: " + dni);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        if(fechaDesde != null){
            System.out.println("Trabaja en el proyecto desde: " + formatter.format(fechaDesde));
        }
        if(fechaHasta != null){
            System.out.println("Dejo de trabajar en el proyecto: " + formatter.format(fechaHasta));
            System.out.println("Razon por dejar el trabajo: " + razon);
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.unAlumno);
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
        final AlumnoEnTrabajo other = (AlumnoEnTrabajo) obj;
        if (!Objects.equals(this.unAlumno, other.unAlumno)) {
            return false;
        }
        return true;
    }
    
    
}
