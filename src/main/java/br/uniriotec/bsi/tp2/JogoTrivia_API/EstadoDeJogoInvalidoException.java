package br.uniriotec.bsi.tp2.JogoTrivia_API;

public class EstadoDeJogoInvalidoException extends RegistravelException{

	private static final long serialVersionUID = 1L;

	public EstadoDeJogoInvalidoException(Object mandante, String mensagem, Object[] objetos) {
		super(mandante, mensagem, objetos);
	}
	
	public EstadoDeJogoInvalidoException(Object mandante, String mensagem) {
		super(mandante, mensagem);
	}
	

}
