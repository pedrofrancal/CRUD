package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import controller.ClientesController;
import dao.DAOException;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class CadastroClienteBoundary implements EventHandler<ActionEvent>, GerarTela{

	private TextField txtEmail = new TextField();
	private TextField txtSenha = new TextField();
	private TextField txtCpf = new TextField();
	private TextField txtNome = new TextField();
	private TextField txtDataNascimento = new TextField();
	private TextField txtEndereco = new TextField();
	private TextField txtTelefone = new TextField();
	private TextField txtCep = new TextField();
	
	private Button btnConfirmar = new Button("Confirmar");
	
	private ClientesController cc = new ClientesController();
	
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
		paneCampos.add(new Label("Data Nascimento"), 0, 5);		
		paneCampos.add(txtDataNascimento, 1, 5);
		paneCampos.add(new Label("Endereço"), 0, 6);
		paneCampos.add(txtEndereco, 1, 6);
		paneCampos.add(new Label("CEP"), 0, 7);
		paneCampos.add(txtCep, 1, 7);
		paneCampos.add(new Label("Telefone"), 0, 8);
		paneCampos.add(txtTelefone, 1, 8);
		
		paneCampos.add(btnConfirmar, 1, 9);
		
		btnConfirmar.setOnAction(this);
		
		paneCampos.setAlignment(Pos.CENTER);
		paneCampos.setHgap(10.0d);
		paneCampos.setVgap(10.0d);
		
		return paneCampos;
	}

	private void vincularCampos() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		StringConverter<LocalDate> dateConverter = new LocalDateStringConverter(dtf, dtf);
		
		Bindings.bindBidirectional(txtNome.textProperty(), cc.getNomeProperty());
		Bindings.bindBidirectional(txtEmail.textProperty(), cc.getEmailProperty());
		Bindings.bindBidirectional(txtSenha.textProperty(), cc.getSenhaProperty());
		Bindings.bindBidirectional(txtCpf.textProperty(), cc.getCpfProperty());
		Bindings.bindBidirectional(txtDataNascimento.textProperty(), cc.getDataNascimentoProperty(), dateConverter);
		Bindings.bindBidirectional(txtEndereco.textProperty(), cc.getEnderecoProperty());
		Bindings.bindBidirectional(txtTelefone.textProperty(), cc.getTelefoneProperty());
		Bindings.bindBidirectional(txtCep.textProperty(), cc.getCepProperty());
	}

	@Override
	public void handle(ActionEvent e) {
		if (btnConfirmar == e.getTarget()) {
			try {
				cc.adicionar();
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
