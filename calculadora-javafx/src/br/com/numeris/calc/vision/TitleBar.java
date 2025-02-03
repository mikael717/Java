package br.com.numeris.calc.vision;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TitleBar extends HBox{

	private Button closeButton = new Button("");
	
	public TitleBar() {
		getStyleClass().add("barra-titulo");
		
		closeButton.setOnAction(e -> System.exit(0));
		
		setOnMouseEntered(e -> closeButton.setText("×"));
		setOnMouseExited(e -> closeButton.setText(""));
		
		closeButton.getStyleClass().add("botao-fechar");
		getChildren().add(closeButton);
	}
}
