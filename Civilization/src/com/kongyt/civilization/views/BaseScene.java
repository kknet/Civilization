package com.kongyt.civilization.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.kongyt.civilization.managers.GM;
import com.kongyt.civilization.utils.SV;

public class BaseScene implements Screen {

	private SpriteBatch batch;
	private BitmapFont font;
	private Matrix4 mat4;
	public BaseScene(){
		this.batch = GM.instance().getSpriteBatch();
		this.font = new BitmapFont();
		font.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		mat4 = new Matrix4().setToOrtho2D(0, 0, SV.SCREEN_WIDTH, SV.SCREEN_HEIGHT);
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		// 调试信息
		if (SV.DEBUG) {
			int renderCalls = batch.renderCalls;
			batch.begin();
			batch.setProjectionMatrix(mat4);
			font.draw(batch, "FPS :" + Gdx.graphics.getFramesPerSecond(), 20, 130);
			font.draw(batch, "DrawCalls : " + batch.renderCalls, 20, 100);
			font.draw(batch, "NativeHeap: " + Gdx.app.getNativeHeap() / 1024f
					/ 1024f + " Mb", 20, 70);
			font.draw(batch, "JavaHeap  : " + Gdx.app.getJavaHeap() / 1024f
					/ 1024f + " Mb", 20, 40);
			batch.end();
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	
	public void onExit(){
		
	}
}
