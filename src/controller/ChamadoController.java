package controller;

import dao.ChamadoDAO;
import dao.DAOException;
import entity.Chamado;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Classe controladora de chamados
 */
public class ChamadoController {
	
	private ObservableList<Chamado> lista = FXCollections.observableArrayList();
	private StringProperty descricao = new SimpleStringProperty();
	private StringProperty emailCliente = new SimpleStringProperty();
	private ChamadoDAO cd = new ChamadoDAO();
	
	/**
	 * Função que retorna uma ObservableList preenchida com todos os chamados via DAO
	 */
	public void pesquisarTodosChamados() throws DAOException{
		lista.clear();
		lista.addAll(cd.pesquisarTodosChamados());
	}
	
	/**
	 * Função que, diferente de seu nome, apaga chamados atendidos via DAO
	 */
	public void arquivar() throws DAOException{
		cd.arquivar(emailCliente.get());
		Alert a = new Alert(AlertType.CONFIRMATION, "ARQUIVADO COM SUCESSO");
        a.show();
	}
	
	/**
	 * Função que permite responder um chamado via DAO
	 */
	public void resolver() throws DAOException {
		cd.resolver(emailCliente.get(), descricao.get());
		Alert a = new Alert(AlertType.CONFIRMATION, "SITUAÇÃO ATUALIZADA");
        a.show();
	}
	
	/**
	 * Função que permite abrir um chamado via DAO
	 */
	public void abrirChamado(String email) throws DAOException{
		cd.abrirChamado(email, this.descricao.get());
		Alert a = new Alert(AlertType.CONFIRMATION, "CHAMADO ABERTO");
        a.show();
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

	public ObservableList<Chamado> getLista() {
		return lista;
	}

	public void setLista(ObservableList<Chamado> lista) {
		this.lista = lista;
	}

}
