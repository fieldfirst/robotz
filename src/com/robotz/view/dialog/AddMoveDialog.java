package com.robotz.view.dialog;

import java.awt.GridBagConstraints;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.robotz.view.EditorJFrame;

public class AddMoveDialog extends AddDialog {
	
	private static final long serialVersionUID = 1L;

	public AddMoveDialog(EditorJFrame frmMain) {
		super(frmMain);
	}

	@Override
	protected void checkInput() {
		if (! xPositionTextField.getText().trim().equals("") && ! yPositionTextField.getText().trim().equals("") && ! directionField.getSelectedItem().equals("")) {
			acceptButton.setEnabled(true);
		}
		else
			acceptButton.setEnabled(false);		
	}

	@Override
	protected void setTitle() {
		setTitle("Add a new move command");
	}

	@Override
	protected void initComponent() {
		c.fill = GridBagConstraints.NONE;
		c.ipady = 5;
		
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		mainPanel.add(new JLabel("Robot name : "), c);
		
		c.gridx = 1;
		c.gridy = 0;
		mainPanel.add(xPositionTextField, c);
		
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(new JLabel("Direction : "), c);
		
		c.gridx = 1;
		c.gridy = 1;
		directionField.addItem(new String("north"));
		directionField.addItem(new String("south"));
		directionField.addItem(new String("west"));
		directionField.addItem(new String("east"));
		mainPanel.add(directionField, c);
		
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 2;
		mainPanel.add(new JLabel("Integer : "), c);
		
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
	public String[] getResult() {
		return new String[]{xPositionTextField.getText(),
				directionField.getSelectedItem().toString(),
				yPositionTextField.getText()};
	}

}
