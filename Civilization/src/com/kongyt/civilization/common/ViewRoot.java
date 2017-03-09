package com.kongyt.civilization.common;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kongyt.civilization.utils.SV;

// 控制整个场景的拉近拉远等等
public class ViewRoot{
	private Group root;
	
	public ViewRoot(Group root){
		this.root = root;
		if(this.root != null){
			//this.root.setOrigin(SV.SCREEN_WIDTH/2, SV.SCREEN_HEIGHT/2);
		}
	}
	
	public void scaleTo(float x, float y, float duration){
		if(this.root != null){
			this.root.clearActions();
			this.root.addAction(Actions.scaleTo(x, y, duration));
		}
	}
	
	public void shake(){
		
	}
	
	public void pull(){
		this.scaleTo(0.5f, 0.5f, 1f);
	}
	
	public void push(){
		this.scaleTo(1f, 1f, 1f);
	}
	
}
