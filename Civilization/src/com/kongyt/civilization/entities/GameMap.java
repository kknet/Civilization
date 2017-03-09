package com.kongyt.civilization.entities;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.kongyt.civilization.utils.SV;

public class GameMap extends Group {	
	
	private int[][] map;
	
	private int width;
	private int height;
	private float cellWidth;
	private float cellHeight;
	
	private ShapeRenderer debugRenderer;
	private boolean isDebug = SV.GAME_MAP_DEBUG;
	
	private List<Sprite> tiles = new ArrayList<>();
	
	public GameMap(int width, int height, float cellWidth, float cellHeight){
		this.width = width;
		this.height = height;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.map = new int[width][height];
		
		this.addObs();
		
		
		if(this.isDebug){
			this.debugRenderer = new ShapeRenderer();
		}
		
		Texture tex = new Texture(Gdx.files.internal("images/map/black.png"));
		
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				if(this.map[i][j] == 0){
					Sprite tmp = new Sprite(tex);
					tmp.setPosition(i * this.cellWidth, j * this.cellHeight);
					tmp.setSize(this.cellWidth, this.cellHeight);
					tiles.add(tmp);
				}				
			}
		}
		
		
		Texture tex2 = new Texture(Gdx.files.internal("images/map/wall.png"));
		for(int i = -16; i < 0; i++){
			for(int j = -9; j < this.height + 18; j++){
				Sprite tmp = new Sprite(tex2);
				tmp.setPosition(i * this.cellWidth, j * this.cellHeight);
				tmp.setSize(this.cellWidth, this.cellHeight);
				tiles.add(tmp);				
			}
		}
		
		for(int i = this.width; i < this.width + 16; i++){
			for(int j = -9; j < this.height + 18; j++){
				Sprite tmp = new Sprite(tex2);
				tmp.setPosition(i * this.cellWidth, j * this.cellHeight);
				tmp.setSize(this.cellWidth, this.cellHeight);
				tiles.add(tmp);				
			}
		}
		
		for(int i = 0; i < this.width; i++){
			for(int j = -9; j < 0; j++){
				Sprite tmp = new Sprite(tex2);
				tmp.setPosition(i * this.cellWidth, j * this.cellHeight);
				tmp.setSize(this.cellWidth, this.cellHeight);
				tiles.add(tmp);				
			}
		}
		
		for(int i = 0; i < this.width; i++){
			for(int j = this.height; j < this.height + 9; j++){
				Sprite tmp = new Sprite(tex2);
				tmp.setPosition(i * this.cellWidth, j * this.cellHeight);
				tmp.setSize(this.cellWidth, this.cellHeight);
				tiles.add(tmp);				
			}
		}
	}
	
	public void addObs(){
		for(int i = 0; i < 3000; i++){
			int x = (int)(Math.random() * (this.width -4)) + 2;
			int y = (int)(Math.random() * (this.height -4)) + 2;
			this.map[x][y] = 1;
			this.map[x][y+1] = 1;
			this.map[x+1][y] = 1;
			this.map[x+1][y+1] = 1;
		}
	}
	
	public boolean canMove(float x, float y){
		System.out.println("x="+x+"   y="+y);
		if(x < 0 || y < 0 || x >= this.width * this.cellWidth || y >= this.height * this.cellHeight){
			return false;
		}
		
		if(map[(int)(x/this.cellWidth)][(int)(y/this.cellHeight)] != 0){
			return false;
		}
		return true;
	}
	

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		// TODO Auto-generated method stub		
		if(this.isDebug){
			batch.end();
			this.debugRenderer.begin(ShapeType.Line);
			this.debugRenderer.setProjectionMatrix(batch.getProjectionMatrix());
			this.debugRenderer.setTransformMatrix(this.computeTransform());
			this.debugRenderer.setColor(1, 0, 0, 1);
			for(int i = 0; i < this.width + 1; i++){
				this.debugRenderer.line(i * this.cellWidth, 0, i*this.cellWidth, this.height * this.cellHeight);
			}
			for(int j = 0; j < this.height + 1; j++){
				this.debugRenderer.line(0, j*this.cellHeight, this.width * this.cellWidth, j * this.cellHeight);
			}
			this.debugRenderer.end();
			batch.begin();
		}
		
		super.draw(batch, parentAlpha);		
		for(int i = 0; i < tiles.size(); i++){
			tiles.get(i).draw(batch);
		}
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);		
	}
	
	

}
