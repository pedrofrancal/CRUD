package boundary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import controller.ChamadoController;
import dao.DAOException;
import entity.Chamado;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
import javafx.util.converter.LocalDateStringConverter;

public class SuporteChamadoBoundary implements EventHandler<ActionEvent>, GerarTela {

	private TextField txtAtendimento = new TextField();
	private TextField txtEmail = new TextField();
	private BorderPane panePrincipal = new BorderPane();

	private Button btnResolver = new Button("Atender chamado");
	private Button btnArquivar = new Button("Arquivar");
	private Button btnPedidos = new Button("Verificar pedidos");

	private ChamadoController cc = new ChamadoController();
	private TableView<Chamado> tabela = new TableView<>();

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

		panePrincipal.setTop(paneCampos);
		panePrincipal.setCenter(tabela);

		try {
			cc.pesquisarTodosChamados();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return panePrincipal;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private void vincularCampos() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		StringConverter<LocalDate> dateConverter = new LocalDateStringConverter(dtf, dtf);
		
		Bindings.bindBidirectional(txtAtendimento.textProperty(), cc.getDescricaoProperty());
		Bindings.bindBidirectional(txtEmail.textProperty(), cc.getEmailClienteProperty());
		
		TableColumn<Chamado, Integer> colId = new TableColumn<>("Id");
		colId.setCellValueFactory(new PropertyValueFactory<Chamado, Integer>("id"));

		TableColumn<Chamado, String> colDescricao = new TableColumn<>("Descricao");
		colDescricao.setCellValueFactory(new PropertyValueFactory<Chamado, String>("descricao"));

		TableColumn<Chamado, String> colData = new TableColumn<>("Data");
        colData.setCellValueFactory(
        		(item) -> {
        			return new ReadOnlyStringWrapper( 
        					item.getValue().getDataChamado().format(dtf)
        					);
        		}
        		);

		TableColumn<Chamado, String> colEmail = new TableColumn<>("Email do Cliente");
		colEmail.setCellValueFactory(
				(item) -> {
					return new ReadOnlyStringWrapper(
							item.getValue().getCliente().getEmail()
							);							
				}
				);

		tabela.getColumns().addAll(colId, colDescricao, colData, colEmail);
		tabela.setItems(cc.getLista());
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
