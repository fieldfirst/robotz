/**
 * 
 */
package com.robotz.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;

import javax.swing.Action;
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
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;

import com.robotz.controller.EditorController;

public class EditorJFrame extends JFrame {

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
	private JButton toolBarOpen;
	private JButton toolBarNew;
	private JButton toolBarSave;
	private JButton toolBarTokenize;
	private JButton toolBarCompile;
	private JButton toolBarExecute;
	
	JTabbedPane mainTabPane;
	
	public EditorJFrame(){
		setTitle("Robotz");
		setVisible(true);
		initComponent();
		setJMenuBar(mainMenu);
		setBackground(Color.WHITE);
		setSize(800, 600);
		setPreferredSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(600, 400));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	private void initComponent() {
		initMenu();
		initToolbar();
		JPanel mainPanel = initMainPanal();
		JPanel editorPanel = new JPanel(new BorderLayout());
		JPanel symbolTablePanel = new JPanel(new FlowLayout());
		JPanel animationPanel = new JPanel();
		JTextPane textPane = new JTextPane();
		LinePainter liPainter = new LinePainter(textPane);
		liPainter.setColor(new Color(243, 235, 235));
		JScrollPane scrollPane = new JScrollPane(textPane);
		TextLineNumber textLineNumber = new TextLineNumber(textPane);
		scrollPane.setRowHeaderView(textLineNumber);
		editorPanel.add(scrollPane, BorderLayout.CENTER);
		mainTabPane.addTab("Editor", editorPanel);
		mainTabPane.addTab("Symbol Table", symbolTablePanel);
		mainTabPane.addTab("Animation", animationPanel);
		mainPanel.add(mainToolBar, BorderLayout.NORTH);
		mainPanel.add(mainTabPane, BorderLayout.CENTER);
		add(mainPanel);
	}

	private JPanel initMainPanal() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainTabPane = new JTabbedPane();
		mainTabPane.setUI(new FlatTabbedPaneUI());
		mainTabPane.setBackground(new Color(0, 121, 107));
		return mainPanel;
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

	public void setMnuOpenAction(Action action) {
		mnuOpen.setAction(action);
	}

	public void setMnuNewAction(Action action) {
		mnuNew.setAction(action);
	}

	public void setMnuSaveAction(Action action) {
		mnuSave.setAction(action);
	}

	public void setMnuSaveAsAction(Action action) {
		mnuSaveAs.setAction(action);
	}

	public void setMnuExitAction(Action action) {
		mnuExit.setAction(action);
	}

	public void setMnuTokenizeAction(Action action) {
		mnuTokenize.setAction(action);
	}

	public void setMnuCompileAction(Action action) {
		mnuCompile.setAction(action);
	}

	public void setMnuExecuteAction(Action action) {
		mnuExecute.setAction(action);
	}

	public void setMnuEditorAction(Action action) {
		mnuEditor.setAction(action);
	}

	public void setMnuSymbolTableAction(Action action) {
		mnuSymbolTable.setAction(action);
	}

	public void setMnuAnimationAction(Action action) {
		mnuAnimation.setAction(action);
	}

	public void setMnuErrorAction(Action action) {
		mnuError.setAction(action);
	}

	public void setMnuReleaseNoteAction(Action action) {
		mnuReleaseNote.setAction(action);
	}

	public void setMnuShowIntroductionDialogAction(Action action) {
		mnuShowIntroductionDialog.setAction(action);
	}

	public void setMnuAboutAction(Action action) {
		mnuAbout.setAction(action);
	}
	
	public void setMnuConfigAction(Action action) {
		mnuConfig.setAction(action);
	}
	
	public void setTabIndex(int index) {
		mainTabPane.setSelectedIndex(index);
	}
	
	public int getTabIndex() {
		return mainTabPane.getSelectedIndex();
	}
	
	public void setTabChangeListener(ChangeListener listener) {
		mainTabPane.addChangeListener(listener);
	}
	
	private void initToolbar() {
		mainToolBar = new JToolBar();
		mainToolBar.setFloatable(false);
		mainToolBar.setOrientation(JToolBar.HORIZONTAL);
		String resourcesPath = getClass().getResource("resources").getPath() + "/";
		toolBarNew = createToolBarButton("New", resourcesPath + "new.png");
		toolBarOpen = createToolBarButton("Open", resourcesPath + "open.png");
		toolBarSave = createToolBarButton("Save", resourcesPath + "save.png");
		mainToolBar.add(new JToolBar.Separator());
		toolBarTokenize = createToolBarButton("Tokenize", resourcesPath + "tokenize.png");
		toolBarCompile = createToolBarButton("Compile", resourcesPath + "compile.png");
		toolBarExecute = createToolBarButton("Execute", resourcesPath + "execute.png");
	}

	private JButton createToolBarButton(String title, String iconPath) {
		JButton t = new JButton(title, new ImageIcon(iconPath));
		t.setUI(new FlatToolBarButtonUI());
		t.setVerticalTextPosition(SwingConstants.BOTTOM);
		t.setHorizontalTextPosition(SwingConstants.CENTER);
		t.setMargin(new Insets(6, 0, 0, 0));
		mainToolBar.add(t);
		return t;
	}
	
	public void setToolBarNewAction(Action action) {
		toolBarNew.setAction(action);
	}
	
	public void setToolBarOpenAction(Action action) {
		toolBarOpen.setAction(action);
	}
	
	public void setToolBarSaveAction(Action action) {
		toolBarSave.setAction(action);
	}
	
	public void setToolBarTokenizeAction(Action action) {
		toolBarTokenize.setAction(action);
	}
	
	public void setToolBarCompileAction(Action action) {
		toolBarCompile.setAction(action);
	}
	
	public void setToolBarExecute(Action action) {
		toolBarExecute.setAction(action);
	}
	
}