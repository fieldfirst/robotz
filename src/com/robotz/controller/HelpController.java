package com.robotz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

public class HelpController extends Controller {
	
	private HelpReleaseNoteAction helpReleaseNoteAction;
	private HelpShowAnIntroductionDialog helpShowAnIntroductionDialogAction;
	private HelpAboutMeAction helpAboutMeAction;
	
	protected void initAction() {
		helpReleaseNoteAction = new HelpReleaseNoteAction();
		helpShowAnIntroductionDialogAction = new HelpShowAnIntroductionDialog();
		helpAboutMeAction = new HelpAboutMeAction();
	}
	
	protected void assignMenuAction() {
		frmMain.setMnuReleaseNoteAction(helpReleaseNoteAction);
		frmMain.setMnuShowIntroductionDialogAction(helpShowAnIntroductionDialogAction);
		frmMain.setMnuAboutAction(helpAboutMeAction);
	}
	
	private class HelpReleaseNoteAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private HelpReleaseNoteAction(){
			super("Release Note", new ImageIcon(frmMain.getClass().getResource("resources/release_note.png")));
			putValue(SHORT_DESCRIPTION, "View the release note");
			putValue(MNEMONIC_KEY, KeyEvent.VK_L);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class HelpShowAnIntroductionDialog extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private HelpShowAnIntroductionDialog(){
			super("Show the introduction dialog", new ImageIcon(frmMain.getClass().getResource("resources/introduction.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F1"));
			putValue(SHORT_DESCRIPTION, "View the introduction dialog");
			putValue(MNEMONIC_KEY, KeyEvent.VK_I);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class HelpAboutMeAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private HelpAboutMeAction(){
			super("About Me", new ImageIcon(frmMain.getClass().getResource("resources/about_me.png")));
			putValue(SHORT_DESCRIPTION, "View the developer's info");
			putValue(MNEMONIC_KEY, KeyEvent.VK_B);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
