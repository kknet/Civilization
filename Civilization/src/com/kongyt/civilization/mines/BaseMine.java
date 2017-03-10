package com.kongyt.civilization.mines;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kongyt.civilization.managers.GM;


// 矿基类
public class BaseMine extends Group {
	
	private int amount;
	private float progress;
	private float workload = 50f;
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	public int getAmount(){
		return this.amount;
	}
	
	public boolean mining(float speed){
		this.progress += speed/workload;
		if(this.progress >= 1){
			this.progress -= 1;			
			return true;
		}else{
			return false;
		}
	}
	public float getProgress(){
		return this.progress;
	}
	
	private int mask;		
	public void setMask(int mask){
		this.mask = mask;
	}
	
	public int getMask(){
		return this.mask;
	}
	
	protected Image img;	
	
	public BaseMine(Texture tex){
		this.img = new Image(tex);
		this.addActor(img);
		this.setSize(img.getWidth(), img.getHeight());
		img.addListener(new ClickListener(){

			
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				switch (button) {
				case Buttons.RIGHT:
					System.out.println("右键按下");
					if(GM.instance().getHero().isReachable(BaseMine.this.getX(), BaseMine.this.getY())){
						GM.instance().getHero().mine(BaseMine.this);
						GM.instance().setMouse(3);
					}else{
						GM.instance().setMouse(2);
					}
					
					break;
				default:
					break;
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				switch (button) {
				case Buttons.RIGHT:
					System.out.println("右键抬起");
					GM.instance().getHero().stopMine(BaseMine.this);
					GM.instance().setMouse(1);
					break;
				default:
					break;
				}
				super.touchUp(event, x, y, pointer, button);
			}			
			
		});
	}

	public void destory() {
		// TODO Auto-generated method stub
		this.setVisible(false);
		GM.instance().getHero().stopMine(BaseMine.this);
		GM.instance().setMouse(1);
	}
	
	
}
