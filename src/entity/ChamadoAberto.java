package entity;

public class ChamadoAberto extends Chamado {
	private Suporte atendente;
	private String descricao;
	private String situacao;

	public Suporte getAtendente() {
		return atendente;
	}

	public void setAtendente(Suporte atendente) {
		this.atendente = atendente;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
}
