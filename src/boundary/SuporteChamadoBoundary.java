package boundary;

import controller.ChamadoController;
import dao.DAOException;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class SuporteChamadoBoundary implements EventHandler<ActionEvent>, GerarTela {

	private TextField txtAtendimento = new TextField();
	private TextField txtEmail = new TextField();
	private BorderPane panePrincipal = new BorderPane();

	private Button btnResolver = new Button("Atender chamado");
	private Button btnArquivar = new Button("Arquivar");
	private Button btnPedidos = new Button("Enviar");

	private ChamadoController cc = new ChamadoController();

	@Override
	public Pane gerarTela() {

		vincularCampos();

		GridPane paneCampos = new GridPane();

		paneCampos.add(new Label("Descrição do atendimento"), 0, 1);
		paneCampos.add(txtAtendimento, 1, 1);
		paneCampos.add(new Label("Email cliente"), 0, 2);
		paneCampos.add(txtEmail, 1, 2);

		paneCampos.add(btnPedidos, 1, 5);
		paneCampos.add(btnResolver, 1, 6);
		paneCampos.add(btnArquivar, 1, 7);

		paneCampos.setAlignment(Pos.CENTER);

		btnResolver.setOnAction(this);
		btnPedidos.setOnAction(this);
		btnArquivar.setOnAction(this);

		panePrincipal.setCenter(paneCampos);

		return panePrincipal;
	}

	private void vincularCampos() {
		Bindings.bindBidirectional(txtAtendimento.textProperty(), cc.getDescricaoProperty());
		Bindings.bindBidirectional(txtEmail.textProperty(), cc.getEmailClienteProperty());
	}

	@Override
	public void handle(ActionEvent e) {
		if (btnResolver == e.getTarget()) {
			try {
				cc.resolver();
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		} else if (btnArquivar == e.getTarget()) {
			try {
				cc.arquivar();
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		} else if (btnPedidos == e.getTarget()) {
			BorderPane paneNovo = (BorderPane) panePrincipal.getParent();
			paneNovo.setCenter(new SuporteBoundary().gerarTela());
		}
	}

}
