package com.robotz.model.parsetree;

public class NodeMove implements Node {

	private String nodeType = "move";
	private String v;
	private Node D;
	private Node a;
	
	@Override
	public String getNodeType() {
		return nodeType;
	}

}
