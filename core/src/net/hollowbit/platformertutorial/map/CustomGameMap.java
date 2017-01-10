package net.hollowbit.platformertutorial.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import net.hollowbit.platformertutorial.map.custom.CustomGameMapData;
import net.hollowbit.platformertutorial.map.custom.CustomGameMapLoader;

public class CustomGameMap extends GameMap {
	
	private String name;
	private int[][][] map;
	
	private SpriteBatch batch;
	private TextureRegion[][] tiles;
	
	public CustomGameMap() {
		CustomGameMapData data = CustomGameMapLoader.loadMap("basic");
		this.name = data.name;
		this.map = data.map;
		
		batch = new SpriteBatch();
		tiles = TextureRegion.split(new Texture("tiles.png"), 16, 16);
	}
	
	@Override
	public void render(OrthographicCamera camera) {
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		for (int layer = 0; layer < map.length; layer++) {//Loop through layers
			for (int row = map[0].length - 1; row >= 0; row--) {//Loop through rows
				for (int col = 0; col < map[0][0].length; col++) {//Loop through columns
					TileType type = this.getTileTypeByCoordinate(layer, col, row);
					if (type != null)
						batch.draw(tiles[0][type.getId() - 1], col * TileType.TILE_SIZE, map[0].length * TileType.TILE_SIZE - row * TileType.TILE_SIZE);
				}
			}
		}
		batch.end();
	}

	public String getName() {
		return name;
	}

	@Override
	public void update(float delta) {

	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public TileType getTileTypeByLocation (int layer, float x, float y) {
		return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), map[0].length - (int) (y / TileType.TILE_SIZE));
	}

	@Override
	public TileType getTileTypeByCoordinate (int layer, int x, int y) {
		return TileType.getTypeById(map[layer][y][x]);
	}

	@Override
	public int getWidth() {
		return map[0][0].length;
	}

	@Override
	public int getHeight() {
		return map[0].length;
	}

	@Override
	public int getLayers() {
		return map.length;
	}

}
