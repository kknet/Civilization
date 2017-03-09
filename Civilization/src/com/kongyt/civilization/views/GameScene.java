package com.kongyt.civilization.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kongyt.civilization.common.ViewRoot;
import com.kongyt.civilization.entities.GameMap;
import com.kongyt.civilization.entities.HeroAgent;
import com.kongyt.civilization.managers.GM;
import com.kongyt.civilization.utils.SV;

public class GameScene extends BaseScene {
	
	
	private SpriteBatch batch;
	private Stage stage;
	private ViewRoot viewRoot;
	private GameMap gameMap;
	private HeroAgent heroAgent;
	
	// 游戏场景输入控制器
	private InputAdapter input = new InputAdapter(){

		@Override
		public boolean keyDown(int keycode) {
			// TODO Auto-generated method stub
			switch(keycode){
			case Keys.W:
				if(heroAgent != null){
					heroAgent.walkNorth();
				}
				break;
			case Keys.A:
				if(heroAgent != null){
					heroAgent.walkWest();
				}
				break;
			case Keys.D:
				if(heroAgent != null){
					heroAgent.walkEast();
				}
				break;
			case Keys.S:
				if(heroAgent != null){
					heroAgent.walkSouth();
				}
				break;
				
			case Keys.MINUS:
				if(viewRoot != null){
					viewRoot.pull();
				}
				break;
			case Keys.EQUALS:
				if(viewRoot != null){
					viewRoot.pull();
				}
				break;
			}
			return super.keyDown(keycode);
		}
		
		

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			switch(keycode){
			case Keys.W:
				if(heroAgent != null){
					heroAgent.stopNorth();
				}
				break;
			case Keys.A:
				if(heroAgent != null){
					heroAgent.stopWest();
				}
				break;
			case Keys.D:
				if(heroAgent != null){
					heroAgent.stopEast();
				}
				break;
			case Keys.S:
				if(heroAgent != null){
					heroAgent.stopSouth();
				}
				break;
			}
			return super.keyUp(keycode);
		}



		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			// TODO Auto-generated method stub
			return super.touchDown(screenX, screenY, pointer, button);
		}	
		
	};
	
	
	
	public GameScene(){
		this.batch = GM.instance().getSpriteBatch();
		this.stage = new Stage(SV.SCREEN_WIDTH, SV.SCREEN_HEIGHT, SV.SCREEN_MODE, this.batch);
		
		this.init();
	}
	
	private void init(){
		this.viewRoot = new ViewRoot(this.stage.getRoot());
		this.gameMap = new GameMap(100, 100, 32, 32);
		this.stage.addActor(this.gameMap);
		
		this.heroAgent = new HeroAgent(this.stage.getCamera(), this.gameMap);
		this.heroAgent.setPosition(0, 0);
		this.stage.addActor(this.heroAgent);
	}
	

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(this.stage != null){
			this.stage.act(delta);
			this.stage.draw();
		}
		
		super.render(delta);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		Gdx.input.setInputProcessor(null);
		if(this.stage != null){
			this.stage.dispose();
		}
	}
	
	
}
