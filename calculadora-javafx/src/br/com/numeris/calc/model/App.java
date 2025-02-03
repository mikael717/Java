package br.com.numeris.calc.model;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

	private double posX = 0;
	private double posY = 0;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Font.loadFont(getClass().getResource("/br/com/numeris/calc/vision/Roboto.ttf").toExternalForm(), 10);
		String css = getClass().getResource("/br/com/numeris/calc/vision/Calculator.css").toExternalForm();
		
		Scene primal = new Scene(new Calculator(), 230, 320);
		primal.setFill(Color.TRANSPARENT);
		primal.getStylesheets().add(css);
		
		primal.setOnMousePressed(event -> {
			posX = primaryStage.getX() - event.getScreenX();
			posY = primaryStage.getY() - event.getScreenY();
		});
		
		primal.setOnMouseDragged(e -> {
			primaryStage.setX(e.getScreenX() + posX);
			primaryStage.setY(e.getScreenY() + posY);
		});
		
		primaryStage.setScene(primal);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
