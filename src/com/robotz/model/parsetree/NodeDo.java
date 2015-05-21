package com.robotz.model.parsetree;

import com.robotz.model.Token;


public class NodeDo extends Node {

	private final String nodeType = "do";
	private Node node;
	private Token a;
	private Token b;
	
	@Override
	public String getNodeType() {
		
		return nodeType;
		
	}
	
	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public Token getA() {
		return a;
	}

	public void setA(Token a) {
		this.a = a;
	}

	public Token getB() {
		return b;
	}

	public void setB(Token b) {
		this.b = b;
	}
	
}
