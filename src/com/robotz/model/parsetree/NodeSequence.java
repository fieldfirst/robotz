package com.robotz.model.parsetree;

public class NodeSequence implements Node {
	
	private String nodeType = "seq";
	private Node syntaxTreeA;
	private Node syntaxTreeB;

	@Override
	public String getNodeType() {
		return nodeType;
	}

}
