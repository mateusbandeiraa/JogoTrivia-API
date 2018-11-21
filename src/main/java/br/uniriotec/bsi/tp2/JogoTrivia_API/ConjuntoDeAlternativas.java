/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;

/**
 *
 * @author Beto
 */

public interface ConjuntoDeAlternativas {
    
    public int getId();
    public String getTipo();
    public ArrayList<Alternativa> getAlternativas();
    public ArrayList<Alternativa> getSolucao();
    public ArrayList<Alternativa> getAlternativasRestantes(int i); //i eh o numero a ser removido;
    
}
