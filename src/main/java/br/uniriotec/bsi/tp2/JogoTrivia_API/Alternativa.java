/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uniriotec.bsi.tp2.JogoTrivia_API;

/**
 *
 * @author Beto
 */

public class Alternativa implements Comparable<Alternativa> {
    private int id;
    private ConjuntoDeAlternativas conjuntoDeAlternativas;
    private String texto;
    private int indice; // -1 para certo, 0 para errado, positivos para ordem
    private boolean removivel;

    public Alternativa() {
    }

    public Alternativa(ConjuntoDeAlternativas conjuntoDeAlternativas, String texto, int indice, boolean removivel) {
        this.conjuntoDeAlternativas = conjuntoDeAlternativas;
        this.texto = texto;
        this.indice = indice;
        this.removivel = removivel;
        corrigeRemovivel();
    }

    public Alternativa(int id, ConjuntoDeAlternativas conjuntoDeAlternativas, String texto, int indice, boolean removivel) {
        this (conjuntoDeAlternativas, texto, indice, removivel);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ConjuntoDeAlternativas getConjuntoDeAlternativas() {
        return conjuntoDeAlternativas;
    }

    public void setConjuntoDeAlternativas(ConjuntoDeAlternativas conjuntoDeAlternativas) {
        this.conjuntoDeAlternativas = conjuntoDeAlternativas;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public boolean ehRemovivel() {
        return removivel;
    }

    public void setRemovivel(boolean removivel) {
        this.removivel = removivel;
        corrigeRemovivel();
    }
    
    public final void corrigeRemovivel() {
        if (indice !=0)
            removivel = false;
    }

    @Override
    public int compareTo(Alternativa a) {
        if (this.indice < a.indice)
            return -1;
        return 1;
    }

    
    
}
