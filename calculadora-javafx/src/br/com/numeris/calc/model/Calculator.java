package br.com.numeris.calc.model;

import br.com.numeris.calc.vision.Display;
import br.com.numeris.calc.vision.Keyboard;
import br.com.numeris.calc.vision.TitleBar;
import javafx.scene.layout.VBox;

public class Calculator extends VBox{

	public Calculator() {
		TitleBar titleBar = new TitleBar();
		Display display = new Display();
		Keyboard keyboard = new Keyboard();
		
		getStyleClass().add("calculadora");
		
		getChildren().add(titleBar);
		getChildren().add(display);
		getChildren().add(keyboard);
		
		keyboard.callOnClick(textOne -> {
			if(textOne.equals("AC")) {
				display.clean();
			}else {
				display.addText(textOne);
			}
		});
	}
	
}
