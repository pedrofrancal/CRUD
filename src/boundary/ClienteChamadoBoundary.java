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

public class ClienteChamadoBoundary implements EventHandler<ActionEvent>, GerarTela {

	private TextField txtDescricao = new TextField();
	private BorderPane panePrincipal = new BorderPane();

	private String email;
	
	private Button btnPedido = new Button("Fazer pedido");
	private Button btnFeedback = new Button("Enviar");
	
	private ChamadoController cc = new ChamadoController();

	@Override
	public Pane gerarTela() {

		vincularCampos();

		GridPane paneCampos = new GridPane();

		paneCampos.add(new Label("Descrição do problema"), 0, 1);
		paneCampos.add(txtDescricao, 1, 1);
		
		txtDescricao.setPrefSize(200, 200);
		
		paneCampos.add(btnFeedback, 1, 5);
		paneCampos.add(btnPedido, 1, 6);
		
		paneCampos.setAlignment(Pos.CENTER);
		
		btnPedido.setOnAction(this);
		btnFeedback.setOnAction(this);

		panePrincipal.setCenter(paneCampos);

		return panePrincipal;
	}

	private void vincularCampos() {
		Bindings.bindBidirectional(txtDescricao.textProperty(), cc.getDescricaoProperty());
	}

	@Override
	public void handle(ActionEvent e) {
		if (btnFeedback == e.getTarget()) {
			try {
				cc.abrirChamado(this.email);
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		} else if (btnPedido == e.getTarget()) {
			BorderPane paneNovo = (BorderPane) panePrincipal.getParent();
			ClienteBoundary cb = new ClienteBoundary();
			cb.setEmail(this.email);
			paneNovo.setCenter(cb.gerarTela());
		}
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
