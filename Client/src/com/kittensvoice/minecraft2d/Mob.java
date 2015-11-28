package com.kittensvoice.minecraft2d;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

public class Mob extends DoubleRectangle implements Runnable{
	
	public int[] id;
	
	public boolean isJumping = false;
	public boolean isRunning = false;
	public boolean isMoving = false;

	
	public double fallingSpeed = 1;
	public double jumpingSpeed = 1;

	public double movingSpeed = 0.4;
	public double dir = movingSpeed;
	public int jumpingHeight = 60, jumpingCount = 0;
	public int animation = 0;
	public int animationFrame = 0, animationTime = 20;
	
	public Mob(int x, int y, int width, int height, int[] id){
		setBounds(x, y, width, height);
		
		this.id = id;
		
		isRunning = true;
		new Thread(this).start();
	}

	public void tick(){
		
		//Falling
			if(Component.isPaused == false && !isJumping && !isCollidingWithBlock(new Point((int) x + 2, (int) (y + height)), new Point((int) (x + width - 2), (int) (y + height)))){
				y += fallingSpeed;
			} else {				
				if(new Random().nextInt(100) < 1){
					isMoving = true;
					
					if(new Random().nextInt(100) > 50){
						dir = -movingSpeed;
					}else{
						dir = movingSpeed;
					}
				}
			}
			
			
			if(isMoving  && Component.isPaused == false){
					boolean canMove = false;
						
					if(dir == movingSpeed){
						canMove = isCollidingWithBlock(new Point((int) (x + width), (int) y), new Point((int) (x + width), (int) (y + (height - 2))));
					}else if(dir == -movingSpeed){
						canMove = isCollidingWithBlock(new Point((int) x - 1, (int) y), new Point((int)x - 1, (int) (y + (height - 2))));
					}
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
						if(!canMove){
							x += dir;
						}
				} else {
					animation = 0;
				}

				
		
		//Jumping
		if(isJumping && Component.isPaused == false){
			if(!isCollidingWithBlock(new Point((int) x + 2, (int) y), new Point((int) (x + width - 2), (int) y))){
				if(jumpingCount >= jumpingHeight){
				
					
					isJumping = false;
					jumpingCount = 0;
				} else {
					y -= jumpingSpeed;
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
				if(Component.level.block[x][y].id != Tile.air)
				if(Component.level.block[x][y].contains(pt1) || Component.level.block[x][y].contains(pt2)){
					return true;
				}
			}
		}
		return false;
	}
	public void render(Graphics g){
		if(Component.dir == movingSpeed){
			g.drawImage(Tile.tileset_terrain, (int) x - (int) Component.sX, (int) y - (int) Component.sY, (int) (x + width) - (int) Component.sX, (int) (y + height) - (int) Component.sY, /**/ (id[0] * Tile.tileSize) + (Tile.tileSize * animation), id[1] * Tile.tileSize, (id[0] * Tile.tileSize) + (Tile.tileSize * animation) + (int)width, id[1] * Tile.tileSize + (int)height, null);
		}else {
			g.drawImage(Tile.tileset_terrain, (int) x - (int) Component.sX, (int) y - (int) Component.sY, (int) (x + width) - (int) Component.sX, (int) (y + height) - (int) Component.sY, /**/(id[0] * Tile.tileSize) + (Tile.tileSize * animation) + (int)width,  id[1] * Tile.tileSize, (id[0] * Tile.tileSize) + (Tile.tileSize * animation),  id[1] * Tile.tileSize + (int)height, null);
		}
	}

	public void run() {
		while(isRunning){
			tick();
			try{
				Thread.sleep(5);
			}catch(Exception e){}
		}
	}
}
