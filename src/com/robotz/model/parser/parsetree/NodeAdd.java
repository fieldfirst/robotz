package com.robotz.model.parser.parsetree;

import com.robotz.model.tokenizer.Token;

public class NodeAdd extends Node {

	private final String nodeType = "add";
	private Token value;
	private Token variableName;
	
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
