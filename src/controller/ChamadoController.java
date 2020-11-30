package controller;

import dao.ChamadoDAO;
import dao.DAOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChamadoController {

	private StringProperty descricao = new SimpleStringProperty();
	private StringProperty emailCliente = new SimpleStringProperty();
	private ChamadoDAO cd = new ChamadoDAO();
	
	public void arquivar() throws DAOException{
		cd.arquivar(emailCliente.get());
	}
	
	public void resolver() throws DAOException {
		cd.resolver(emailCliente.get(), descricao.get());
	}
	
	public void abrirChamado(String email) throws DAOException{
		cd.abrirChamado(email, this.descricao.get());
	}

	public StringProperty getDescricaoProperty() {
		return descricao;
	}

	public void setDescricaoProperty(StringProperty descricao) {
		this.descricao = descricao;
	}

	public StringProperty getEmailClienteProperty() {
		return emailCliente;
	}

	public void setEmailClienteProperty(StringProperty emailCliente) {
		this.emailCliente = emailCliente;
	}

}
