package net.hollowbit.platformertutorial.world;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import net.hollowbit.platformertutorial.entities.Entity;
import net.hollowbit.platformertutorial.entities.EntityLoader;

public abstract class GameMap {
	
	protected ArrayList<Entity> entities;
	
	public GameMap() {
		entities = new ArrayList<Entity>();
		entities.addAll(EntityLoader.loadEntities("basic", this, entities));
	}
	
	public void render (OrthographicCamera camera, SpriteBatch batch) {
		for (Entity entity : entities) {
			entity.render(batch);
		}
	}
	
	public void update (float delta) {
		for (Entity entity : entities) {
			entity.update(delta, -9.8f);
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.S)) {
			EntityLoader.saveEntities("basic", entities);
		}
	}
	
	public void dispose () {
		
	}
	
	/**
	 * Gets a tile by pixel position within the game world at a specified layer.
	 * @param layer
	 * @param x
	 * @param y
	 * @return
	 */
	public TileType getTileTypeByLocation(int layer, float x, float y) {
		return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y / TileType.TILE_SIZE));
	}
	
	/**
	 * Gets a tile at its coordinate within the map at a specified layer.
	 * @param layer
	 * @param col
	 * @param row
	 * @return
	 */
	public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);
	
	public boolean doesRectCollideWithMap(float x, float y, int width, int height) {
		if (x < 0 || y < 0 || x + width > getPixelWidth() || y + height > getPixelHeight())
			return true;
		
		for (int row = (int) (y / TileType.TILE_SIZE); row < Math.ceil((y + height) / TileType.TILE_SIZE); row++) {
			for (int col = (int) (x / TileType.TILE_SIZE); col < Math.ceil((x + width) / TileType.TILE_SIZE); col++) {
				for (int layer = 0; layer < getLayers(); layer++) {
					TileType type = getTileTypeByCoordinate(layer, col, row);
					if (type != null && type.isCollidable())
						return true;
				}
			}
		}
		
		return false;
	}
	
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getLayers();
	
	public int getPixelWidth() {
		return this.getWidth() * TileType.TILE_SIZE;
	}
	
	public int getPixelHeight() {
		return this.getHeight() * TileType.TILE_SIZE;
	}
	
}
