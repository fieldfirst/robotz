package com.robotz.model.parsetree;

public class NodeDo implements Node {

	private String nodeType = "do";
	private Node L;
	private Node a;
	private Node b;
	
	@Override
	public String getNodeType() {
		return nodeType;
	}

}
