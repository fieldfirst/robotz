package com.robotz.model.tokenizer;


public interface Token {
	public int getIntValue();
	public String getCharValue();
	public String getType();
	public int getLineNumber();
	public void setSymbolTableRow(int row);
	public int getSymbolTableRow();
}