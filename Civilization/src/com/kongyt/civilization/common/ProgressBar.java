package com.kongyt.civilization.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ProgressBar extends Group {
	
	private Image bg1;
	private Image bg2;
	private float maxWidth = 300;
	public ProgressBar(){
		NinePatch np1 = new NinePatch(new Texture(Gdx.files.internal("images/ui/progress_bar1.png")));
		bg1 = new Image(np1);
		this.addActor(bg1);
		this.bg1.setWidth(maxWidth);
		this.bg1.setPosition(-this.maxWidth/2, 0);
		
		NinePatch np2 = new NinePatch(new Texture(Gdx.files.internal("images/ui/progress_bar2.png")));
		bg2 = new Image(np2);
		this.addActor(bg2);
		this.bg2.setPosition(-this.maxWidth/2, 0);
	}	
	
	public void setProgress(float progress){
		if(progress < 0 || progress > 1){
			return;
		}
		this.bg2.setWidth(progress * maxWidth);
	}
	
}
