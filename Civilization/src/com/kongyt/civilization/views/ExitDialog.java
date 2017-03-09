package com.kongyt.civilization.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kongyt.civilization.managers.GM;
import com.kongyt.civilization.utils.SV;

public class ExitDialog extends Group { 
	private float dis2center = 80;
	private Group window = new Group();
	private Image exit_bg;
	private Image exit_yes;
	private Image exit_no;
	
	public ExitDialog(){
		this.addActor(window);
		this.setSize(SV.SCREEN_WIDTH, SV.SCREEN_HEIGHT);
		
		this.exit_bg = new Image(new Texture(Gdx.files.internal("images/ui/exit_bg.png")));
		this.exit_bg.setPosition(-this.exit_bg.getWidth()/2, -this.exit_bg.getHeight()/2);
		this.window.addActor(this.exit_bg);
		
		this.exit_yes = new Image(new Texture(Gdx.files.internal("images/ui/exit_yes.png")));
		this.exit_yes.setPosition(-dis2center - this.exit_yes.getWidth()/2, -this.exit_yes.getHeight()/2);
		this.window.addActor(this.exit_yes);
		this.exit_yes.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				GM.instance().getApplication().realExit();
			}			
		});
		
		this.exit_no = new Image(new Texture(Gdx.files.internal("images/ui/exit_no.png")));
		this.exit_no.setPosition(dis2center - this.exit_no.getWidth()/2, -this.exit_no.getHeight()/2);
		this.window.addActor(this.exit_no);
		this.exit_no.addListener(new ClickListener(){

			@Override
			public void clicked(InputEvent event, float x, float y) {
				// TODO Auto-generated method stub
				super.clicked(event, x, y);
				ExitDialog.this.setVisible(false);
			}			
		});
	}
}  
