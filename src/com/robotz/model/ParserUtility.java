package com.robotz.model;

import java.util.HashMap;

public class ParserUtility {
	
	private static HashMap<String, String> typeToStringMap;
	
	private static void initTypeToStringMap() {
		typeToStringMap = new HashMap<String, String>();
		typeToStringMap.put("b", "begin");
		typeToStringMap.put("i", "int");
		typeToStringMap.put("e", "east");
		typeToStringMap.put("w", "west");
		typeToStringMap.put("n", "north");
		typeToStringMap.put("s", "south");
		typeToStringMap.put("v", "var");
		typeToStringMap.put("h", "halt");
		typeToStringMap.put("m", "move");
		typeToStringMap.put("r", "robot");
		typeToStringMap.put("u", "until");
		typeToStringMap.put("d", "do");
		typeToStringMap.put("a", "add");
		typeToStringMap.put("t", "to");
		typeToStringMap.put("o", "obstacle");
		typeToStringMap.put(";", ";");
	}
	
	public static String getTypeToStringMap(String key) {
		if (typeToStringMap != null) {
			return typeToStringMap.get(key);
		}
		else {
			initTypeToStringMap();
			return typeToStringMap.get(key);
		}
	}
	
}
