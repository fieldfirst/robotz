package com.robotz.view.dialog;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.robotz.view.EditorJFrame;

public class AddRobotDialog extends AddDialog {
	
	public AddRobotDialog(EditorJFrame frmMain) {
		super(frmMain);
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void initComponent() {
		c.fill = GridBagConstraints.NONE;
		c.ipady = 5;
		
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(new JLabel("Robot's name : "), c);
		
		c.gridx = 1;
		c.gridy = 0;
		mainPanel.add(variableTextField, c);
		
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(new JLabel("X position : "), c);
		
		c.gridx = 1;
		c.gridy = 1;
		mainPanel.add(xPositionTextField, c);
		
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 2;
		mainPanel.add(new JLabel("Y position : "), c);
		
		c.gridx = 1;
		c.gridy = 2;
		mainPanel.add(yPositionTextField, c);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 3;
		JPanel panel = new JPanel();
		panel.add(acceptButton);
		panel.add(cancelButton);
		mainPanel.add(panel, c);
	}
	
	@Override
	protected void setTitle() {
		setTitle("Add a new robot");
	}

	@Override
	public String[] getResult() {
		return new String[]{variableTextField.getText(),
							xPositionTextField.getText(),
							yPositionTextField.getText()};
	}
	
	@Override
	protected void checkInput() {
		if (! xPositionTextField.getText().trim().equals("") && ! yPositionTextField.getText().trim().equals("") && ! variableTextField.getText().trim().equals(""))
			acceptButton.setEnabled(true);
		else
			acceptButton.setEnabled(false);
	}

}
