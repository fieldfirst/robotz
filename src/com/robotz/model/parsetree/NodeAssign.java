package com.robotz.model.parsetree;

public class NodeAssign implements Node {
	
	private String nodeType = "assn";
	private String v;
	private Node a;

	@Override
	public String getNodeType() {
		return nodeType;
	}

}
