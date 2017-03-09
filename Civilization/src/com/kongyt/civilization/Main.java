package com.kongyt.civilization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.kongyt.civilization.managers.GM;
import com.kongyt.civilization.utils.SV;
import com.kongyt.civilization.views.ExitDialog;

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
		config.foregroundFPS = 0;
		config.useGL20 = true;
		config.addIcon("assets/images/ui/icon.png", null);
		config.title = SV.GameName;
//		config.fullscreen = true;
		CivilizationApplication app = new CivilizationApplication(new CivilizationGame(), config);
		


	}

}
