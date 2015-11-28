package com.kittensvoice.minecraft2d;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;



public class Component extends Applet implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public static int pixelSize = 2;

	
	public static int moveFromBorder = 0;
	public static double sX = moveFromBorder, sY = moveFromBorder;
	public static double dir = 0;
	
	public static Dimension realSize = new Dimension();
	public static Dimension size = new Dimension(800, 600);
	public static Dimension pixel = new Dimension(size.width/pixelSize, size.height / pixelSize);
	
	public static Point mse = new Point(0, 0);
	
	public static String name = "Minecraft 2D Alpha 0.01";

	public static boolean isRunning = false;
	public static boolean isMoving = false;
	public static boolean isJumping = false;
	public static boolean isMouseLeft = false;
	public static boolean isMouseRight = false;
	public static boolean isPaused = false;
	
	private Image screen;
	
	public static Level level;
	public static Character character;
	public static Inventory inventory;
	public static Sky sky;
	public static ArrayList<Mob> mob = new ArrayList<Mob>();
	public static Listening listening;
	
	//Menu stuff

	public static boolean StartMenu = true;
	public static boolean aboutMenu = false;
	public static boolean escMenu = false;


	
	private String startGame = "StartGame";
	private String about = "About";
	private String mainMenu = "Back To Menu";
	
	//Ingame Menu
	private String exit = "Exit";
	private String resume = "Resume";

	//Sounds

	
	public Component(){
		setPreferredSize(size);
		
		addKeyListener(new Listening());
		addMouseListener(new Listening());
		addMouseMotionListener(new Listening());
		addMouseWheelListener(new Listening());

		
	
	}
	
	public void start(){
		//Defining objects etc.
		new Tile(); //Loading images.
		level = new Level();
		inventory = new Inventory();
		sky = new Sky();
		character = new Character(Tile.tileSize, Tile.tileSize * 2, null);
		listening = new Listening();
		

		//Starting game loop.
		isRunning = true;
		new Thread(this).start();


	}
	
	public void stop(){
		isRunning = false;
	}
	

	private static JFrame frame;

	public static void main(String args[]){
		Component component = new Component();
		
		frame = new JFrame();
		frame.add(component);
		frame.pack();
		
		realSize = new Dimension(frame.getWidth(), frame.getHeight());
		
		frame.setTitle(name);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		component.start();
	}
	
	public void tick(){
		
		
		
		if(aboutMenu){
			isPaused = true;
			escMenu = false;
		}
		
		
		if(StartMenu){
			isPaused = true;
			escMenu = false;
		}
		
		if(frame.getWidth() != realSize.width || frame.getHeight() != realSize.height){
			frame.pack();
		}
		
		level.tick((int) sX, (int) sY, (pixel.width / Tile.tileSize) + 2, (pixel.height / Tile.tileSize) + 2);
		sky.tick();
		inventory.tick();
		character.tick();
		
	}
	
	public void render(){
		Graphics g = screen.getGraphics();
		//Drawing things.
	
		
		
		sky.render(g);


		level.render(g, (int)sX, (int)sY, (pixel.width / Tile.tileSize) + 2, (pixel.height / Tile.tileSize) + 2);

		character.render(g);
		
		for(int i = 0; i < mob.toArray().length; i++){
			mob.get(i).render(g);
		}
		
		inventory.render(g);
		
		if(StartMenu){
			
			
			//Fills a black bg.
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(0, 0, getWidth(), getHeight());
			
			//Drawing text stuff.
			g.setFont(new Font("Verdana", 10, 40));
			g.setColor(Color.YELLOW);
			g.drawString("Main Menu", 90, 50);
			
			g.setColor(Color.WHITE);

			g.setFont(new Font("Verdana", 10, 20));
			
			//StartGame
			g.drawImage(Tile.button, 120, 140, 180, 40, null);

			g.drawString(startGame, 155, 165);

			
			//About
			g.drawImage(Tile.button, 120, 194, 180, 40, null);

			g.drawString(about, 175, 220);
			
			
			//Copyright things
			g.setFont(new Font("TimesNewRoman", 10, 9));
			g.drawString("@Copyright Aleks2000", 270, 290);
		}
		

		
		if(aboutMenu){
			//Sets the bg color.
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(0, 0, getWidth(), getHeight());
			
			//All the text.
			g.setColor(Color.WHITE);
			g.drawString("Minecraft 2D a game made by Aleks2000(Aleksander Bertelsen).", 10, 40);
			g.drawString("Use A and D to walk around, and use SPACE to jump. Use your mouse", 10, 50);
			g.drawString(" to break blocks and place them out again.", 10, 60);
			g.drawString("To open inventory you press E, and use ", 10, 90);
			g.drawString("SCROLL to select different slots.", 10, 105);
			g.drawString("CREDITS: Thanks Ulixava for his great tutorials on youtube", 10, 140);
			g.drawString("(How to make a Java 'Minecraft 2D Platformer' game?)", 10, 150);

			//Back to main menu button

			g.drawImage(Tile.button, 120, 250, 180, 40, null);
			g.setFont(new Font("Verdana", 10, 20));
			g.drawString(mainMenu, 140, 277);
			
		}
		if(escMenu){
			//Fill the rectangle
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(0, 0, 1000, 800);
			
			
			//Draw all the text stuff
			g.setColor(Color.BLACK);
			g.setFont(new Font("Verdana", 10, 50));
			g.drawString("Menu", 130, 40);
			
			g.setColor(Color.WHITE);

			g.setFont(new Font("TimesNewRoman", 10, 30));

			
			//Resume
			g.drawImage(Tile.button, 110, 70, 180, 40, null);

			g.drawString(resume, 145, 100);
			
			//Exit
			g.drawImage(Tile.button, 110, 134, 180, 40, null);
			g.drawString(exit, 175, 165);
		}
		g = getGraphics();

		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);


		
		g.dispose();

	}
	
	public void run() {
		screen = createVolatileImage(pixel.width, pixel.height);
		requestFocus();

		while(isRunning){
			tick();
			render();
			try{
				Thread.sleep(5);
			}catch(Exception e){}
		}
	}


}
