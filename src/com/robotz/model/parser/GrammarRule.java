package com.robotz.model.parser;

import java.util.Stack;

public class GrammarRule {

	private String[] grammarTokens;
	private String leftSymbol;
	private String rightSymbol;
		
	public GrammarRule(String[] grammarTokens, String leftSymbol) {
		this.grammarTokens = grammarTokens;
		this.leftSymbol = leftSymbol;
		this.rightSymbol = "";
	}

	public boolean evaluate(Stack<String> expression) {
		
		this.rightSymbol = "";
		int counter = 0;
		while (! expression.isEmpty()) {
			if (grammarTokens[counter].equals(expression.pop())) {
				if (ParserUtility.getTypeToStringMap(grammarTokens[counter]) != null) {
					rightSymbol += " " + ParserUtility.getTypeToStringMap(grammarTokens[counter]);
				}
				else {
					rightSymbol += " " + grammarTokens[counter];
				}
				counter++;
			}
			else {
				// Error detected, can't reduce
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
	
	public String getProductionRuleAsString() {		
		return this.leftSymbol + " ->" + this.rightSymbol;
	}

}
