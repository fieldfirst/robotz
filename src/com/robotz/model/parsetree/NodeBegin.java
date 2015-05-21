package com.robotz.model.parsetree;

public class NodeBegin extends Node {
	
	private final String nodeType = "begin";
	private int mapWidth;
	private int mapHeight;
	private Node node;

	@Override
	public String getNodeType() {
		
		return nodeType;
		
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}
	
}
