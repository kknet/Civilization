package com.kongyt.civilization.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.kongyt.civilization.managers.GM;
import com.kongyt.civilization.net.BaseModule;
import com.kongyt.civilization.net.Net;
import com.kongyt.civilization.utils.SV;


public class LoginScene extends BaseScene {
	private SpriteBatch batch;
	private Stage stage;

	private Image bg;
	
	private BitmapFont labelFont;
	private BitmapFont textFieldFont;
	
	private Label usernameL;
	private Label passwordL;
	
	private TextField usernameTF;
	private TextField passwordTF;
	
	private TextButton loginTB;
	private TextButton registerTB;
	private TextButton debugTB;
	
	BaseModule baseModule = new BaseModule();
	
	public LoginScene(){
		
		this.batch = GM.instance().getSpriteBatch();
		this.stage = new Stage(SV.SCREEN_WIDTH, SV.SCREEN_HEIGHT, SV.SCREEN_MODE, this.batch);
		
		GM.instance().getNet().registerModule(baseModule);		
		
		
		Texture tex = new Texture(Gdx.files.internal("images/ui/menubg.png"));
		tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		bg = new Image(tex);
		bg.setSize(SV.SCREEN_WIDTH, SV.SCREEN_HEIGHT);
		this.stage.addActor(bg);
		
		labelFont = new BitmapFont(Gdx.files.internal("fonts/labelFont.fnt"));
		
		LabelStyle lstyle = new LabelStyle(labelFont, new Color(1,1,1,1));
		usernameL = new Label("用户名:", lstyle);
		usernameL.setPosition(340, 240);
		stage.addActor(usernameL);
		
		passwordL = new Label("密    码:", lstyle);
		passwordL.setPosition(340, 180);
		stage.addActor(passwordL);
		
		
		
		textFieldFont = new BitmapFont(Gdx.files.internal("fonts/text_field_font.fnt"));
		textFieldFont.setScale(0.5f);
		
		TextureAtlas atlas = new TextureAtlas("images/ui/hero_common.atlas");
		
		TextField.TextFieldStyle tfstyle = new TextField.TextFieldStyle();
		tfstyle.cursor = new TextureRegionDrawable(atlas.createSprite("text_field_cursor"));
		


		tfstyle.background = new TextureRegionDrawable(atlas.createSprite("text_field_bg"));
		tfstyle.font = textFieldFont;
		tfstyle.fontColor = new Color(0, 0, 0, 1);
		 
		usernameTF = new TextField("", tfstyle);
		usernameTF.setPosition(460, 240);
		usernameTF.setSize(160, 36);
		stage.addActor(usernameTF);
		
		passwordTF = new TextField("", tfstyle);
		passwordTF.setPasswordCharacter('*');
		passwordTF.setPasswordMode(true);
		passwordTF.setPosition(460, 180);
		passwordTF.setSize(160, 36);
		stage.addActor(passwordTF);

		TextButtonStyle tbstyle = new TextButtonStyle();
		tbstyle.up = new TextureRegionDrawable(atlas.createSprite("btn_bg"));
		tbstyle.font = labelFont;
		registerTB = new TextButton("注册", tbstyle);
		registerTB.setSize(80, 36);
		registerTB.setPosition(360, 100);
		registerTB.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub				
				register();				
				super.clicked(event, x, y);				
			}			
		});
		stage.addActor(registerTB);
		
		loginTB = new TextButton("登陆", tbstyle);
		loginTB.setSize(80, 36);
		loginTB.setPosition(500, 100);
		loginTB.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub				
				login();				
				super.clicked(event, x, y);				
			}			
		});
		stage.addActor(loginTB);
		
//		debugTB = new TextButton("Debug", tbstyle);
//		debugTB.setSize(80, 36);
//		debugTB.setPosition(500, 50);
//		debugTB.addListener(new ClickListener(){
//
//			@Override
//			public void clicked(InputEvent event, float x, float y) {
//				// TODO Auto-generated method stub				
//				debug();				
//				super.clicked(event, x, y);				
//			}			
//		});
//		stage.addActor(debugTB);
		

	}
	
	
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
		
		super.render(delta);
		if(baseModule.isLogin){
			GM.instance().changeScene(SV.SCENE_MENU);
		}
	}



	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
	}


	private void register(){
		String username = usernameTF.getText();
		String password = passwordTF.getText();
		
		//baseModule.sendRegisterRequest(username, password);
	}

	private void login(){
		String username = usernameTF.getText();
		String password = passwordTF.getText();
		
		//baseModule.sendLoginRequest(username, password);
		

	}
	
	private void debug(){
		//baseModule.sendDebugCommand("testDebug");
	}
}
