package com.robotz.model.parsetree;

import com.robotz.model.Token;

public class NodeObstacle extends Node {

	private final String nodeType = "obst";
	private Token xPosition;
	private Token yPosition;

	@Override
	public String getNodeType() {
		
		return nodeType;
		
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
