package com.robotz.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class ErrorDialog extends FlatDialog{

	private static final long serialVersionUID = -8771397140602186635L;
	private static ErrorDialog instance;
	private Color bgColor;
	private Color foregroundColor;
	private JButton closeButton;
	private JTextArea errorArea;
	
	private ErrorDialog() {
		setPreferredSize(new Dimension(650, 400));
		setMinimumSize(new Dimension(650, 400));
		setTitle("Error console");
		setVisible(false);
		setAlwaysOnTop(true);
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setLayout(new BorderLayout());
		bgColor = new Color(239, 83, 80);
		foregroundColor = Color.WHITE;
		setBackground(bgColor);
		initComponent();
	}

	public static ErrorDialog getInstance() {
		if (instance == null)
			instance = new ErrorDialog();
		return instance;
	}
	
	protected final void initComponent() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		topPanel.setBackground(bgColor);
		JLabel title = new JLabel("Error Console");
		title.setForeground(foregroundColor);
		topPanel.add(title);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		JPanel centerPanel = new JPanel(new BorderLayout());
		errorArea = new JTextArea();
		errorArea.setText("There is no error :)");
		errorArea.setBackground(bgColor);
		errorArea.setForeground(foregroundColor);
		errorArea.setLineWrap(true);
		errorArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(errorArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		bottomPanel.setBackground(bgColor);
		closeButton = new JButton("Close");
		bottomPanel.add(closeButton);
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		mainPanel.setOpaque(false);
		add(mainPanel);
	}
	
	public void setbtnCloseAction(Action action) {
		closeButton.addActionListener(action);
	}
	
	public void appendError(String error) {
		errorArea.append(error + "\n\n");
	}
	
	public void clearError() {
		errorArea.setText("");
	}
	
}
