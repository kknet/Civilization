package com.kongyt.civilization.managers;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kongyt.civilization.CivilizationApplication;
import com.kongyt.civilization.entities.HeroAgent;
import com.kongyt.civilization.utils.SV;
import com.kongyt.civilization.views.BaseScene;
import com.kongyt.civilization.views.GameScene;
import com.kongyt.civilization.views.MenuScene;

public class GM {
	private static GM instance = null;
	
	private GM(){
		
	}
	
	public static GM instance(){
		if(instance == null){
			instance = new GM();
		}
		return instance;
	}
	
	
	public static void relInstance(){
		if(instance != null){
			instance.destory();
		}
		instance = null;
	}
	
	
	//===============================================游戏相关==================================================
	
	private CivilizationApplication app;
	public void registerApplication(CivilizationApplication app){
		this.app = app;
	}
	
	public CivilizationApplication getApplication(){
		return this.app;
	}
	
	private Game game;
	
	// 注册游戏实例
	public void registerGame(Game game){
		this.game = game;
	}
	
	// 获取游戏实例
	public Game getGame(){
		return this.game;
	}
	
	
	// 处理退出事件
	public void onExit(){
		((BaseScene)(this.game.getScreen())).onExit();
	}
	
	
	// 销毁资源
	private void destory(){
		
	}
	
	
	//==============================================场景相关=====================================================
	public void changeScene(int sceneId){
		if(this.game != null && this.game.getScreen() != null){
			this.game.getScreen().dispose();
		}
		switch(sceneId){
		case SV.SCENE_LOADING:
			this.logE("场景暂不存在");
			break;
		case SV.SCENE_MENU:
			this.logD("切换到主菜单场景");
			this.game.setScreen(new MenuScene());
			break;
		case SV.SCENE_GAME:
			this.logD("切换到游戏场景");
			this.game.setScreen(new GameScene());
			break;
		}
	}
	
	//==============================================日志相关=====================================================
	public void logE(String message) {
		Gdx.app.error("[Error]", message);
	}

	public void logI(String message) {
		Gdx.app.log("[Info]", message);
	}

	public void logD(String message) {
		Gdx.app.debug("[Debug]", message);
	}

	public void setLogLevel(int logLevel) {
		Gdx.app.setLogLevel(logLevel);
	}
	
	//=============================================全局资源相关===================================================
	
	private SpriteBatch batch;
	
	// 获取SpriteBatch，尽量保证整个游戏只有一个batch
	public SpriteBatch getSpriteBatch(){
		if(this.batch == null){
			this.batch = new SpriteBatch();
		}		
		return this.batch;
	}
	
	// 释放SpriteBatch
	public void relSpriteBatch(){
		if(this.batch != null){
			this.batch.dispose();
		}
		this.batch = null;
	}
	
	//============================================时间线相关=======================================================
	private float timeLine = 0;
	private boolean timeRuning = true;
	
	public void startTimeLine(){
		this.timeLine = 0;
		this.timeRuning = true;
	}
	
	public void updateTimeLine(float delta){
		if(this.timeRuning == true){
			this.timeLine += delta;
		}
	}
	
	public float getTimeLine(){
		return this.timeLine;
	}
	
	public void pauseTimeLine(){
		this.timeRuning = false;
	}
	
	public void resumeTimeLine(){
		this.timeRuning = true;
	}
	
	public void endTimeLine(){
		this.timeRuning = false;
	}
	
	//==========================================角色==============================================
	private HeroAgent hero;
	public void setHero(HeroAgent hero){
		this.hero = hero;
	}
	
	public HeroAgent getHero(){
		return this.hero;
	}
	
	
	//==========================================鼠标光标==========================================
	private Pixmap mouse1;
	private Pixmap mouse2;
	private Pixmap mouse3;
	
	public Pixmap getMouse1Pixmap(){
		if(this.mouse1 == null){
			this.mouse1 = new Pixmap(Gdx.files.internal("images/ui/mouse.png"));
		}
		return this.mouse1;
	}
	public Pixmap getMouse2Pixmap(){
		if(this.mouse2 == null){
			this.mouse2 = new Pixmap(Gdx.files.internal("images/ui/mouse_red.png"));
		}
		return this.mouse2;
	}
	
	public Pixmap getMouse3Pixmap(){
		if(this.mouse3 == null){
			this.mouse3 = new Pixmap(Gdx.files.internal("images/ui/mouse_green.png"));
		}
		return this.mouse3;
	}
	
	
	private int mouseType = 0;
	public void setMouse(int type){
		if(this.mouseType != type){
			this.mouseType = type;
			if(type == 1){
				Gdx.input.setCursorImage(this.getMouse1Pixmap(), 0, 0);
			}else if(type == 2){
				Gdx.input.setCursorImage(this.getMouse2Pixmap(), 0, 0);
			}else {
				Gdx.input.setCursorImage(this.getMouse3Pixmap(), 0, 0);
			}
		}
		
		
	}
	
	
}
