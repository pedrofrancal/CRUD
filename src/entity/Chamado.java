package entity;

import java.time.LocalDate;

public class Chamado {
	private int id;
	private String descricao;
	private Cliente cliente;
	private LocalDate dataChamado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDataChamado() {
		return dataChamado;
	}
	public void setDataChamado(LocalDate dataChamado) {
		this.dataChamado = dataChamado;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
