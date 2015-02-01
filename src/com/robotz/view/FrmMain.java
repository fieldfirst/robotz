/**
 * 
 */
package com.robotz.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class FrmMain extends JFrame {

	private static final long serialVersionUID = -2317507914722664644L;
	private JMenuBar mainMenu;
	private JMenu mnuFile;
	private JMenuItem mnuOpen;
	private JMenuItem mnuNew;
	private JMenuItem mnuSave;
	private JMenuItem mnuSaveAs;
	private JMenuItem mnuExit;
	
	private JMenu mnuCommand;
	private JMenuItem mnuTokenize;
	private JMenuItem mnuCompile;
	private JMenuItem mnuExecute;
	
	private JMenu mnuView;
	private JMenuItem mnuEditor;
	private JMenuItem mnuSymbolTable;
	private JMenuItem mnuAnimation;
	private JMenuItem mnuError;
	private JMenuItem mnuConfig;
	
	private JMenu mnuHelp;
	private JMenuItem mnuReleaseNote;
	private JMenuItem mnuShowIntroductionDialog;
	private JMenuItem mnuAbout;
	
	private JPanel mainPanel;
	private JToolBar mainToolbar;
	
	public FrmMain(){
		setTitle("Robotz");
		setSize(500, 500);
		setVisible(true);
		initComponent();
		setJMenuBar(mainMenu);
		setBackground(new Color(40, 44, 49));
		setPreferredSize(new Dimension(500, 500));
		pack();
	}
	
	private void initComponent() {
		initMenu();
		mainPanel = new JPanel(new BorderLayout());
		JTextArea textPane = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textPane);
		TextLineNumber tln = new TextLineNumber(textPane);
		scrollPane.setRowHeaderView(tln);
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		// mainTab.addTab("test1", a);
		// mainTab.addTab("test2", new JPanel());
		// mainPanel.add(mainTab, BorderLayout.CENTER);
		mainToolbar = new JToolBar();
		mainToolbar.add(new JButton("test"));
		mainPanel.add(mainToolbar, BorderLayout.NORTH);
		add(mainPanel);
	}
	
	private void initMenu() {
		mainMenu = new JMenuBar();
		initFileMenu();
		initCommandMenu();
		initViewMenu();
		initHelpMenu();
		initMenuIcon();
		addToMenuBar();
	}
	
	private void addToMenuBar() {
		mainMenu.add(mnuFile);
		mainMenu.add(mnuCommand);
		mainMenu.add(mnuView);
		mainMenu.add(mnuHelp);
	}
	
	private void initFileMenu() {
		mnuFile = new JMenu("File");
		mnuFile.setMnemonic(KeyEvent.VK_F);
		mnuOpen = new JMenuItem("Open", KeyEvent.VK_O);
		mnuNew = new JMenuItem("New", KeyEvent.VK_N);
		mnuSave = new JMenuItem("Save", KeyEvent.VK_S);
		mnuSaveAs = new JMenuItem("Save As", KeyEvent.VK_A);
		mnuExit = new JMenuItem("Exit", KeyEvent.VK_X);
		mnuFile.add(mnuOpen);
		mnuFile.add(mnuNew);
		mnuFile.add(new JSeparator());
		mnuFile.add(mnuSave);
		mnuFile.add(mnuSaveAs);
		mnuFile.add(new JSeparator());
		mnuFile.add(mnuExit);
		
		// Add the accelerator
		mnuOpen.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
		mnuNew.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		mnuSave.setAccelerator(KeyStroke.getKeyStroke("ctrl S"));
		mnuExit.setAccelerator(KeyStroke.getKeyStroke("alt F4"));
	}
	
	private void initCommandMenu() {
		mnuCommand = new JMenu("Command");
		mnuCommand.setMnemonic(KeyEvent.VK_C);
		mnuTokenize = new JMenuItem("Tokenize", KeyEvent.VK_T);
		mnuCompile = new JMenuItem("Compile", KeyEvent.VK_P);
		mnuExecute = new JMenuItem("Execute", KeyEvent.VK_E);
		mnuCommand.add(mnuTokenize);
		mnuCommand.add(mnuCompile);
		mnuCommand.add(mnuExecute);
		
		// Add the accelerator
		mnuTokenize.setAccelerator(KeyStroke.getKeyStroke("ctrl T"));
		mnuCompile.setAccelerator(KeyStroke.getKeyStroke("ctrl alt C"));
		mnuExecute.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));
	}
	
	private void initViewMenu() {
		mnuView = new JMenu("View");
		mnuView.setMnemonic(KeyEvent.VK_V);
		mnuEditor = new JMenuItem("Editor", KeyEvent.VK_D);
		mnuSymbolTable = new JMenuItem("Symbol Table", KeyEvent.VK_Y);
		mnuAnimation = new JMenuItem("Animation", KeyEvent.VK_M);
		mnuError = new JMenuItem("Error", KeyEvent.VK_R);
		mnuConfig = new JMenuItem("Configuration", KeyEvent.VK_G);
		mnuView.add(mnuEditor);
		mnuView.add(mnuSymbolTable);
		mnuView.add(mnuAnimation);
		mnuView.add(new JSeparator());
		mnuView.add(mnuError);
		mnuView.add(new JSeparator());
		mnuView.add(mnuConfig);
		
		// Add the accelerator
		mnuEditor.setAccelerator(KeyStroke.getKeyStroke("ctrl alt E"));
		mnuSymbolTable.setAccelerator(KeyStroke.getKeyStroke("ctrl alt S"));
		mnuAnimation.setAccelerator(KeyStroke.getKeyStroke("ctrl alt A"));
		mnuError.setAccelerator(KeyStroke.getKeyStroke("ctrl E"));
	}
	
	private void initHelpMenu() {
		mnuHelp = new JMenu("Help");
		mnuHelp.setMnemonic(KeyEvent.VK_H);
		mnuReleaseNote = new JMenuItem("Release Note", KeyEvent.VK_L);
		mnuShowIntroductionDialog = new JMenuItem("Show an Introduction dialog", KeyEvent.VK_I);
		mnuAbout = new JMenuItem("About Me", KeyEvent.VK_B);
		mnuHelp.add(mnuReleaseNote);
		mnuHelp.add(mnuShowIntroductionDialog);
		mnuHelp.add(new JSeparator());
		mnuHelp.add(mnuAbout);
		
		// Add the accelerator
		mnuShowIntroductionDialog.setAccelerator(KeyStroke.getKeyStroke("F1"));
	}
	
	private void initMenuIcon() {
		mnuOpen.setIcon(new ImageIcon("./resources/open.png"));
		mnuNew.setIcon(new ImageIcon("./resources/new.png"));
		mnuSave.setIcon(new ImageIcon("./resources/save.png"));
		mnuSaveAs.setIcon(new ImageIcon("./resources/save.png"));
		mnuExit.setIcon(new ImageIcon("./resources/exit.png"));
		mnuTokenize.setIcon(new ImageIcon("./resources/tokenize.png"));
		mnuCompile.setIcon(new ImageIcon("./resources/compile.png"));
		mnuExecute.setIcon(new ImageIcon("./resources/execute.png"));
		mnuEditor.setIcon(new ImageIcon("./resources/editor.png"));
		mnuSymbolTable.setIcon(new ImageIcon("./resources/symbol_table.png"));
		mnuAnimation.setIcon(new ImageIcon("./resources/animation.png"));
		mnuError.setIcon(new ImageIcon("./resources/error_toolbar.png"));
		mnuConfig.setIcon(new ImageIcon("./resources/configuration.png"));
		mnuReleaseNote.setIcon(new ImageIcon("./resources/release_note.png"));
		mnuShowIntroductionDialog.setIcon(new ImageIcon("./resources/introduction.png"));
		mnuAbout.setIcon(new ImageIcon("./resources/about_me.png"));
	}

	public JMenuBar getMainMenu() {
		return mainMenu;
	}

	public JMenu getMnuFile() {
		return mnuFile;
	}

	public JMenuItem getMnuOpen() {
		return mnuOpen;
	}

	public JMenuItem getMnuNew() {
		return mnuNew;
	}

	public JMenuItem getMnuSave() {
		return mnuSave;
	}

	public JMenuItem getMnuSaveAs() {
		return mnuSaveAs;
	}

	public JMenuItem getMnuExit() {
		return mnuExit;
	}

	public JMenu getMnuCommand() {
		return mnuCommand;
	}

	public JMenuItem getMnuTokenize() {
		return mnuTokenize;
	}

	public JMenuItem getMnuCompile() {
		return mnuCompile;
	}

	public JMenuItem getMnuExecute() {
		return mnuExecute;
	}

	public JMenu getMnuView() {
		return mnuView;
	}

	public JMenuItem getMnuEditor() {
		return mnuEditor;
	}

	public JMenuItem getMnuSymbolTable() {
		return mnuSymbolTable;
	}

	public JMenuItem getMnuAnimation() {
		return mnuAnimation;
	}

	public JMenuItem getMnuError() {
		return mnuError;
	}

	public JMenu getMnuHelp() {
		return mnuHelp;
	}

	public JMenuItem getMnuReleaseNote() {
		return mnuReleaseNote;
	}

	public JMenuItem getMnuShowWelcomeDialog() {
		return mnuShowIntroductionDialog;
	}

	public JMenuItem getMnuAbout() {
		return mnuAbout;
	}

}