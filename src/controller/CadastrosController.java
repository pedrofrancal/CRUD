package controller;

import dao.CadastrosDAO;
import dao.DAOException;
import entity.Funcionario;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CadastrosController {

	private ObservableList<Funcionario> lista = FXCollections.observableArrayList();
	private SimpleStringProperty email = new SimpleStringProperty();
	private SimpleStringProperty senha = new SimpleStringProperty();
	private SimpleStringProperty cpf = new SimpleStringProperty();
	private SimpleStringProperty nome = new SimpleStringProperty();
	private CadastrosDAO cd = new CadastrosDAO();
	
	public void adicionar() throws DAOException {
		Funcionario f = getCadastro();
		cd.adicionar(f);
	}

	public void pesquisarPorEmail() throws DAOException {
		String txt = this.email.get();
		lista.clear();
		lista.addAll(cd.pesquisarPorEmail(txt));
	}
	
	public void atualizar() throws DAOException{
		Funcionario f = getCadastro();
		cd.atualizar(f);
	}
	
	public void deletar() throws DAOException{
		Funcionario f = getCadastro();
		cd.deletar(f);
	}
	
	public Funcionario getCadastro() {
		Funcionario c = new Funcionario();
		c.setEmail(this.email.get());
		c.setSenha(this.senha.get());
		c.setNome(this.nome.get());
		c.setCpf(this.cpf.get());
		return c;
	}
	
	public void setCadastro(Funcionario c) {
		if(c!=null) {
			this.email.set(c.getEmail());
			this.cpf.set(c.getCpf());
			this.nome.set(c.getNome());
			this.senha.set(c.getSenha());
		}
	}

	public void setLista(ObservableList<Funcionario> lista) {
		this.lista = lista;
	}
	
	public ObservableList<Funcionario> getLista() {
		return lista;
	}

	public SimpleStringProperty getEmailProperty() {
		return email;
	}

	public void setEmailProperty(SimpleStringProperty email) {
		this.email = email;
	}

	public SimpleStringProperty getSenhaProperty() {
		return senha;
	}

	public void setSenhaProperty(SimpleStringProperty senha) {
		this.senha = senha;
	}

	public SimpleStringProperty getCpfProperty() {
		return cpf;
	}

	public void setCpfProperty(SimpleStringProperty cpf) {
		this.cpf = cpf;
	}

	public SimpleStringProperty getNomeProperty() {
		return nome;
	}

	public void setNomeProperty(SimpleStringProperty nome) {
		this.nome = nome;
	}
	
}
