package boundary;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Classe que controla tela de créditos
 */
public class CreditosBoundary implements GerarTela {

	@Override
	/**
	 * Função que gera a tela de créditos
	 */
	public Pane gerarTela() {
		GridPane grid = new GridPane();
		
		Label dev = new Label("DESENVOLVIDO POR: ");
		dev.setTextFill(Color.RED);
		
		grid.add(dev, 0, 0);
		grid.add(new Label("Pedro França"), 0, 1);
		grid.add(new Label("Henrique Chaves "), 0, 2);
		grid.add(new Label("Augusto Furtado "), 0, 3);
		grid.add(new Label("Luiz Felipe "), 0, 4);

		Label img = new Label("IMAGENS FEITAS POR: ");
		img.setTextFill(Color.BLUE);
		
		grid.add(img, 0, 6);
		grid.add(new Label("Henrique Chaves"), 0, 7);

		Label bd = new Label("BANCO DE DADOS FEITO POR: ");
		bd.setTextFill(Color.GREEN);

		grid.add(bd, 0, 9);
		grid.add(new Label("Pedro França"), 0, 10);
		grid.add(new Label("Henrique Chaves"), 0, 11);

		grid.setAlignment(Pos.CENTER);
		
		return grid;
	}

}
