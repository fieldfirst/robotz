package com.robotz.model.parser.parsetree;

import com.robotz.model.tokenizer.Token;

public class NodeAssign extends Node {
	
	private final String nodeType = "assn";
	private Token variableName;
	private Token value;
	
	@Override
	public String getNodeType() {
		
		return nodeType;
		
	}

	public Token getVariableName() {
		return variableName;
	}

	public void setVariableName(Token variableName) {
		this.variableName = variableName;
	}

	public Token getValue() {
		return value;
	}

	public void setValue(Token value) {
		this.value = value;
	}
	
}
