package br.uniriotec.bsi.tp2.JogoTrivia_API;

import java.util.ArrayList;

public class Questao {
    private int id;
    private String textoPergunta;
    private int tempoDisponivel; // em segundos
    private int tempoBonus; // em segundos
    private ConjuntoDeAlternativas listaDeAlternativas;

    public Questao(int id, String textoPergunta, int tempoDisponivel, int tempoBonus) {
            this(textoPergunta, tempoDisponivel, tempoBonus);
            this.id = id;
    }

    public Questao(String textoPergunta, int tempoDisponivel, int tempoBonus) {
            this.textoPergunta = textoPergunta;
            this.tempoDisponivel = tempoDisponivel;
            this.tempoBonus = tempoBonus;
    }	

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextoPergunta() {
        return textoPergunta;
    }

    public void setTextoPergunta(String textoPergunta) {
        this.textoPergunta = textoPergunta;
    }

    public int getTempoDisponivel() {
        return tempoDisponivel;
    }

    public void setTempoDisponivel(int tempoDisponivel) {
        this.tempoDisponivel = tempoDisponivel;
    }

    public int getTempoBonus() {
        return tempoBonus;
    }

    public void setTempoBonus(int tempoBonus) {
        this.tempoBonus = tempoBonus;
    }

    public ConjuntoDeAlternativas getListaDeAlternativas() {
        return listaDeAlternativas;
    }

    public void setListaDeAlternativas(ConjuntoDeAlternativas listaDeAlternativas) {
        this.listaDeAlternativas = listaDeAlternativas;
    }
        
    public double calcularTaxaDeAcerto(ArrayList<Interacao> interacoes) {
            int qtdCertas = 0;
            for (Interacao i : interacoes) {
                    if (i.solucaoTeveExito())
                            qtdCertas++;
            }
            return qtdCertas / interacoes.size() * 1.0;
    }


}