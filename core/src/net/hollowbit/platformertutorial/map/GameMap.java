package net.hollowbit.platformertutorial.map;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class GameMap {
	
	public abstract void render (OrthographicCamera camera);
	public abstract void update (float delta);
	public abstract void dispose ();
	
	/**
	 * Get tile by pixel position within world at a specified layer.
	 * @param x
	 * @param y
	 * @return
	 */
	public abstract TileType getTileTypeByLocation (int layer, float x, float y);
	
	/**
	 * Get tile by tile coordinate at a specified layer.
	 * @param x
	 * @param y
	 * @return
	 */
	public abstract TileType getTileTypeByCoordinate (int layer, int x, int y);
	
	
	public boolean doesRectCollideWithWorld (float x, float y, float width, float height) {
		if (x < 0 || y < 0 || x + width > getWidth() * TileType.TILE_SIZE || y + height > getHeight() * TileType.TILE_SIZE)
			return true;
		
		System.out.println("");
		for (int row = (int) (y / TileType.TILE_SIZE) - 1; row < (int) (height / TileType.TILE_SIZE) + (y / TileType.TILE_SIZE) + 2; row++) {
			for (int col = (int) (x / TileType.TILE_SIZE) - 1; col < (int) (width / TileType.TILE_SIZE) + (x / TileType.TILE_SIZE) + 2; col++) {
				if (row < 0 || row >= getHeight() || col < 0 || col >= getWidth())//If out of bounds, continue to next
					continue;
				
				for (int layer = 0; layer < getLayers(); layer++) {
					TileType type = getTileTypeByCoordinate(layer, col, row);
					if (type != null && type.isCollidable()) {
						System.out.println("Tile: " + type.getName());
						float tileX = col * TileType.TILE_SIZE;
						float tileY = row * TileType.TILE_SIZE;
						
						if (x + width > tileX && x < tileX + TileType.TILE_SIZE && y + height > tileY && y < tileY + TileType.TILE_SIZE)
							return true;
					}
				}
			}
		}
		
		return false;
	}
	
	public abstract int getWidth ();
	public abstract int getHeight ();
	public abstract int getLayers ();
	
}
