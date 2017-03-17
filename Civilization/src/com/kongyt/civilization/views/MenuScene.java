package com.kongyt.civilization.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.TextureData.TextureDataType;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.kongyt.civilization.managers.GM;
import com.kongyt.civilization.utils.SV;

public class MenuScene extends BaseScene {
	
	private SpriteBatch batch;
	private Stage stage;

	private Image bg;	
	
	// 主菜单场景输入控制器
	private InputAdapter input = new InputAdapter(){

		@Override
		public boolean keyDown(int keycode) {
			// TODO Auto-generated method stub
			switch(keycode){
			case Keys.ENTER:
				GM.instance().changeScene(SV.SCENE_GAME);
				break;
			case Keys.ESCAPE:
				GM.instance().exitGame();
				break;
			}
			GM.instance().logD("按键"+keycode+"按下");
			return super.keyDown(keycode);
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer,
				int button) {
			// TODO Auto-generated method stub
			GM.instance().changeScene(SV.SCENE_GAME);
			return super.touchDown(screenX, screenY, pointer, button);
		}
		
	};
	
	
	
	public MenuScene(){
		this.batch = GM.instance().getSpriteBatch();
		this.stage = new Stage(SV.SCREEN_WIDTH, SV.SCREEN_HEIGHT, SV.SCREEN_MODE, this.batch);
		this.init();
	}
	
	public void init(){
		Texture tex = new Texture(Gdx.files.internal("images/ui/menubg.png"));
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bg = new Image(tex);
		bg.setSize(SV.SCREEN_WIDTH, SV.SCREEN_HEIGHT);
		this.stage.addActor(bg);
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
