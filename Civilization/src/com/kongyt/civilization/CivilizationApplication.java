package com.kongyt.civilization;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kongyt.civilization.managers.GM;

public class CivilizationApplication extends LwjglApplication {

	public CivilizationApplication(ApplicationListener listener,
			LwjglApplicationConfiguration config) {
		super(listener, config);
		// TODO Auto-generated constructor stub
		GM.instance().registerApplication(this);
	}
	
	
	
	@Override
	public void exit() {
		// TODO Auto-generated method stub
		GM.instance().onExit();		
	}



	public void realExit(){
		GM.instance().logD("游戏退出");
		GM.relInstance();
		super.exit();
	}
	

}
