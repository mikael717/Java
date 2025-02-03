package br.com.numeris.calc.vision;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Display extends HBox {

	private Label label = new Label("0");
	
	public Display() {
		setAlignment(Pos.BOTTOM_RIGHT);
		getStyleClass().add("display");
		
		label.getStyleClass().add("texto-display");
		getChildren().add(label);
	}
	
	public void addText(String newText) {
		String textOne = getCurrentText();
		
		if(textOne.isEmpty() && newText.equals(",")) {
			textOne = "0,";
		}else if(!textOne.contains(",") && newText.equals(",")) {
			textOne += newText;
		}else if(newText.matches("\\d")) {
			textOne += newText;
		}
		
		if(textOne.isEmpty() || textOne.equals("00")) {
			textOne = "0";
		}
		
		setCurrentText(textOne);
	}
	
	public String getCurrentText() {
		return "0".equals(label.getText()) ? "" : label.getText();
	}
	
	private void setCurrentText(String newText) {
		label.setText(newText);
		ajustFontSize();
	}
	
	private void ajustFontSize() {
		final int FONT_DEFAULT_SIZE = 44;
		int size = FONT_DEFAULT_SIZE;
		
		Text text = new Text(label.getText());
		
		do {
			text.setFont(new Font(label.getFont().getName(), size));
			label.setStyle("-fx-font-size:" + size + "px;");
			size--;
		}while(text.getBoundsInLocal().getWidth() > 210);
	}
	
	public void clean() {
		label.setText("0");
	}
}
