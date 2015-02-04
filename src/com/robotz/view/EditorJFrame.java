/**
 * 
 */
package com.robotz.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

public class EditorJFrame extends JFrame implements EditorView {

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
	
	private JToolBar mainToolBar;
	
	public EditorJFrame(){
		setTitle("Robotz");
		setVisible(true);
		initComponent();
		setJMenuBar(mainMenu);
		setBackground(new Color(40, 44, 49));
		setSize(800, 600);
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(600, 400));
		pack();
	}
	
	private void initComponent() {
		initMenu();
		JPanel mainPanel = new JPanel(new BorderLayout());
		JTabbedPane mainTabPane = new JTabbedPane();
		JPanel editorPanel = new JPanel(new BorderLayout());
		JPanel symbolTablePanel = new JPanel();
		JPanel animationPanel = new JPanel();
		JTextPane textPane = new JTextPane();
		JScrollPane scrollPane = new JScrollPane(textPane);
		TextLineNumber textLineNumber = new TextLineNumber(textPane);
		scrollPane.setRowHeaderView(textLineNumber);
		editorPanel.add(scrollPane, BorderLayout.CENTER);
		mainTabPane.addTab("Editor", editorPanel);
		mainTabPane.addTab("Symbol Table", symbolTablePanel);
		mainTabPane.addTab("Animation", animationPanel);
		mainToolBar = new JToolBar();
		mainPanel.add(mainToolBar, BorderLayout.NORTH);
		mainPanel.add(mainTabPane, BorderLayout.CENTER);
		add(mainPanel);
	}
	
	private void initMenu() {
		mainMenu = new JMenuBar();
		initFileMenu();
		initCommandMenu();
		initViewMenu();
		initHelpMenu();
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
		mnuOpen = new JMenuItem();
		mnuNew = new JMenuItem();
		mnuSave = new JMenuItem();
		mnuSaveAs = new JMenuItem();
		mnuExit = new JMenuItem();
		mnuFile.add(mnuOpen);
		mnuFile.add(mnuNew);
		mnuFile.add(new JSeparator());
		mnuFile.add(mnuSave);
		mnuFile.add(mnuSaveAs);
		mnuFile.add(new JSeparator());
		mnuFile.add(mnuExit);
	}
	
	private void initCommandMenu() {
		mnuCommand = new JMenu("Command");
		mnuCommand.setMnemonic(KeyEvent.VK_C);
		mnuTokenize = new JMenuItem();
		mnuCompile = new JMenuItem();
		mnuExecute = new JMenuItem();
		mnuCommand.add(mnuTokenize);
		mnuCommand.add(mnuCompile);
		mnuCommand.add(mnuExecute);
	}
	
	private void initViewMenu() {
		mnuView = new JMenu("View");
		mnuView.setMnemonic(KeyEvent.VK_V);
		mnuEditor = new JMenuItem();
		mnuSymbolTable = new JMenuItem();
		mnuAnimation = new JMenuItem();
		mnuError = new JMenuItem();
		mnuConfig = new JMenuItem();
		mnuView.add(mnuEditor);
		mnuView.add(mnuSymbolTable);
		mnuView.add(mnuAnimation);
		mnuView.add(new JSeparator());
		mnuView.add(mnuError);
		mnuView.add(new JSeparator());
		mnuView.add(mnuConfig);
	}
	
	private void initHelpMenu() {
		mnuHelp = new JMenu("Help");
		mnuHelp.setMnemonic(KeyEvent.VK_H);
		mnuReleaseNote = new JMenuItem();
		mnuShowIntroductionDialog = new JMenuItem();
		mnuAbout = new JMenuItem();
		mnuHelp.add(mnuReleaseNote);
		mnuHelp.add(mnuShowIntroductionDialog);
		mnuHelp.add(new JSeparator());
		mnuHelp.add(mnuAbout);
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
	
	public JMenuItem getMnuConfig() {
		return mnuConfig;
	}
	
	public JToolBar getMainToolBar() {
		return mainToolBar;
	}

	@Override
	public void setController() {
		
	}

}