package br.com.numeris.calc.vision;

import java.util.function.Consumer;
import javafx.scene.control.Button;

public class Buttons extends Button{

	public Buttons(String text, Consumer<String> func, String... classesCSS) {
		super(text);
		 
		getStyleClass().add("botao");
		
		setOnAction(e -> func.accept(getText()));
		
		for(String classCSS: classesCSS) {
			getStyleClass().add(classCSS);
		}
	}
}
