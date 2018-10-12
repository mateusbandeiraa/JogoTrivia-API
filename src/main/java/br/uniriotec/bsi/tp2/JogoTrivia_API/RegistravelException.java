package br.uniriotec.bsi.tp2.JogoTrivia_API;

/**
 * Classe de Exceção que pode ser usada para criar logs no projeto servidor.
 * O atributo <tt>Object[] objetos</tt> pode armazenar os objetos envolvidos na
 * exceção.
 * 
 * @author Mateus Bandeira
 *
 */
public abstract class RegistravelException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private Object mandante;
	private Object[] objetos;

	/**
	 * Constroi uma exceção com todos os parâmetros.
	 * 
	 * @param mandante
	 *            O objeto que instanciou a exceção.
	 * @param mensagem
	 *            A mensagem a ser registrada.
	 * @param objetos
	 *            Os objetos envolvidos na exceção.
	 */
	public RegistravelException(Object mandante, String mensagem, Object[] objetos) {
		super(mensagem);
		this.mandante = mandante;
		this.objetos = objetos;
	}

	/**
	 * Constroi uma exceção mandante e mensagem.
	 * 
	 * @param mandante
	 *            O objeto que instanciou a exceção.
	 * @param mensagem
	 *            A mensagem a ser registrada.
	 */
	public RegistravelException(Object mandante, String mensagem) {
		super(mensagem);
		this.mandante = mandante;
	}

	public Object getMandante() {
		return mandante;
	}

	public Object[] getObjetos() {
		return objetos;
	}
}
