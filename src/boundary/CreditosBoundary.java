package boundary;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class CreditosBoundary implements GerarTela{

	@Override
	public Pane gerarTela() {
		GridPane grid = new GridPane();
		grid.add(new Label("Desenvolvido por: "), 0, 0);
		grid.add(new Label("Pedro França"), 0, 1);
		grid.add(new Label("Augusto Furtado "), 0, 2);
		grid.add(new Label("Henrique Chaves "), 0, 3);
		grid.add(new Label("Luiz Felipe "), 0, 4);
		
		return grid;
	}

}
