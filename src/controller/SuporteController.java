package controller;

import dao.DAOException;
import dao.SuporteDAO;
import entity.Carrinho;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe controladora das funções do suporte
 */
public class SuporteController {
	
	private ObservableList<Carrinho> lista = FXCollections.observableArrayList();
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty email = new SimpleStringProperty();
	private SuporteDAO sd = new SuporteDAO();
	
	/**
	 * Função que preenche um carrinho com base em suas properties
	 */
	public Carrinho getCarrinho() {
		Carrinho c = new Carrinho();
		c.setIdProduto(this.id.get());
		c.setClienteEmail(this.email.get());
		return c;
	}

	/**
	 * Função que preenche as properties de acordo com um carrinho
	 */
	public void setCarrinho(Carrinho c) {
		if (c != null) {
			this.id.set(c.getIdProduto());
			this.email.set(c.getClienteEmail());
		}
	}
	
	/**
	 * Função que retorna todas as compras pendentes via DAO
	 */
	public void pesquisarTodosProdutos() throws DAOException{
		lista.clear();
		lista.addAll(sd.pesquisarTodasCompras());
	}
	
	/**
	 * Função que pesquisa por email compras pendentes via DAO
	 */
	public void pesquisarProduto() throws DAOException{
		String txt = this.email.get();
		lista.clear();
		lista.addAll(sd.pesquisarPorEmail(txt));
	}

	public ObservableList<Carrinho> getLista() {
		return lista;
	}

	public void setLista(ObservableList<Carrinho> lista) {
		this.lista = lista;
	}

	public IntegerProperty getIdProperty() {
		return id;
	}

	public void setIdProperty(IntegerProperty id) {
		this.id = id;
	}

	public StringProperty getEmailProperty() {
		return email;
	}

	public void setEmailProperty(StringProperty email) {
		this.email = email;
	}
}
