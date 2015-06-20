package com.robotz.model.parser.parsetree;

import com.robotz.model.tokenizer.Token;
import com.robotz.model.tokenizer.TokenKeyword;

public class NodeMove extends Node {

	private final String nodeType = "move";
	private String variableName;
	private TokenKeyword direction;
	private Token distance;
	
	@Override
	public String getNodeType() {
		
		return nodeType;
		
	}
	
	public String getVariableName() {
		return this.variableName;
	}
	
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public TokenKeyword getDirection() {
		return direction;
	}

	public void setDirection(TokenKeyword direction) {
		this.direction = direction;
	}

	public Token getDistance() {
		return distance;
	}

	public void setDistance(Token distance) {
		this.distance = distance;
	}

}
