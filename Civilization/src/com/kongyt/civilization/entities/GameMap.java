package com.kongyt.civilization.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.kongyt.civilization.mines.BaseMine;
import com.kongyt.civilization.mines.CoalMine;
import com.kongyt.civilization.mines.CopperMine;
import com.kongyt.civilization.mines.IronMine;
import com.kongyt.civilization.mines.OilMine;
import com.kongyt.civilization.mines.StoneMine;
import com.kongyt.civilization.mines.Tree;
import com.kongyt.civilization.utils.SV;

public class GameMap extends Group {	
	
	private MapCell[][] map;
	
	private int width;
	private int height;
	private float cellWidth;
	private float cellHeight;
	
	private ShapeRenderer debugRenderer;
	private boolean isDebug = SV.GAME_MAP_DEBUG;
	
	// 四周填补的纹理先画
	private List<Sprite> aroundSprite = new ArrayList<Sprite>();
	
	// 一格的矿先于人画
	private List<BaseMine> smallMines = new ArrayList<BaseMine>();
	
	// 大矿根据人物位置分批画
	private List<BaseMine> mineLines[];
	
	
	// 人物引用
	private HeroAgent hero;
	
	
	public GameMap(int width, int height, float cellWidth, float cellHeight){
		this.width = width;
		this.height = height;
		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.map = new MapCell[width][height];
		this.setWidth(this.width*this.cellWidth);
		this.setHeight(this.height*this.cellHeight);

		
		System.out.println(""+this.height);
		this.mineLines = new List[this.height];	
		
		
		// 初始化
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				this.map[i][j] = new MapCell();
				this.map[i][j].mask = SV.getMask(SV.ATTR_TYPE_WALKABLE, SV.FIRST_TYPE_NONE, SV.SECOND_TYPE_NONE, SV.TEX_ID_NONE);	
			}
		}
		
		// 添加障碍物
//		this.addObs(10);
		
		
		if(this.isDebug){
			this.debugRenderer = new ShapeRenderer();
		}
		
		Texture tex = new Texture(Gdx.files.internal("images/map/black.png"));
		
		
		// 添加可行走区域的背景贴图
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				if(SV.isWalkable(this.map[i][j].mask)){
					Sprite tmp = new Sprite(tex);
					tmp.setPosition(i * this.cellWidth, j * this.cellHeight);
					tmp.setSize(this.cellWidth, this.cellHeight);
					this.map[i][j].sprite = tmp;
				}				
			}
		}
		
		
		// 添加地图四轴贴图
		Texture tex2 = new Texture(Gdx.files.internal("images/map/wall.png"));
		for(int i = -23; i < 0; i++){
			for(int j = -13; j < this.height + 13; j++){
				Sprite tmp = new Sprite(tex2);
				tmp.setPosition(i * this.cellWidth, j * this.cellHeight);
				tmp.setSize(this.cellWidth, this.cellHeight);
				aroundSprite.add(tmp);				
			}
		}
	
		
		for(int i = this.width; i < this.width + 23; i++){
			for(int j = -13; j < this.height + 13; j++){
				Sprite tmp = new Sprite(tex2);
				tmp.setPosition(i * this.cellWidth, j * this.cellHeight);
				tmp.setSize(this.cellWidth, this.cellHeight);
				aroundSprite.add(tmp);				
			}
		}
		
		for(int i = 0; i < this.width; i++){
			for(int j = -13; j < 0; j++){
				Sprite tmp = new Sprite(tex2);
				tmp.setPosition(i * this.cellWidth, j * this.cellHeight);
				tmp.setSize(this.cellWidth, this.cellHeight);
				aroundSprite.add(tmp);				
			}
		}
		
		for(int i = 0; i < this.width; i++){
			for(int j = this.height; j < this.height + 13; j++){
				Sprite tmp = new Sprite(tex2);
				tmp.setPosition(i * this.cellWidth, j * this.cellHeight);
				tmp.setSize(this.cellWidth, this.cellHeight);
				aroundSprite.add(tmp);				
			}
		}
		
