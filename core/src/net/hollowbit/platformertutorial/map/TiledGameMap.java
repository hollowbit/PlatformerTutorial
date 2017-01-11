package net.hollowbit.platformertutorial.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class TiledGameMap extends GameMap {

    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tiledMapRenderer;
    
    public TiledGameMap () {
        tiledMap = new TmxMapLoader().load("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1);
	}
    
	@Override
	public void render (OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void dispose() {
		tiledMap.dispose();
	}

	@Override
	public TileType getTileTypeByLocation(int layer, float x, float y) {
		return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y / TileType.TILE_SIZE));
	}

	@Override
	public TileType getTileTypeByCoordinate(int layer, int x, int y) {
		Cell cell = ((TiledMapTileLayer) tiledMap.getLayers().get(layer)).getCell(x, y);
    	
    	if (cell != null) {
    		TiledMapTile tile = cell.getTile();
    		
    		if (tile != null) {
    			int id = tile.getId();
    			TileType type = TileType.getTypeById(id);
    			return type;
    		}
    	}
		return null;
	}

	@Override
	public int getWidth() {
		return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getWidth();
	}

	@Override
	public int getHeight() {
		return ((TiledMapTileLayer) tiledMap.getLayers().get(0)).getHeight();
	}

	@Override
	public int getLayers() {
		return tiledMap.getLayers().getCount();
	}

}
