package com.robotz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ViewController extends Controller {
	
	private ViewEditorAction viewEditorAction;
	private ViewSymbolTableAction viewSymbolTableAction;
	private ViewDerivationAction viewDerivationAction;
	private ViewAnimationAction viewAnimationAction;
	private ViewConfigurationAction viewConfigurationAction;
	private ViewErrorAction viewErrorAction;
	
	protected void initAction() {
		viewEditorAction = new ViewEditorAction();
		viewSymbolTableAction = new ViewSymbolTableAction();
		viewDerivationAction = new ViewDerivationAction();
		viewAnimationAction = new ViewAnimationAction();
		viewConfigurationAction = new ViewConfigurationAction();
		viewErrorAction = new ViewErrorAction();
		
		frmMain.setTabChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent arg0) {
				if (frmMain.getTabIndex() == 0) {
					viewEditorAction.setEnabled(false);
					viewSymbolTableAction.setEnabled(true);
					viewDerivationAction.setEnabled(true);
					viewAnimationAction.setEnabled(true);
				}
				else if (frmMain.getTabIndex() == 1) {
					viewEditorAction.setEnabled(true);
					viewSymbolTableAction.setEnabled(false);
					viewDerivationAction.setEnabled(true);
					viewAnimationAction.setEnabled(true);
				}
				else if (frmMain.getTabIndex() == 2) {
					viewEditorAction.setEnabled(true);
					viewSymbolTableAction.setEnabled(true);
					viewDerivationAction.setEnabled(false);
					viewAnimationAction.setEnabled(true);
				}
			}
		});
	}
	
	protected void assignMenuAction() {
		frmMain.setMnuEditorAction(viewEditorAction);
		frmMain.setMnuDerivationAction(viewDerivationAction);
		frmMain.setMnuSymbolTableAction(viewSymbolTableAction);
		frmMain.setMnuAnimationAction(viewAnimationAction);
		frmMain.setMnuErrorAction(viewErrorAction);
		frmMain.setMnuConfigAction(viewConfigurationAction);
	}
	
	protected void assignToolBarAction() {
		frmMain.setToolBarError(viewErrorAction);
	}
	
	private class ViewEditorAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewEditorAction(){
			super("Editor", new ImageIcon(frmMain.getClass().getResource("resources/editor.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt E"));
			putValue(SHORT_DESCRIPTION, "Switch to editor");
			putValue(MNEMONIC_KEY, KeyEvent.VK_D);
			setEnabled(false);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frmMain.setTabIndex(0);
			setEnabled(false);
			viewSymbolTableAction.setEnabled(true);
			viewDerivationAction.setEnabled(true);
			viewAnimationAction.setEnabled(true);
		}
		
	}
	
	private class ViewSymbolTableAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewSymbolTableAction(){
			super("Symbol Table", new ImageIcon(frmMain.getClass().getResource("resources/symbol_table.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt S"));
			putValue(SHORT_DESCRIPTION, "View a symbol table");
			putValue(MNEMONIC_KEY, KeyEvent.VK_Y);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frmMain.setTabIndex(1);
			setEnabled(false);
			viewEditorAction.setEnabled(true);
			viewDerivationAction.setEnabled(true);
			viewAnimationAction.setEnabled(true);
		}
		
	}
	
	private class ViewDerivationAction extends AbstractAction {
		
		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewDerivationAction(){
			super("Derivation", new ImageIcon(frmMain.getClass().getResource("resources/derivation_table.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl D"));
			putValue(SHORT_DESCRIPTION, "View a derivation table");
			putValue(MNEMONIC_KEY, KeyEvent.VK_D);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			frmMain.setTabIndex(2);
			setEnabled(false);
			viewEditorAction.setEnabled(true);
			viewSymbolTableAction.setEnabled(true);
			viewAnimationAction.setEnabled(true);
		}
	}
	
	private class ViewAnimationAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewAnimationAction(){
			super("Animation", new ImageIcon(frmMain.getClass().getResource("resources/animation.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl alt A"));
			putValue(SHORT_DESCRIPTION, "View an animation");
			putValue(MNEMONIC_KEY, KeyEvent.VK_M);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			animationDialog.setVisible(true);
			animationDialog.setLocationRelativeTo(frmMain);
			
		}
		
	}
	
	private class ViewErrorAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewErrorAction(){
			super("Error", new ImageIcon(frmMain.getClass().getResource("resources/error_toolbar.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
			putValue(SHORT_DESCRIPTION, "Show an error");
			putValue(MNEMONIC_KEY, KeyEvent.VK_R);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			errorDialog.setVisible(true);
			errorDialog.setLocationRelativeTo(frmMain);
		}
		
	}
	
	private class ViewConfigurationAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private ViewConfigurationAction(){
			super("Configuration", new ImageIcon(frmMain.getClass().getResource("resources/configuration.png")));
			putValue(SHORT_DESCRIPTION, "View the configuation panel");
			putValue(MNEMONIC_KEY, KeyEvent.VK_G);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
