package com.robotz.model.parsetree;

public class STStackItem {
	
	private String leftSymbol;
	private Object item;
	
	public STStackItem(String leftSymbol, Object item) {
		
		this.leftSymbol = leftSymbol;
		this.item = item;
		
	}

	public String getLeftSymbol() {
		return leftSymbol;
	}

	public Object getItem() {
		return item;
	}

}
