package net.hollowbit.platformertutorial.map;

import java.util.HashMap;

public enum TileType {
	
	GRASS(1, true, "Grass"),
	DIRT(2, true, "Dirt"),
	SKY(3, false, "Sky"),
	LAVA(4, true, "Lava", 1),
	CLOUD(5, true, "Cloud"),
	STONE(6, true, "Stone");
	
	public static final int TILE_SIZE = 16;
	
	private int id;
	private boolean collidable;
	private String name;
	private float damage;
	
	private TileType (int id, boolean collidable, String name) {
		this(id, collidable, name, 0);
	}
	
	private TileType (int id, boolean collidable, String name, float damage) {
		this.id = id;
		this.collidable = collidable;
		this.name= name;
		this.damage = damage;
	}

	public int getId() {
		return id;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public float getDamage() {
		return damage;
	}
	
	public String getName() {
		return name;
	}

	private static HashMap<Integer, TileType> tilesMap;
	
	static {
		tilesMap = new HashMap<Integer, TileType>();
		for (TileType tileType : TileType.values()) {
			tilesMap.put(tileType.getId(), tileType);
		}
	}
	
	public static TileType getTypeById (int id) {
		return tilesMap.get(id);
	}
	
}
