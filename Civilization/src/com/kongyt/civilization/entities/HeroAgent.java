package com.kongyt.civilization.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.kongyt.civilization.utils.SV;

public class HeroAgent extends Group {
	
	private TextureRegion hero_n[] = new TextureRegion[8];
	private TextureRegion hero_s[] = new TextureRegion[8];
	private TextureRegion hero_w[] = new TextureRegion[8];
	private TextureRegion hero_e[] = new TextureRegion[8];
	private TextureRegion hero_nw[] = new TextureRegion[8];
	private TextureRegion hero_ne[] = new TextureRegion[8];
	private TextureRegion hero_sw[] = new TextureRegion[8];
	private TextureRegion hero_se[] = new TextureRegion[8];
	
	private Animation hero_n_ani;
	private Animation hero_s_ani;
	private Animation hero_w_ani;
	private Animation hero_e_ani;
	private Animation hero_nw_ani;
	private Animation hero_ne_ani;
	private Animation hero_sw_ani;
	private Animation hero_se_ani;
	
	private Animation currentAni;
	
	private Sprite curSprite;
	
	private float moveSpeed = 200;
	private boolean isMoving = false;
	
	private GameMap map;
	
	private Actor cameraScaleActor;
	
	public enum HDir{
		E,
		W,
		None,
	};
	
	public enum VDir{
		N,
		S,
		None,
	};
	
	private HDir h_dir = HDir.None;
	private VDir v_dir = VDir.None;
	
	private Camera camera;
	
	public HeroAgent(Camera camera, GameMap map){
		this.camera = camera;
		this.map = map;
		
		Texture tex = new Texture(Gdx.files.internal("images/hero/hero.png"));
		
		for(int i = 0; i < 8; i++){
			hero_s[i] = new TextureRegion(tex, i * 2048 / 8, 2048 / 8 * 0, 2048 /8 , 2048 / 8);
			hero_w[i] = new TextureRegion(tex, i * 2048 / 8, 2048 / 8 * 1, 2048 /8 , 2048 / 8);
			hero_e[i] = new TextureRegion(tex, i * 2048 / 8, 2048 / 8 * 2, 2048 /8 , 2048 / 8);
			hero_n[i] = new TextureRegion(tex, i * 2048 / 8, 2048 / 8 * 3, 2048 /8 , 2048 / 8);
			hero_sw[i] = new TextureRegion(tex, i * 2048 / 8, 2048 / 8 * 4, 2048 /8 , 2048 / 8);
			hero_se[i] = new TextureRegion(tex, i * 2048 / 8, 2048 / 8 * 5, 2048 /8 , 2048 / 8);
			hero_nw[i] = new TextureRegion(tex, i * 2048 / 8, 2048 / 8 * 6, 2048 /8 , 2048 / 8);
			hero_ne[i] = new TextureRegion(tex, i * 2048 / 8, 2048 / 8 * 7, 2048 /8 , 2048 / 8);
		}
		
		hero_s_ani = new Animation(0.05f, hero_s);
		hero_w_ani = new Animation(0.05f, hero_w);
		hero_e_ani = new Animation(0.05f, hero_e);
		hero_n_ani = new Animation(0.05f, hero_n);
		hero_sw_ani = new Animation(0.05f, hero_sw);
		hero_se_ani = new Animation(0.05f, hero_se);
		hero_nw_ani = new Animation(0.05f, hero_nw);
		hero_ne_ani = new Animation(0.05f, hero_ne);		
		
		currentAni = hero_s_ani;
		
		this.curSprite = new Sprite(hero_s[0]);
		
		cameraScaleActor = new Actor();
		this.addActor(cameraScaleActor);
	}

	@Override
	public void setX(float x) {
		// TODO Auto-generated method stub
		super.setX(x);
		if(this.camera != null){
			this.camera.position.x = this.getX();
		}
	}

	@Override
	public void setY(float y) {
		// TODO Auto-generated method stub
		super.setY(y);
		if(this.camera != null){
			this.camera.position.y = this.getY();
		}
	}

	@Override
	public void setPosition(float x, float y) {
		// TODO Auto-generated method stub
		super.setPosition(x, y);
		if(this.camera != null){
			this.camera.position.x = this.getX() ;
			this.camera.position.y = this.getY();
		}
	}

	@Override
	public void translate(float x, float y) {
		// TODO Auto-generated method stub
		super.translate(x, y);
		if(this.camera != null){
			this.camera.position.x = this.getX() ;
			this.camera.position.y = this.getY() ;
		}
	}
	
	
	private float stateTime = 0;
	
	
	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		
		super.act(delta);
		
		this.camera.viewportWidth = SV.SCREEN_WIDTH * this.cameraScaleActor.getScaleX();
		this.camera.viewportHeight = SV.SCREEN_HEIGHT * this.cameraScaleActor.getScaleY();
		
