package com.kittensvoice.minecraft2d;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile {
	public static int tileSize = 20;
	public static int invLenght = 8;
	public static int invHeight = 4;
	public static int invCellSize = 25;
	public static int invCellSpace = 4;
	public static int invBorderSpace = 4;
	public static int invItemBorder = 3;
	public static int invCraftingLegth = 2;
	public static String name;


	
	
	public static final int[] air = {-1, -1};
	public static final int[] dirt = {0, 0};
	public static final int[] grass = {1, 0};
	public static final int[] sand = {2, 0};
	public static final int[] solidAir = {3, 0};
	public static final int[] stone = {4, 0};
	public static final int[] cobblestone = {5, 0};
	public static final int[] tnt = {6, 0};
	public static final int[] wood = {11, 0};
	public static final int[] ladder = {12, 0};



	
	//Ores
	public static final int[] ironOre = {7, 0};
	public static final int[] coalOre = {8, 0};
	public static final int[] goldOre = {9, 0};
	public static final int[] diamondOre = {10, 0};



	
	public static int[] mobDummy = {0, 16};
	public static int[] character = {0, 18};

	
	public static BufferedImage tileset_terrain;
	public static BufferedImage tile_cell;
	public static BufferedImage tile_select;
	public static BufferedImage aboutMenu;
	public static BufferedImage button;



	
	public Tile(){
		try {
			Tile.tileset_terrain = ImageIO.read(new File("res/tileset_terrain.png"));
			Tile.tile_cell = ImageIO.read(new File("res/tile_cell.png"));
			Tile.tile_select = ImageIO.read(new File("res/tile_select.png"));
			Tile.aboutMenu = ImageIO.read(new File("res/aboutMenu.png"));
			Tile.button = ImageIO.read(new File("res/button.png"));



		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
