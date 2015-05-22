package com.robotz.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import com.robotz.view.AboutMeJFrame;
import com.robotz.view.AcknowledgementJFrame;
import com.robotz.view.OpinionJFrame;
import com.robotz.view.UserManualJFrame;

public class HelpController extends Controller {
	
	private HelpReleaseNoteAction helpReleaseNoteAction;
	private HelpShowAnIntroductionDialog helpShowAnIntroductionDialogAction;
	private HelpAboutMeAction helpAboutMeAction;
	private HelpOpinionAction helpOpinionAction;
	
	protected void initAction() {
		helpReleaseNoteAction = new HelpReleaseNoteAction();
		helpShowAnIntroductionDialogAction = new HelpShowAnIntroductionDialog();
		helpAboutMeAction = new HelpAboutMeAction();
		helpOpinionAction = new HelpOpinionAction();
	}
	
	protected void assignMenuAction() {
		frmMain.setMnuReleaseNoteAction(helpReleaseNoteAction);
		frmMain.setMnuShowIntroductionDialogAction(helpShowAnIntroductionDialogAction);
		frmMain.setMnuAboutAction(helpAboutMeAction);
		frmMain.setMnuOpinionAction(helpOpinionAction);
	}
	
	private class HelpReleaseNoteAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private HelpReleaseNoteAction(){
			super("Acknowledgement", new ImageIcon(frmMain.getClass().getResource("resources/release_note.png")));
			putValue(SHORT_DESCRIPTION, "View the acknowledgement");
			putValue(MNEMONIC_KEY, KeyEvent.VK_L);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			AcknowledgementJFrame af = new AcknowledgementJFrame();
			
			af.setLocationRelativeTo(frmMain);
			
		}
		
	}
	
	private class HelpShowAnIntroductionDialog extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private HelpShowAnIntroductionDialog(){
			super("User's Manual", new ImageIcon(frmMain.getClass().getResource("resources/introduction.png")));
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke("F1"));
			putValue(SHORT_DESCRIPTION, "View the User's manual");
			putValue(MNEMONIC_KEY, KeyEvent.VK_I);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			UserManualJFrame uf = new UserManualJFrame();
			
			uf.setLocationRelativeTo(frmMain);
			
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
			
			AboutMeJFrame af = new AboutMeJFrame();
			
			af.setLocationRelativeTo(frmMain);
			
		}
		
	}
	
	private class HelpOpinionAction extends AbstractAction {

		private static final long serialVersionUID = -957102144625612679L;
		
		private HelpOpinionAction(){
			super("My opinion on this project", new ImageIcon(frmMain.getClass().getResource("resources/about_me.png")));
			putValue(SHORT_DESCRIPTION, "View the required expertise and my opinion");
			//putValue(MNEMONIC_KEY, KeyEvent.VK_B);
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			OpinionJFrame of = new OpinionJFrame();
			
			of.setLocationRelativeTo(frmMain);
			
		}
		
	}

}
