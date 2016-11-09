package console_pc_client;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

public class MouseMover {

	private Robot moverBot;
	private PointerInfo pointerInfo = null;
	private Point mousePosition = null;
	
	public MouseMover(){
		
		try {
			moverBot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void moveMouse(float x, float y, float z)
	{
		pointerInfo = MouseInfo.getPointerInfo();
		mousePosition = pointerInfo.getLocation();
		
		System.out.println("   MousePosition: " + mousePosition.x + ", " + mousePosition.y);
		
		int xMove = (int) x;
		int zMove = (int) z;
		
		moverBot.mouseMove(mousePosition.x - xMove, mousePosition.y - zMove);
		
	}
}
