package com.robotz.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Robot extends JPanel implements Texture {

	private static final long serialVersionUID = 1L;

	private String variableName;

	private String currentDirection;

	private final String NORTH_DIRECTION = "north";
	private final String EAST_DIRECTION = "east";
	private final String WEST_DIRECTION = "west";
	private final String SOUTH_DIRECTION = "south";

	private ImageIcon NORTH_TEXTURE;
	private ImageIcon EAST_TEXTURE;
	private ImageIcon WEST_TEXTURE;
	private ImageIcon SOUTH_TEXTURE;

	private int xPosition;
	private int yPosition;

	private int oldXPosition;
	private int oldYPosition;

	private Image background;

	private AnimationJFrame animationJFrame;

	private final String textureName = "Robot";

	// Store a ground object type for comparison
	private final String GROUND = "Ground";

	public Robot(String name, int xPosition, int yPosition, AnimationJFrame animationJFrame) {

		// Initial robot's heading direction is north

		this.variableName = name;

		this.xPosition = xPosition;

		this.yPosition = yPosition;

		this.animationJFrame = animationJFrame;

		selectRandomColor();

		background = NORTH_TEXTURE.getImage();

		setMinimumSize(AnimationJFrame.textureSize);

		setPreferredSize(AnimationJFrame.textureSize);

	}

	@Override
	public void updateSize() {

		setPreferredSize(AnimationJFrame.textureSize);

		setMinimumSize(AnimationJFrame.textureSize);

	}

	@Override
	protected void paintComponent(Graphics g) {

		Double width = AnimationJFrame.textureSize.getWidth();

		Double height = AnimationJFrame.textureSize.getHeight();

		g.drawImage(background, 0, 0, width.intValue(), height.intValue(), null);

		g.setColor(Color.WHITE);

		g.setFont(new Font(g.getFont().getName(), Font.PLAIN, calculateFontSize(width.intValue())));

		g.drawString(variableName, width.intValue() - 32, height.intValue() - 20);

		g.drawString(xPosition + "," + yPosition, width.intValue() - 32, height.intValue() - 10);

	}

	private int calculateFontSize(int width) {
		
		if (width <= 64 && width >= 54)
			return LARGE_FONT;
		
		else if (width < 54 && width >= 44)
			return MEDIUM_FONT;
		
		else if (width < 44 && width >= 32)
			return SMALL_FONT;
		
		return 0;
		
	}

	public void setDirection(String direction) {

		this.currentDirection = direction;

		if (direction.equals(NORTH_DIRECTION))
			background = NORTH_TEXTURE.getImage();

		else if (direction.equals(EAST_DIRECTION))
			background = EAST_TEXTURE.getImage();

		else if (direction.equals(WEST_DIRECTION))
			background = WEST_TEXTURE.getImage();

		else if (direction.equals(SOUTH_DIRECTION))
			background = SOUTH_TEXTURE.getImage();


	}

	public void selectRandomColor() {

		Random rand = new Random();

		int randomNum = rand.nextInt((3 - 1) + 1) + 1;

		switch (randomNum) {

		case 1 : 

			NORTH_TEXTURE = new ImageIcon(getClass().getResource("resources/robot_north_red.png"));

			EAST_TEXTURE = new ImageIcon(getClass().getResource("resources/robot_east_red.png"));

			WEST_TEXTURE = new ImageIcon(getClass().getResource("resources/robot_west_red.png"));

			SOUTH_TEXTURE = new ImageIcon(getClass().getResource("resources/robot_south_red.png"));

			break;

		case 2 : 

			NORTH_TEXTURE = new ImageIcon(getClass().getResource("resources/robot_north_yellow.png"));

			EAST_TEXTURE = new ImageIcon(getClass().getResource("resources/robot_east_yellow.png"));

			WEST_TEXTURE = new ImageIcon(getClass().getResource("resources/robot_west_yellow.png"));

			SOUTH_TEXTURE = new ImageIcon(getClass().getResource("resources/robot_south_yellow.png"));

			break;

		case 3 :

			NORTH_TEXTURE = new ImageIcon(getClass().getResource("resources/robot_north_gray.png"));

			EAST_TEXTURE = new ImageIcon(getClass().getResource("resources/robot_east_gray.png"));

			WEST_TEXTURE = new ImageIcon(getClass().getResource("resources/robot_west_gray.png"));

			SOUTH_TEXTURE = new ImageIcon(getClass().getResource("resources/robot_south_gray.png"));

			break;

		}

	}

	public void move(ArrayList<ArrayList<JPanel>> tiles) {

		if (currentDirection.equals(NORTH_DIRECTION)) {

			if (edgeDetection() && detectCollision(tiles)) {

				moveNorth(tiles);

			}
			else {

				// Avoid then, try to move again
				collisionAvoidance();
				move(tiles);

			}

		}

		else if (currentDirection.equals(WEST_DIRECTION)) {

			if (edgeDetection() && detectCollision(tiles)) {

				moveWest(tiles);

			}
			else {

				collisionAvoidance();
				move(tiles);

			}
		}

		else if (currentDirection.equals(SOUTH_DIRECTION)) {

			if (edgeDetection() && detectCollision(tiles)) {

				moveSouth(tiles);

			}
			else {

				collisionAvoidance();
				move(tiles);

			}

		}

		else if (currentDirection.equals(EAST_DIRECTION)) {

			if (edgeDetection() && detectCollision(tiles)) {

				moveEast(tiles);

			}
			else {

				collisionAvoidance();
				move(tiles);

			}

		}

	}

	private void moveNorth(ArrayList<ArrayList<JPanel>> tiles) {

		//Set the robot's position to Ground, then replace the Ground below
		tiles.get(xPosition).set(yPosition, new Ground());

		preserveOldCoordinate();
		yPosition -= 1;

		tiles.get(xPosition).set(yPosition, this);

	}

	private void moveWest(ArrayList<ArrayList<JPanel>> tiles) {

		//Set the robot's position to Ground, then replace the Ground in the right
		tiles.get(xPosition).set(yPosition, new Ground());

		preserveOldCoordinate();
		xPosition -= 1;

		tiles.get(xPosition).set(yPosition, this);

	}

	private void moveSouth(ArrayList<ArrayList<JPanel>> tiles) {

		//Set the robot's position to Ground, then replace the Ground above
		tiles.get(xPosition).set(yPosition, new Ground());

		preserveOldCoordinate();
		yPosition += 1;

		tiles.get(xPosition).set(yPosition, this);

	}

	private void moveEast(ArrayList<ArrayList<JPanel>> tiles) {

		//Set the robot's position to Ground, then replace the Ground in the left
		tiles.get(xPosition).set(yPosition, new Ground());

		preserveOldCoordinate();
		xPosition += 1;

		tiles.get(xPosition).set(yPosition, this);

	}

	private boolean detectCollision(ArrayList<ArrayList<JPanel>> tiles) {

		// Check the robot is trying to crash the obstacle or another robot
		// Return false if there is an obstacle ahead

		if (currentDirection.equals(NORTH_DIRECTION)) {

			// if the next tile isn't the Ground, then it must be an obstacle or another robot

			if (! ((Texture) tiles.get(xPosition).get(yPosition - 1)).getTextureName().equals(GROUND)) {

				return false;
			}

		}

		else if (currentDirection.equals(SOUTH_DIRECTION)) {

			if (! ((Texture) tiles.get(xPosition).get(yPosition + 1)).getTextureName().equals(GROUND)) {

				return false;
			}

		}

		else if (currentDirection.equals(WEST_DIRECTION)) {

			if (! ((Texture) tiles.get(xPosition -  1).get(yPosition)).getTextureName().equals(GROUND)) {

				return false;
			}

		}

		else if (currentDirection.equals(EAST_DIRECTION)) {

			if (! ((Texture) tiles.get(xPosition + 1).get(yPosition)).getTextureName().equals(GROUND)) {

				return false;
			}

		}

		return true;
	}

	private boolean edgeDetection() {

		// Check if the robot is trying to escape the map
		// Return false if the robot was already reached the edge of the map

		if (currentDirection.equals(NORTH_DIRECTION)) {

			if (this.yPosition == 0) {

				return false;
			}

		}

		else if (currentDirection.equals(SOUTH_DIRECTION)) {

			if (this.yPosition == animationJFrame.getMaximumY()) {

				return false;

			}

		}

		else if (currentDirection.equals(WEST_DIRECTION)) {

			if (this.xPosition == 0) {

				return false;

			}

		}

		else if (currentDirection.equals(EAST_DIRECTION)) {

			if (this.xPosition == animationJFrame.getMaximumX()) {

				return false;

			}

		}

		return true;
	}

	private void collisionAvoidance() {

		// Force rotate the robot, counter-clockwise

		if (currentDirection.equals(NORTH_DIRECTION))

			setDirection(WEST_DIRECTION);

		else if (currentDirection.equals(WEST_DIRECTION))

			setDirection(SOUTH_DIRECTION);

		else if (currentDirection.equals(SOUTH_DIRECTION))

			setDirection(EAST_DIRECTION);

		else if (currentDirection.equals(EAST_DIRECTION))

			setDirection(NORTH_DIRECTION);

	}

	private void preserveOldCoordinate() {

		oldXPosition = xPosition;
		oldYPosition = yPosition;

	}

	public String getRobotName() {

		return this.variableName;

	}

	public int getXPosition() {
		return xPosition;
	}


	public int getYPosition() {
		return yPosition;
	}


	public int getOldXPosition() {
		return oldXPosition;
	}


	public int getOldYPosition() {
		return oldYPosition;
	}

	@Override
	public String getTextureName() {

		return this.textureName;

	}

}
