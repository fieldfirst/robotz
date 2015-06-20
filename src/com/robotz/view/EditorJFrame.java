
package com.robotz.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.net.URL;

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
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.CaretListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.BadLocationException;

import Main.RobotMain;

public class EditorJFrame extends JFrame {

	private static final long serialVersionUID = -2317507914722664644L;
	private JMenuBar mainMenu;
	private JMenu mnuFile;
	private JMenuItem mnuOpen;
	private JMenuItem mnuNew;
	private JMenuItem mnuSave;
	private JMenuItem mnuSaveAs;
	private JMenuItem mnuExit;

	private JMenu mnuEdit;
	private JMenuItem mnuUndo;
	private JMenuItem mnuRedo;
	private JMenuItem mnuCut;
	private JMenuItem mnuCopy;
	private JMenuItem mnuPaste;

	private JMenu mnuCommand;
	private JMenuItem mnuTokenize;
	private JMenuItem mnuCompile;
	private JMenuItem mnuExecute;

	private JMenu mnuView;
	private JMenuItem mnuEditor;
	private JMenuItem mnuSymbolTable;
	private JMenuItem mnuDerivation;
	private JMenuItem mnuAnimation;
	private JMenuItem mnuError;
	private JMenuItem mnuConfig;

	private JMenu mnuHelp;
	private JMenuItem mnuReleaseNote;
	private JMenuItem mnuShowIntroductionDialog;
	private JMenuItem mnuAbout;
	private JMenuItem mnuOpinion;

	private JToolBar mainToolBar;
	private Insets toolBarButtonInsets;
	private JButton toolBarUndo;
	private JButton toolBarRedo;
	private JButton toolBarCopy;
	private JButton toolBarPaste;
	private JButton toolBarError;
	private JButton toolBarOpen;
	private JButton toolBarNew;
	private JButton toolBarSave;
	private JButton toolBarTokenize;
	private JButton toolBarCompile;
	private JButton toolBarExecute;

	private JToolBar addToolBar;
	private JButton toolBarRobot;
	private JButton toolBarObstacle;
	private JButton toolBarAdd;
	private JButton toolBarMove;
	private JButton toolBarVariable;
	private JButton toolBarDo;

	private JTextPane textPane;
	private JTabbedPane mainTabPane;

	private SymbolTableModel symbolTableModel;
	private JTable derivationTable;
	private JTable symbolTable;
	private DerivationTableModel derivationTableModel;

	private FlatCellRenderer fcr = new FlatCellRenderer();

	public EditorJFrame(){
		osSpecific();
		setTitle("Robotz - New file");
		setVisible(true);
		initComponent();
		setJMenuBar(mainMenu);
		setBackground(Color.WHITE);
		setSize(900, 600);
		setPreferredSize(new Dimension(900, 600));
		setMinimumSize(new Dimension(900, 600));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();

	}

	private void osSpecific() {
		if (RobotMain.getOSName().contains("win")) {
			toolBarButtonInsets = new Insets(8, 18, 8, 18);
		}
		else if (RobotMain.getOSName().contains("mac")) {
			toolBarButtonInsets = new Insets(6, 0, 0, 0);
		}
	}

	private void initComponent() {
		initMenu();
		initToolbar();
		initAddToolBar();
		JPanel mainPanel = initMainPanal();
		JPanel editorPanel = new JPanel(new BorderLayout());
		editorPanel.add(initEditorPanel(), BorderLayout.CENTER);
		mainTabPane.addTab("Editor", editorPanel);
		mainTabPane.addTab("Symbol Table", initSymbolTablePanel());
		mainTabPane.addTab("Derivation", initDerivationPanel());
		mainPanel.add(mainToolBar, BorderLayout.NORTH);
		mainPanel.add(addToolBar, BorderLayout.EAST);
		mainPanel.add(mainTabPane, BorderLayout.CENTER);
		mainPanel.setBackground(Color.WHITE);
		add(mainPanel);
	}

