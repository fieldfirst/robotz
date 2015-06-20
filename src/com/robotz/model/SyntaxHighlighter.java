package com.robotz.model;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

public class SyntaxHighlighter extends SwingWorker<Void, Object> {

	private JTextPane textPane;
	private StyleContext style;
	private AttributeSet textStyleKeyword;
	private AttributeSet textStyleVariable;
	private AttributeSet textStyleInteger;
	private AttributeSet textStyleComment;
	
	
	public SyntaxHighlighter(JTextPane textPane) {
		this.textPane = textPane;
		style = StyleContext.getDefaultStyleContext();
		textStyleKeyword = style.addAttribute(style.getEmptySet(), StyleConstants.Foreground, new Color(162, 107, 147));
		textStyleVariable = style.addAttribute(style.getEmptySet(), StyleConstants.Foreground, new Color(27, 165, 220));
		textStyleInteger = style.addAttribute(style.getEmptySet(), StyleConstants.Foreground, new Color(23, 161, 85));
		textStyleComment = style.addAttribute(style.getEmptySet(), StyleConstants.Foreground, new Color(251, 176, 134));
	}
	
	private final Pattern KEYWORD = Pattern.compile("(begin|halt|obstacle|add|to|move|north|south|east|west|robot|do|until|;|<|>|=)");
	private final Pattern VARIABLE = Pattern.compile("[a-zA-Z]{1,8}");
	private final Pattern INTEGER = Pattern.compile("\\d{1,8}");
	private final Pattern COMMENT_PART = Pattern.compile("\\*-.*-\\*");
	
	@Override
	protected Void doInBackground() throws Exception {
				
		Matcher m = VARIABLE.matcher(textPane.getDocument().getText(0, textPane.getDocument().getLength()));
		while (m.find()) {
			textPane.getStyledDocument().setCharacterAttributes(m.start(), (m.end() - m.start()), textStyleVariable, false);
		}
		
		m = INTEGER.matcher(textPane.getDocument().getText(0, textPane.getDocument().getLength()));
		while (m.find()) {
			textPane.getStyledDocument().setCharacterAttributes(m.start(), (m.end() - m.start()), textStyleInteger, false);
		}
		
		m = COMMENT_PART.matcher(textPane.getDocument().getText(0, textPane.getDocument().getLength()));
		while (m.find()) {
			textPane.getStyledDocument().setCharacterAttributes(m.start(), (m.end() - m.start()), textStyleComment, false);
		}
		
		m = KEYWORD.matcher(textPane.getDocument().getText(0, textPane.getDocument().getLength()));
		while (m.find()) {
			textPane.getStyledDocument().setCharacterAttributes(m.start(), (m.end() - m.start()), textStyleKeyword, false);
		}
								
		return null;
	}

}
