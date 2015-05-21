package com.robotz.model;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import com.robotz.model.parsetree.Node;
import com.robotz.model.parsetree.NodeAdd;
import com.robotz.model.parsetree.NodeAssign;
import com.robotz.model.parsetree.NodeBegin;
import com.robotz.model.parsetree.NodeDo;
import com.robotz.model.parsetree.NodeMove;
import com.robotz.model.parsetree.NodeObstacle;
import com.robotz.model.parsetree.NodeRobot;
import com.robotz.model.parsetree.NodeSequence;
import com.robotz.view.AnimationJFrame;
import com.robotz.view.EditorJFrame;

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
	private Node rootNode;
	
	public ExecuteEngine(Node root, AnimationJFrame animationJFrame, EditorJFrame frmMain) {
		
		this.animationJFrame = animationJFrame;
		this.frmMain = frmMain;
		this.rootNode = root;
		
	}
	
	public void startAnimation() {
		
		Thread animationRunner = new Thread(this);
		animationRunner.start();
		
	}
	
	@Override
	public void run() {
		
		try {
			
			ReentrantLock locker = new ReentrantLock();
			
			locker.lock();
			
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
			
			
			
		}
		
		Thread.sleep(sleepTimer);
		
	}

	private void assignNodeAction(Node currentNode) {
				
		variableMap.put(((NodeAssign) currentNode).getVariableName(), ((NodeAssign) currentNode).getValue().getIntValue());
		
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
		
		maxLoop = resolveToken(doNode.getB());
		
		while (counter <= maxLoop) {
			
			treeTraversal(doNode.getNode());
			
			counter = resolveToken(doNode.getA());
			
		}
		
	}

	private void addNodeAction(Node currentNode) {
				
		int oldValue = variableMap.get(((NodeAdd) currentNode).getVariableName());
		
		variableMap.put(((NodeAdd) currentNode).getVariableName(), oldValue + ((NodeAdd) currentNode).getValue().getIntValue());
		
	}
	
	private int resolveToken(Token tk) {
		
		if (tk.getType().equals(VARIABLE_TOKEN)) {
			
			return variableMap.get(tk.getCharValue());
			
		}
		
		else {
			
			return tk.getIntValue();
			
		}
		
	}

}
