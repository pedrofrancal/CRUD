package boundary;

import controller.CadastrosController;
import dao.DAOException;
import entity.Funcionario;
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

public class CadastrosBoundary implements EventHandler<ActionEvent>, GerarTela{

	private TextField txtEmail = new TextField();
	private TextField txtSenha = new TextField();
	private TextField txtCpf = new TextField();
	private TextField txtNome = new TextField();

	private BorderPane panePrincipal = new BorderPane();
	
	private Button btnGravar = new Button("Gravar");
	private Button btnPesquisar = new Button("Pesquisar");
	private Button btnAtualizar = new Button("Atualizar");
	private Button btnDeletar = new Button("Deletar");
	private Button btnProdutos = new Button("Gerenciar produtos");
	
	private CadastrosController cc = new CadastrosController();
	
	private TableView<Funcionario> tabela = new TableView<>();
	
	@Override
	public Pane gerarTela() {
		vincularCampos();

		GridPane paneCampos = new GridPane();

		paneCampos.add(new Label("Email"), 0, 1);
		paneCampos.add(txtEmail, 1, 1);
		paneCampos.add(new Label("Senha"), 0, 2);
		paneCampos.add(txtSenha, 1, 2);
		paneCampos.add(new Label("CPF"), 0, 3);
		paneCampos.add(txtCpf, 1, 3);
		paneCampos.add(new Label("Nome"), 0, 4);
		paneCampos.add(txtNome, 1, 4);
		paneCampos.add(btnGravar, 0, 5);
		paneCampos.add(btnPesquisar, 1, 5);
		paneCampos.add(btnAtualizar, 2, 5);
		paneCampos.add(btnDeletar, 3, 5);
		paneCampos.add(btnProdutos, 4, 5);

		btnGravar.setOnAction(this);
		btnPesquisar.setOnAction(this);
		btnProdutos.setOnAction(this);
		btnAtualizar.setOnAction(this);
		btnDeletar.setOnAction(this);

		panePrincipal.setTop(paneCampos);
		panePrincipal.setCenter(tabela);
		
		return panePrincipal;
	}

	@Override
	public void handle(ActionEvent e) {
		if (btnGravar == e.getTarget()) {
			try {
				cc.adicionar();
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		} else if (btnPesquisar == e.getTarget()) {
			try {
				cc.pesquisarPorEmail();
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		}else if (btnProdutos == e.getTarget()) {
			BorderPane paneNovo = (BorderPane) panePrincipal.getParent();
			paneNovo.setCenter(new AdministradorBoundary().gerarTela());
		}else if(btnAtualizar == e.getTarget()) {
			try {
				cc.atualizar();
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		}else if(btnDeletar == e.getTarget()) {
			try {
				cc.deletar();
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void vincularCampos() {
		Bindings.bindBidirectional(txtNome.textProperty(), cc.getNomeProperty());
		Bindings.bindBidirectional(txtEmail.textProperty(), cc.getEmailProperty());
		Bindings.bindBidirectional(txtSenha.textProperty(), cc.getSenhaProperty());
		Bindings.bindBidirectional(txtCpf.textProperty(), cc.getCpfProperty());

		TableColumn<Funcionario, String> colCpf = new TableColumn<>("Cpf");
		colCpf.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("cpf"));

		TableColumn<Funcionario, String> colNome = new TableColumn<>("Nome");
		colNome.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("nome"));

		TableColumn<Funcionario, String> colEmail = new TableColumn<>("Email");
		colEmail.setCellValueFactory(new PropertyValueFactory<Funcionario, String>("email"));

		tabela.getColumns().addAll(colCpf, colNome, colEmail);
		tabela.setItems(cc.getLista());
	}

}
