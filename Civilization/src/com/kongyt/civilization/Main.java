package com.kongyt.civilization;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kongyt.civilization.utils.SV;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		int scale = 6;
		config.width = 160 * scale;
		config.height = 90 * scale;
		config.useGL20 = true;
		config.addIcon("assets/images/ui/icon.png", null);
		config.title = SV.GameName;
//		config.fullscreen = true;
		new LwjglApplication(new CivilizationGame(), config);
	}
}
