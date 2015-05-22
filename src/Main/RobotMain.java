package Main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.robotz.controller.Controller;

public class RobotMain {
	
	private static String OS = System.getProperty("os.name").toLowerCase();

	public static void main(String[] args) {
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (UnsupportedLookAndFeelException e) {
		       // handle exception
		}
		catch (ClassNotFoundException e) {
		       // handle exception
		}
		catch (InstantiationException e) {
		       // handle exception
		}
		catch (IllegalAccessException e) {
		       // handle exception
		}
		new Controller();
		
	}
	
	public static String getOSName() {
		return OS;
	}

}
