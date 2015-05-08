package com.robotz.model.parsetree;

public class NodeBegin implements Node {
	
	private String nodeType = "begin";
	private Integer i;
	private Integer j;
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
	
	public void setIReference(Integer iReference) {
		this.i = iReference;
	}
	
	public void setJReference(Integer jReference) {
		this.j = jReference;
	}
	
	public int getI() {
		return i.intValue();
	}
	
	public int getJ() {
		return j.intValue();
	}

}
