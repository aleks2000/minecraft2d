package com.kittensvoice.minecraft2d;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Listening implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener{

	public boolean isHovered = false;

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		switch(key){
			
			case KeyEvent.VK_D:
				if(Component.isPaused == false){
					Component.isMoving = true;
					Component.dir = Component.character.movingSpeed;
				}
				break;
			
			case KeyEvent.VK_A:
				if(Component.isPaused == false){
					Component.isMoving = true;
					Component.dir = -Component.character.movingSpeed;
				}
				break;
			
			case KeyEvent.VK_SPACE:
				if(Component.isPaused == false)
					Component.isJumping = true;
				break;
			
			case KeyEvent.VK_E:
				if(Inventory.isOpen && !Component.isPaused){
					
					Inventory.isOpen = false;
				} else if(!Component.isPaused){
					Inventory.isOpen = true;
				}
				break;
			
			case KeyEvent.VK_ESCAPE:
				if(Component.escMenu){
					Component.escMenu = false;
					Component.isPaused = false;
				} else {
					Component.escMenu = true;
					Component.isPaused = true;
				}
				break;
			
			case KeyEvent.VK_B:
			
				if(Level.isBuildingBgBlock){
					Level.isBuildingBgBlock = false;
				}else{
					Level.isBuildingBgBlock = true;
				}
				break;
			}
		}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();
		
		switch(key){
			
			case KeyEvent.VK_D:
				if(Component.dir == Component.character.movingSpeed && Component.isPaused == false){
					Component.isMoving = false;
				}
				break;
			
			case KeyEvent.VK_A:
				if(Component.dir == -Component.character.movingSpeed && Component.isPaused == false){
					Component.isMoving = false;
				}
				break;
			
			case KeyEvent.VK_SPACE:
				Component.isJumping = false;
				break;

		}
	}

	public void keyTyped(KeyEvent e) {
		
	}

	
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() > 0){//Downward
			if(Inventory.selected < Tile.invLenght-1 && !Component.isPaused){
				Inventory.selected += 1;
			} else if(!Component.isPaused){
				Inventory.selected = 0;
			}
		}else if(e.getWheelRotation() < 0){//Upward
			if(Inventory.selected > 0 && !Component.isPaused){
				Inventory.selected -= 1;
			} else if(!Component.isPaused){
				Inventory.selected = Tile.invLenght-1;
			}
		}
	}

	
	public void mouseDragged(MouseEvent e) {
		Component.mse.setLocation(e.getX(), e.getY());
	}

	
	public void mouseMoved(MouseEvent e) {
		Component.mse.setLocation(e.getX(), e.getY());
		

	}

	
	public void mouseClicked(MouseEvent e) {
		
		System.out.println("X: " + e.getX() + " Y:" + e.getY());
		
		//Starting game
		if(Component.StartMenu && e.getX()>= 242 && e.getX() < 598 && e.getY() >= 281 && e.getY() <= 356){
			Component.StartMenu = false;
			Component.isPaused = false;
			
			
		}
		
		//About menu
		if(Component.StartMenu && e.getX()>= 241 && e.getX() < 597 && e.getY() >= 391 && e.getY() <= 465){
			Component.StartMenu = false;
			Component.aboutMenu = true;
		}
		
		//Back to mainMenu
			if(Component.aboutMenu && e.getX()>= 241 && e.getX() < 598 && e.getY() >= 500 && e.getY() <= 577){
				Component.aboutMenu = false;
				Component.StartMenu = true;

			}
		
		//INGAME MENU
		//Resume
		if(Component.escMenu && e.getX() >= 343 && e.getX() < 697 && e.getY() >= 143 && e.getY() < 216){
			Component.escMenu = false;
			Component.isPaused = false;

		}
		
		//Exit
		if(Component.escMenu && e.getX() >= 343 && e.getX() < 697 && e.getY() >= 269 && e.getY() < 343){
			System.exit(0);

		}


	}

	
	public void mouseEntered(MouseEvent e) {
	
	}

	
	public void mouseExited(MouseEvent e) {
		
	}

	
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			Component.isMouseLeft = true;
		}else if(e.getButton() == MouseEvent.BUTTON3){
			Component.isMouseRight = true;
		}
		
		Inventory.click(e);
	}

	
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1){
			Component.isMouseLeft = false;
		}else if(e.getButton() == MouseEvent.BUTTON3){
			Component.isMouseRight = false;
		}
	}

}
