package com.kittensvoice.minecraft2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Character extends DoubleRectangle{
	public double fallingSpeed = 1.3;
	public double movingSpeed = 0.7;
	public double jumpingSpeed = 1;
	
	public int jumpingHeight = 30, jumpingCount = 0;
	public int animation = 0;
	public int animationFrame = 0, animationTime = 20;
	
	private String username;
	private int nameX = 243, nameY = 176;
	
	public boolean isJumping = false;
	//(Component.pixel.width / 2) - (width / 2), (Component.pixel.height / 2) - (height / 2)
	
	public Character(int width, int height, String username){
		setBounds((Component.pixel.width / 2) - (width / 2), (Component.pixel.height / 2) - (height / 2), width, height);
		this.username = username;
	}
	
	public void tick(){
		
		
		if(!isJumping && !isCollidingWithBlock(new Point((int) x + 2, (int) (y + height)), new Point((int) (x + width - 2), (int) (y + height))) && !Component.isPaused){
			y += fallingSpeed;
			Component.sY += fallingSpeed;
		} else {
			if(Component.isJumping  && !Inventory.isOpen){
				isJumping = true;
			}
		}
		
		if(Component.isMoving && !Inventory.isOpen){
			boolean canMove = false;
			
			if(Component.dir == movingSpeed){
				canMove = isCollidingWithBlock(new Point((int) (x + width), (int) y), new Point((int) (x + width), (int) (y + (height - 2))));
			}else if(Component.dir == -movingSpeed){
				canMove = isCollidingWithBlock(new Point((int) x - 1, (int) y), new Point((int)x - 1, (int) (y + (height - 2))));
			}
			if(!canMove){
				if(animationFrame >= animationTime){
					if(animation > 1){
						animation = 1;
					}else{
						animation += 1;
					}
					animationFrame = 0;
				} else {
					animationFrame += 1;
				}
				
				x += Component.dir;
				Component.sX += Component.dir;
			}
		} else {
			animation = 0;
		}
		
		if(isJumping){
			if(!isCollidingWithBlock(new Point((int) x + 2, (int) y), new Point((int) (x + width - 2), (int) y))){
				if(jumpingCount >= jumpingHeight){
				
					
					isJumping = false;
					jumpingCount = 0;
				} else {
					y -= jumpingSpeed;
					Component.sY -= jumpingSpeed;
					jumpingCount += 1;
				}
			}else {
				isJumping = false;
				jumpingCount = 0;
			}
		}
	}
	
	public boolean isCollidingWithBlock(Point pt1, Point pt2){
		for(int x=(int)(this.x/Tile.tileSize);x<(int)(this.x/Tile.tileSize) + 3; x++){
			for(int y = (int) (this.y / Tile.tileSize); y < (int) (this.y / Tile.tileSize) + 3; y++){
				if(x >= 0 && y >= 0 && x < Component.level.block.length && y < Component.level.block.length)
				if(Component.level.block[x][y].id != Tile.air && Component.level.block[x][y].isBgBlock == false)
				if(Component.level.block[x][y].contains(pt1) || Component.level.block[x][y].contains(pt2)){
					return true;
				}
			}
		}
		return false;
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		if(username != null){
			g.drawString(username,  nameX, nameY);
		} 
			if(Component.dir == movingSpeed){
				g.drawImage(Tile.tileset_terrain, (int) x - (int) Component.sX, (int) y - (int) Component.sY, (int) (x + width) - (int) Component.sX, (int) (y + height) - (int) Component.sY, /**/ (Tile.character[0] * Tile.tileSize) + (Tile.tileSize * animation), Tile.character[1] * Tile.tileSize, (Tile.character[0] * Tile.tileSize) + (Tile.tileSize * animation) + (int)width, Tile.character[1] * Tile.tileSize + (int)height, null);
			}else {
				g.drawImage(Tile.tileset_terrain, (int) x - (int) Component.sX, (int) y - (int) Component.sY, (int) (x + width) - (int) Component.sX, (int) (y + height) - (int) Component.sY, /**/(Tile.character[0] * Tile.tileSize) + (Tile.tileSize * animation) + (int)width,  Tile.character[1] * Tile.tileSize, (Tile.character[0] * Tile.tileSize) + (Tile.tileSize * animation),  Tile.character[1] * Tile.tileSize + (int)height, null);
			}
			
	
		}
	}
