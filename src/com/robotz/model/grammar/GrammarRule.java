package com.robotz.model.grammar;

import java.util.Stack;

public class GrammarRule {

	private String[] grammarTokens;
	private String leftSymbol;
	
	public GrammarRule(String[] grammarTokens, String leftSymbol) {
		this.grammarTokens = grammarTokens;
		this.leftSymbol = leftSymbol;
	}

	public boolean evaluate(Stack<String> expression) {

		int counter = 0;
		while (! expression.isEmpty()) {
			if (grammarTokens[counter].equals(expression.pop())) {
				counter++;
			}
			else {
				return false;
			}
		}

		if (counter == grammarTokens.length) {
			return true;
		}
		else {
			return false;
		}
	}

	public String getLeftSymbol() {
		return leftSymbol;
	}
	
	public int getExpressionSize() {
		return grammarTokens.length;
	}

}
