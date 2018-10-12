package br.uniriotec.bsi.tp2.JogoTrivia_API;

public class HorarioException extends RegistravelException {
	private static final long serialVersionUID = 1L;

	public HorarioException(Object mandante, String mensagem, Object[] objetos) {
		super(mandante, mensagem, objetos);
	}

	public HorarioException(Object mandante, String mensagem) {
		super(mandante, mensagem);
	}

}
