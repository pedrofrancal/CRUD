package boundary;

import controller.ClientesController;
import controller.ProdutosController;
import dao.DAOException;
import entity.Produto;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;

/**
 * Classe que controla as interações do cliente
 */
public class ClienteBoundary implements EventHandler<ActionEvent>, GerarTela {

	private TextField txtNome = new TextField();
	private TextField txtId = new TextField();
	
	private String email;
	
	private BorderPane panePrincipal = new BorderPane();

	private Button btnPedir = new Button("Fazer pedido");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnChamado = new Button("Fazer chamado");

	private ProdutosController pc = new ProdutosController();
	private ClientesController cc = new ClientesController();

	private TableView<Produto> tabela = new TableView<>();

	@Override
	/**
	 * Função que retorna a Pane com a tela gerada
	 */
	public Pane gerarTela() {
		vincularCampos();

		GridPane paneCampos = new GridPane();

		paneCampos.add(new Label("Nome"), 0, 1);
		paneCampos.add(txtNome, 1, 1);
		paneCampos.add(new Label("ID"), 0, 2);
		paneCampos.add(txtId, 1, 2);
				
		paneCampos.add(btnPedir, 0, 5);
		paneCampos.add(btnPesquisar, 1, 5);
		paneCampos.add(btnChamado, 2, 5);

		btnChamado.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnPedir.setOnAction(this);

		panePrincipal.setTop(paneCampos);
		panePrincipal.setCenter(tabela);

		try {
			pc.pesquisarTodosProdutos();
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return panePrincipal;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Classe que vincula os campos
	 */
	private void vincularCampos() {
		StringConverter<? extends Number> converter = new IntegerStringConverter();

		Bindings.bindBidirectional(txtNome.textProperty(), cc.getNomeProperty());
		Bindings.bindBidirectional(txtId.textProperty(), cc.getIdProperty(), (StringConverter<Number>) converter);

		TableColumn<Produto, Long> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(new PropertyValueFactory<Produto, Long>("id"));

		TableColumn<Produto, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<Produto, String>("nome"));

		TableColumn<Produto, String> colDescricao = new TableColumn<>("Descricao");
		colDescricao.setCellValueFactory(new PropertyValueFactory<Produto, String>("descricao"));

		TableColumn<Produto, String> colPreco = new TableColumn<>("Preco");
		colPreco.setCellValueFactory(new PropertyValueFactory<Produto, String>("preco"));

		tabela.getColumns().addAll(colId, colNome, colDescricao, colPreco);
		tabela.setItems(pc.getLista());
	}

	@Override
	/**
	 * Função que ativa as funções do controller pelos botões
	 */
	public void handle(ActionEvent e) {
		if (btnChamado == e.getTarget()) {
			BorderPane paneNovo = (BorderPane) panePrincipal.getParent();
			ClienteChamadoBoundary ccb = new ClienteChamadoBoundary();
			ccb.setEmail(this.email);
			paneNovo.setCenter(ccb.gerarTela());
		} else if (btnPesquisar == e.getTarget()) {
			try {
				pc.pesquisarPorNome();
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		} else if (btnPedir == e.getTarget()) {
			try {
				cc.adicionarPedidoCliente(email);
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Função para copiar o email para a boundary
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
