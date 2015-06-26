package com.robotz.model.interpreter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.robotz.model.parser.parsetree.Node;
import com.robotz.model.parser.parsetree.NodeAdd;
import com.robotz.model.parser.parsetree.NodeAssign;
import com.robotz.model.parser.parsetree.NodeBegin;
import com.robotz.model.parser.parsetree.NodeDo;
import com.robotz.model.parser.parsetree.NodeMove;
import com.robotz.model.parser.parsetree.NodeObstacle;
import com.robotz.model.parser.parsetree.NodeRobot;
import com.robotz.model.parser.parsetree.NodeSequence;
import com.robotz.model.tokenizer.Token;
import com.robotz.view.AnimationJFrame;
import com.robotz.view.EditorJFrame;
import com.robotz.view.ErrorDialog;

public class ExecuteEngine implements Runnable {

	private HashMap<String, Integer> variableMap = new HashMap<String, Integer>();

	private int sleepTimer = 1000;

	private final Node BEGIN_NODE = new NodeBegin();
	private final Node ROBOT_NODE = new NodeRobot();
	private final Node OBSTACLE_NODE = new NodeObstacle();
	private final Node SEQUENCE_NODE = new NodeSequence();
	private final Node ADD_NODE = new NodeAdd();
	private final Node MOVE_NODE = new NodeMove();
	private final Node ASSIGN_NODE = new NodeAssign();
	private final Node DO_NODE = new NodeDo();


	//private final String INT_TOKEN = "i";
	private final String VARIABLE_TOKEN = "v";

	private AnimationJFrame animationJFrame;
	private EditorJFrame frmMain;
	private ErrorDialog errorDialog;
	private Node rootNode;

	private Thread animationRunner;

	public ExecuteEngine(Node root, final AnimationJFrame animationJFrame, EditorJFrame frmMain, ErrorDialog errordialog) {

		this.animationJFrame = animationJFrame;
		this.frmMain = frmMain;
		this.rootNode = root;
		this.errorDialog = errordialog;

		animationJFrame.setSpeedSliderListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {

				sleepTimer = animationJFrame.getSpeedSliderValue();

			}

		});

		animationJFrame.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent we) {

				animationRunner.interrupt();

			}

		});

	}

	public void startAnimation() {

		animationRunner = new Thread(this);
		animationRunner.start();

	}

	@Override
	public void run() {

		try {

			ReentrantLock locker = new ReentrantLock();

			locker.lock();

			errorDialog.clearError();

			treeTraversal(rootNode);

			animationJFrame.addDescription("finished");

			locker.unlock();

		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	private void treeTraversal(Node currentNode) throws InterruptedException {

		// build an instruction list from parse tree

		if (currentNode.equals(BEGIN_NODE)) {

			beginNodeAction(currentNode);

		}

		else if (currentNode.equals(SEQUENCE_NODE)) {

			treeTraversal(((NodeSequence) currentNode).getANode());

			treeTraversal(((NodeSequence) currentNode).getBNode());

		}

		else if (currentNode.equals(DO_NODE)) {

			doNodeAction(currentNode);

		}

		else if (currentNode.equals(ASSIGN_NODE)) {

			assignNodeAction(currentNode);

		}

		else if (currentNode.equals(ADD_NODE)) {

			addNodeAction(currentNode);

		}

		else {

			decideAction(currentNode);

		}

	}

	private void decideAction(Node currentNode) throws InterruptedException {

		if (currentNode.equals(OBSTACLE_NODE)) {

			NodeObstacle obstacleNode = (NodeObstacle) currentNode;

			animationJFrame.addObstacle(resolveToken(obstacleNode.getxPosition()), resolveToken(obstacleNode.getyPosition()));

			animationJFrame.addDescription("Obstacle at : " + resolveToken(obstacleNode.getxPosition()) + "," + resolveToken(obstacleNode.getyPosition()));

		}

		else if (currentNode.equals(ROBOT_NODE)) {

			NodeRobot robotNode = (NodeRobot) currentNode;

			animationJFrame.addRobot(robotNode.getVariableName(), resolveToken(robotNode.getxPosition()), resolveToken(robotNode.getyPosition()));

			animationJFrame.addDescription("Robot " + robotNode.getVariableName() + " at : " + resolveToken(robotNode.getxPosition()) + "," + resolveToken(robotNode.getyPosition()));

		}

		else if (currentNode.equals(MOVE_NODE)) {

			NodeMove moveNode = (NodeMove) currentNode;

			animationJFrame.moveRobot(moveNode.getVariableName(), moveNode.getDirection().getCharValue(), resolveToken(moveNode.getDistance()), sleepTimer);

		}

		Thread.sleep(sleepTimer);

	}

	private void assignNodeAction(Node currentNode) {

		int valueToAssign = resolveToken(((NodeAssign) currentNode).getValue());

		variableMap.put(((NodeAssign) currentNode).getVariableName().getCharValue(), valueToAssign);

		frmMain.setSymbolTableValueAt(valueToAssign, ((NodeAssign) currentNode).getVariableName().getSymbolTableRow(), 2);

	}

	private void beginNodeAction(Node currentNode) throws InterruptedException {

		NodeBegin beginNode = (NodeBegin) currentNode;

		animationJFrame.initAnimation(beginNode.getMapWidth(), beginNode.getMapHeight());

		animationJFrame.addDescription("size : " + beginNode.getMapWidth() + " x " + beginNode.getMapHeight());

		treeTraversal(beginNode.getNode());

	}

	private void doNodeAction(Node currentNode) throws InterruptedException {

		int counter;

		int maxLoop;

		NodeDo doNode = (NodeDo) currentNode;

		counter = resolveToken(doNode.getA());

		int oldCounter = counter;

		maxLoop = resolveToken(doNode.getB());

		while (counter <= maxLoop) {

			treeTraversal(doNode.getNode());

			counter = resolveToken(doNode.getA());

			// update the symbol table
			frmMain.setSymbolTableValueAt(counter, doNode.getA().getSymbolTableRow(), 2);

		}

		// force reset the counter value to the initial value
		variableMap.put(doNode.getA().getCharValue(), oldCounter);

	}

	private void addNodeAction(Node currentNode) {

		int oldValue = resolveToken(((NodeAdd) currentNode).getVariableName());

		int newValue = oldValue + resolveToken(((NodeAdd) currentNode).getValue());

		variableMap.put(((NodeAdd) currentNode).getVariableName().getCharValue(), newValue);

		frmMain.setSymbolTableValueAt(newValue, ((NodeAdd) currentNode).getVariableName().getSymbolTableRow(), 2);

	}

	private int resolveToken(Token tk) {

		/* If the variable is a token, then replace with the integer value from variableMap
		 * 
		 * Or return its integer value if it is already an integer token
		 * 
		 */


		if (tk.getType().equals(VARIABLE_TOKEN)) {

			if (variableMap.containsKey(tk.getCharValue())) {

				// update the symbol table before return the int value
				frmMain.setSymbolTableValueAt(variableMap.get(tk.getCharValue()), tk.getSymbolTableRow(), 2);

				return variableMap.get(tk.getCharValue());

			}

			else {

				errorDialog.appendError("Unknow variable : " + tk.getCharValue());

				errorDialog.setVisible(true);

				errorDialog.setLocationRelativeTo(frmMain);

				Thread.currentThread().interrupt();

				return -1;

			}

		}

		else {

			return tk.getIntValue();

		}

	}

}
