package com.robotz.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class ErrorDialog extends JDialog{

	private static final long serialVersionUID = -8771397140602186635L;
	private static ErrorDialog instance;
	private Color bgColor;
	private Color foregroundColor;
	private JButton closeButton;
	private JTextArea errorArea;
	
	private ErrorDialog() {
		setPreferredSize(new Dimension(400, 400));
		setMinimumSize(new Dimension(400, 400));
		setTitle("Error console");
		setVisible(false);
		setAlwaysOnTop(true);
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		setLayout(new BorderLayout());
		bgColor = new Color(229, 115, 115);
		foregroundColor = new Color(183, 28, 28);
		setBackground(bgColor);
		initComponent();
		initAction();
	}

	public static ErrorDialog getInstance() {
		if (instance == null)
			instance = new ErrorDialog();
		return instance;
	}
	
	private void initComponent() {
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		JLabel title = new JLabel("Error Console");
		title.setForeground(foregroundColor);
		topPanel.add(title);
		add(topPanel, BorderLayout.NORTH);
		JPanel centerPanel = new JPanel();
		errorArea = new JTextArea();
		errorArea.setText("There is no error :)");
		errorArea.setBackground(bgColor);
		errorArea.setForeground(foregroundColor);
		JScrollPane scrollPane = new JScrollPane(errorArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		centerPanel.add(errorArea);
		add(centerPanel, BorderLayout.CENTER);
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		closeButton = new JButton("Close");
		bottomPanel.add(closeButton);
		add(bottomPanel, BorderLayout.SOUTH);
	}
	
	private void initAction() {
		closeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);		
			}	
		});
	}
	
	public void appendError(String error) {
		errorArea.append(error);
	}
	
}