		if(this.isMoving){
			this.stateTime += delta;
			if(this.v_dir == VDir.N){
				float y = this.getY() + this.moveSpeed * delta;
				if(this.map.canMove(this.getX(), y)){
					this.setY(y);
				}
				
			}else if(this.v_dir == VDir.S){
				float y = this.getY() - this.moveSpeed * delta;
				if(this.map.canMove(this.getX(), y)){
					this.setY(y);
				}
			}
			
			if(this.h_dir == HDir.E){
				float x = this.getX() + this.moveSpeed * delta;
				if(this.map.canMove(x, this.getY())){
					this.setX(x);
				}
			}else if(this.h_dir == HDir.W){
				float x = this.getX() - this.moveSpeed * delta;
				if(this.map.canMove(x, this.getY())){
					this.setX(x);
				}
			}
		}
		
	}

	
	
	
	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub
		super.draw(batch, parentAlpha);
		if(this.currentAni != null){
			TextureRegion tex = this.currentAni.getKeyFrame(stateTime, true);
			this.curSprite.setRegion(tex, 0, 0, tex.getRegionWidth(), tex.getRegionHeight());
			
			batch.setTransformMatrix(this.computeTransform());
			this.curSprite.setPosition(-this.curSprite.getWidth()/2, -20);
			this.curSprite.draw(batch);
		}
	}

	public void walkNorth(){
		this.v_dir = VDir.N;
		this.isMoving = true;
		if(this.h_dir == HDir.E){
			this.currentAni = hero_ne_ani;
		}else if(this.h_dir == HDir.W){
			this.currentAni = hero_nw_ani;
		}else{
			this.currentAni = hero_n_ani;
		}
	}
	
	public void walkEast(){
		this.h_dir = HDir.E;
		this.isMoving = true;
		if(this.v_dir == VDir.N){
			this.currentAni = hero_ne_ani;
		}else if(this.v_dir == VDir.S){
			this.currentAni = hero_se_ani;
		}else{
			this.currentAni = hero_e_ani;
		}
	}
	
	public void walkSouth(){
		this.v_dir = VDir.S;
		this.isMoving = true;
		if(this.h_dir == HDir.E){
			this.currentAni = hero_se_ani;
		}else if(this.h_dir == HDir.W){
			this.currentAni = hero_sw_ani;
		}else{
			this.currentAni = hero_s_ani;
		}
	}
	
	public void walkWest(){
		this.h_dir = HDir.W;
		this.isMoving = true;
		if(this.v_dir == VDir.N){
			this.currentAni = hero_nw_ani;
		}else if(this.v_dir == VDir.S){
			this.currentAni = hero_sw_ani;
		}else{
			this.currentAni = hero_w_ani;
		}
	}
	
	public void stopNorth(){
		if(this.v_dir == VDir.N){
			this.v_dir = VDir.None;
			if(this.h_dir == HDir.E){
				this.currentAni = hero_e_ani;
			}else if(this.h_dir == HDir.W){
				this.currentAni = hero_w_ani;
			}
		}
		if(this.h_dir == HDir.None){
			this.isMoving = false;
			this.stateTime = 0;
		}
	}
	
	public void stopEast(){
		if(this.h_dir == HDir.E){
			this.h_dir = HDir.None;
			if(this.v_dir == VDir.N){
				this.currentAni = hero_n_ani;
			}else if(this.v_dir == VDir.S){
				this.currentAni = hero_s_ani;
			}
		}
		if(this.v_dir == VDir.None){
			this.isMoving = false;
			this.stateTime = 0;
		}
	}
	
	public void stopSouth(){
		if(this.v_dir == VDir.S){
			this.v_dir = VDir.None;
			if(this.h_dir == HDir.E){
				this.currentAni = hero_e_ani;
			}else if(this.h_dir == HDir.W){
				this.currentAni = hero_w_ani;
			}
		}
		if(this.h_dir == HDir.None){
			this.isMoving = false;
			this.stateTime = 0;
		}
	}
	
	public void stopWest(){
		if(this.h_dir == HDir.W){
			this.h_dir = HDir.None;
			if(this.v_dir == VDir.N){
				this.currentAni = hero_n_ani;
			}else if(this.v_dir == VDir.S){
				this.currentAni = hero_s_ani;
			}
		}
		if(this.v_dir == VDir.None){
			this.isMoving = false;
			this.stateTime = 0;
		}
	}

	
	
	public void pull(){
		this.cameraScaleActor.addAction(Actions.scaleTo(1.5f, 1.5f, 1));
	}
	
	public void push(){
		this.cameraScaleActor.addAction(Actions.scaleTo(1f, 1f, 1));
	}
	
	
}
