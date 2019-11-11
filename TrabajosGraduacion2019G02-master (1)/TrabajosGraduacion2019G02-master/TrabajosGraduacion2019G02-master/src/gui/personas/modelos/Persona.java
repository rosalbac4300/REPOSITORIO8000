/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.personas.modelos;

/**
 *
 * @author User
 */
public abstract class Persona {
    private String nombre;
    private String apellido;
    private int dni;
    
    public Persona(String nombre, String apellido, int dni){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.dni;
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
        if (getClass().getSuperclass()!= obj.getClass().getSuperclass()) {
            return false;
        }
        final Persona other = (Persona) obj;
        if (this.dni != other.dni) {
            return false;
        }
        return true;
    }
   
    @Override
    public String toString(){
        return ("[" + dni + "] " + apellido.toUpperCase() + ", " + nombre.toUpperCase()); 
    }
    
    public void mostrar(){
        System.out.println("[" + dni + "] " + apellido.toUpperCase() + ", " + nombre.toUpperCase());
    }
}
