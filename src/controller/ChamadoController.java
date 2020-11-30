package controller;

import dao.ChamadoDAO;
import dao.DAOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChamadoController {

	private StringProperty descricao = new SimpleStringProperty();
	private ChamadoDAO cd = new ChamadoDAO();
	
	public void abrirChamado(String email) throws DAOException{
		cd.abrirChamado(email, this.descricao.get());
	}

	public StringProperty getDescricaoProperty() {
		return descricao;
	}

	public void setDescricaoProperty(StringProperty descricao) {
		this.descricao = descricao;
	}

}
