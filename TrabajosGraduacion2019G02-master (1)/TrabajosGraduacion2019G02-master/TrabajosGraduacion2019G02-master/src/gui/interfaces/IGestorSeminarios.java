/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.interfaces;

import gui.seminarios.modelos.NotaAprobacion;
import gui.seminarios.modelos.Seminario;
import java.time.LocalDate;

/**
 *
 * @author Rosalba Caldez
 */
public interface IGestorSeminarios {
    String validarSeminario(LocalDate fechaExposicion, NotaAprobacion nota, String obs);
}
