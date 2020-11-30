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

public class SuporteController {
	
	private ObservableList<Carrinho> lista = FXCollections.observableArrayList();
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty email = new SimpleStringProperty();
	private SuporteDAO sd = new SuporteDAO();
	
	public Carrinho getCarrinho() {
		Carrinho c = new Carrinho();
		c.setIdProduto(this.id.get());
		c.setClienteEmail(this.email.get());
		return c;
	}

	public void setCarrinho(Carrinho c) {
		if (c != null) {
			this.id.set(c.getIdProduto());
			this.email.set(c.getClienteEmail());
		}
	}
	
	public void pesquisarTodosProdutos() throws DAOException{
		lista.clear();
		lista.addAll(sd.pesquisarTodasCompras());
	}
	
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