//		// 添加铜矿
		this.addCopperMine(3);
//		
//		// 添加铁矿
		this.addCopperMine(4);
//				
//		// 添加石矿
		this.addStoneMine(6);
//		
//		// 添加煤矿
		this.addCoalMine(7);
		
		
		// 添加树木
		this.addTree(5);

	}
	
	public void addToMineLine(int y, BaseMine mine){
		if(this.mineLines[y] == null){
			this.mineLines[y] = new ArrayList<BaseMine>();
			System.out.println("y="+y);
		}
		this.mineLines[y].add(mine);
	}
	
	public void addObs(int num){
		for(int i = 0; i < num; i++){
			int x = (int)(Math.random() * (this.width -4)) + 2;
			int y = (int)(Math.random() * (this.height -4)) + 2;
			if(this.map[x][y].mask == SV.getMask(SV.ATTR_TYPE_NO_WALKABLE, SV.FIRST_TYPE_NONE, SV.SECOND_TYPE_NONE, SV.TEX_ID_NONE)){
				i--;
			}else{
				this.map[x][y].mask = SV.getMask(SV.ATTR_TYPE_NO_WALKABLE, SV.FIRST_TYPE_NONE, SV.SECOND_TYPE_NONE, SV.TEX_ID_NONE);
			}
		}
	}
	
	public void addCopperMine(int num){
		Texture tex = new Texture(Gdx.files.internal("images/map/copper.png"));
		for(int i = 0; i < num; i++){
			int x = (int)(Math.random() * (this.width -4)) + 2;
			int y = (int)(Math.random() * (this.height -4)) + 2;
			if(SV.getFirstType(this.map[x][y].mask) != SV.FIRST_TYPE_NONE){
				i--;
			}else{
				BaseMine mine = new CopperMine(tex);
				this.map[x][y].mask = mine.getMask();
				mine.setPosition(x*this.cellWidth, y*this.cellHeight);
				this.addActor(mine);
				this.smallMines.add(mine);
			}
			
		}
		
		
	}
	
	// 添加石矿
	public void addStoneMine(int num){
		Texture tex = new Texture(Gdx.files.internal("images/map/stone.png"));
		for(int i = 0; i < num; i++){
			int x = (int)(Math.random() * (this.width -4)) + 2;
			int y = (int)(Math.random() * (this.height -4)) + 2;
			if(SV.getFirstType(this.map[x][y].mask) != SV.FIRST_TYPE_NONE){
				i--;
			}else{
				BaseMine mine = new StoneMine(tex);
				this.map[x][y].mask = mine.getMask();
				mine.setPosition(x*this.cellWidth, y*this.cellHeight);
				this.addActor(mine);
				this.smallMines.add(mine);
			}
			
		}
	}
	
	// 添加煤矿
	public void addCoalMine(int num){
		Texture tex = new Texture(Gdx.files.internal("images/map/coal.png"));
		for(int i = 0; i < num; i++){
			int x = (int)(Math.random() * (this.width -4)) + 2;
			int y = (int)(Math.random() * (this.height -4)) + 2;
			if(SV.getFirstType(this.map[x][y].mask) != SV.FIRST_TYPE_NONE){
				i--;
			}else{
				BaseMine mine = new CoalMine(tex);
				this.map[x][y].mask = mine.getMask();
				mine.setPosition(x*this.cellWidth, y*this.cellHeight);
				this.addActor(mine);
				this.smallMines.add(mine);
			}
			
		}
	}
	
	public void addIronMine(int num){
		Texture tex = new Texture(Gdx.files.internal("images/map/iron.png"));
		for(int i = 0; i < num; i++){
			int x = (int)(Math.random() * (this.width -4)) + 2;
			int y = (int)(Math.random() * (this.height -4)) + 2;
			if(SV.getFirstType(this.map[x][y].mask) != SV.FIRST_TYPE_NONE){
				i--;
			}else{
				BaseMine mine = new IronMine(tex);
				this.map[x][y].mask = mine.getMask();
				mine.setPosition(x*this.cellWidth, y*this.cellHeight);
				this.addActor(mine);
				this.smallMines.add(mine);
			}
			
		}
	}
	
	public void addOilMine(int num){
		Texture tex = new Texture(Gdx.files.internal("images/map/oil.png"));
		for(int i = 0; i < num; i++){
			int x = (int)(Math.random() * (this.width -4)) + 2;
			int y = (int)(Math.random() * (this.height -4)) + 2;
			if(SV.getFirstType(this.map[x][y].mask) != SV.FIRST_TYPE_NONE){
				i--;
			}else{
				BaseMine mine = new OilMine(tex);
				this.map[x][y].mask = mine.getMask();
				mine.setPosition(x*this.cellWidth, y*this.cellHeight);
				this.addActor(mine);
				this.smallMines.add(mine);
			}
			
		}
	}
	
	public void addTree(int num){
		Texture tex = new Texture(Gdx.files.internal("images/map/tree1.png"));
		for(int i = 0; i < num; i++){
			int x = (int)(Math.random() * (this.width -4)) + 2;
			int y = (int)(Math.random() * (this.height -4)) + 2;
			if(SV.getFirstType(this.map[x][y].mask) != SV.FIRST_TYPE_NONE){
				i--;
			}else{
				BaseMine mine = new Tree(tex);
				this.map[x][y].mask = mine.getMask();
				mine.setPosition(x*this.cellWidth, y*this.cellHeight);
				this.addActor(mine);
				this.addToMineLine(y, mine);
			}
			
		}
	}
	
	
	public boolean canMove(float x, float y){
		System.out.println("x="+x+"   y="+y);
		if(x < 0 || y < 0 || x >= this.width * this.cellWidth || y >= this.height * this.cellHeight){
			return false;
		}
		
		if(SV.isWalkable(map[(int)(x/this.cellWidth)][(int)(y/this.cellHeight)].mask)){
			return true;
		}
		return false;
	}
	

	
	// 不调用父类的draw方法
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
		
		
		if (this.isTransform()) applyTransform(batch, computeTransform());

		
		// 绘制周围图块
		for(int i = 0; i < aroundSprite.size(); i++){
			aroundSprite.get(i).draw(batch);
		}
		
		// 绘制背景图块
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++){
				if(this.map[i][j].sprite != null){
					this.map[i][j].sprite.draw(batch);
				}
			}
		}
		
		// 绘制单格矿物
		for(int i = 0; i < smallMines.size(); i++){
			if(smallMines.get(i).isVisible())
				smallMines.get(i).draw(batch, parentAlpha);
		}
		
		int hero_y = (int)((this.hero.getY() - 16) / this.cellHeight);

		// 绘制人物以北的图块
		for(int i = this.height - 1; i > hero_y&&i>=0; i--){
			if(this.mineLines[i] != null){
				for(BaseMine mine: this.mineLines[i]){
					if(mine.isVisible())
						mine.draw(batch, parentAlpha);
				}
			}			
		}
		
		// 绘制人物
		this.hero.draw(batch, parentAlpha);
		
		
		// 绘制人物以南的图块
		for (int i = hero_y; i >= 0; i--) {
			if(this.mineLines[i] != null){
				for(BaseMine mine: this.mineLines[i]){
					if(mine.isVisible())
						mine.draw(batch, parentAlpha);
				}
			}		
		}	
		
		
		this.hero.drawUI(batch, parentAlpha);
		
		if (this.isTransform()) resetTransform(batch);
	}

	@Override
	public void act(float delta) {
		// TODO Auto-generated method stub
		super.act(delta);		
	}

	public void setHero(HeroAgent heroAgent) {
		// TODO Auto-generated method stub
		this.hero = heroAgent;
		this.addActor(hero);
	}
	
	

}
