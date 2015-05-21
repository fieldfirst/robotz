package com.robotz.model.parsetree;

import com.robotz.model.Token;

public class NodeRobot extends Node {

	private final String nodeType = "robot";
	private String variableName;
	private Token xPosition;
	private Token yPosition;
	
	@Override
	public String getNodeType() {
		
		return nodeType;
		
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public Token getxPosition() {
		return xPosition;
	}

	public void setxPosition(Token xPosition) {
		this.xPosition = xPosition;
	}

	public Token getyPosition() {
		return yPosition;
	}

	public void setyPosition(Token yPosition) {
		this.yPosition = yPosition;
	}

}