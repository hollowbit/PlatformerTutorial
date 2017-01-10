package net.hollowbit.platformertutorial.map.custom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class CustomGameMapLoader {
	
	private static Json json = new Json();
	
	public static CustomGameMapData loadMap (String id) {
		FileHandle file = Gdx.files.internal(id + ".map");
		if (file.exists()) {
			CustomGameMapData data = json.fromJson(CustomGameMapData.class, file.readString());
			return data;
		}
		return null;
	}
	
}
