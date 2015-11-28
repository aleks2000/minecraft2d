package com.kittensvoice.minecraft2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;


public class Cell extends Rectangle{
	private static final long serialVersionUID = 1L;
	
	public int[] id = {0, 0};
	
	

    boolean occupied, isArray, full;
	
	public Cell(Rectangle size, int[] id){
		setBounds(size);
		this.id = id;
	}
	
	public void render(Graphics g, boolean isSelected){
		
		if(Inventory.isOpen && contains(new Point(Component.mse.x / Component.pixelSize, Component.mse.y / Component.pixelSize))){
			g.setColor(new Color(255, 255, 255, 130));
			g.fillRect(x, y, width, height);
		}
		
		g.drawImage(Tile.tile_cell, x, y, width, height, null);
	
		
		if(id != Tile.air){
			g.drawImage(Tile.tileset_terrain, x + Tile.invItemBorder, y + Tile.invItemBorder, x + width - Tile.invItemBorder, y + height - Tile.invItemBorder, id[0] * Tile.tileSize, id[1] * Tile.tileSize, id[0] * Tile.tileSize + Tile.tileSize, id[1] * Tile.tileSize + Tile.tileSize, null);
		}
		
		
		if(isSelected && !Inventory.isOpen){
			g.drawImage(Tile.tile_select, x - 1, y - 1, width + 2, height + 2, null);
		}
	}
}
