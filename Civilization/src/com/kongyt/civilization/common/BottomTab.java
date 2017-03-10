package com.kongyt.civilization.common;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class BottomTab extends Group {
	
	private int num;
	private Group baseGroup;
	private Image[] tabs;
	public BottomTab(int num){
		this.num = num;
		this.tabs = new Image[this.num];
		this.baseGroup = new Group();
		this.addActor(baseGroup);
		
		
		Texture tex = new Texture(Gdx.files.internal("images/ui/bottom_tab.png"));
		for(int i = 0; i < this.num; i++){
			this.tabs[i] = new Image(tex);
			this.tabs[i].setPosition(tex.getWidth()*i, 0);
			this.baseGroup.addActor(this.tabs[i]);
		}
		this.baseGroup.setPosition(-tex.getWidth() * this.num / 2, 0);
	}

}
