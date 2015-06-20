package com.robotz.model.parser.parsetree;

public abstract class Node {
	
	protected String productionRule;
	
	public abstract String getNodeType();
	
	public String getProductionRuleAsString() {
		
		return this.productionRule;
		
	}
	
	public void setProductionRuleAsString(String rule) {
		
		this.productionRule = rule;
		
	}

	@Override
	public boolean equals(Object nodeToCompare) {
		
		return this.getNodeType().equals(((Node) nodeToCompare).getNodeType()) ? true : false;
		
	}
	
}
