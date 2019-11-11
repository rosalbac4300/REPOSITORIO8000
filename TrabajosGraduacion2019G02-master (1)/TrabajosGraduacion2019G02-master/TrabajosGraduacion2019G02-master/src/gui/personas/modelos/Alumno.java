/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.personas.modelos;

import java.util.Objects;

/**
 *
 * @author gabinete
 */
public class Alumno extends Persona{
    
    private String cx;
      
    
    /**
     * Constructor para inicializar los atributos CX, DNI, apellido y nombre. 
     * @param cx
     * @param dni
     * @param apellido
     * @param nombre 
     */
    public Alumno(String apellido, String nombre, int dni, String cx){
        super(nombre,apellido,dni);
        this.cx = cx;
    }

    public String getCx() {
        return cx;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    @Override
    public void setDni(int dni) {
        super.setDni(dni);
    }

    @Override
    public int getDni() {
        return super.getDni(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setApellido(String apellido) {
        super.setApellido(apellido); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getApellido() {
        return super.getApellido(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNombre() {
        return super.getNombre(); //To change body of generated methods, choose Tools | Templates.
    }  
    
    @Override
     public void mostrar(){
         System.out.println();
         System.out.println("ALUMNO: ");
         super.mostrar();
         System.out.println("CX: " + cx);
    }
     
    @Override
     public String toString(){
         return "\n" + "ALUMNO: \n" + super.toString() + "\n" + "CX: " + cx;
     }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.cx);
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
        if(obj.getClass() == Profesor.class){
            final Persona other1 = (Persona) obj;
            if(this.getDni() != other1.getDni()){
                return false;
            }
        }else{
            final Alumno other2 = (Alumno) obj;
            if((!Objects.equals(this.cx, other2.cx)) && (this.getDni() != other2.getDni())){
                return false;
            }
        }

        return true;
    }
}
    