	private JScrollPane initDerivationPanel() {
		derivationTableModel = new DerivationTableModel();
		derivationTable = new JTable(derivationTableModel);
		derivationTable.setFillsViewportHeight(true);
		derivationTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		derivationTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		derivationTable.getColumnModel().getColumn(1).setPreferredWidth(600);
		derivationTable.setDefaultRenderer(derivationTable.getModel().getColumnClass(0), fcr);
		derivationTable.setDefaultRenderer(derivationTable.getModel().getColumnClass(1), fcr);
		return new JScrollPane(derivationTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}

	public void autoResizeColumnWidth() {
		JTable table = derivationTable;
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 200; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width, width);
			}
			columnModel.getColumn(column).setPreferredWidth(width+50);
		}
	}

	private JScrollPane initSymbolTablePanel() {
		symbolTableModel = new SymbolTableModel();
		symbolTable = new JTable(symbolTableModel);
		symbolTable.setFillsViewportHeight(true);
		symbolTable.setDefaultRenderer(symbolTable.getModel().getColumnClass(0), fcr);
		symbolTable.setDefaultRenderer(symbolTable.getModel().getColumnClass(1), fcr);
		symbolTable.setDefaultRenderer(symbolTable.getModel().getColumnClass(2), fcr);
		return new JScrollPane(symbolTable);
	}

	private JScrollPane initEditorPanel() {
		textPane = new JTextPane();
		textPane.setText("begin i j\n\nhalt");
		LinePainter liPainter = new LinePainter(textPane);
		liPainter.setColor(new Color(243, 235, 235));
		JScrollPane scrollPane = new JScrollPane(textPane);
		TextLineNumber textLineNumber = new TextLineNumber(textPane);
		scrollPane.setRowHeaderView(textLineNumber);
		return scrollPane;
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
		initEditMenu();
		initCommandMenu();
		initViewMenu();
		initHelpMenu();
		addToMenuBar();
	}

	private void addToMenuBar() {
		mainMenu.add(mnuFile);
		mainMenu.add(mnuEdit);
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

	private void initEditMenu() {
		mnuEdit = new JMenu("Edit");
		mnuEdit.setMnemonic(KeyEvent.VK_E);
		mnuUndo = new JMenuItem();
		mnuRedo = new JMenuItem();
		mnuCut = new JMenuItem();
		mnuCopy = new JMenuItem();
		mnuPaste = new JMenuItem();
		mnuEdit.add(mnuUndo);
		mnuEdit.add(mnuRedo);
		mnuEdit.add(new JSeparator());
		mnuEdit.add(mnuCut);
		mnuEdit.add(mnuCopy);
		mnuEdit.add(mnuPaste);
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
		mnuDerivation = new JMenuItem();
		mnuAnimation = new JMenuItem();
		mnuError = new JMenuItem();
		mnuConfig = new JMenuItem();
		mnuView.add(mnuEditor);
		mnuView.add(mnuSymbolTable);
		mnuView.add(mnuDerivation);
		mnuView.add(mnuAnimation);
		mnuView.add(new JSeparator());
		mnuView.add(mnuError);
		//mnuView.add(new JSeparator());
		//mnuView.add(mnuConfig);
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
		mnuOpinion = new JMenuItem();
		mnuHelp.add(mnuOpinion);
	}

	private void initToolbar() {
		mainToolBar = new JToolBar();
		mainToolBar.setBackground(Color.WHITE);
		mainToolBar.setFloatable(false);
		mainToolBar.setOrientation(JToolBar.HORIZONTAL);
		toolBarNew = createToolBarButton("New", getClass().getResource("resources/new.png"));
		toolBarOpen = createToolBarButton("Open", getClass().getResource("resources/open.png"));
		toolBarSave = createToolBarButton("Save", getClass().getResource("resources/save.png"));
		mainToolBar.add(new JToolBar.Separator());
		toolBarUndo = createToolBarButton("Undo", getClass().getResource("resources/undo.png"));
		toolBarRedo = createToolBarButton("Redo", getClass().getResource("resources/redo.png"));
		toolBarCopy = createToolBarButton("Copy", getClass().getResource("resources/copy.png"));
		toolBarPaste = createToolBarButton("Paste", getClass().getResource("resources/paste.png"));
		toolBarError = createToolBarButton("Error", getClass().getResource("resources/error_toolbar.png"));
		mainToolBar.add(new JToolBar.Separator());
		toolBarTokenize = createToolBarButton("Tokenize", getClass().getResource("resources/tokenize.png"));
		toolBarCompile = createToolBarButton("Compile", getClass().getResource("resources/compile.png"));
		toolBarExecute = createToolBarButton("Execute", getClass().getResource("resources/execute.png"));
	}

	private JButton createToolBarButton(String title, URL iconPath) {
		JButton t = new JButton(title, new ImageIcon(iconPath));
		t.setUI(new FlatToolBarButtonUI());
		t.setVerticalTextPosition(SwingConstants.BOTTOM);
		t.setHorizontalTextPosition(SwingConstants.CENTER);
		t.setMargin(toolBarButtonInsets);
		t.setBackground(Color.WHITE);
		mainToolBar.add(t);
		return t;
	}

	private void initAddToolBar() {
		addToolBar = new JToolBar(null, JToolBar.VERTICAL);
		addToolBar.setBackground(Color.WHITE);
		addToolBar.setFloatable(false);
		addToolBar.setLayout(new GridLayout(6, 1));
		toolBarRobot = createAddToolBarButton("Robot", getClass().getResource("resources/robot.png"));
		toolBarObstacle = createAddToolBarButton("Obstacle.", getClass().getResource("resources/obstacle.png"));
		toolBarAdd = createAddToolBarButton("Add", getClass().getResource("resources/add.png"));
		toolBarMove = createAddToolBarButton("Move", getClass().getResource("resources/move.png"));
		toolBarVariable = createAddToolBarButton("Variable", getClass().getResource("resources/variable.png"));
		toolBarDo = createAddToolBarButton("Loop", getClass().getResource("resources/do.png"));
	}

	private JButton createAddToolBarButton(String title, URL iconPath) {
		JButton t = new JButton(title, new ImageIcon(iconPath));
		t.setUI(new VerticalFlatToolBarButtonUI());
		t.setVerticalTextPosition(SwingConstants.BOTTOM);
		t.setHorizontalTextPosition(SwingConstants.CENTER);
		t.setMargin(toolBarButtonInsets);
		t.setBackground(Color.WHITE);
		addToolBar.add(t);
		return t;
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

	public void setMnuUndoAction(Action action) {
		mnuUndo.setAction(action);
	}

	public void setMnuRedoAction(Action action) {
		mnuRedo.setAction(action);
	}

	public void setMnuCutAction(Action action) {
		mnuCut.setAction(action);
	}

	public void setMnuCopyAction(Action action) {
		mnuCopy.setAction(action);
	}

	public void setMnuPasteAction(Action action) {
		mnuPaste.setAction(action);
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

	public void setMnuDerivationAction(Action action) {
		mnuDerivation.setAction(action);
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

	public void setMnuOpinionAction(Action action) {
		mnuOpinion.setAction(action);
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

	public void setToolBarRobot(Action action) {
		toolBarRobot.setAction(action);
	}

	public void setToolBarObstacle(Action action) {
		toolBarObstacle.setAction(action);
	}

	public void setToolBarAdd(Action action) {
		toolBarAdd.setAction(action);
	}

	public void setToolBarMove(Action action) {
		toolBarMove.setAction(action);
	}

	public void setToolBarVariable(Action action) {
		toolBarVariable.setAction(action);
	}

	public void setToolBarDo(Action action) {
		toolBarDo.setAction(action);
	}

	public void setToolBarError(Action action) {
		toolBarError.setAction(action);
	}

	public void setToolBarUndo(Action action) {
		toolBarUndo.setAction(action);
	}

	public void setToolBarRedo(Action action) {
		toolBarRedo.setAction(action);
	}

	public void setToolBarCopy(Action action) {
		toolBarCopy.setAction(action);
	}

	public void setToolBarPaste(Action action) {
		toolBarPaste.setAction(action);
	}

	public String getTextPaneText() {
		String allText = null;
		try {
			allText = textPane.getDocument().getText(0, textPane.getDocument().getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		return allText;
	}

	public void setTextPaneText(String text) {
		textPane.setText(text);
	}

	public void setTextPaneDocumentListener(DocumentListener listener) {
		textPane.getDocument().addDocumentListener(listener);
	}

	public void setInsertText(String text) {
		try {
			textPane.getDocument().insertString(textPane.getCaretPosition(), text, null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setAppendText(String line) {
		textPane.setText(textPane.getText() + line);
	}

	public void repaintTextPane() {
		textPane.repaint(10);
	}

	public void addTokenizeItem(String type, String chValue, int intValue) {
		symbolTableModel.addItem(type, chValue, intValue);
	}

	public void clearTokenizedItem() {
		symbolTableModel.clearTable();
	}

	public void setEditorTitle(String fileName) {
		setTitle("Robotz - " + fileName);
	}

	public void addDerivationItem(String step, String derivation) {
		derivationTableModel.addItem(step, derivation);
	}

	public void clearDerivationItem() {
		derivationTableModel.clearTable();
	}

	public void textPanePaste() {
		textPane.paste();
	}

	public void textPaneCut() {
		textPane.cut();
	}

	public void textPaneCopy() {
		textPane.copy();
	}

	public void setTextPaneCaretListener(CaretListener listener) {
		textPane.addCaretListener(listener);
	}

	public JTextPane getJTextPane() {
		return textPane;
	}

	public Object getSymbolTableValueAt(int row, int col) {
		return symbolTableModel.getValueAt(row, col);
	}

	public void setSymbolTableValueAt(Object value, int row, int col) {

		fcr.setUpdatedRow(row);
		symbolTableModel.setValueAt(value, row, col);
		symbolTable.repaint();

	}

	public void resetTextPaneDocumentListener(DocumentListener listener) {
		textPane.getDocument().removeDocumentListener(listener);
	}

	public int getCurrentSymbolTableRow() {
		return symbolTableModel.getRowCount() - 1;
	}

}