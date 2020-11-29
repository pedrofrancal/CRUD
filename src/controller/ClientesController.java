package controller;

import java.time.LocalDate;

import dao.ClientesDAO;
import dao.DAOException;
import entity.Cliente;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class ClientesController {
	
	private SimpleStringProperty Email= new SimpleStringProperty();
	private SimpleStringProperty Senha = new SimpleStringProperty();
	private SimpleStringProperty Cpf = new SimpleStringProperty();
	private SimpleStringProperty Nome = new SimpleStringProperty();
	private ObjectProperty<LocalDate> DataNascimento = new SimpleObjectProperty<>(LocalDate.now());
	private SimpleStringProperty Endereco = new SimpleStringProperty();
	private SimpleStringProperty Telefone = new SimpleStringProperty();
	private SimpleStringProperty Cep = new SimpleStringProperty();
	
	private ClientesDAO cd = new ClientesDAO();
	
	public Cliente getCliente() {
		Cliente c = new Cliente();
		c.setCep(this.Cep.get());
		c.setCpf(this.Cpf.get());
		c.setEmail(this.Email.get());
		c.setEndereco(this.Endereco.get());
		c.setNascimento(this.DataNascimento.get());
		c.setNome(this.Nome.get());
		c.setSenha(this.Senha.get());
		c.setTelefone(this.Telefone.get());
		return c;
	}
	
	public void setCliente(Cliente c) {
		if(c!=null) {
			this.Cep.set(c.getCep());
			this.Cpf.set(c.getCpf());
			this.DataNascimento.set(c.getNascimento());
			this.Email.set(c.getEmail());
			this.Endereco.set(c.getEndereco());
			this.Nome.set(c.getNome());
			this.Senha.set(c.getSenha());
			this.Telefone.set(c.getTelefone());
		}
	}
	
	public void adicionar() throws DAOException{
		Cliente c = new Cliente();
		cd.adicionar(c);
	}

	public SimpleStringProperty getEmailProperty() {
		return Email;
	}

	public void setEmailProperty(SimpleStringProperty emailProperty) {
		Email= emailProperty;
	}

	public SimpleStringProperty getSenhaProperty() {
		return Senha;
	}

	public void setSenhaProperty(SimpleStringProperty senhaProperty) {
		Senha= senhaProperty;
	}

	public SimpleStringProperty getCpfProperty() {
		return Cpf;
	}

	public void setCpfProperty(SimpleStringProperty cpfProperty) {
		Cpf= cpfProperty;
	}

	public SimpleStringProperty getNomeProperty() {
		return Nome;
	}

	public void setNomeProperty(SimpleStringProperty nomeProperty) {
		Nome= nomeProperty;
	}

	public ObjectProperty<LocalDate> getDataNascimentoProperty() {
		return DataNascimento;
	}

	public void setDataNascimentoProperty(ObjectProperty<LocalDate> dataNascimentoProperty) {
		DataNascimento= dataNascimentoProperty;
	}

	public SimpleStringProperty getEnderecoProperty() {
		return Endereco;
	}

	public void setEnderecoProperty(SimpleStringProperty enderecoProperty) {
		Endereco= enderecoProperty;
	}

	public SimpleStringProperty getTelefoneProperty() {
		return Telefone;
	}

	public void setTelefoneProperty(SimpleStringProperty telefoneProperty) {
		Telefone= telefoneProperty;
	}

	public SimpleStringProperty getCepProperty() {
		return Cep;
	}

	public void setCepProperty(SimpleStringProperty cepProperty) {
		Cep= cepProperty;
	}
}
