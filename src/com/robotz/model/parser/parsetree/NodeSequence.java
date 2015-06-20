package com.robotz.model.parser.parsetree;

public class NodeSequence extends Node {
	
	private final String nodeType = "seq";
	private Node syntaxTreeA;
	private Node syntaxTreeB;
	
	@Override
	public String getNodeType() {
		
		return nodeType;
		
	}
	
	public void setANode(Node aNode) {
		this.syntaxTreeA = aNode;
	}
	
	public void setBNode(Node bNode) {
		this.syntaxTreeB = bNode;
	}
	
	public Node getANode() {
		return this.syntaxTreeA;
	}
	
	public Node getBNode() {
		return this.syntaxTreeB;
	}

}
