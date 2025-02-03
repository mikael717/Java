package br.com.numeris.calc.vision;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.scene.layout.GridPane;

public class Keyboard extends GridPane implements Consumer<String> {

	private List<Consumer<String>> functions = new ArrayList<>();

	private Buttons buttonAC = new Buttons("AC", this, "escuro");
	private Buttons buttonMoreLess = new Buttons("±", this, "escuro");
	private Buttons buttonPerc = new Buttons("%", this, "escuro");
	private Buttons buttonDiv = new Buttons("÷", this, "escuro");
	
	private Buttons button7 = new Buttons("7", this);
	private Buttons button8 = new Buttons("8", this);
	private Buttons button9 = new Buttons("9", this);
	private Buttons buttonMult = new Buttons("×", this, "operacao");
	
	private Buttons button4 = new Buttons("4", this);
	private Buttons button5 = new Buttons("5", this);
	private Buttons button6 = new Buttons("6", this);
	private Buttons buttonSub = new Buttons("-", this, "operacao");
	
	private Buttons button1 = new Buttons("1", this);
	private Buttons button2 = new Buttons("2", this);
	private Buttons button3 = new Buttons("3", this);
	private Buttons buttonAdd = new Buttons("+", this, "operacao");
	
	private Buttons button0 = new Buttons("0", this, "duplo", "arredondado-e");
	private Buttons buttonVirg = new Buttons(",", this);
	private Buttons buttonResultado = new Buttons ("=", this, "operacao", "arredondado-d");
	
	public Keyboard() {
		setHgap(2);
		setVgap(2);
		
		add(buttonAC, 0, 0);
		add(buttonMoreLess, 1, 0);
		add(buttonPerc, 2, 0);
		add(buttonDiv, 3, 0);
		
		add(button7, 0, 1);
		add(button8, 1, 1);
		add(button9, 2, 1);
		add(buttonMult, 3, 1);
		
		add(button4, 0, 2);
		add(button5, 1, 2);
		add(button6, 2, 2);
		add(buttonSub, 3, 2);
		
		add(button1, 0, 3);
		add(button2, 1, 3);
		add(button3, 2, 3);
		add(buttonAdd, 3, 3);
		
		add(button0, 0, 4, 2, 1);
		add(buttonVirg, 2, 4);
		add(buttonResultado, 3, 4);
		
		setOnKeyTyped(e -> accept(e.getCharacter()));
		buttonAC.requestFocus();
	}
	
	public void callOnClick(Consumer<String> function) {
		functions.add(function);
	}
	
	@Override
	public void accept(String t) {
		functions.forEach(fn -> fn.accept(t));
	}

}
