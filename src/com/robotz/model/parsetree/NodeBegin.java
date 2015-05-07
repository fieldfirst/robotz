package com.robotz.model.parsetree;

public class NodeBegin implements Node {
	
	private String nodeType = "begin";
	private int i;
	private int j;
	private Node node;

	@Override
	public String getNodeType() {
		return this.nodeType;
	}
	
	public void setNode(Node node) {
		this.node = node;
	}
	
	public Node getNode() {
		return this.node;
	}

}
