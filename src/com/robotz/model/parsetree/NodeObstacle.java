package com.robotz.model.parsetree;

public class NodeObstacle implements Node {

	private String nodeType = "obst";
	private Node a;
	private Node b;
	
	@Override
	public String getNodeType() {
		return nodeType;
	}

}
