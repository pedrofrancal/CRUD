package boundary;

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
import javafx.util.converter.DoubleStringConverter;

/**
 * Classe que controla as interações do administrador
 */
public class AdministradorBoundary implements EventHandler<ActionEvent>, GerarTela {

	private TextField txtNome = new TextField();
	private TextField txtDescricao = new TextField();
	private TextField txtPreco = new TextField();

	private BorderPane panePrincipal = new BorderPane();
	
	private Button btnGravar = new Button("Gravar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnCadastros = new Button("Gerenciar cadastros");
	
	private ProdutosController pc = new ProdutosController();
	
	private TableView<Produto> tabela = new TableView<>();

	@Override
	/**
	 * Retorna um Pane, gerando uma tela JavaFX
	 */
	public Pane gerarTela() {
				
		vincularCampos();

		GridPane paneCampos = new GridPane();

		paneCampos.add(new Label("Nome"), 0, 1);
		paneCampos.add(txtNome, 1, 1);
		paneCampos.add(new Label("Descrição"), 0, 2);
		paneCampos.add(txtDescricao, 1, 2);
		paneCampos.add(new Label("Preço"), 0, 3);
		paneCampos.add(txtPreco, 1, 3);
		paneCampos.add(btnGravar, 0, 5);
		paneCampos.add(btnPesquisar, 1, 5);
		paneCampos.add(btnCadastros, 2, 5);

		btnGravar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnCadastros.setOnAction(this);

		panePrincipal.setTop(paneCampos);
		panePrincipal.setCenter(tabela);
		
		return panePrincipal;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Vincula os campos da classe com o controller
	 */
	private void vincularCampos() {
		StringConverter<? extends Number> converter = new DoubleStringConverter();
		Bindings.bindBidirectional(txtNome.textProperty(), pc.getNomeProperty());
		Bindings.bindBidirectional(txtDescricao.textProperty(), pc.getDescricaoProperty());
		Bindings.bindBidirectional(txtPreco.textProperty(), pc.getPrecoProperty(), (StringConverter<Number>) converter);

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
	 * Chama as funções do controller
	 */
	public void handle(ActionEvent e) {
		if (btnGravar == e.getTarget()) {
			try {
				pc.adicionar();
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		} else if (btnPesquisar == e.getTarget()) {
			try {
				pc.pesquisarPorNome();
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		}else if (btnCadastros == e.getTarget()) {
			BorderPane paneNovo = (BorderPane) panePrincipal.getParent();
			paneNovo.setCenter(new CadastrosBoundary().gerarTela());
		}
	}

}
