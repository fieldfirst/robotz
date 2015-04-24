package com.robotz.model.parsetree;

public class NodeRobot implements Node {

	private String nodeType = "robot";
	private String v;
	private Node a;
	private Node b;

	@Override
	public String getNodeType() {
		return this.nodeType;
	}

}