package com.robotz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileController extends Controller {

	private FileOpenAction fileOpenAction;
	private FileNewAction fileNewAction;
	private FileSaveAction fileSaveAction;
	private FileSaveAsAction fileSaveAsAction;
	private FileExitAction fileExitAction;

	private String fileName = "";
	
	private DocumentListener textPaneListener;

	protected void initAction() {
		fileOpenAction = new FileOpenAction();
		fileNewAction = new FileNewAction();
		fileSaveAction = new FileSaveAction();
		fileSaveAsAction = new FileSaveAsAction();
		fileExitAction = new FileExitAction();
		
		textPaneListener = new DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				fileSaveAction.setEnabled(true);
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				fileSaveAction.setEnabled(true);
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				fileSaveAction.setEnabled(true);
			}
		};

		frmMain.setTextPaneDocumentListener(textPaneListener);

		frmMain.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent arg0) {
				if (!fileSaveAction.isEnabled())
					System.exit(0);
				else
				{
					int n = JOptionPane.showConfirmDialog(
							frmMain,
							"This file was edited ?\nDo you want to discard it ?",
							"Discard Confirmation",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);
					if (n == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
					else
					{
						File file = new File(fileName);
						if (!file.exists())
						{
							if (saveAs()) {
								System.exit(0);
							}
						}
						else
						{
							saveFile(file);
							System.exit(0);
						}
					}
				}
			}

		});

	}

	protected void assignMenuAction() {
		frmMain.setMnuOpenAction(fileOpenAction);
		frmMain.setMnuNewAction(fileNewAction);
		frmMain.setMnuSaveAction(fileSaveAction);
		frmMain.setMnuSaveAsAction(fileSaveAsAction);
		frmMain.setMnuExitAction(fileExitAction);
	}

	protected void assignToolBarAction() {
		frmMain.setToolBarNewAction(fileNewAction);
		frmMain.setToolBarOpenAction(fileOpenAction);
		frmMain.setToolBarSaveAction(fileSaveAction);
	}

	private void saveFile(File file) {
		BufferedWriter fileWriter;
		try
		{
			fileWriter = new BufferedWriter(new FileWriter(file, false));
			fileWriter.write(frmMain.getTextPaneText());
			fileWriter.flush();
			fileWriter.close();
		}
		catch (IOException e)
		{

		}
	}

	private boolean saveAs() {
		JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Save as");
		fc.setFileFilter(new FileNameExtensionFilter("Robotz", "robotz"));
		File workingDirectory = new File(System.getProperty("user.dir"));
		fc.setCurrentDirectory(workingDirectory);
		int result = fc.showSaveDialog(frmMain);
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = new File(fc.getSelectedFile() + ".robotz");
			if (!file.exists())
			{
				saveFile(file);
				fileSaveAction.setEnabled(false);
				fileName = fc.getSelectedFile().getAbsolutePath() + ".robotz";
				frmMain.setEditorTitle(fileName.replace(".robotz", ""));
			}
			else
			{
				int n = JOptionPane.showConfirmDialog(
						frmMain,
						"This file was already existed ?\nDo you want to replace it ?",
						"Overwrite Confirmation",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (n == JOptionPane.YES_OPTION) {
					saveFile(file);
					fileSaveAction.setEnabled(false);
					fileName = fc.getSelectedFile().getAbsolutePath() + ".robotz";
					frmMain.setEditorTitle(fileName.replace(".robotz", ""));
				}
				else {
					saveAs();
				}
			}
		} else if (result == JFileChooser.CANCEL_OPTION) {
			return false;
		}
		return true;
	}

	private void preparation() {
		frmMain.setTabIndex(0);
		errorDialog.clearError();
		errorDialog.appendError("There is no error :)");
		frmMain.clearTokenizedItem();
		frmMain.clearDerivationItem();
		editController.resetUndoRedoStatus();
	}

	private class FileNewAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;

		private FileNewAction(){
			super("New", new ImageIcon(frmMain.getClass().getResource("resources/new.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
			putValue(SHORT_DESCRIPTION, "Create a new file");
			putValue(MNEMONIC_KEY, KeyEvent.VK_N);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {			

			boolean isSaveSuccessful = true;

			if (fileSaveAction.isEnabled())
			{
				int n = JOptionPane.showConfirmDialog(
						frmMain,
						"This file was edited ?\nDo you want to discard it ?",
						"Discard Confirmation",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (n == JOptionPane.NO_OPTION) {
					File file = new File(fileName);
					if (!file.exists())
					{
						isSaveSuccessful = saveAs();
					}
					else
					{
						saveFile(file);
					}
				}
			}

			if (isSaveSuccessful) {				
				
				frmMain.resetTextPaneDocumentListener(textPaneListener);
				
				frmMain.setTextPaneText("begin i j\n\nhalt");
				frmMain.setEditorTitle("New file");
				fileSaveAction.setEnabled(false);
				fileName = "";
				preparation();
				
				frmMain.setTextPaneDocumentListener(textPaneListener);
								
			}
		}

	}

	private class FileOpenAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;

		private FileOpenAction(){
			super("Open", new ImageIcon(frmMain.getClass().getResource("resources/open.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
			putValue(SHORT_DESCRIPTION, "Open an existed file");
			putValue(MNEMONIC_KEY, KeyEvent.VK_O);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {

			boolean isSaveSuccessful = true;

			if (fileSaveAction.isEnabled())
			{
				int n = JOptionPane.showConfirmDialog(
						frmMain,
						"This file was edited ?\nDo you want to discard it ?",
						"Discard Confirmation",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (n == JOptionPane.NO_OPTION) {
					File file = new File(fileName);
					if (!file.exists())
					{
						isSaveSuccessful = saveAs();
					}
					else
					{
						saveFile(file);
						fileSaveAction.setEnabled(false);
					}
				}
			}
			if (isSaveSuccessful) {
				
				frmMain.resetTextPaneDocumentListener(textPaneListener);
				
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Open");
				fc.setFileFilter(new FileNameExtensionFilter("Robotz", "robotz"));
				File workingDirectory = new File(System.getProperty("user.dir"));
				fc.setCurrentDirectory(workingDirectory);
				if (fc.showOpenDialog(frmMain) == JFileChooser.APPROVE_OPTION) {
					frmMain.setTextPaneText("");
					File file = new File(fc.getSelectedFile().getAbsolutePath());
					fileName = fc.getSelectedFile().getAbsolutePath();
					frmMain.setEditorTitle(fileName.replace(".robotz", ""));
					BufferedReader fileReader;
					try
					{
						fileReader = new BufferedReader(new FileReader(file));
						String line = null;
						while ((line = fileReader.readLine()) != null) {							
							frmMain.setAppendText(line + "\n");
						}
						fileReader.close();
						fileSaveAction.setEnabled(false);
						frmMain.repaintTextPane();
						preparation();
					}
					catch (IOException e)
					{

					}
				}
				
				frmMain.setTextPaneDocumentListener(textPaneListener);
		
			}
		}

	}

	private class FileSaveAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;

		private FileSaveAction(){
			super("Save", new ImageIcon(frmMain.getClass().getResource("resources/save.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
			putValue(SHORT_DESCRIPTION, "Save a change");
			putValue(MNEMONIC_KEY, KeyEvent.VK_S);
			setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			File file = new File(fileName);
			if (!file.exists())
			{
				saveAs();
			}
			else
			{
				saveFile(file);
				fileSaveAction.setEnabled(false);
			}
		}

	}

	private class FileSaveAsAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;

		private FileSaveAsAction(){
			super("Save As", new ImageIcon(frmMain.getClass().getResource("resources/save.png")));
			putValue(SHORT_DESCRIPTION, "Save a new file");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			saveAs();
		}

	}

	private class FileExitAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;

		private FileExitAction(){
			super("Exit", new ImageIcon(frmMain.getClass().getResource("resources/exit.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
			putValue(SHORT_DESCRIPTION, "Exit the program");
			putValue(MNEMONIC_KEY, KeyEvent.VK_X);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (!fileSaveAction.isEnabled())
				System.exit(0);
			else
			{
				int n = JOptionPane.showConfirmDialog(
						frmMain,
						"This file was edited ?\nDo you want to discard it ?",
						"Discard Confirmation",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (n == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
				else
				{
					File file = new File(fileName);
					if (!file.exists())
					{
						if (saveAs()) {
							System.exit(0);
						}
					}
					else
					{
						saveFile(file);
						System.exit(0);
					}
				}
			}
		}

	}

}
