package com.robotz.model;

import java.util.HashMap;

public class ParserState {

	private String stateName;
	private HashMap<String, String[]> actionMap = new HashMap<String, String[]>();
	private HashMap<String, String> gotoMap = new HashMap<String, String>();
	
	public ParserState(String stateName) {
		this.stateName = stateName;
	}
	
	public String getStateName() {
		return this.stateName;
	}
	
	public void setActionMap(String terminalSymbol, String operation) {
		if (! operation.equals("acc")) {
			actionMap.put(terminalSymbol, new String[]{operation.substring(0, 1), operation.substring(1)});
		}
		else {
			// for the 'acc' input [state 1 with '$' as an input symbol]
			actionMap.put(terminalSymbol, new String[]{operation});
		}
	}
	
	public HashMap<String, String> getActionMap(String terminalSymbol) {
		if (actionMap.containsKey(terminalSymbol)) {
			HashMap<String, String> action = new HashMap<String, String>();
			if (! actionMap.get(terminalSymbol)[0].equals("acc")) {
				action.put("operation", actionMap.get(terminalSymbol)[0]);
				action.put("number", actionMap.get(terminalSymbol)[1]);
			}
			else {
				// for the 'acc' output [state 1 with '$' as an input symbol]
				action.put("operation", actionMap.get(terminalSymbol)[0]);
				action.put("number", null);
			}
			return action;
		}
		else {
			return null;
		}
	}
	
	public boolean hasGotoMap() {
		if (gotoMap.size() == 0) {
			return false;
		}
		return true;
	}
	
	public void setGotoMap(String nonTerminalSymbol, String stateNumber) {
		gotoMap.put(nonTerminalSymbol, stateNumber);
	}
	
	public HashMap<String, String> getGotoMap() {
		return gotoMap;
	}
	
}
