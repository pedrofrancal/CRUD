package boundary;

import controller.LoginController;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Classe que controla as interações com o login
 */
public class LoginBoundary implements EventHandler<ActionEvent>, GerarTela {

	GridPane grid = new GridPane();
	private TextField txtEmail = new TextField();
	private TextField txtSenha = new TextField();
	private Button btnLogar = new Button("Entrar");
	private Button btnCadastrar = new Button("Cadastrar");

	private LoginController lg = new LoginController();

	@Override
	/**
	 * Função que gera a tela de créditos
	 */
	public Pane gerarTela() {

		vincularCampos();

		grid.add(new Label("Email"), 0, 0);
		grid.add(txtEmail, 1, 0);
		grid.add(new Label("Senha"), 0, 1);
		grid.add(txtSenha, 1, 1);

		grid.add(btnLogar, 1, 2);
		grid.add(btnCadastrar, 1, 3);

		btnLogar.setOnAction(this);
		btnCadastrar.setOnAction(this);

		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10.0d);
		grid.setVgap(10.0d);

		return grid;
	}

	/**
	 * Função que vincula os campos com o controller
	 */
	public void vincularCampos() {
		Bindings.bindBidirectional(txtEmail.textProperty(), lg.getEmailProperty());
		Bindings.bindBidirectional(txtSenha.textProperty(), lg.getSenhaProperty());
	}

	@Override
	/**
	 * Função que envia comando aos controllers
	 */
	public void handle(ActionEvent e) {
		if (btnLogar == e.getTarget()) {
			try {
				BorderPane panePrincipal = (BorderPane) grid.getParent();
				panePrincipal.setCenter(lg.logar());
			} catch (Exception e1) {
				Alert a = new Alert(AlertType.ERROR, "USUARIO INVALIDO");
				a.show();
				e1.printStackTrace();
			}
		} else if (btnCadastrar == e.getTarget()) {
			try {
				BorderPane panePrincipal = (BorderPane) grid.getParent();
				panePrincipal.setCenter(new CadastroClienteBoundary().gerarTela());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
