package net.hollowbit.platformertutorial;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import net.hollowbit.platformertutorial.map.CustomGameMap;
import net.hollowbit.platformertutorial.map.GameMap;
import net.hollowbit.platformertutorial.map.TileType;

public class PlatformerGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

    OrthographicCamera camera;
    
    GameMap gameMap;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false,w,h);
        camera.update();
        
        gameMap = new CustomGameMap();
	}

	@Override
	public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    	Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        
        if (Gdx.input.isTouched())
        	camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
        
        if (Gdx.input.justTouched()) {
        	TileType type = gameMap.getTileTypeByLocation(1, pos.x, pos.y);
        	
        	if (type != null) {
        		System.out.println("You touched a " + type.getName() + " tile!");
        	}
        }
        
        camera.update();
        gameMap.update(Gdx.graphics.getDeltaTime());
        gameMap.render(camera);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		gameMap.dispose();
	}
}
