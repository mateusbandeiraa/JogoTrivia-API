package br.uniriotec.bsi.tp2.JogoTrivia_API;

public enum EstadoParticipante {
	ATIVO("Ativo"),INATIVO("Inativo");
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}
	
	 EstadoParticipante(String descricao){
		this.descricao= descricao;
		
	}
	
}