package controller;

import dao.DAOException;
import dao.ProdutosDAO;
import entity.Produto;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe controladora das função relacionadas a produto
 */
public class ProdutosController {

	private ObservableList<Produto> lista = FXCollections.observableArrayList();
	private IntegerProperty id = new SimpleIntegerProperty();
	private DoubleProperty preco = new SimpleDoubleProperty();
	private StringProperty nome = new SimpleStringProperty();
	private StringProperty descricao = new SimpleStringProperty();
	private ProdutosDAO pd = new ProdutosDAO();

	/**
	 * Função que retorna um produto preenchido de acordo com o property
	 */
	public Produto getProduto() {
		Produto p = new Produto();
		p.setDescricao(this.descricao.get());
		p.setId(this.id.get());
		p.setPreco(this.preco.get());
		p.setNome(this.nome.get());
		return p;
	}

	/**
	 * Função modifica a property de acordo com um produto
	 */
	public void setProduto(Produto p) {
		if (p != null) {
			this.id.set(p.getId());
			this.descricao.set(p.getDescricao());
			this.nome.set(p.getNome());
			this.preco.set(p.getPreco());
		}
	}

	/**
	 * Função que adiciona no banco de dados um produto
	 */
	public void adicionar() throws DAOException{
		Produto p = getProduto();
		pd.adicionar(p);
	}

	/**
	 * Função que pesquisa por nome um produto
	 */
	public void pesquisarPorNome() throws DAOException {
		String txt = this.nome.get();
		lista.clear();
		lista.addAll(pd.pesquisarPorNome(txt));
	}
	
	/**
	 * Função que pesquisa todos os produtos
	 */
	public void pesquisarTodosProdutos() throws DAOException{
		lista.clear();
		lista.addAll(pd.pesquisarTodosProdutos());
	}
	
	public IntegerProperty getIdProperty() {
		return id;
	}

	public void setIdProperty(IntegerProperty id) {
		this.id = id;
	}

	public DoubleProperty getPrecoProperty() {
		return preco;
	}

	public void setPrecoProperty(DoubleProperty preco) {
		this.preco = preco;
	}

	public StringProperty getNomeProperty() {
		return nome;
	}

	public void setNomeProperty(StringProperty nome) {
		this.nome = nome;
	}

	public StringProperty getDescricaoProperty() {
		return descricao;
	}

	public void setDescricaoProperty(StringProperty descricao) {
		this.descricao = descricao;
	}

	public ObservableList<Produto> getLista() {
		return lista;
	}

	public void setLista(ObservableList<Produto> lista) {
		this.lista = lista;
	}

}
