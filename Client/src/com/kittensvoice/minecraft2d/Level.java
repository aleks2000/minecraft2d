package com.kittensvoice.minecraft2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class Level {
	public int worldW = 200, worldH = 200;
	public Block[][] block = new Block[worldW][worldH];
	public static int blockBreakTimer = 0;
	public static int blockBreakTime = 0;
	
	public static int TNT_BreakTimer = 0;
	public static int TNT_BreakTime = 0;
	
	private String BuldingBgText;
		
	public static boolean isBuildingBgBlock = false;
	

	public Level() {
		for (int x = 0; x < block.length; x++) {
			for (int y = 0; y < block[0].length; y++) {
				block[x][y] = new Block(new Rectangle(x * Tile.tileSize, y * Tile.tileSize, Tile.tileSize, Tile.tileSize), Tile.air);
			}
		}

		
		generateLevel();
	}
	
	

	public void generateLevel(){
		//Generating world
		for (int y = 0; y < block.length;y++) {
				for (int x = 0; x < block[0].length; x++) {
				if(y > worldH / 16){
					
					if(new Random().nextInt(worldH)<worldW){
						block[x][y].id = Tile.dirt;
					}
					if(y > worldH / 8){

						if(new Random().nextInt(worldH)<=200){
							block[x][y].id = Tile.stone;
						}
					}
				}
			}
		}
		//Placing out iron blocks
				for (int y = 0; y < block.length;y++) {
					for (int x = 0; x < block[0].length; x++) {
						try{
							if(block[x][y].id == Tile.stone){
								if(new Random().nextInt(150) <= 0.7){
									block[x][y].id = Tile.ironOre;
									block[x+1][y].id = Tile.ironOre;
									block[x][y+1].id = Tile.ironOre;
									block[x+1][y+1].id = Tile.ironOre;
									block[x-1][y+1].id = Tile.ironOre;

								}
							}
						}catch(Exception e){}
					}
				}
				
				
		//Placing out coal
				for (int y = 0; y < block.length;y++) {
					for (int x = 0; x < block[0].length; x++) {
						try{
							if(block[x][y].id == Tile.stone){
								if(new Random().nextInt(200) <= 0.02){
									block[x][y].id = Tile.coalOre;
									block[x+1][y].id = Tile.coalOre;
									block[x][y+1].id = Tile.coalOre;
									block[x+1][y+1].id = Tile.coalOre;
									block[x-1][y+1].id = Tile.coalOre;
									block[x+1][y-1].id = Tile.coalOre;
									block[x-1][y-1].id = Tile.coalOre;

								}
							}
						}catch(Exception e){}
					}
				}
				
			//Placing out gold
				for (int y = 0; y < block.length;y++) {
					for (int x = 0; x < block[0].length; x++) {
						try{
							if(block[x][y].id == Tile.stone){
								if(new Random().nextInt(1000) <= 0.1){
									block[x][y].id = Tile.goldOre;
									block[x+1][y].id = Tile.goldOre;
									block[x][y+1].id = Tile.goldOre;
								}
							}
						}catch(Exception e){}
					}
				}
		//Placing out diamond
				for (int y = 0; y < block.length;y++) {
					for (int x = 0; x < block[0].length; x++) {
						try{
							if(block[x][y].id == Tile.stone){
								if(new Random().nextInt(1000) <= 0.1){
									block[x][y].id = Tile.diamondOre;
									block[x][y+1].id = Tile.diamondOre;
									block[x+1][y+2].id = Tile.diamondOre;

								}
							}
						}catch(Exception e){}
					}
				}
		
		//Placing out grass blocks
		for (int y = 0; y < block.length;y++) {
			for (int x = 0; x < block[0].length; x++) {
				if(block[x][y].id == Tile.dirt && block[x][y-1].id == Tile.air){
					block[x][y].id = Tile.grass;
				}
			}
		}

		for (int y = 0; y < block.length;y++) {
			for (int x = 0; x < block[0].length; x++) {
				
				if(x == 0 || y == 0 || x == block[0].length-1 || y == block.length-1){
					block[x][y].id = Tile.solidAir;
				}
			}
		}
	}
	
	public void building(int camX, int camY, int renW, int renH){
		if(Component.isMouseLeft || Component.isMouseRight){
			for (int x = (camX / Tile.tileSize); x <(camX / Tile.tileSize) + renW; x++) {
				for (int y = (camY / Tile.tileSize); y < (camY / Tile.tileSize) + renH; y++) {
						if(x >= 0 && y >= 0 && x < worldW && y < worldH){
							if(block[x][y].contains(new Point((Component.mse.x  / Component.pixelSize) + (int)Component.sX, (Component.mse.y  / Component.pixelSize) +(int) Component.sY))){
								int sid[] = Inventory.invBar[Inventory.selected].id;
								
								
								if(Component.isMouseLeft){
					
									
									
									if(block[x][y].id != Tile.solidAir){
										if(block[x][y].id != Tile.air){
												
											
											if(block[x][y].id == Tile.tnt && TNT_BreakTime <= TNT_BreakTimer){
												try{
													block[x][y].id = Tile.air;
													block[x+1][y].id = Tile.air;
													block[x-1][y].id = Tile.air;
													block[x][y+1].id = Tile.air;
													block[x][y-1].id = Tile.air;
													block[x+1][y+1].id = Tile.air;
													block[x-1][y+1].id = Tile.air;
													block[x+1][y-1].id = Tile.air;
													block[x-1][y-1].id = Tile.air;

												}catch(Exception e){}
												
												TNT_BreakTimer = 0;
											}else{
												TNT_BreakTimer++;
											}
												
											if( block[x][y].id == Tile.coalOre || block[x][y].id == Tile.ironOre || block[x][y].id == Tile.goldOre || block[x][y].id == Tile.diamondOre && block[x][y].isBgBlock == false && blockBreakTime <= blockBreakTimer){
												block[x][y].isBgBlock = true;
												block[x][y].id = Tile.stone;
												
												blockBreakTimer = 0;
											}else{
												blockBreakTimer++;
											}
											
											
										
											
											if(blockBreakTime <= blockBreakTimer && block[x][y].isBgBlock == false){
												block[x][y].isBgBlock = true;
												blockBreakTimer = 0;
											}else{
												blockBreakTimer++;
											}
											if(blockBreakTime <= blockBreakTimer && block[x][y].isBgBlock && block[x][y].id != Tile.air  && block[x][y].id != Tile.stone && block[x][y].id != Tile.grass && block[x][y].id != Tile.dirt && !isBuildingBgBlock){
												block[x][y].id = Tile.air;
												blockBreakTimer = 0;
											}else if(isBuildingBgBlock){
												block[x][y].id = Tile.air;
												blockBreakTimer++;
											}
										}
									}
									
									}else{ if(Component.isMouseRight){
										if(block[x][y].id == Tile.air || block[x][y].isBgBlock){
											
											if(block[x][y].isBgBlock){
												block[x][y].isBgBlock = false;
											}
											
											if(isBuildingBgBlock && Component.isMouseRight){
												block[x][y].isBgBlock = true;
											}
											
											if(sid ==  Inventory.invBar[Inventory.selected].id){
												block[x][y].id = sid;
											
											if(block[x][y+1].id == Tile.grass){
												block[x][y+1].id = Tile.dirt;
											}
										}
									}
								}
								break;
							}
						}
					}
				}
			}
		}		
	}

	
	public void tick(int camX, int camY, int renW, int renH) {		
		if(!Component.aboutMenu && !Component.escMenu && !Component.StartMenu){
			building(camX, camY, renW, renH);
		}
		
		
		if(isBuildingBgBlock){
			BuldingBgText = "Background block enabled. Press [B] to disable it.";
		}else{
			BuldingBgText = "Background block disabled. Press [B] to enable it.";
		}
	}

	public void render(Graphics g, int camX, int camY, int renW, int renH) {
		for (int x = (camX / Tile.tileSize); x <(camX / Tile.tileSize) + renW; x++) {
			for (int y = (camY / Tile.tileSize); y < (camY / Tile.tileSize) + renH; y++) {
					if(x >= 0 && y >= 0 && x < worldW && y < worldH){
						block[x][y].render(g);
						

						if(block[x][y].id != Tile.air  && block[x][y].id != Tile.solidAir && !Inventory.isOpen && Component.isPaused == false){
							if(block[x][y].contains(new Point((Component.mse.x  / Component.pixelSize) + (int)Component.sX, (Component.mse.y  / Component.pixelSize) +(int) Component.sY))){
								g.setColor(new Color(188, 188, 188, 100));
								g.fillRect(block[x][y].x - camX, block[x][y].y - camY, block[x][y].width, block[x][y].height);
							}
						}
						//Sets the bgBlock color
						if(block[x][y].isBgBlock && block[x][y].id != Tile.air && block[x][y].id != Tile.solidAir){
							g.setColor(new Color(73, 73, 73, 100));
							g.fillRect(block[x][y].x - camX, block[x][y].y - camY, block[x][y].width, block[x][y].height);
						}
					}
				}
			}
			
			if(isBuildingBgBlock){
				g.setColor(Color.WHITE);
				g.drawString(BuldingBgText, 10, 250);
			}else{
				g.setColor(Color.WHITE);
				g.drawString(BuldingBgText, 10, 250);
			}
		}	
	}
