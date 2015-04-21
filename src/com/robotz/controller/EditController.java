package com.robotz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class EditController extends Controller {
	
	private EditUndoAction editUndoAction;
	private EditRedoAction editRedoAction;
	private EditCutAction editCutAction;
	private EditCopyAction editCopyAction;
	private EditPasteAction editPasteAction;
	
	protected void initAction() {
		editUndoAction = new EditUndoAction();
		editRedoAction = new EditRedoAction();
		editCutAction = new EditCutAction();
		editCopyAction = new EditCopyAction();
		editPasteAction = new EditPasteAction();
		
		frmMain.setTextPaneCaretListener(new CaretListener(){

			@Override
			public void caretUpdate(CaretEvent arg0) {
				if (! (frmMain.getJTextPane().getSelectedText() == null)) {
					editCutAction.setEnabled(true);
					editCopyAction.setEnabled(true);
				}
				else {
					editCutAction.setEnabled(false);
					editCopyAction.setEnabled(false);
				}
			}
			
		});
	}
	
	protected void assignMenuAction() {
		frmMain.setMnuUndoAction(editUndoAction);
		frmMain.setMnuRedoAction(editRedoAction);
		frmMain.setMnuCutAction(editCutAction);
		frmMain.setMnuCopyAction(editCopyAction);
		frmMain.setMnuPasteAction(editPasteAction);
	}
	
	protected void assignToolBarAction() {
		
	}
	
	private class EditUndoAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private EditUndoAction(){
			super("Undo", new ImageIcon(frmMain.getClass().getResource("resources/undo.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl Z"));
			putValue(SHORT_DESCRIPTION, "Undo the latest change");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		}
		
	}
	
	private class EditRedoAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private EditRedoAction(){
			super("Redo", new ImageIcon(frmMain.getClass().getResource("resources/redo.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl shift Z"));
			putValue(SHORT_DESCRIPTION, "Redo the latest change");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		}
		
	}
	
	private class EditCutAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private EditCutAction(){
			super("Cut", new ImageIcon(frmMain.getClass().getResource("resources/cut.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl X"));
			putValue(SHORT_DESCRIPTION, "Cut selected content into the memory");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
			setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frmMain.textPaneCut();
		}
		
	}
	
	private class EditCopyAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private EditCopyAction(){
			super("Copy", new ImageIcon(frmMain.getClass().getResource("resources/copy.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl C"));
			putValue(SHORT_DESCRIPTION, "Copy selected content into the memory");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
			setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frmMain.textPaneCopy();
		}
		
	}
	
	private class EditPasteAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private EditPasteAction(){
			super("Paste", new ImageIcon(frmMain.getClass().getResource("resources/paste.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl V"));
			putValue(SHORT_DESCRIPTION, "Paste content from the memory");
			putValue(MNEMONIC_KEY, KeyEvent.VK_A);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frmMain.textPanePaste();
		}
		
	}

}
